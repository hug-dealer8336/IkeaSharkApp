import SwiftUI
import FirebaseDatabase

struct ContentView: View {
    @State private var selectedIndex = 0
    @State private var likeCount: Int = 0

    let items = [("ikea_shark", "IKEA Shark"), ("dodo", "Dodo")]

    var body: some View {
        NavigationView {
            VStack {
                Picker("Item", selection: $selectedIndex) {
                    ForEach(0..<items.count, id: \.self) { idx in
                        Text(items[idx].1).tag(idx)
                    }
                }.pickerStyle(.segmented)
                Spacer()
                Image(items[selectedIndex].0)
                    .resizable()
                    .scaledToFit()
                    .frame(maxWidth: 300, maxHeight: 300)
                Spacer()
                Text(\"Likes: \\(likeCount)\")
                    .font(.largeTitle)
                Button(action: incrementLike) {
                    Label(\"I LIKE\", systemImage: \"heart.fill\")
                        .padding()
                        .background(Color.accentColor)
                        .foregroundColor(.white)
                        .cornerRadius(12)
                }
                Spacer()
            }
            .padding()
            .navigationTitle(\"Fanboard\")
        }
    }

    func incrementLike() {
        // Placeholder: integrate Firebase Database updates here
        likeCount += 1
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
