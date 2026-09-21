class MyLinkedList {

    private static class Node {
        int val;
        Node prev;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    private final Node head;
    private final Node tail;
    private int size;

    public MyLinkedList() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        Node curr;
        // Optimize search from the closer end
        if (index < size / 2) {
            curr = head.next;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else {
            curr = tail.prev;
            for (int i = 0; i < size - 1 - index; i++) {
                curr = curr.prev;
            }
        }

        return curr.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }

        // Find the node currently at 'index' (the new node will precede it)
        Node succ;
        if (index < size / 2) {
            succ = head.next;
            for (int i = 0; i < index; i++) {
                succ = succ.next;
            }
        } else {
            succ = tail;
            for (int i = 0; i < size - index; i++) {
                succ = succ.prev;
            }
        }

        Node pred = succ.prev;
        Node toAdd = new Node(val);

        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;

        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        // Locate the target node
        Node target;
        if (index < size / 2) {
            target = head.next;
            for (int i = 0; i < index; i++) {
                target = target.next;
            }
        } else {
            target = tail.prev;
            for (int i = 0; i < size - 1 - index; i++) {
                target = target.prev;
            }
        }

        Node pred = target.prev;
        Node succ = target.next;

        pred.next = succ;
        succ.prev = pred;

        size--;
    }
}
