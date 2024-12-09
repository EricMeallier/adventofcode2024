package fr.meallier.adventofcode;

import java.net.URI;
import java.util.Arrays;

public class Day7 {
    private long[][] ele;
    private long[] result;

    private Day7() {}

    // Builders /////////////////////////////////////
    public static Day7 buildFromTab(long[][] ele, long[] result) {
        Day7 day = new Day7();
        day.result=result;
        day.ele=ele;
        return day;
    }

    private static Day7 buildFromLines(String[] lines) {
        Day7 day = new Day7();

        day.ele=new long[lines.length][];
        day.result=new long[lines.length];

        for (int index = 0; index < lines.length; index++) {

            String[] fields = lines[index].split("[: ]+");

            day.result[index]= Long.parseLong(fields[0]);
            day.ele[index] = new long[fields.length-1];
            for (int i = 0; i <fields.length-1; i++) {
                day.ele[index][i] = Long.parseLong(fields[i+1]);
            }
        }
        return day;
    }

    public static Day7 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day7 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    // Business /////////////////////////////////////
    public long computeExisting() {
        long cumul = 0;

        for (int i = 0; i < result.length; i++) {
            if (findCombinaison(result[i],ele[i]))
                cumul+=result[i];
        }

        return cumul;
    }
    public long computeExistingV2() {
        long cumul = 0;

        for (int i = 0; i < result.length; i++) {
            if (findCombinaisonV2(result[i],ele[i]))
                cumul+=result[i];
        }

        return cumul;
    }
    public static boolean findCombinaison(long result, long[]ele) {
        if (ele.length<1) // must not happen
            return false;
        if (ele.length==1)
            return (result==ele[0]);

        // Add
        long[] inter = Arrays.copyOfRange(ele,1,ele.length);
        inter[0]=ele[0]+ele[1];
        if (findCombinaison(result, inter)) {
            return true;
        }

        // Mul
        inter[0]=ele[0]*ele[1];
        return findCombinaison(result, inter);
    }

    public static boolean findCombinaisonV2(long result, long[]ele) {
        if (ele.length<1) // must not happen
            return false;
        if (ele.length==1)
            return (result==ele[0]);

        // Add
        long[] inter = Arrays.copyOfRange(ele,1,ele.length);
        inter[0]=ele[0]+ele[1];
        if (findCombinaisonV2(result, inter)) {
            return true;
        }

        // Mul
        inter[0]=ele[0]*ele[1];
        if (findCombinaisonV2(result, inter)) {
            return true;
        }

        // Concat
        inter[0]=Long.parseLong(ele[0] + "" + ele[1]);
        return findCombinaisonV2(result, inter);
    }


    // Utilities /////////////////////////////////////

}
