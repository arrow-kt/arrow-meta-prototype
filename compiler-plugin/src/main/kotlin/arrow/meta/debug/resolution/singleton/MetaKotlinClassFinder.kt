package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.load.java.structure.JavaClass
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import java.io.InputStream

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaKotlinClassFinder(val delegate: KotlinClassFinder) : KotlinClassFinder by delegate {
  override fun findBuiltInsData(packageFqName: FqName): InputStream? {
    println("MetaKotlinClassFinder.findBuiltInsData: $packageFqName")
    return delegate.findBuiltInsData(packageFqName)
  }

  override fun findKotlinClassOrContent(javaClass: JavaClass): KotlinClassFinder.Result? {
    println("MetaKotlinClassFinder.findKotlinClassOrContent: $javaClass")
    return delegate.findKotlinClassOrContent(javaClass)
  }

  override fun findKotlinClassOrContent(classId: ClassId): KotlinClassFinder.Result? {
    println("MetaKotlinClassFinder.findKotlinClassOrContent: $classId")
    return delegate.findKotlinClassOrContent(classId)
  }

  override fun findMetadata(classId: ClassId): InputStream? {
    println("MetaKotlinClassFinder.findMetadata: $classId")
    return delegate.findMetadata(classId)
  }

  override fun hasMetadataPackage(fqName: FqName): Boolean {
    println("MetaKotlinClassFinder.hasMetadataPackage: $fqName")
    return delegate.hasMetadataPackage(fqName)
  }
}