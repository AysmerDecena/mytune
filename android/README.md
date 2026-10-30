# MyTune Android wrapper

This Android Studio project wraps the existing MyTune website in a small WebView app. YouTube video links selected through **Open in YouTube** open directly in the official YouTube Android app when it is installed, using the account already signed in there. If the YouTube app is unavailable, Android offers other compatible apps in its chooser. The wrapper does not embed NewPipe or control its playback.

## Build and install on the Galaxy S22 Ultra

1. Install Android Studio on a computer and open the `android` folder in this repository.
2. Let Android Studio install the requested Android SDK and Gradle components, then choose **Build > Build APK(s)**.
3. Copy `android/app/build/outputs/apk/debug/app-debug.apk` to the phone and open it to install. If Android asks, allow installs from that file app.
4. In MyTune, tap **Open in YouTube** for a video. The official YouTube app will use the account already signed in on your phone.

The app loads `https://aysmerdecena.github.io/mytune/` and stores its API key/favorites in this app's WebView storage. Enter the API key again in the wrapper the first time; Chrome's saved MyTune settings are separate. Network access is required. The **Play here** option remains the YouTube embedded player and follows YouTube's playback rules.

## Notes

- Minimum Android version: Android 6.0 (API 23); target SDK 35.
- This project uses the installed public MyTune website, so later website updates appear in the wrapper without rebuilding the APK.
- Android app handoff targets the official YouTube app first. If it is not installed, MyTune falls back to Android's chooser for other apps that can open YouTube links.
- A debug APK is for personal installation. A Play Store release requires a separately configured signing key and store listing.
