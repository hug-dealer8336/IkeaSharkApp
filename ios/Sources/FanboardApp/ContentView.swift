import SwiftUI
import FirebaseDatabase

struct ContentView: View {
    @State private var selectedIndex = 0
    @StateObject private var viewModel = LikeViewModel()

    let items = [("ikea_shark", "IKEA Shark"), ("dodo", "Dodo")]

    var body: some View {
        NavigationView {
            VStack {
                Picker("Item", selection: $selectedIndex) {
                    ForEach(0..<items.count, id: \.self) { idx in
                        Text(items[idx].1).tag(idx)
                    }
                }
                .pickerStyle(.segmented)
                .onChange(of: selectedIndex) { newIndex in
                    viewModel.select(index: newIndex)
                }

                Spacer()

                Image(items[selectedIndex].0)
                    .resizable()
                    .scaledToFit()
                    .frame(maxWidth: 300, maxHeight: 300)

                Spacer()

                Text("Likes: \(viewModel.likeCount)")
                    .font(.largeTitle)

                Button(action: {
                    viewModel.increment()
                }) {
                    Label("I LIKE", systemImage: "heart.fill")
                        .padding()
                        .background(Color.accentColor)
                        .foregroundColor(.white)
                        .cornerRadius(12)
                }
                Spacer()
            }
            .padding()
            .navigationTitle("Fanboard")
            .onAppear {
                viewModel.select(index: selectedIndex)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
