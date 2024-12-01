package fr.meallier.adventofcode;

import java.util.Arrays;

public class Day1 {


    public int findTotalDistance(int[] l1, int[] l2) {
        int result = 0;

        // Sort the two arrays
        Arrays.sort(l1);
        Arrays.sort(l2);

        // Loop each rows, adding
        for (int i = 0; i < l1.length; i++) {
            result+=Math.abs(l1[i]-l2[i]);
        }
        return result;
    }
}
