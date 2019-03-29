package sedgewick.basic.ds.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CircularLinkedList<T> implements Iterable<T> {
    public static class Node<T> {
        T value;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = (next == null) ? this : next;
        }

        Node(T value) {
            this(value, null);
        }
    }

    private Node<T> last = null;

    public boolean isEmpty() {
        return this.last == null;
    }

    public int size() {
        if(isEmpty()) return 0;
        int numNodes = 1;
        for (Node<T> node = this.last; node.next != this.last; node = node.next) {
            ++numNodes;
        }
        return numNodes;
    }

    public void add(final T value) {
        // special handling for empty list
        if(this.last == null) {
            this.last = new Node<>(value);
            return;
        }

        this.last = this.last.next = new Node<>(value, this.last.next);
    }

    public boolean insertAfter(final T after, final T value) {
        Node<T> node = findNode(after);
        if(node != null) {
            node.next = new Node<>(value, node.next);
            // handling for tail
            if(this.last == node)
                this.last = node.next;
            return true;
        }
        return false;
    }

    public void remove(final T value) {
        if(this.last != null) {
            Node<T> node = this.last;
            do {
                if(node.next.value.equals(value)) {
                    removeAfter(node);
                }
                node = node.next;
            } while((this.last != null) && (node != this.last));
        }
    }

    public Node<T> removeAfter(final Node<T> after) {
        Objects.requireNonNull(after);
        Objects.requireNonNull(after.next);

        Node<T> deletedNode = after.next;

        // special handling when the last node needs to be deleted. Updates last node as well
        if(this.last == deletedNode) {
            // handling for a single element circular list
            if(this.last.next == this.last) {
                this.last = null;
            } else {
                this.last = deletedNode.next;
            }
        }

        if(this.last != null) {
            after.next = deletedNode.next;
        }

        deletedNode.next = null;    // orphan the node to be deleted
        return deletedNode;
    }

    public Node<T> findNode(final T value) {
        for(Node<T> current = this.last; (current != null) && (current != current.next); current = current.next) {
            if(current.value.equals(value)) {
                return current;
            }
        }
        return null;
    }


    public boolean find(final T value) {
        return findNode(value) != null;
    }

    @Override
    public Iterator<T> iterator() {
        final CircularLinkedListIterator iterator = new CircularLinkedListIterator();
        return iterator;
    }

    private class CircularLinkedListIterator implements Iterator<T> {
        private Node<T> current = last;
        private Node<T> previous;

        public CircularLinkedListIterator() {
            if(hasNext()) {
                this.previous = this.current;
                while(this.previous.next != this.current) {
                    this.previous = this.previous.next;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            final T item = this.current.value;
            this.previous = this.current;
            this.current = this.current.next;
            return item;
        }

        @Override
        public void remove() {
            if(!hasNext())
                throw new NoSuchElementException();

            removeAfter(this.previous);
            if(hasNext())
                this.current = this.previous.next;
            else
                this.current = this.previous = null;
        }
    }
}
