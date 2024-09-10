## Structure
This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop, Server.
* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.

## Important Gradle tasks (WIP)
Can be run with `./gradlew` but it's recommended to create a run configuration.
* Run desktop: `desktopRun -DmainClass=MainKt --quiet`
* Run web: `:composeApp:wasmJsBrowserDevelopmentRun`
* Run web JS: `:composeApp:jsBrowserDevelopmentRun`
* Build web: `./gradlew clean wasmJsBrowserDistribution`
* Build web JS: `./gradlew clean jsBrowserDistribution`
When building for web it's recommended to also run a clean to avoid possible bugs
