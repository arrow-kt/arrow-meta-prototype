
package arrow.meta.plugin.idea

import com.intellij.openapi.externalSystem.model.DataNode
import com.intellij.openapi.externalSystem.model.project.ModuleData
import org.jetbrains.kotlin.idea.configuration.GradleProjectImportHandler
import org.jetbrains.kotlin.idea.facet.KotlinFacet
import org.jetbrains.plugins.gradle.model.data.GradleSourceSetData

class ArrowGradleImportHandler : GradleProjectImportHandler {
    override fun importBySourceSet(facet: KotlinFacet, sourceSetNode: DataNode<GradleSourceSetData>) {
        ArrowImportHandler.modifyCompilerArguments(facet, PLUGIN_GRADLE_JAR)
    }

    override fun importByModule(facet: KotlinFacet, moduleNode: DataNode<ModuleData>) {
        ArrowImportHandler.modifyCompilerArguments(facet, PLUGIN_GRADLE_JAR)
    }

    private val PLUGIN_GRADLE_JAR = "gradle-plugin-all"
}
