package kmenu;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.jetbrains.annotations.NotNull;

/**
 * This class written on Java because Paper couldn't load Kotlin class without Kotlin :/
 */
@SuppressWarnings("UnstableApiUsage")
public class KMenuLoader implements PluginLoader {

    @Override
    public void classloader(PluginClasspathBuilder classpathBuilder) {
        MavenLibraryResolver resolver = new MavenLibraryResolver();

        resolver.addRepository(
                new RemoteRepository.Builder(
                        "central",
                        "default",
                        MavenLibraryResolver.MAVEN_CENTRAL_DEFAULT_MIRROR
                ).build()
        );

        addDependency(resolver, "org.jetbrains.kotlin:kotlin-stdlib:2.2.10");

        classpathBuilder.addLibrary(resolver);
    }

    private void addDependency(@NotNull MavenLibraryResolver resolver, @NotNull String dependency) {
        resolver.addDependency(
                new Dependency(
                        new DefaultArtifact(dependency), null
                )
        );
    }
}
