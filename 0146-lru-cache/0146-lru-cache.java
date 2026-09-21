import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Initialize dummy head and tail
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // Move the accessed node to the front (most recently used)
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node != null) {
            // Key exists: update value and move to head
            node.value = value;
            moveToHead(node);
        } else {
            // Key does not exist: create and insert
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addFirst(newNode);

            // Evict the least recently used element if over capacity
            if (cache.size() > capacity) {
                Node lru = removeTail();
                cache.remove(lru.key);
            }
        }
    }

    // Helper: Remove a node from the doubly-linked list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper: Insert a node right after the dummy head
    private void addFirst(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // Helper: Move an existing node to head
    private void moveToHead(Node node) {
        removeNode(node);
        addFirst(node);
    }

    // Helper: Pop the least recently used node (before tail)
    private Node removeTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }
}