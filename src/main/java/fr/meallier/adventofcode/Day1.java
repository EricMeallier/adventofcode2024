package fr.meallier.adventofcode;

import java.net.URI;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Day1 {

    private int[] list1;
    private int[] list2;

    private Day1() {
    }

    // Builders /////////////////////////////////////
    public static Day1 buildFromArray(int[] list1, int[] list2) {
        Day1 day1 = new Day1();
        day1.list1=list1;
        day1.list2=list2;
        return day1;
    }

    public static Day1 buildFromLines(String[] lines) {
        Day1 day1 = new Day1();

        day1.list1 = new int[lines.length];
        day1.list2 = new int[lines.length];

        for (int i = 0; i < lines.length; i++) {
            StringTokenizer st = new StringTokenizer(lines[i]);

            if (st.countTokens()==2) {
                day1.list1[i] = Integer.parseInt(st.nextToken());
                day1.list2[i] = Integer.parseInt(st.nextToken());
            }
        }
        return day1;
    }

    public static Day1 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day1 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    // Business /////////////////////////////////////
    public int findTotalDistance() {
        int result = 0;

        // Sort the two arrays
        Arrays.sort(list1);
        Arrays.sort(list2);

        // Loop each rows, adding
        for (int i = 0; i < list1.length; i++) {
            result+=Math.abs(list1[i]-list2[i]);
        }
        return result;
    }

    public int findSimilarity() {
        int result = 0;
        for (int j : list1) {
            result += j * inList2NumberOf(j);
        }

        return  result;
    }

    // Utilities /////////////////////////////////////
    private int inList2NumberOf(int v) {
        int result = 0;

        for (int j : list2) {
            if (v == j)
                result++;
        }
        return  result;
    }
}
