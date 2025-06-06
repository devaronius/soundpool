allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.layout.buildDirectory = file("../build")
subprojects {
    val directory = "${rootProject.layout.buildDirectory.get().asFile}/${project.name}"
    project.layout.buildDirectory = file(directory)
}

subprojects {
    project.evaluationDependsOn(":app")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
