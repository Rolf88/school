package dk.cphbusiness.ja.generics.v3;

import java.util.Iterator;

public class LinkedPath<T> implements Path<T> {
  private final T first;
  private final Path<T> rest;
  
  // <editor-fold desc="LinkedPath operations ">

  public LinkedPath(T first) {
    this.first = first;
    this.rest = EMPTY;
    }

  public LinkedPath(T first, Path<T> rest) {
    this.first = first;
    this.rest = rest;
    }

  public LinkedPath(T first, T... steps) {
    Path<T> rest = EMPTY;
    for (int index = steps.length - 1; index >= 0; index--) {
      rest = new LinkedPath<>(steps[index], rest);
      }
    this.first = first;
    this.rest = rest;
    }
  
  public static <T> Path<T> create(Iterable<T> steps) {
    return create(steps.iterator());
    }
  
  private static <T> Path<T> create(Iterator<T> iterator) {
    if (!iterator.hasNext()) return Path.EMPTY;
    return new LinkedPath<>(iterator.next(), create(iterator));
    }
  
  @Override
  public T getFirst() { return first; }

  @Override
  public Path<T> getRest() { return rest; }

  @Override
  public boolean isEmpty() { return false; }
  
  // </editor-fold>
  
  @Override
  public Iterator<T> iterator() { return new PathIterator<>(this); }

  private class PathIterator<T> implements Iterator<T> {
    private Path<T> path;

    public PathIterator(Path<T> path) {
      this.path = path;
      }
    
    @Override
    public boolean hasNext() { return !path.isEmpty(); }

    @Override
    public T next() {
      T step = path.getFirst();
      path = path.getRest();
      return step;
      }
    
    }
  
  }



  
