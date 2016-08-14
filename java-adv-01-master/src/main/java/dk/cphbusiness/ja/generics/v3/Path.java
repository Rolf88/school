package dk.cphbusiness.ja.generics.v3;

import static java.util.Collections.emptyIterator;
import java.util.Iterator;

public interface Path<T> extends Iterable<T> {
  static final Path EMPTY = new Path(){};

  default T getFirst() {
    throw new UnsupportedOperationException("Empty path has no first");
    }

  default Path<T> getRest() {
    throw new UnsupportedOperationException("Empty path has no rest");
    }

  default boolean isEmpty() { return true; }

  @Override
  default Iterator<T> iterator() { return emptyIterator(); }
  
  }

