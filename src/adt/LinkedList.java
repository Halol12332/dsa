package adt;
import java.io.Serializable;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.Iterator;

public class LinkedList<T> implements ListInterface<T>, Iterable<T>, Serializable {

    private Node firstNode; // reference to first node
    private int numberOfEntries;  	// number of entries in list

    public LinkedList() {
        clear();
    }

    @Override
    public final void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);	// create the new node

        if (isEmpty()) {
            firstNode = newNode;
        } else {                        // add to end of nonempty list
            Node currentNode = firstNode;	// traverse linked list with p pointing to the current node
            while (currentNode.next != null) { // while have not reached the last node
                currentNode = currentNode.next;
            }
            currentNode.next = newNode; // make last node reference new node
        }

        numberOfEntries++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newEntry) { // OutOfMemoryError possible
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            Node newNode = new Node(newEntry);

            if (isEmpty() || (newPosition == 1)) { // case 1: add to beginning of list
                newNode.next = firstNode;
                firstNode = newNode;
            } else {								// case 2: list is not empty and newPosition > 1
                Node nodeBefore = firstNode;
                for (int i = 1; i < newPosition - 1; ++i) {
                    nodeBefore = nodeBefore.next;		// advance nodeBefore to its next node
                }

                newNode.next = nodeBefore.next;	// make new node point to current node at newPosition
                nodeBefore.next = newNode;		// make the node before point to the new node
            }
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
    }

    @Override
    public T remove(int givenPosition) {
        if ((givenPosition < 1) || (givenPosition > numberOfEntries)) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        T result = null;
        if (givenPosition == 1) {
            result = firstNode.data;
            firstNode = firstNode.next;
        } else {
            Node nodeBefore = firstNode;
            for (int i = 1; i < givenPosition - 1; ++i) {
                nodeBefore = nodeBefore.next;
            }
            result = nodeBefore.next.data;
            nodeBefore.next = nodeBefore.next.next;
        }
        numberOfEntries--;
        return result;
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; ++i) {
                currentNode = currentNode.next;		// advance currentNode to next node
            }
            currentNode.data = newEntry;	// currentNode is pointing to the node at givenPosition
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; ++i) {
                currentNode = currentNode.next;		// advance currentNode to next node
            }
            result = currentNode.data;	// currentNode is pointing to the node at givenPosition
        }
        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return found;
    }
    
    //<------- Filter by criterias ------->
    public LinkedList<T> filter(Predicate<T> condition){
        LinkedList<T> filteredList = new LinkedList<>();
        Node currentNode = firstNode;
        while(currentNode != null){
            if(condition.test(currentNode.data)){
                filteredList.add(currentNode.data);
            }
            currentNode = currentNode.next;
        }
        return filteredList;   
    }
        //<---------- Merge Sort ---------->
    public void sort(Comparator<T> comparator){
      if(numberOfEntries > 1){
         firstNode = mergeSort(firstNode, comparator); 
      }   
    }
    
    private Node mergeSort(Node firstNode, Comparator<T> comparator){
        if (firstNode == null || firstNode.next == null){
              return firstNode;
        }
        Node middle = getMiddle(firstNode);
        Node nextOfMiddle = middle.next;
        middle.next = null;

        Node left = mergeSort(firstNode, comparator);
        Node right = mergeSort(nextOfMiddle, comparator);

        return merge(left, right, comparator);
        }

        private Node getMiddle(Node firstNode){
            if(firstNode == null){
                return firstNode;
            }
            Node slow = firstNode;
            Node fast = firstNode.next;

            while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        private Node merge(Node left, Node right, Comparator<T> comparator){
            if (left == null){
                return right;
            }

            if (right == null){
                return left;
            }

            Node result;
            if(comparator.compare(left.data, right.data) <= 0){
                result= left;
                result.next = merge(left.next, right, comparator);

            }else{
                result = right;
                result.next = merge(left, right.next, comparator);
            }   
            return result;
        }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        boolean result;
        result = numberOfEntries == 0;
        return result;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public String toString() {
        String outputStr = "";
        Node currentNode = firstNode;
        while (currentNode != null) {
            outputStr += currentNode.data + "\n";
            currentNode = currentNode.next;
        }
        return outputStr;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node nextNode;

        public LinkedListIterator() {
            nextNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                //throw new NoSuchElementException();
            }
            T data = nextNode.data;
            nextNode = nextNode.next;
            return data;
        }
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}