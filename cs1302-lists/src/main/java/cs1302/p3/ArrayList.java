package cs1302.p3;
import java.util.Arrays;

/**
 * An ArrayList that houses objects
 * 
 * @param <T> the type of the elements in this list
 * @author Noah Terrell
 */
public class ArrayList<T> implements List<T> {
    private Box<T>[] list;

    public ArrayList() {
	list = Box.<T>array(0);
    }//ArrayList

    /**
     * Extends the size of the list by 10 because the old
     * list if full
     */
    public void extendListSize() {
	list = Arrays.copyOf(list, list.length+10);
    }//extendListSize

    /**
     * Adds the specified element to the list at an implementation-specific
     * position.
     * @param elem element to be inserted
     * @throws NullPointerException if elem is null
     */
    public void add(T elem) throws NullPointerException {
	//Checks if elem is null
	if (elem == null) {
	    throw new NullPointerException("elem cannot be null");
	}//if
	//Extends the size of list if list is full
	if (list.length == size()) {
	    extendListSize();
	}//if
	//Creates a box elem
	Box<T> boxed = new Box<T>(elem);
	//Adds boxed to the end of the list
	list[size()] = boxed; 
    }//add

    /**
     * Inserts the specified element at the specified position in this list. 
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     * @param index index at which the specified element is to be inserted
     * @param elem  element to be inserted
     * @throws NullPointerException if elem is null
     * @throws IndexOutOfBoundsException if the index is out of range 
     *         {@code (index < 0 || index > size())}
     */
    public void add(int index, T elem) throws NullPointerException, IndexOutOfBoundsException {
	//Creates a box elem
	Box<T> boxed = new Box<T>(elem);
	Box<T>[] nope = Box.<T>array(list.length);

	//Checks if elem is null
	if (elem == null) {
	    throw new NullPointerException("elem cannot be null");
	}//if
	//Checks if index is out of bounds
	if ((index > size()) || (index < 0)) {
	    throw new IndexOutOfBoundsException("Index is out of bounds.");
	}//if
	if (list.length == size()) {
	    extendListSize();
	}//if
	//Checks if index user wants is the first index
	if (index == 0) {
	    nope[0] = boxed;
	    //Moves all existing elements over one index value
	    for (int i = 1; i <= size(); i++) {
		nope[i] = list[i-1];
	    }//for
	}//if
	//Checks if the index the user indicated is the last index
	else if (index == size()) {
	    nope = list;
	    nope[size()] = boxed;
	}//else if
	//Creates a new array list with an empty index for a new elements
	else {
	    System.arraycopy(list, 0, nope, 0, index);
	    System.arraycopy(list, index, nope, index+1, nope.length-index-1);
	    nope[index] = boxed;
	}//else
	list = nope;
    }//add
    
    /** 
     * Removes all of the elements from this list. The list will be empty after 
     * this call returns.
     */
    public void clear() {
	list = Box.<T>array(0);
    }//clear

    /** 
     * Compares the specified list with this list for equality. Returns 
     * <code>true</code> if and only if the specified object is also a 
     * <code>List</code>, both lists have the same size, and all corresponding 
     * pairs of elements in the two lists are equal.
     * If <code>list</code> is a list of the same list type, then students
     * may safely cast the object to the appropriat list type when implementing
     * this method.
     * @param list the object to be compared for equality with this list
     * @return <code>true</code> if the specified object is equal to this list
     */
    public boolean equals(Object list) {
	//List asked for must be of the type list
	if (!(list instanceof List)) {
	    return false;
	}//if
	List p = (List) list;
	if (p.size() != size()) {
	    return false;
	}//if
	//Checks is all elements are equal
	for (int i = 0; i < size(); i++) {
	    if (!(this.list[i].get().equals(p.get(i)))) {
		return false;
	    }//if
	}//for
	return true;
    }//equals

    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         {@code (index < 0 || index >= size())}
     */
    public T get(int index) throws IndexOutOfBoundsException {
	if ((index >= size()) || (index < 0)) {
	    throw new IndexOutOfBoundsException("Index is out of bounds");
	}//if
	return list[index].get();
    }//get

    /**
     * Replaces the element at the specified position in this list with the 
     * specified element
     * @param index index of the element to replace
     * @param elem element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws NullPointerException if the specified element is null
     * @throws IndexOutOfBoundsException if the index is out of range
     *         {@code (index < 0 || index >= size())}
     */
    public T set(int index, T elem) throws NullPointerException, IndexOutOfBoundsException {
	//Checks if elem is null
	if (elem == null) {
	    throw new NullPointerException("elem cannot be null.");
	}//if
	//Checks if index is out of bounds
	if ((index >= size()) || (index < 0)) {
	    throw new IndexOutOfBoundsException("Index is out of bounds");
	}//if
	Box<T> replace = list[index];
	Box<T> boxed = new Box<T>(elem);
	list[index] = boxed;
	return replace.get();
    }//set

    /**
     * Returns the number of elements in this list. If this list contains more 
     * than <code>Integer.MAX_VALUE</code> elements, return 
     * <code>Integer.MAX_VALUE</code>.
     * @return the number of elements in this list
     */
    public int size() {
	int counter = 0;
	//Checks how many boxes have Box elements
	for (Box<T> boxxy: list) {
	    if (boxxy != null) {
		if (boxxy.get() != null) {
		    counter++;
		}//if
	    }//if
	}//for
	if (counter > Integer.MAX_VALUE) {
	    counter = Integer.MAX_VALUE;
	}//if
	return counter;
    }//size

    /**
     * Returns true if and only if this list contains no elements.
     * @return <code>true</code> if this list contains no elements
     */ 
    public boolean isEmpty() {
	if (size() == 0) {
	    return true;
	}//if
	else {
	    return false;
	}//else
    }//isEmpty

    /**
     * Returns <code>true</code> if this list contains the specified element. 
     * More formally, returns <code>true</code> if and only if this list 
     * contains at least one element <code>o</code> such that 
     * <code>o.equals(elem)</code>.
     * @param elem element whose presence in this list is to be tested
     * @return <code>true</code> if this list contains the specified element
     * @throws NullPointerException if elem is null
     */
    public boolean contains(T elem) throws NullPointerException {
	//Checks if elem is null
	if (elem == null) {
	    throw new NullPointerException("elem cannot be null.");
	}//if
	//Checks through list if it contains the elem that the user wants found
	for (int i = 0; i < size(); i++) {
	    if (list[i].get().equals(elem)) {
		return true;
	    }//if
	}//for
	return false;
    }//contains

    /** 
     * Removes the first occurrence of the specified element from this list, if 
     * it is present. If this list does not contain the element, it is 
     * unchanged. More formally, removes the element with the lowest index 
     * <code>i</code> such that <code>elem.equals(get(i))</code> (if such an 
     * element exists). Returns <code>true</code> if this list contained the 
     * specified element (or equivalently, if this list changed as a result of 
     * the call).
     * @param elem element to be removed from this list, if present
     * @return <code>true</code> if this list contained the specified element
     * @throws NullPointerException if elem is null
     */
    public boolean remove(T elem) throws NullPointerException {
	//Checks if elem is null
	if (elem == null) {
	    throw new NullPointerException("elem cannot be null.");
	}//if
	//Checks through list
	for (int i = 0; i <size(); i++) {
	    //If elem is found, enters if statement
	    if (list[i].get().equals(elem)) {
		//Creates a list with one less space then fills it
		//with the old list minus the the value that is removed
		Box<T>[] boop = Box.<T>array(list.length-1);
		System.arraycopy(list, 0, boop, 0, i);
		System.arraycopy(list, i+1, boop, i, list.length - i - 1);
		list = boop;
		//Returns true is elem was removed
		return true;
	    }//if
	}//for
	//Returns false if elem was not removed
	return false;
    }//remove

    /**
     * Returns the index of the first occurrence of the specified element in 
     * this list, or -1 if this list does not contain the element.
     * @param elem element to search for
     * @return the index of the first occurrence of the specified element in 
     *         this list, or -1 if this list does not contain the element
     * @throws NullPointerException if elem is null
     */
    public int indexOf(T elem) throws NullPointerException {
	//Checks if elem is null
	if (elem == null) {
	    throw new NullPointerException("elem cannot be null.");
	}//if
	//Checks through list
	for (int i = 0; i < size(); i++) {
	    if (list[i].get().equals(elem)) {
		return i;
	    }//if
	}//for
	return -1;
    }//indexOf

}//ArrayList