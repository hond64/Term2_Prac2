//Miguel Wentzel
//4478677
//Practical 2
//Question 1 + 2
public class BinarySearchTree {
    private Node root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public BinarySearchTree(Node root, int size) {
        this.root = root;
        this.size = size;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean search(int data) {
        Node current = root;
        while (current != null) {
            if (data == current.getData()) {
                return true;
            } else if (data < current.getData()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return false;
    }

    public void insert(int data) {
        if (root == null) {
            root = new Node(data, null, null);
            size++;
            return;
        }

        Node parent = null;
        Node current = root;

        while (current != null) {
            parent = current;
            if (data == current.getData()) {
                System.out.println("Input not valid");
                return;
            } else if (data < current.getData()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        if (data < parent.getData()) {
            parent.setLeft(new Node(data, null, null));
        } else {
            parent.setRight(new Node(data, null, null));
        }
        size++;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    public void print_in_order() {
        StringBuilder sb = new StringBuilder();
        buildInOrder(root, sb);
        System.out.println("IN_ORDER: " + sb.toString().trim());
    }

    public String getInOrderString() {
        StringBuilder sb = new StringBuilder();
        buildInOrder(root, sb);
        return sb.toString().trim();
    }

    private void buildInOrder(Node node, StringBuilder sb) {
        if (node != null) {
            buildInOrder(node.getLeft(), sb);
            sb.append(node.getData()).append(" ");
            buildInOrder(node.getRight(), sb);
        }
    }

    public int find_kth_smallest(int k) {
        if (k <= 0 || k > size) {
            System.out.println("Input not valid");
            return -1;
        }

        Counter counter = new Counter();
        Node result = findKthSmallest(root, k, counter);
        if (result == null) {
            System.out.println("Input not valid");
            return -1;
        }
        return result.getData();
    }

    private Node findKthSmallest(Node node, int k, Counter counter) {
        if (node == null) {
            return null;
        }

        Node leftResult = findKthSmallest(node.getLeft(), k, counter);
        if (leftResult != null) {
            return leftResult;
        }

        counter.count++;
        if (counter.count == k) {
            return node;
        }

        return findKthSmallest(node.getRight(), k, counter);
    }

    public void delete(int data) {
        if (!search(data)) {
            System.out.println("Input not valid");
            return;
        }
        root = deleteRecursive(root, data);
        size--;
    }

    private Node deleteRecursive(Node node, int data) {
        if (node == null) {
            return null;
        }

        if (data < node.getData()) {
            node.setLeft(deleteRecursive(node.getLeft(), data));
        } else if (data > node.getData()) {
            node.setRight(deleteRecursive(node.getRight(), data));
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }

            Node predecessor = findMax(node.getLeft());
            node.setData(predecessor.getData());
            node.setLeft(deleteRecursive(node.getLeft(), predecessor.getData()));
        }
        return node;
    }

    private Node findMax(Node node) {
        Node current = node;
        while (current != null && current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }

    private static class Counter {
        private int count = 0;
    }
}
