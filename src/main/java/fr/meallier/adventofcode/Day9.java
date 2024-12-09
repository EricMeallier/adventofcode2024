package fr.meallier.adventofcode;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Day9 {
    private int[] ele;
    public List<Integer> expandedModele;

    private Day9() {}

    // Builders /////////////////////////////////////
    public static Day9 buildFromTab(int[] ele) {
        Day9 day = new Day9();
        day.ele=ele;
        return day;
    }

    private static Day9 buildFromLines(String[] lines) {
        Day9 day = new Day9();
        String line = lines[0];

        day.ele=new int[line.length()];

        for (int index = 0; index < line.length(); index++) {
            day.ele[index] = Integer.parseInt(line.substring(index,index+1));
        }

        return day;
    }

    public static Day9 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day9 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public void expandModele() {
        boolean empty=false;
        int currentValue=0;
        expandedModele = new ArrayList<>();

        for (int value : ele) {
            if (empty) {
                for (int j = 0; j < value; j++) {
                    expandedModele.add(Integer.MAX_VALUE);
                }
                empty = false;
                currentValue++;
            } else {
                for (int j = 0; j < value; j++) {
                    expandedModele.add(currentValue);
                }
                empty = true;
            }
        }
    }

    public void reduceModele() {

        int writingIndex=0;
        int readingIndex=expandedModele.size()-1;

        while (writingIndex<readingIndex) {
            // Move Writing Index aka looking for '.'
            while (expandedModele.get(writingIndex) != Integer.MAX_VALUE && writingIndex<expandedModele.size())
                writingIndex++;

            // Move Reading Index aka looking for non '.'
            while (expandedModele.get(readingIndex) == Integer.MAX_VALUE && readingIndex>=0)
                readingIndex--;

            if (writingIndex<readingIndex) {
                expandedModele.set(writingIndex,expandedModele.get(readingIndex));
                expandedModele.set(readingIndex,Integer.MAX_VALUE);
                writingIndex++;
                readingIndex--;
            }
        }
    }

    public void compactModele() {

    }

    public long checkSum() {
        long cumul = 0;

        for (int i = 0; i < expandedModele.size() && expandedModele.get(i) != Integer.MAX_VALUE; i++) {
            cumul += (long) i * expandedModele.get(i);
        }
        return cumul;
    }
}
