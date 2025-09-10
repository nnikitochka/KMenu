package kmenu

import io.papermc.paper.plugin.loader.PluginClasspathBuilder
import io.papermc.paper.plugin.loader.PluginLoader
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver

@Suppress("UnstableApiUsage")
object KMenuLoader : PluginLoader {

    override fun classloader(classpathBuilder: PluginClasspathBuilder) {
        val resolver = MavenLibraryResolver()

        // Dependencies here

        classpathBuilder.addLibrary(resolver)
    }
}