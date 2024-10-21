# TMDB Project Overview

## Introduction
The TMDB (The Movie Database) project is an application designed to interact with the TMDB API to fetch and display information about movies and actors. The project is built using Kotlin and it leverages Gradle for dependency management and build automation. The project is organized into multiple modules to enhance maintainability and scalability.

## App Design
![TMDB Cover](https://github.com/winechit-dev/tmdb-android/blob/master/TMDB%20cover.png)

## Design Reference
You can view the design on Figma by following [this link](https://www.figma.com/design/obfHECfEsAK3y5mgIqSe60/TMDB-(Community)?node-id=0-1&node-type=canvas&t=XDLJ7LcSOit03MTQ-0).

## My Favorite Features
- [Theme Settings](https://winechit-dev.github.io/tmdb-android/video/Theme%20setting.mp4).
- [Bound Click Effect](https://winechit-dev.github.io/tmdb-android/video/bound%20click%20effect.webm).
- [Haptic Feedback effect](https://winechit-dev.github.io/tmdb-android/video/haptic%20feedback%20effect.webm).
- [Share Element Transation](https://winechit-dev.github.io/tmdb-android/video/share%20element%20transation.webm).
- [System bars management](https://winechit-dev.github.io/tmdb-android/video/system%20bars%20management.mp4).

## Project Structure
The project is divided into the following modules:

1. **app**: The main application module.
2. **core**: Contains core functionalities and utilities used across the project.
3. **feature**: Contains individual features of the application.
4. **build-logic**: Contains custom Gradle scripts and build logic.

## Key Module

### app
- **Description**: The main entry point of the application. It includes the application class, main activity, and other essential components.
- **Dependencies**: Depends on `core` and `feature` modules.

## Core Module Structure

The `core` module is divided into several sub-modules, each responsible for a specific aspect of the application's core functionalities. Below is an explanation of each sub-module:

### `core:designsystem`
- **Description**: Contains design-related resources and components such as themes, styles, and reusable UI elements.
- **Components**: Custom views, themes, styles, and drawable resources.

### `core:network`
- **Description**: Manages network operations and API interactions.
- **Components**: Retrofit setup, API service interfaces, network interceptors, and network utility classes.

### `core:database`
- **Description**: Handles local database operations.
- **Components**: Database entities, DAOs (Data Access Objects), and database configuration.

### `core:data`
- **Description**: Manages data operations and acts as a bridge between the network and database layers.
- **Components**: Repositories, data sources, and data mappers.

### `core:domain`
- **Description**: Contains business logic.
- **Components**:  Domain models, and business logic.

### `core:ui`
- **Description**: Provides common UI components and utilities used across the application.
- **Components**:  UI utilities.

### `core:datastore`
- **Description**: Manages data storage using Jetpack DataStore.
- **Components**: DataStore preferences, serializers, and data access classes.

### `core:common`
- **Description**: Contains common utilities and helper classes used throughout the project.
- **Components**: Utility functions, constants, and extension functions.


## Feature Module Structure

The `feature` module is divided into several sub-modules, each responsible for a specific feature of the application. Below is an explanation of each sub-module:

### `feature:discover`
- **Description**: Manages the discovery of movies.
- **Components**: UI components for displaying lists of movies, view models, and related business logic.

### `feature:favorites`
- **Description**: Handles the functionality related to managing favorite movies.
- **Components**: UI components for displaying favorite items, view models, and data management for favorites.

### `feature:search`
- **Description**: Provides search functionality for movies.
- **Components**: UI components for search input and results, view models, and search-related logic.

### `feature:settings`
- **Description**: Manages application settings and preferences.
- **Components**: UI components for settings screens, view models, and logic for handling user preferences.

This modular structure ensures that each feature is encapsulated within its own module, promoting reusability and maintainability across the project.

### build-logic
- **Description**: Contains custom Gradle scripts and build logic to streamline the build process.
- **Components**:
  - **Gradle Scripts**: Custom scripts for dependency management, code quality checks, and other build-related tasks.

## Dependencies

### Core Libraries
- **androidx-core-ktx**: Provides Kotlin extensions for Android core libraries.
- **junit**: JUnit 4 framework for unit testing.
- **kotlinx-coroutines-test**: Testing utilities for Kotlin coroutines.
- **mockk**: Mocking library for Kotlin.
- **turbine**: A small testing library for Kotlin Flow.

### AndroidX Libraries
- **androidx-junit**: JUnit extensions for Android.
- **android-compose-screenshot**: UI testing Compose screenshot testing.
- **androidx-lifecycle-runtime-ktx**: Kotlin extensions for Android Lifecycle.
- **androidx-activity-compose**: Integration of Jetpack Compose with Android activities.
- **androidx-compose-bom**: Bill of Materials for Jetpack Compose.
- **androidx-ui**: Core UI components for Jetpack Compose.
- **androidx-ui-graphics**: Graphics utilities for Jetpack Compose.
- **androidx-ui-tooling**: Tooling support for Jetpack Compose.
- **androidx-ui-tooling-preview**: Preview support for Jetpack Compose.
- **androidx-ui-test-manifest**: Manifest support for UI tests.
- **androidx-material3**: Material Design components for Jetpack Compose.
- **androidx-lifecycle-viewmodel-compose**: ViewModel support for Jetpack Compose.
- **androidx-compose-foundation**: Foundation components for Jetpack Compose.
- **androidx-navigation-compose**: Navigation support for Jetpack Compose.

### Google Libraries
- **desugaring**: Desugaring library for Java 8+ APIs.

### Dagger Hilt Libraries
- **hilt-android**: Dagger Hilt for dependency injection.
- **hilt-compiler**: Compiler for Dagger Hilt.

### Kotlin Libraries
- **kotlin-gradle-plugin**: Gradle plugin for Kotlin.
- **kotlin-serialization**: Kotlin serialization library.
- **kotlinx-serialization-json**: JSON serialization for Kotlin.
- **kotlinx-coroutines-android**: Kotlin coroutines support for Android.

### Retrofit Libraries
- **retrofit-kotlin-serialization**: Retrofit converter for Kotlin serialization.
- **retrofit-core**: Core Retrofit library.
- **okhttp-logging**: Logging interceptor for OkHttp.

### Arrow Libraries
- **arrow**: Functional programming library for Kotlin.

### Coil Libraries
- **coil**: Image loading library for Jetpack Compose.

### Room Libraries
- **room-runtime**: Runtime components for Room.
- **room-compiler**: Annotation processor for Room.
- **room-ktx**: Kotlin extensions for Room.

### SQLCipher Libraries
- **sqlcipher**: SQLCipher for Android.

### DataStore Libraries
- **androidx-dataStore-core**: Core DataStore library.
- **androidx-dataStore-pref**: Preferences DataStore library.

### Custom Plugins
- **convention-application**: Custom plugin for Android application conventions.
- **convention-library**: Custom plugin for Android library conventions.
- **convention-compose-application**: Custom plugin for Compose application conventions.
- **convention-compose-library**: Custom plugin for Compose library conventions.
- **jetbrains-kotlin-jvm**: Kotlin JVM plugin.

  

## Getting Started
1. **Clone the Repository**: `git clone git@github.com:winechit-dev/tmdb-android.git`
2. **Open in Android Studio**: Open the project in Android Studio.
3. **Build the Project**: Use Gradle to build the project.
4. **Run the Application**: Deploy the application to an emulator or physical device.

## Configuration
- **API Key**: Obtain an API key from TMDB and configure it in the project.
- **Gradle Scripts**: Ensure all dependencies are correctly specified in the `build.gradle` files.

## Contributing
- **Fork the Repository**: Create a personal fork of the repository.
- **Create a Branch**: Create a new branch for your feature or bugfix.
- **Submit a Pull Request**: Submit a pull request with a detailed description of your changes.
