package fr.meallier.adventofcode;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Day11 {
    private List<Long> ele;

    private Day11() {}

    // Builders /////////////////////////////////////

    private static Day11 buildFromLines(String[] lines) {
        Day11 day = new Day11();
        String[] fields = lines[0].split(" ");

        day.ele = new ArrayList<>();
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

    public void blink() {
        blink(ele);
    }
    public void blink(List<Long> ele) {
        int size = ele.size();
        for (int i = 0; i < size; i++) {
            long value = ele.get(i);

            if (value == 0L)
                ele.set(i,1L);
            else {
                int digitCount = (int)Math.log10(value)+1;
                if (digitCount % 2 == 0) {
                    int exp=digitCount / 2;
                    long rate = (long)Math.pow(10,exp);
                    ele.set(i,value / rate);
                    ele.add(value % rate);
                } else {
                    ele.set(i,value * 2024);
                }
            }
        }
    }

    public void printEle() {
        for (Long value: ele) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
