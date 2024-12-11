package fr.meallier.adventofcode;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Day11 {
    private HugeList ele;

    private Day11() {}

    // Builders /////////////////////////////////////

    private static Day11 buildFromLines(String[] lines) {
        Day11 day = new Day11();
        String[] fields = lines[0].split(" ");

        day.ele = new HugeList();
        for (String field: fields) {
            day.ele.add(Long.parseLong(field));
        }

        return day;
    }

    public static Day11 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day11 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    // Business /////////////////////////////////////
    public long length() {
        return ele.size();
    }

    public void blink(int count) {
        long size = ele.size();

        try {
            System.out.println("Blink(" + count + "):" + size + "/" + ele.storage.size());
            for (long i = 0; i < size; i++) {
                long value = ele.get(i);

                if (value == 0L)
                    ele.set(i, 1L);
                else {
                    int digitCount = (int) Math.log10(value) + 1;
                    if (digitCount % 2 == 0) {
                        int exp = digitCount / 2;
                        long rate = (long) Math.pow(10, exp);
                        ele.set(i, value / rate);
                        ele.add(value % rate);
                    } else {
                        ele.set(i, value * 2024);
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Size: " + size + ": " + e.getMessage());
            throw e;
        }
    }

    public void printEle() {
        for (long i = 0; i < ele.size(); i++) {
            System.out.print(ele.get(i) + " ");
        }
        System.out.println();
    }
}

class HugeList {
    private final static int SPAN = 100_000;
    public final List<List<Long>> storage;
    private long currentSize;

    public HugeList() {
        storage = new ArrayList<>();
        currentSize = 0;
    }

    public void add(long value) {
        set(currentSize++,value);
    }

    public void set(long index, long value) {
        int spanNumber = (int)index / SPAN;
        int subIndex = (int)index % SPAN;

        try {
            if (storage.size() <= spanNumber) {
                storage.add(new ArrayList<>());
            }

            if (storage.get(spanNumber).size() <= subIndex)
                storage.get(spanNumber).add(value);
            else
                storage.get(spanNumber).set(subIndex,value);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Set: " + index + ", " + value + ": " + e.getMessage());
            System.out.println("span: " + spanNumber + ", subIndex: " + subIndex);
            throw e;
        }
    }

    public long get(long index) {
        int spanNumber = (int)index / SPAN;
        int subIndex = (int)index % SPAN;

        try {
            return storage.get(spanNumber).get(subIndex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Set: " + index + ": " + e.getMessage());
            throw e;
        }
    }

    public long size() {
        return currentSize;
    }
}
