import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.the

fun Project.setupPublishing() {
    plugins.withId("maven-publish") {
        val sourcesJar = tasks.register("sourcesJar", Jar::class) {
            archiveClassifier.set("sources")
            from(project.the<SourceSetContainer>().named<SourceSet>("main").get().allSource)
        }

        extensions.configure(PublishingExtension::class.java) {
            publications.create("maven", MavenPublication::class.java) {
                groupId = rootProject.group.toString()
                artifactId = project.name
                version = rootProject.version.toString()

                from(components["kotlin"])

                artifact(sourcesJar)
            }
        }
    }
}