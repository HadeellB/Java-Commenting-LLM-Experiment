package DataStructures.dynamicArrays;

// Stores objects in a backing array that expands and contracts as elements change.
public class DynamicArray {
    private int size; // Number of stored elements.
    private int capacity = 10; // Number of available slots in the backing array.
    private Object[] array;

    public DynamicArray() {
        this.array = new Object[capacity];
    }

    public DynamicArray(int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
    }

    // Appends an element, expanding the backing array when it is full.
    public void add(Object data) {

        if(size >= capacity) {
            grow();
        }

        array[size] = data;
        size++;
    }

    public void insert(int index, Object data) {

        if(size >= capacity) {
            grow();
        }

        // Shift elements right to make room at the specified index.
        for(int i=size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = data;
        size++;

    }

    public void delete(Object data) {

        for(int i=0; i<size; i++) {
            // Compare object references rather than values using equals().
            if (array[i] == data) {
                // Intended to shift subsequent elements left.
                // Note: this loop increments i instead of j, which can leave
                // elements incorrectly shifted when multiple elements follow.
                for (int j=0; j < (size - i - 1); i++) {
                    array[i + j] = array[i + j + 1];
                }
                // Clear the last occupied slot and reduce the element count.
                array[size - 1] = null;
                size--;

                // Reduce capacity when at most one third of the slots are occupied.
                if (size <= (int) (capacity/3)) {
                    shrink();
                }

                // Stop after processing the first matching reference.
                break;
            }
        }
    }

    // Return the first index containing the same object reference, or -1 if absent.
    public int search(Object data) {

        for(int i=0; i<size; i++) {
            if(array[i] == data) {
                return i;
            }
        }

        return -1;
    }

    // Double the backing array's capacity and copy the stored elements.
    private void grow() {

        int newCapacity = capacity * 2;
        Object[] newArray = new Object[newCapacity];

        for(int i=0; i<size; i++) {
            newArray[i] = array[i];
        }

        capacity = newCapacity;
        array = newArray;

    }

    // Halve the backing array's capacity and copy the stored elements.
    private void shrink() {

        int newCapacity = (int) (capacity / 2);
        Object[] newArray = new Object[newCapacity];

        for(int i=0; i<size; i++) {
            newArray[i] = array[i];
        }

        capacity = newCapacity;
        array = newArray;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {

        // Include only stored elements, excluding unused array slots.
        StringBuilder str = new StringBuilder();
        for(int i=0; i < size; i++) {
            str.append(array[i]).append(", ");
        }

        // Remove the trailing separator and enclose the contents in brackets.
        if (!str.isEmpty()) {
            str = new StringBuilder("[" + str.substring(0, str.length() - 2) + "]");
        } else {
            str = new StringBuilder("[]");
        }

        return str.toString();
    }
}