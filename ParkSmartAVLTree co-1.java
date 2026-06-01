class AVLNode {
    int key;
    AVLNode left, right;
    int height = 1;

    AVLNode(int key) {
        this.key = key;
    }
}

public class ParkSmartAVLTree {

    static int height(AVLNode n) {
        return n == null ? 0 : n.height;
    }

    static int balance(AVLNode n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    static void updateHeight(AVLNode n) {
        if (n != null)
            n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    // Right Rotation
    static AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Left Rotation
    static AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Insert into AVL Tree
    static AVLNode insert(AVLNode node, int key) {

        if (node == null)
            return new AVLNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        updateHeight(node);

        int bf = balance(node);

        // LL Case
        if (bf > 1 && key < node.left.key)
            return rotateRight(node);

        // RR Case
        if (bf < -1 && key > node.right.key)
            return rotateLeft(node);

        // LR Case
        if (bf > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RL Case
        if (bf < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Find Minimum Node
    static AVLNode minNode(AVLNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // Delete Node
    static AVLNode delete(AVLNode node, int key) {

        if (node == null)
            return null;

        if (key < node.key)
            node.left = delete(node.left, key);

        else if (key > node.key)
            node.right = delete(node.right, key);

        else {

            if (node.left == null)
                return node.right;

            if (node.right == null)
                return node.left;

            AVLNode successor = minNode(node.right);

            node.key = successor.key;

            node.right = delete(node.right, successor.key);
        }

        updateHeight(node);

        int bf = balance(node);

        // LL
        if (bf > 1 && balance(node.left) >= 0)
            return rotateRight(node);

        // LR
        if (bf > 1 && balance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RR
        if (bf < -1 && balance(node.right) <= 0)
            return rotateLeft(node);

        // RL
        if (bf < -1 && balance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Inorder Traversal
    static void inorder(AVLNode node) {
        if (node == null)
            return;

        inorder(node.left);
        System.out.print(node.key + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {

        // Parking Slot IDs
        int[] slotIDs = {
            20, 30, 35, 40, 45, 50,
            60, 65, 70, 75, 80, 85, 90
        };

        AVLNode root = null;

        for (int id : slotIDs)
            root = insert(root, id);

        System.out.println("======================================");
        System.out.println(" ParkSmart Parking Slot AVL Tree");
        System.out.println("======================================");

        System.out.print("Parking Slot IDs (Inorder): ");
        inorder(root);

        System.out.println("\nAVL Height After Insertions: "
                + height(root));

        // Maintenance Deletions
        int[] deletions = {30, 70, 50};

        System.out.println("\nRemoving Parking Slots:");

        for (int id : deletions) {
            root = delete(root, id);

            System.out.println(
                "After Removing Slot-ID "
                + id
                + " -> Height = "
                + height(root)
            );
        }

        System.out.print("\nFinal Parking Slot AVL Tree (Inorder): ");
        inorder(root);

        System.out.println("\n\nFinal AVL Height: " + height(root));

        System.out.println("\nParkSmart AVL Tree Operations Completed Successfully.");
    }
}