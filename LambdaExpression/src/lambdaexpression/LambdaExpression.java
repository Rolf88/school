/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lambdaexpression;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author RolfMoikjær
 */
public class LambdaExpression {

    private static <T> String concatenate(Comparator<T> comparator,
            CharSequence delimiter,
            Predicate<T> predicater,
            Function<T, String> converter,
            Iterable<T> collection) {
        String text = "";
        List<T> col = new ArrayList();

        for (T item : collection) {
            col.add(item);
        }

        for (int i = 0; i < col.size(); i++) {
            T item = col.get(i);
            T nextItem = i + 1 < col.size() ? col.get(i + 1) : null;

            if (!predicater.test(item)) {
                continue;
            }

            if (comparator.compare(item, nextItem) == 0) {
                delimiter = "=";
            } else if (comparator.compare(item, nextItem) == 1) {
                delimiter = ">";
            } else if (comparator.compare(item, nextItem) == 2) {
                delimiter = "";
            } else {
                delimiter = "<";
            }

            text += converter.apply(item) + delimiter;

        }
        return text;
    }

    private static <T> String concatenate(CharSequence delimiter, Predicate<T> predicater, Function<T, String> converter, Iterable<T> collection) {

        String text = null;

        for (T item : collection) {
            if (!predicater.test(item)) {
                continue;
            }
            if (text == null) {
                text = converter.apply(item);
            } else {
                text += delimiter + converter.apply(item);
            }
        }
        return text;
    }

    public static void main(String[] args) {
        List<Integer> col = new ArrayList<>();
        col.add(1);
        col.add(2);
        col.add(4);
        col.add(4);
        col.add(5);
        col.add(9);
        col.add(7);
        col.add(8);

        //System.out.println(concatenate(" , ", x -> x != 2, x -> "'" + x + "'", col));
        System.out.println(concatenate((p, q) -> {
            if (q == null) {
                return 2;
            } else if (p.equals(q)) {
                return 0;
            } else if (p > q) {
                return 1;
            } else {
                return -1;
            }
        }, " , ", x -> x != 102, x -> "'" + x + "'", col
        ));

    }

}
