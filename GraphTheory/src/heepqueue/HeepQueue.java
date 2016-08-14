/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heepqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author RolfMoikjær
 */
public class HeepQueue<E> {

    private Comparator<E> comp;
    private ArrayList<E> array;
    private Map<E, Integer> map;

    public HeepQueue(Comparator<E> comp) {
        this.comp = comp;
        this.array = new ArrayList();
        this.map = new HashMap();
    }

    public int size() {
        return array.size();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    public boolean contains(E element) {
        return map.containsKey(element);
    }

    public void add(E element) {
        int index = array.size();
        array.add(element);
        map.put(element, index);
        heapifyUp(index);
    }

    public void update(E element) {
        Integer index = map.get(element);
        if (index == null) {
            throw new RuntimeException("Element is not in the map");
        }
        heapifyUp(index);
        heapifyDown(index);
    }

    public E poll() {
        if (array.isEmpty()) {
            return null;
        }

        E res = array.get(0);

        if (array.size() == 1) {
            array.clear();;
            map.clear();
            return res;
        }

        map.remove(res);
        E last = array.remove(array.size() - 1);
        array.set(0, last);
        map.put(last, 0);
        heapifyDown(0);
        return res;
    }

    private void heapifyUp(int i) {
        int parentIndex = parent(i);

        if (parentIndex < 0) {
            return;
        }
        E self = array.get(i);
        E parent = array.get(parentIndex);
        if (comp.compare(self, parent) < 0) {
            swap(i, parentIndex);
            heapifyUp(parentIndex);
        }

    }

    private void heapifyDown(int i) {
        int left = leftChild(i);
        if (left < 0) {
            return;
        }
        int minIndex = left;
        E minValue = array.get(minIndex);

        int rightIndex = rightChild(i);
        if (rightIndex >= 0) {
            E rightValue = array.get(rightIndex);
            if (comp.compare(rightValue, minValue) < 0) {
                minIndex = rightIndex;
                minValue = rightValue;
            }
        }
        E self = array.get(i);
        if (comp.compare(minValue, self) < 0) {
            swap(i, minIndex);
            heapifyDown(minIndex);
        }
    }

    private void swap(int a, int b) {
        E temp = array.get(a);
        array.set(a, array.get(b));
        array.set(b, temp);
        map.replace(array.get(a), a);
        map.replace(array.get(b), b);
    }

    private int leftChild(int i) {
        int res = i * 2 + 1;
        return res < array.size() ? res : -1;
    }

    private int rightChild(int i) {
        int res = i * 2 + 2;
        return res < array.size() ? res : -1;
    }

    private int parent(int i) {
        return i > 0 ? (i - 1) / 2 : -1;
    }
}
