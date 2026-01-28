iOS SwiftUI Placeholder Project

This folder contains a minimal SwiftUI app scaffold and a placeholder Firebase config.

How to use:
1. Open Xcode and create a new SwiftUI iOS app project (File → New → Project → App → Interface: SwiftUI). Name it `Fanboard`.
2. Replace the project's `App` and `ContentView` with the files in `Sources/` below, or copy the files into your Xcode project.
3. Add Firebase SDK following Firebase docs (or use Swift Package Manager to add `FirebaseDatabase`).
4. Replace `GoogleService-Info.plist` with your Firebase iOS config.

Firebase setup notes:
- In the Firebase console, register an iOS app with bundle id `com.ikeafan.sharkapp` (or your chosen bundle id).
- Download the `GoogleService-Info.plist` and add it to your Xcode project (ensure it's included in the app target).
- In Xcode: File → Add Packages... → enter `https://github.com/firebase/firebase-ios-sdk` and add the `FirebaseDatabase` package (and `FirebaseCore` if not auto-added).
- Ensure `import FirebaseCore` is present in `App.swift` and `FirebaseApp.configure()` is called (already in scaffold).
- After adding the package, build once to allow Xcode to fetch dependencies.

Runtime notes:
- The SwiftUI `ContentView` uses `LikeViewModel` (Sources/FanboardApp/LikeViewModel.swift) which listens to the Realtime Database keys `shark_likes` and `dodo_likes`. Both platforms should use the same paths to share counts.
- The app uses a transaction to increment likes safely so Android and iOS updates won't conflict.

Building:
- You can run the app in the iOS Simulator without an Apple Developer Program account.
- To run on a physical device, you can use a free Apple ID for development provisioning (Xcode will guide you).

Placeholder files inside:
- GoogleService-Info.plist (placeholder) — replace with your file.
- Sources/FanboardApp/App.swift
- Sources/FanboardApp/ContentView.swift
- Package.swift (optional; for SwiftPM)

