
public class Q1 {
    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            left = right = null;
        }
    }
    static Node root;
    static Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }
        return root;
    }
    static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.key + " ");
        inorder(root.right);
    }
    static void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.key + " ");
        preorder(root.left);
        preorder(root.right);
    }
    static void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.key + " ");
    }
    static boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (root.key == key) {
            return true;
        }
        if (key < root.key) {
            return search(root.left, key);
        }
        return search(root.right, key);
    }

    public static void main(String[] args) {

        int[] values = {50, 30, 70, 20, 40, 60, 80, 10};
        for (int value : values) {
            root = insert(root, value);
        }
        System.out.print("Inorder: ");
        inorder(root);
        System.out.print("\nPreorder: ");
        preorder(root);
        System.out.print("\nPostorder: ");
        postorder(root);
        System.out.println("\n\nSearch 40: " +
                (search(root, 40) ? "Found" : "Not Found"));
        System.out.println("Search 90: " +
                (search(root, 90) ? "Found" : "Not Found"));
    }
}