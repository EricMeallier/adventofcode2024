package fr.meallier.adventofcode;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Day2 {
    private int[][] list;

    private Day2() {}

    // Builders /////////////////////////////////////
    public static Day2 buildFromArray(int[][] list) {
        Day2 day2 = new Day2();
        day2.list=list;
        return day2;
    }

    private static Day2 buildFromLines(String[] lines) {
        Day2 day2 = new Day2();

        day2.list = new int[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            StringTokenizer st = new StringTokenizer(lines[i]);
            int levelCount = st.countTokens();
            day2.list[i] = new int[levelCount];

            for (int j = 0; j < levelCount; j++) {
                day2.list[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        return day2;
    }

    public static Day2 buildFromFile(URI filepath) throws IOException {
        return buildFromLines(Files.lines(Paths.get(filepath)).toArray(String[]::new));
    }

    public static Day2 buildFromFile(String filepath) throws IOException {
        return buildFromLines(Files.lines(Paths.get(filepath)).toArray(String[]::new));
    }

    // Business /////////////////////////////////////
    public static boolean safeness(int[] levels) {
        if (levels.length<2)
            return false;
        boolean increasing=(levels[1]>levels[0]);

        for (int i = 1; i < levels.length; i++) {
            if (levels[i] == levels[i-1])
                return false;
            if (levels[i] > levels[i-1] && !increasing)
                return false;
            if (levels[i] < levels[i-1] && increasing)
                return false;
            if (Math.abs(levels[i]-levels[i-1])>3)
                return false;
        }
        return true;
    }

    public static boolean safenessV2(int[] levels) {
        if (safeness(levels))
            return true;

        for (int i = 0; i < levels.length; i++) {
            if (safeness(removeElementFromArray(levels,i)))
                return true;
        }
        return false;
    }

    public int countSafeness() {
        int result = 0;

        for (int[] levels : list) {
            if (safeness(levels))
                result++;
        }

        return result;
    }

    public int countSafenessV2() {
        int result = 0;

        for (int[] levels : list) {
            if (safenessV2(levels))
                result++;
        }

        return result;
    }

    // Utilities /////////////////////////////////////
    static public int[] removeElementFromArray(int[] list, int indexToRemove) {
        int[] result = new int[list.length-1];
        int shifting = 0;
        for (int i = 0; i < list.length; i++) {
            if (i==indexToRemove) {
                shifting++;
            } else {
                result[i-shifting]=list[i];
            }
        }
        return result;
    }
}
