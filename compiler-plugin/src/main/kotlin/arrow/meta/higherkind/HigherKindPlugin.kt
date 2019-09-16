package arrow.meta.higherkind

import arrow.meta.extensions.ExtensionPhase
import arrow.meta.extensions.MetaComponentRegistrar
import arrow.meta.qq.classOrObject
import arrow.meta.utils.ide
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider
import org.jetbrains.kotlin.js.resolve.diagnostics.findPsi
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.types.DelegatingSimpleTypeImpl
import org.jetbrains.kotlin.utils.addToStdlib.safeAs


val MetaComponentRegistrar.higherKindedTypes: Pair<Name, List<ExtensionPhase>>
  get() =
    Name.identifier("higherKindedTypes") to
      meta(
        additionalSources(
          collectAdditionalSourcesAndUpdateConfiguration = { knownSources, configuration, project ->
            println("additionalSources.collectAdditionalSourcesAndUpdateConfiguration: $knownSources")
            knownSources
          }
        ),
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
    val simpleName = segments.lastOrNull() ?: Name.special("index not ready")
    return Name.identifier("${simpleName}Of")
  }
