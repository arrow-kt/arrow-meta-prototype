package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.load.java.JavaClassFinder
import org.jetbrains.kotlin.load.java.structure.JavaClass
import org.jetbrains.kotlin.load.java.structure.JavaPackage
import org.jetbrains.kotlin.name.FqName

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaJavaClassFinder(val delegate : JavaClassFinder) : JavaClassFinder by delegate {
  override fun findClass(request: JavaClassFinder.Request): JavaClass? {
    println("MetaJavaClassFinder.findClass: $request")
    return delegate.findClass(request)
  }

  override fun findPackage(fqName: FqName): JavaPackage? {
    println("MetaJavaClassFinder.findPackage: $fqName")
    return delegate.findPackage(fqName)
  }

  override fun knownClassNamesInPackage(packageFqName: FqName): Set<String>? {
    println("MetaJavaClassFinder.knownClassNamesInPackage: $packageFqName")
    return delegate.knownClassNamesInPackage(packageFqName)
  }
}