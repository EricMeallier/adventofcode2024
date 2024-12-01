package fr.meallier.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Day1 {

    public Day1(int[] list1, int[] list2) {
        this.list1 = list1;
        this.list2 = list2;
    }

    public Day1() {
    }


    private int[] list1;
    private int[] list2;

    public void readFromFile(String filepath) throws IOException {
        String[] lines = Files.lines(Paths.get(filepath)).toArray(String[]::new);

        list1 = new int[lines.length];
        list2 = new int[lines.length];

        for (int i = 0; i < lines.length; i++) {
            StringTokenizer st = new StringTokenizer(lines[i]);

            if (st.countTokens()==2) {
                list1[i] = Integer.parseInt(st.nextToken());
                list2[i] = Integer.parseInt(st.nextToken());
            }
        }
    }

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

    private int inList2NumberOf(int v) {
        int result = 0;

        for (int j : list2) {
            if (v == j)
                result++;
        }
        return  result;
    }
}
