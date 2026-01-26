# IKEA Shark Fan App

An Android app to show your love for the IKEA shark (Blåhaj)! Press the heart button to add your like to the global counter that everyone can see.

## Features

- IKEA-themed design (blue & yellow)
- Shows your IKEA shark picture
- Heart "I LIKE" button with animation
- Real-time global like counter (shared across all users!)
- Bounce animation for the shark when you like

## Setup Instructions

### 1. Set Up Firebase (Required for Online Counter)

The app uses Firebase Realtime Database to store the like count so everyone sees the same number.

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Click "Create a project" or "Add project"
3. Name it something like "ikea-shark-fan"
4. (Optional) Disable Google Analytics if you don't need it
5. Click "Create project"

### 2. Add Android App to Firebase

1. In your Firebase project, click the Android icon to add an Android app
2. Enter the package name: `com.ikeafan.sharkapp`
3. Enter an app nickname: "IKEA Shark Fan"
4. Click "Register app"
5. Download the `google-services.json` file
6. Replace the placeholder `google-services.json` in `app/` with your downloaded file

### 3. Set Up Realtime Database

1. In Firebase Console, go to "Build" → "Realtime Database"
2. Click "Create Database"
3. Choose a location close to your users
4. Start in **Test mode** (or configure security rules later)
5. Click "Enable"

#### Database Rules (Optional - for production)

For a simple public counter, use these rules:

```json
{
  "rules": {
    "shark_likes": {
      ".read": true,
      ".write": true
    }
  }
}
```

### 4. Build the APK

#### Using Android Studio:
1. Open the `IkeaSharkApp` folder in Android Studio
2. Wait for Gradle sync to complete
3. Go to Build → Build Bundle(s) / APK(s) → Build APK(s)
4. The APK will be in `app/build/outputs/apk/debug/`

#### Using Command Line:
```bash
cd IkeaSharkApp
./gradlew assembleDebug
```

The APK will be at `app/build/outputs/apk/debug/app-debug.apk`

### 5. Install on Your Phone

1. Transfer the APK to your Android phone
2. Open the APK file on your phone
3. Allow installation from unknown sources if prompted
4. Install and enjoy!

## Project Structure

```
IkeaSharkApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/ikeafan/sharkapp/
│   │   │   └── MainActivity.kt      # Main app logic
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml # UI layout
│   │   │   ├── drawable/
│   │   │   │   └── ikea_shark.jpg   # Your shark image!
│   │   │   ├── anim/                 # Button animations
│   │   │   └── values/               # Colors, strings, themes
│   │   └── AndroidManifest.xml
│   ├── google-services.json          # Firebase config (replace this!)
│   └── build.gradle.kts
├── build.gradle.kts
└── settings.gradle.kts
```

## Troubleshooting

**"Failed to load likes" error:**
- Check your internet connection
- Make sure Firebase is set up correctly
- Verify the `google-services.json` file is valid

**App crashes on start:**
- Make sure you replaced the placeholder `google-services.json`
- Check that Firebase Realtime Database is enabled

**Counter not updating:**
- Check Firebase Database rules allow read/write
- Check your internet connection

## Love for Blåhaj! 🦈❤️

This app was made with love for the iconic IKEA shark. Every like counts!
