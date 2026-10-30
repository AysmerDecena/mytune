# MyTune Android wrapper

This Android Studio project wraps the existing MyTune website in a small WebView app. YouTube video links selected through **Open in player** are sent to Android's app chooser. If NewPipe is installed and registered to open YouTube links, choose it there. Otherwise choose YouTube or another supported app. The wrapper does not embed NewPipe or control its playback.

## Build and install on the Galaxy S22 Ultra

1. Install Android Studio on a computer and open the `android` folder in this repository.
2. Let Android Studio install the requested Android SDK and Gradle components, then choose **Build > Build APK(s)**.
3. Copy `android/app/build/outputs/apk/debug/app-debug.apk` to the phone and open it to install. If Android asks, allow installs from that file app.
4. Install NewPipe from its official source if desired. In MyTune, tap **Open in player** for a video and select NewPipe in Android's chooser.

The app loads `https://aysmerdecena.github.io/mytune/` and stores its API key/favorites in this app's WebView storage. Enter the API key again in the wrapper the first time; Chrome's saved MyTune settings are separate. Network access is required. The **Play here** option remains the YouTube embedded player and follows YouTube's playback rules.

## Notes

- Minimum Android version: Android 6.0 (API 23); target SDK 35.
- This project uses the installed public MyTune website, so later website updates appear in the wrapper without rebuilding the APK.
- Android app handoff uses the standard YouTube URL and chooser. NewPipe availability and handling depend on that installed version and Android's app-link settings.
- A debug APK is for personal installation. A Play Store release requires a separately configured signing key and store listing.
