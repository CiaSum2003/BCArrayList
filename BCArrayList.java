import java.util.Arrays;

interface List<E>{
    void add(int index, E item);
    // adds item to certain index, shifting items following it to the right
    E removeAt(int index);
    // removes an item at a certain index, shifting items preceding it to the left 
    E get (int index);
    // return item at certain index
    int indexOf(E item);
    // returns index of first occurrence of item, if false return -1
    boolean is_Empty();
    // returns true if the list has no elements
    E set(int index, E item);
    // replaces item at index with inputted item, returns the previous item
    int size();
    // returns the size of the list
    void output();
    // returns the result of the operation with a trailing newline
}

public class BCArrayList<E> implements List<E>{
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity; // the amount of elements a List can currently hold
    private int size; // the current number of elements in the List
    private E[] elements; // the list

    public BCArrayList(){// initializes a new instance of the BCArrayList class with the default capacity
        this(DEFAULT_CAPACITY);
    }
    public BCArrayList(int capacity){// creates the elements list while passing the fixed capacity as an integer 
        this.capacity = capacity;
        this.elements = (E[]) new Object[capacity];
        this.size = 0;
    }
    @Override
    public void add(int index, E item){// adds the parameter item at the parameter index, shifts other items accordingly
        if (size == capacity){
           make_space();
        }
        for (int i = size - 1; i >= index; i--) {
              elements[i + 1] = elements[i];
          }
           elements[index] = item;
           size++;
 }
    public E removeAt(int index){ // removes the item at the parameter index
        E removed = elements[index];
        for (int i = index; i < size-1; i++){
            elements[i] = elements[i+1];
        }
        elements[size - 1] = null;
        size -= 1;
        return removed;
    }    
    public E get(int index){return elements[index];}
    public int indexOf(E item) {
        if (size == 0){
            return -1;
        }
        E[] copy_of = Arrays.copyOf(elements, size);
        Arrays.sort(copy_of, 0, size);
        return Arrays.binarySearch(copy_of, 0, size, item);
    }
    public boolean is_Empty(){if (size == 0){return true;} return false;}

    public int size(){return size;}
    
    public E set(int index, E item){ // replaces item in list, returns orignal value
        E temp = elements[index];
        elements[index] = item;
        return temp;
    }
    
    private void make_space(){ // if the current size of the area equals the capacity, increase the capacity
        capacity *=2;
        @SuppressWarnings("unchecked")
        E[] newElements = (E[]) new Object[capacity];
         for (int i=0; i < size; i++) {
            newElements[i] = elements[i];
    }
        elements = newElements;
    }
    public void output(){ //capacity prints the list
         for (int i = 0; i < capacity ; i++){
            if (elements[i]!= null){
            System.out.print(elements[i] +" ");
         }
        }
         System.out.println();
    }
    public static void main(String[] args) {
        java.util.Scanner myScanner = new java.util.Scanner(System.in);
        List<Integer> myList = new BCArrayList<>();
        boolean done = false;
        while (!done) {
            String operation = myScanner.next();
            if (operation.equals("add")) {
                myList.add(myScanner.nextInt(), myScanner.nextInt());
            }
            else if (operation.equals("get")) {
                System.out.println(myList.get(myScanner.nextInt()));
            }
            else if (operation.equals("removeAt")) {
                System.out.println(myList.removeAt(myScanner.nextInt()));
            }
            else if (operation.equals("indexOf")) {
                System.out.println(myList.indexOf(myScanner.nextInt()));
            }
            else if (operation.equals("output")) {
                myList.output();
            }
            else {
                done = true;
                myScanner.close();
            }
        }
    }
}