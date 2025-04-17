/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * An interface
 */
public interface ListInterface<T> {
    //insert 
    void add(T element);
    
    //remove an entry
    void remove(int index);
    
    //retrieve the element at an index
    T get(int index) throws IndexOutOfBoundsException;
    
    //check if the list contains the specified elements
    boolean contains(T element);
    
    //returns number of elements in the list
    int size();
    
    //clears the list
    void clear();
    
    //checks if the list is empty
    boolean isEmpty();
}
