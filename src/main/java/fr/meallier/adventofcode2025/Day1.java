package fr.meallier.adventofcode2025;

import fr.meallier.adventofcode2025.Utils;

import java.net.URI;
import java.util.Arrays;

public class Day1 {
    private long[][] ele;

    private Day1() {}

    // Builders /////////////////////////////////////
    public static Day1 buildFromTab(long[][] ele, long[] result) {
        Day1 day = new Day1();
        day.ele=ele;
        return day;
    }

    private static Day1 buildFromLines(String[] lines) {
        Day1 day = new Day1();

        day.ele=new long[lines.length][];

        for (int index = 0; index < lines.length; index++) {

            char direction = lines[index].charAt(0);
            long count = Long.parseLong(lines[index].substring(1));

            day.ele[index]=new long[2];
            if (direction=='L')
                day.ele[index][0]=-1;
            else
                day.ele[index][0]=1;
            day.ele[index][1]=count;
        }
        return day;
    }

    public static Day1 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day1 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    // Business /////////////////////////////////////
    public long computeSequence() {
        long current = 50;
        int counter = 0;

        for (int i = 0; i < ele.length; i++) {
            current+=ele[i][0]*ele[i][1];
            if (current%100 == 0)
                counter++;
        }

        return counter;
    }

    public long computeSequencePassword() {
        long current = 50;
        int counter = 0;

        for (int i = 0; i < ele.length; i++) {

            for (int j = 0; j < ele[i][1]; j++) {
                current+=ele[i][0];
                current=current%100;
                if (current == 0)
                    counter++;
            }

//            boolean currentNull = (current == 0);
//
//            current+=ele[i][0]*ele[i][1];
//
//            // Pour les négatifs, on ramene en positif
//            if (current<0L) {
//                current += 100 * Math.abs(current / 100) + 100;
//                if (!currentNull)
//                    counter++;
//            }
//
//            // Pour les positifs, on compte les tours, sauf dans la cas multiple pile
//            counter += Math.abs((current) / 100);
//            if (current%100 == 0 && Math.abs((current) / 100) >= 1) {
//                counter--;
//            }
//
//            current=current%100;
//            if (current == 0)
//                counter++;

            System.out.println("[" + i + "]- " + String.valueOf(current) + ": " + String.valueOf(ele[i][0]) + '*' + String.valueOf(ele[i][1]) + ": " + counter);
        }

        return counter;
    }


    // Utilities /////////////////////////////////////

}
