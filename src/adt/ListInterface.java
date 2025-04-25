package adt;
import java.util.function.Predicate;
import java.util.Comparator;

public interface ListInterface<T>{

  public boolean add(T newEntry);

  public boolean add(int newPosition, T newEntry);
  
  public T remove(int givenPosition);

  public void clear();

  public boolean replace(int givenPosition, T newEntry);

  public T getEntry(int givenPosition);

  public boolean contains(T anEntry);
  
  public LinkedList<T> filter(Predicate<T> condition);
  
  public void sort(Comparator<T> comparator);

  public int getNumberOfEntries();

  public boolean isEmpty();

  public boolean isFull();
}