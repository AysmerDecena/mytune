# MyTune

A personal, mobile-first YouTube video search and favorites app. It uses YouTube Data API v3 for search and the official YouTube embedded player for playback. Favorites and the API key are saved in the browser's local storage on this device.

## Android setup

1. Create or choose a project in [Google Cloud Console](https://console.cloud.google.com/).
2. Enable **YouTube Data API v3**, create an API key, and restrict the key to this API. Once the app is hosted, add an HTTP referrer restriction for its exact site origin.
3. Host this folder on an HTTPS static host. A secure site is required for installable PWA behavior and service workers.
4. On your Galaxy S22 Ultra, open the hosted site in Chrome, tap **⋮**, and choose **Install app** or **Add to Home screen**.
5. Open MyTune, tap ⚙, paste the API key, then search.

The key is stored only in local browser storage. For a public app, proxy API calls through a backend rather than distributing a shared unrestricted API key.

## Playback limits

Playback remains in the visible YouTube embedded player with its controls and branding. This app does not offer background playback, audio extraction, downloads, or ad removal. YouTube's developer policies prohibit an API client from enabling background playback or separating audio from video. To listen to music in the background, use the official YouTube Music app with a membership that includes that benefit. Premium Lite's background feature excludes music content; it applies to most other videos in the official YouTube app.

## Local preview

You can open `index.html` to inspect the layout, but API search and installable behavior require serving it from a secure HTTPS origin. Search also requires your own YouTube Data API key.
