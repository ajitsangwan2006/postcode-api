package com.power.springbootapi.common;

import java.util.Comparator;

public class NamesComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        int cmp = s1.length() - s2.length();
        if (cmp == 0) {
            cmp = s1.compareTo(s2);
        }
        return cmp;
    }
}
