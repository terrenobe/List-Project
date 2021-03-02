package cs1302.p3;
import java.util.Arrays;

/**
 * SortedArrayList that houses sorted objects
 * 
 * @param <T> the type of element in this array
 * @author Noah Terrell
 */
public class SortedArrayList<T extends Comparable<T>> extends ArrayList<T> {

    private Box<T>[] list;

    public SortedArrayList() {
	list = Box.<T>array(0);
    }//SortedArrayList

    /**
     * Extends the size of the list by 10 because the old
     * list is full
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
	//Creates new box element
	Box<T> boxed = new Box<T>(elem);
	//Captures size of the list
	int putInEnd = size();
	//Checks if elem is null
	if (elem == null) {
	    throw new NullPointerException("Cannot be null.");
	}//if
	//Checks if list size needs to be extended due to it being full
	if (list.length == size()) {
	    extendListSize();
	}//if
	//Checks through list to find where to add the elem
        for (int i = 0; i < size(); i++) {
	    if (list[i].get().compareTo(elem) >= 0) {
		putInEnd = i;
		break;
	    }//if
	}//for
	//Moves values over one
	for (int i = size()-1; i >= putInEnd; i--) {
	    list[i+1] = list[i];
	}//for
	list[putInEnd] = boxed;
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
	Box<T> boxed = new Box<T>(elem);
	int putInEnd = size();
	//Checks if elem is null
	if (elem == null) {
	    throw new NullPointerException("Cannot be null.");
	}//if
	//Checks if index is out of bounds
	if ((index > size()) || (index < 0)) {
	    throw new IndexOutOfBoundsException("Index is out of bounds.");
	}//if
	//Checks if list size needs to be extended due to list being full
	if (list.length == size()) {
	    extendListSize();
	}//if
	//Checks through list to find where to add the elem
	for (int i = 0; i < size(); i++) {
	    if (list[i].get().compareTo(elem) >= 0) {
		putInEnd = i;
		break;
	    }//if
	}//for
	//Moves values down one
	for (int i = size()-1; i >=putInEnd; i--) {
	    list[i+1] = list[i];
	}//for
	list[putInEnd] = boxed;
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
	//Checks if list if of the type list
	if (!(list instanceof List)) {
	    return false;
	}//if
	List p = (List) list;
	if (p.size() != size()) {
	    return false;
	}//if
	//Checks if both lists have the same values
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
	//Checks if index is out of bounds
	if ((index >= size()) || (index < 0)) {
	    throw new IndexOutOfBoundsException("Index is out of bounds.");
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
	    throw new NullPointerException("Cannot be null.");
	}//if
	//Checks if index is out of bounds
	if ((index >= size()) || (index < 0)) {
	    throw new IndexOutOfBoundsException("Index is out of bounds.");
	}//if
	//Puts elem that will be replaced away
	Box<T> replace = list[index];
	//Creates a new list with the same length as the old list
	Box<T> boxed = new Box<T>(elem);
	Box<T>[] temp = Box.<T>array(list.length);
	//Populates temp without elem put in replace
	System.arraycopy(list, 0, boxed, 0, index);
	System.arraycopy(list, index, boxed, index, list.length-index-1);
	list = temp;
	//Adds new elem
	add(elem);
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
	//Counts number of indexes that have values
	for (Box<T> boxes: list) {
	    if (boxes != null) {
		counter++;
	    }//if
	}//for
	if(counter > Integer.MAX_VALUE) {
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
	    throw new NullPointerException("Cannot be null.");
	}//if
	//Searches for elem user wants found, returns truw if it did.
	//False if it did not
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
	    throw new NullPointerException("Cannot be null.");
	}//if
	for (int i = 0; i < size(); i++) {
	    if (list[i].get().equals(elem)) {
		//Makes a new list minus one index
		Box<T>[] boop = Box.<T>array(list.length-1);
		//Copies old list without the removed element
		System.arraycopy(list, 0, boop, 0, i);
		System.arraycopy(list, i+1, boop, i, list.length-i-1);
		list = boop;
		return true;
	    }//if
	}//for
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
	    throw new NullPointerException("Cannot be null.");
	}//if
	//Returns the index of a value the user wants
	for (int i = 0; i < size(); i++) {
	    if (list[i].get().equals(elem)) {
		return i;
	    }//if
	}//for
	return -1;
    }//index

}//SortedArrayList