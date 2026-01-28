// swift-tools-version:5.7
import PackageDescription

let package = Package(
    name: "FanboardApp",
    platforms: [
        .iOS(.v15)
    ],
    products: [
        .executable(name: "FanboardApp", targets: ["FanboardApp"])
    ],
    dependencies: [
        // Add Firebase packages here when ready
    ],
    targets: [
        .executableTarget(
            name: "FanboardApp",
            path: "Sources/FanboardApp"
        )
    ]
)
