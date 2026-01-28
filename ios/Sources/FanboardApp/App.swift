import SwiftUI
import FirebaseCore

@main
struct FanboardApp: App {
    init() {
        // Placeholder Firebase initialization; replace with valid plist
        if let _ = Bundle.main.path(forResource: "GoogleService-Info", ofType: "plist") {
            FirebaseApp.configure()
        }
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
