
import java.util.Random;
public class Q3 {
    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
        }
    }
    static class BST {
        Node root;
        void insert(int key) {
            root = insert(root, key);
        }
        Node insert(Node root, int key) {
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
        boolean search(int key) {
            Node current = root;
            while (current != null) {
                if (current.key == key) {
                    return true;
                }
                if (key < current.key) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return false;
        }
        void delete(int key) {
            root = delete(root, key);
        }
        Node delete(Node root, int key) {

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
                if (root.left == null && root.right == null) {
                    return null;
                }
                if (root.left == null) {
                    return root.right;
                }
                if (root.right == null) {
                    return root.left;
                }
                Node successor = findMin(root.right);
                root.key = successor.key;
                root.right = delete(root.right, successor.key);
            }
            return root;
        }
        Node findMin(Node root) {
            while (root.left != null) {
                root = root.left;
            }
            return root;
        }
        int height() {
            return height(root);
        }
        int height(Node root) {
            if (root == null) {
                return 0;
            }
            return 1 + Math.max(
                    height(root.left),
                    height(root.right)
            );
        }
    }
    static int[] generateData(int n, String type) {
        int[] data = new int[n];
        if (type.equals("Sorted")) {
            for (int i = 0; i < n; i++) {
                data[i] = i + 1;
            }
        }
        else if (type.equals("Reverse")) {
            for (int i = 0; i < n; i++) {
                data[i] = n - i;
            }
        }
        else {
            for (int i = 0; i < n; i++) {
                data[i] = i + 1;
            }
            Random random = new Random(42);
            for (int i = n - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }
        return data;
    }
    static void test(String type, int n) {
        int[] data = generateData(n, type);
        BST tree = new BST();
        long start = System.nanoTime();
        for (int value : data) {
            tree.insert(value);
        }
        long end = System.nanoTime();
        double buildTime =
                (end - start) / 1_000_000.0;
        int height = tree.height();
        Random random = new Random(100);
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int key = random.nextInt(n) + 1;
            tree.search(key);
        }
        end = System.nanoTime();

        double searchTime =(end - start) / 1_000_000.0;
        start = System.nanoTime();
        for (int i = 1; i <= 500; i++) {
            tree.delete(i);
        }
        end = System.nanoTime();
        double deleteTime =(end - start) / 1_000_000.0;
        System.out.printf(
                "%-15s %-8d %-15.3f %-10d %-15.3f %-15.3f%n",
                type,
                n,
                buildTime,
                height,
                searchTime,
                deleteTime
        );
    }
    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000};
        String[] types = {
                "Random",
                "Sorted",
                "Reverse"
        };
        System.out.println(
                "BST Performance Analysis"
        );
        System.out.println();
        System.out.printf(
                "%-15s %-8s %-15s %-10s %-15s %-15s%n",
                "Input",
                "n",
                "Build(ms)",
                "Height",
                "Search(ms)",
                "Delete(ms)"
        );
        System.out.println(
                "-------------------------------------------------------------------------"
        );
        for (String type : types) {
            for (int n : sizes) {
                test(type, n);
            }
            System.out.println();
        }
    }
}