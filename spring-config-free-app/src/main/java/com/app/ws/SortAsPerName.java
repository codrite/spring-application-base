package com.app.ws;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by cheebu on 7/14/2014.
 */
public class SortAsPerName implements Comparable<String> {

private String name;

public SortAsPerName(String name) {
        this.name = name;
        }

@Override
public int compareTo(String o) {
        return this.compareTo(o);
        }

public String getName() {
        return name;
        }

public static void main(String[] args) {
        List<String> UNSORTED_STRING_LIST = Arrays.asList(new String[]{"a", "z", "c", "b1", "a1"});
        Collections.sort(UNSORTED_STRING_LIST);
        System.out.println(UNSORTED_STRING_LIST);
        }

        }
