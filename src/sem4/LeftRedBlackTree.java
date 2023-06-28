package sem4;

public class LeftRedBlackTree<T extends Comparable<T>> {
    private Node root;
    private class Node {
        T value;
        Color color;
        Node left, right;

        public Node (T value) {
            this.value = value;
            this.color = Color.Red;
            this.left = null;
            this.right = null;
        }
    }
    public void add(T value) {
        root = addNode(root, value);  // Добавляем новый узел, начиная от корня
        root.color = Color.Black;     // Корень всегда черный
    }
    private Node addNode(Node node, T value) {
        // Если узел пустой, то создаем новый узел
        if (node == null) {
            return new Node(value);
        }

        // Добавляем новый узел в левое поддерево
        if (value.compareTo(node.value) < 0) {
            node.left = addNode(node.left, value);

        }
        // Добавляем новый узел в правое поддерево
        else if (value.compareTo(node.value) > 0) {
            node.right = addNode(node.right, value);

        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = leftSwap(node);
        }
        // 2
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightSwap(node);
        }
        // 3
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }
    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == Color.Red;
    }
    private Node leftSwap(Node node) {

        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = Color.Red;
        return x;
    }
    private Node rightSwap(Node node) {

        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = Color.Red;
        return x;
    }

    // Метод для выполнения смены цвета
    private void flipColors(Node node) {

        node.color = Color.Red;
        node.left.color = Color.Black;
        node.right.color = Color.Black;
    }
}
