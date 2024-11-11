# MyExercise, 2023-DEV2-032
> Tic-Tac-Kata Time: build better code with every move!

This is a test exercise implemented by 2023-DEV2-032 for Tic Tac Toe

## Which Kata

[Tick Tac Toe](https://github.com/stephane-genicot/katas/blob/master/TicTacToe.md)

## How to install dependencies

Open Android Studio, run gradle sync

Select correct build variant in the build variant menu. For example: `debug`

## How to run on device

* Press build in Android Studio and make sure the correct device or simulator is selected in the dropdown menu on the top right
* Install on device via gradle command `./gradlew app:install{build variant}`. Example: `./gradlew app:installDebug`

## How to debug

You can debug the application by pressing the debug button top right. It will build the application on a device or simulator in debug mode.

## How to make build/release
* Android Studio: Under top menu `build` -> `Generate signed bundle / apk`
* CLI: `./gradlew app:assembleRelease`  

## How to run tests

Press build in Android Studio and make sure the correct device or simulator is selected in the dropdown menu on the top right.

## How to debug

You can debug the application by pressing the debug button top right. It will build the application on a device or simulator in debug mode.

Tip: use [Flipper](https://github.com/facebook/flipper) to inspect layouts, network calls, shared preferences and databases.

## Screenshot testing
> Because you never know when a rogue pixel might try to start its own reality show!

Validate screenshots.
```sh
./gradlew validateDebugScreenshotTest
```

Update screenshots.
```sh
./gradlew updateDebugScreenshotTest
```

## Concepts used

* [MVVM](https://proandroiddev.com/understanding-mvvm-pattern-for-android-in-2021-98b155b37b54)
* [Test Driven Development](https://medium.com/swlh/tdd-in-android-d0347c944a9a)
* [Software craftsmanship](http://manifesto.softwarecraftsmanship.org/)

## Libraries

* UI [Jetpack Compose](https://developer.android.com/compose)
* Dependency Injection: [Koin](https://insert-koin.io/)
* Asynchronous or non-blocking programming: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
* Testing: [JUnit4](https://developer.android.com/training/testing/junit-rules)
* Logging: [Timber](https://github.com/JakeWharton/timber)
* Screenshot testing: [Compose Preview Screenshot Testing](https://developer.android.com/studio/preview/compose-screenshot-testing)

## Useful resources and references

* [Android Architecture](https://developer.android.com/topic/architecture/intro)
* [Koin in Compose](https://insert-koin.io/docs/quickstart/android-compose/)
* [Build local unit tests](https://developer.android.com/training/testing/local-tests)
* [Android testing](https://developer.android.com/training/testing)
* [How to perform testing](https://en.paradigmadigital.com/dev/android-testing-how-to-perform-unit-tests/)