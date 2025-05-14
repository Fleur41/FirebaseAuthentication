//pluginManagement {
//    repositories {
//        google {
//            content {
//                includeGroupByRegex("com\\.android.*")
//                includeGroupByRegex("com\\.google.*")
//                includeGroupByRegex("androidx.*")
//            }
//        }
//        mavenCentral()
//        gradlePluginPortal()
//    }
//}
//dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
//    repositories {
//        google()
//        mavenCentral()
//    }
//}
//
////// Add the versionCatalogs block here
////versionCatalogs {
////    create("libs") {
////        from(files("gradle/libs.versions.toml"))
////    }
////}
////
////
////// Add this line to enable type-safe project accessors
////enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
//
//rootProject.name = "Firebase Authentication"
//include(":app")
//
//

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    // This must be INSIDE dependencyResolutionManagement
//    versionCatalogs {
//        create("libs") {
//            from(files("gradle/libs.versions.toml"))
//        }
//    }
}
//
//// Keep this at the bottom (outside all blocks)
//enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Firebase Authentication"
include(":app")