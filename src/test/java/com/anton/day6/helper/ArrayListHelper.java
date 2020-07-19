package com.anton.day6.helper;

import java.util.ArrayList;
import java.util.Comparator;

public class ArrayListHelper<T> {
    public boolean isSorted(ArrayList<T> arr, Comparator<T> comparator) {
        boolean flag = false;
        if (arr != null && comparator != null) {
            flag = true;
            for (int i = 0; i < arr.size() - 1; i++) {
                if (comparator.compare(arr.get(i), arr.get(i + 1)) > 0) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
}
