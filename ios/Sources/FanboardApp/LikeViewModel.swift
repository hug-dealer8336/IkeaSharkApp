import Foundation
import FirebaseDatabase
import Combine

final class LikeViewModel: ObservableObject {
    @Published var likeCount: Int = 0

    private var ref: DatabaseReference?
    private var handle: DatabaseHandle?

    // Match the same keys used by Android
    private let paths = ["shark_likes", "dodo_likes"]

    func select(index: Int) {
        detach()
        let safeIndex = (0 <= index && index < paths.count) ? index : 0
        ref = Database.database().reference(withPath: paths[safeIndex])
        // Observe value changes
        handle = ref?.observe(.value, with: { [weak self] snapshot in
            let val = snapshot.value as? Int ?? 0
            DispatchQueue.main.async {
                self?.likeCount = val
            }
        })
    }

    func increment() {
        guard let r = ref else { return }
        r.runTransactionBlock { currentData in
            var value = currentData.value as? Int ?? 0
            value += 1
            currentData.value = value
            return TransactionResult.success(withValue: currentData)
        } andCompletionBlock: { error, committed, snapshot in
            // handle error if needed
        }
    }

    private func detach() {
        if let h = handle, let r = ref {
            r.removeObserver(withHandle: h)
        }
        handle = nil
        ref = nil
    }

    deinit {
        detach()
    }
}

