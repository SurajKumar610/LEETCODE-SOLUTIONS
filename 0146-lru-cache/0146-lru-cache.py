class Node:
    def __init__(self, key: int = 0, val: int = 0):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None


class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache = {}  # key -> Node

        # Dummy head and tail to simplify boundary conditions
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head

    def _remove(self, node: Node) -> None:
        """Remove an existing node from the linked list."""
        node.prev.next = node.next
        node.next.prev = node.prev

    def _add_first(self, node: Node) -> None:
        """Insert a node right after the dummy head (mark as most recently used)."""
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node

    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1

        node = self.cache[key]
        self._remove(node)
        self._add_first(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            node = self.cache[key]
            node.val = value
            self._remove(node)
            self._add_first(node)
        else:
            new_node = Node(key, value)
            self.cache[key] = new_node
            self._add_first(new_node)

            if len(self.cache) > self.capacity:
                # Evict the least recently used node (node right before tail)
                lru = self.tail.prev
                self._remove(lru)
                del self.cache[lru.key]