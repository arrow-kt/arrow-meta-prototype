package arrow.meta.higherkind

import arrow.meta.extensions.ExtensionPhase
import arrow.meta.extensions.MetaComponentRegistrar
import arrow.meta.qq.classOrObject
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.descriptors.ClassifierDescriptorWithTypeParameters
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.SourceElement
import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.descriptors.impl.AbstractTypeAliasDescriptor
import org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.synthetics.SyntheticClassOrObjectDescriptor
import org.jetbrains.kotlin.resolve.lazy.LazyClassContext
import org.jetbrains.kotlin.resolve.lazy.declarations.ClassMemberDeclarationProvider
import org.jetbrains.kotlin.storage.StorageManager
import org.jetbrains.kotlin.types.SimpleType
import org.jetbrains.kotlin.types.TypeSubstitutor


val MetaComponentRegistrar.higherKindedTypes: List<ExtensionPhase>
  get() =
    meta(
      classOrObject(::isHigherKindedType) { c ->
        println("Processing Higher Kind: ${c.name}")
        listOfNotNull(
          /** Kind Marker **/
          "class For$name private constructor() { companion object }",
          /** Single arg type alias **/
          "typealias ${name}Of<${typeParameters.invariant}> = arrow.Kind${c.kindAritySuffix}<For$name, ${typeParameters.invariant}>",
          /** generate partial aliases if this kind has > 1 type parameters **/
          if (c.arity > 1)
            "typealias ${name}PartialOf<${c.partialTypeParameters}> = arrow.Kind${c.partialKindAritySuffix}<For$name, ${c.partialTypeParameters}>"
          else null,
          /** Class redefinition with kinded super type **/
          """
              |$modality $visibility $kind $name<$typeParameters>($valueParameters) : ${name}Of<${typeParameters.invariant}> {
              |  $body
              |}
              |"""
        )
      }
    )

private val Name.invariant: String
  get() = identifier
    .replace("out ", "")
    .replace("in ", "")

private val KtClass.partialTypeParameters: String
  get() = typeParameters
    .dropLast(1)
    .joinToString(separator = ", ") {
      it.nameAsSafeName.identifier
    }

private val KtClass.arity: Int
  get() = typeParameters.size

private val KtClass.kindAritySuffix: String
  get() = arity.let { if (it > 1) "$it" else "" }

private val KtClass.partialKindAritySuffix: String
  get() = (arity - 1).let { if (it > 1) "$it" else "" }

fun isHigherKindedType(ktClass: KtClass): Boolean =
  ktClass.fqName?.asString()?.startsWith("arrow.Kind") != true &&
    !ktClass.isAnnotation() &&
    ktClass.typeParameters.isNotEmpty() &&
    ktClass.parent is KtFile

val kindName: FqName = FqName("arrow.Kind")

val FqName.kindTypeAliasName: Name
  get() {
    val segments = pathSegments()
    val simpleName = segments.last()
    return Name.identifier("${simpleName}Of")
  }

fun provideSyntheticClass(
  ktclass: ClassDescriptor,
  declarationProvider: ClassMemberDeclarationProvider,
  ctx: LazyClassContext
): ClassDescriptor {
  val ktclassDecl = declarationProvider.correspondingClassOrObject!!
  val scope = ctx.declarationScopeProvider.getResolutionScopeForDeclaration(declarationProvider.ownerInfo!!.scopeAnchor)

  val props = ktclassDecl.primaryConstructorParameters
  // if there is some properties, there will be a public synthetic constructor at the codegen phase
  val descriptor = SyntheticClassOrObjectDescriptor(
    ctx,
    ktclassDecl,
    ktclass,
    Name.identifier("For${ktclass.name.identifier}"),
    ktclass.source,
    scope,
    Modality.FINAL,
    Visibilities.PUBLIC,
    Annotations.EMPTY,
    Visibilities.PRIVATE,
    ClassKind.CLASS,
    false
  )
  descriptor.initialize()
  return descriptor
}

fun KtClass.provideSyntheticAlias(): TypeAliasDescriptor =
  object : AbstractTypeAliasDescriptor(
    containingDeclaration = TODO(),
    annotations = Annotations.EMPTY,
    name = TODO(),
    sourceElement = SourceElement.NO_SOURCE,
    visibilityImpl = Visibilities.PUBLIC) {
    override val classDescriptor: ClassDescriptor?
      get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val constructors: Collection<TypeAliasConstructorDescriptor>
      get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val expandedType: SimpleType
      get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val storageManager: StorageManager
      get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val underlyingType: SimpleType
      get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun getDefaultType(): SimpleType {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTypeConstructorTypeParameters(): List<TypeParameterDescriptor> {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun substitute(substitutor: TypeSubstitutor): ClassifierDescriptorWithTypeParameters {
      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
  }
