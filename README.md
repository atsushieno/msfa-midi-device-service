# What is this?

This is an experimental implementation of Android MidiDeviceService based on
https://github.com/google/music-synthesizer-for-android .

Android 6.0 introduced the API so that any MIDI client apps can access to virtual synthesizer service. So, why not applying it to "MSFA" ?

# Building

Although the original app is written for Android too, its build system has obsoleted premise in the build scripts, therefore I use my own fork of it with the updated build.gradle and NDK support.

Building from Android Studio or gradlew is expected, but let me know if it doesn't work well for you.
(I usually provide awful build experience but it should be better...!)

# Licenses

I copied raw resource from google/music-synthesizer-for-android so it should
follow Apache License 2.0. Other than that see [LICENSE](LICENSE).
