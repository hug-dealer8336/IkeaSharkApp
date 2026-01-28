iOS SwiftUI Placeholder Project

This folder contains a minimal SwiftUI app scaffold and a placeholder Firebase config.

How to use:
1. Open Xcode and create a new SwiftUI iOS app project (File → New → Project → App → Interface: SwiftUI). Name it `Fanboard`.
2. Replace the project's `App` and `ContentView` with the files in `Sources/` below, or copy the files into your Xcode project.
3. Add Firebase SDK following Firebase docs (or use Swift Package Manager to add `FirebaseDatabase`).
4. Replace `GoogleService-Info.plist` with your Firebase iOS config.

Placeholder files inside:
- GoogleService-Info.plist (placeholder) — replace with your file.
- Sources/FanboardApp/App.swift
- Sources/FanboardApp/ContentView.swift
- Package.swift (optional; for SwiftPM)

