
package arrow.meta.plugin.gradle

/**
 * The available Gradle configuration for this plugin.
 */
open class ArrowExtension {

    var pureCheck: Boolean = false

    open fun pureCheck(pureCheck: Boolean) {
        this.pureCheck = pureCheck
    }
}
