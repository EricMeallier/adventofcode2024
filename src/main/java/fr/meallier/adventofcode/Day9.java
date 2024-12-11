package fr.meallier.adventofcode;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Day9 {
    private int[] ele;
    public List<Integer> expandedModele;
    private int reduceTarget;
    private int reduceTargetIndex;
    private int reduceTargetLength;
    private int reduceHoleIndex;

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
        int groupIndex = expandedModele.size()-1;

        while (groupIndex>0) {
            findLastGroup(groupIndex);
            if (reduceTargetLength > 0) {
                //System.out.println("Found ele{" + reduceTarget + "} with length " + reduceTargetLength + " beginning at " + reduceTargetIndex);

                if (findFirstHole(reduceTargetLength) && reduceTargetIndex>reduceHoleIndex) {
                    //System.out.println("find a hole for the group => Move block");
                    for (int i = 0; i < reduceTargetLength; i++) {
                        expandedModele.set(reduceHoleIndex + i, reduceTarget);
                        expandedModele.set(reduceTargetIndex - i, Integer.MAX_VALUE);
                    }
                //} else {
                    //System.out.println("No hole found !");
                }
                groupIndex=reduceTargetIndex-reduceTargetLength;
                //printExpanded();
            } else {
                groupIndex--;
            }
        }
    }

    public void findLastGroup(int from) {
        int index=from;

        // looking for last digit
        while (expandedModele.get(index)==Integer.MAX_VALUE && index>1)
            index--;

        // find length of the block
        reduceTarget=expandedModele.get(index);
        reduceTargetIndex=index;
        while (expandedModele.get(index)==reduceTarget && index>1)
            index--;

        // Compute length of block
        reduceTargetLength=reduceTargetIndex-index;
    }

    public boolean findFirstHole(int length) {
        int index = 0;
        int beginIndex;

        while (index<expandedModele.size()-1) {
            // find first hole
            while (expandedModele.get(index) != Integer.MAX_VALUE && index < expandedModele.size()-1)
                index++;

            // find hole length
            beginIndex = index;
            while (expandedModele.get(index) == Integer.MAX_VALUE && index < expandedModele.size()-1)
                index++;

            // length enough ?
            if (index - beginIndex >= length) {
                reduceHoleIndex = beginIndex;
                return true;
            }
        }

        return false;
    }

    public long checkSum() {
        long cumul = 0;

        for (int i = 0; i < expandedModele.size(); i++) {
            if (expandedModele.get(i) != Integer.MAX_VALUE)
                cumul += (long) i * expandedModele.get(i);
        }
        return cumul;
    }

    public void printExpanded() {
        for (Integer value: expandedModele) {
            if (value==Integer.MAX_VALUE)
                System.out.print("#,");
            else
                System.out.print(value + ",");
        }
        System.out.println();
    }
}
