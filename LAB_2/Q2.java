
public class Q2 {
    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
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
    static Node findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
    static Node delete(Node root, int key) {

        if (root == null) {
            return null;
        }
        if (key < root.key) {
            root.left = delete(root.left, key);
        }
        else if (key > root.key) {
            root.right = delete(root.right, key);
        }
        else {
            //Leaf node
            if (root.left == null && root.right == null) {
                return null;
            }
            //Only right child
            if (root.left == null) {
                return root.right;
            }
            //Only left child
            if (root.right == null) {
                return root.left;
            }
            //Two children
            Node successor = findMin(root.right);
            root.key = successor.key;
            root.right = delete(root.right, successor.key);
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
    public static void main(String[] args) {
        int[] values = {
            50, 30, 70, 20, 40, 60, 80, 10, 25
        };
        for (int value : values) {
            root = insert(root, value);
        }
        System.out.print("Original inorder: ");
        inorder(root);
        System.out.println("\n\nDelete leaf node: 10");
        root = delete(root, 10);
        System.out.print("Inorder: ");
        inorder(root);
        System.out.println("\n\nDelete node with one child: 20");
        root = delete(root, 20);
        System.out.print("Inorder: ");
        inorder(root);
        System.out.println("\n\nDelete node with two children: 50");
        root = delete(root, 50);
        System.out.print("Inorder: ");
        inorder(root);
    }
}