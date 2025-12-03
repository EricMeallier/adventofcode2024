package fr.meallier.adventofcode2025;

import java.net.URI;
import java.util.regex.Pattern;

public class Day2 {
    private String[] interval;

    private static final Pattern patternInterval = Pattern.compile(",");
    private static final Pattern patternPeriod = Pattern.compile("-");

    private Day2() {}

    // Builders /////////////////////////////////////
    public static Day2 buildFromTab(String[] ele) {
        Day2 day = new Day2();
        day.interval=ele;
        return day;
    }

    private static Day2 buildFromLines(String[] lines) {
        Day2 day = new Day2();

        day.interval = patternInterval.split(lines[0]);

        return day;
    }

    public static Day2 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day2 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    // Business /////////////////////////////////////
    public long computeSequence() {
        long counter = 0L;

        for (int i = 0; i < interval.length; i++) {
            String[] bornes = patternPeriod.split(interval[i]);

            long debut = Long.parseLong(bornes[0]);
            long fin = Long.parseLong(bornes[1]);

            for (long j = debut; j <= fin; j++) {
                if (isMirror(j))
                    counter+=j;
            }
        }
        return counter;
    }

    public long computeSequenceRepeat() {
        long counter = 0L;

        for (int i = 0; i < interval.length; i++) {
            String[] bornes = patternPeriod.split(interval[i]);

            long debut = Long.parseLong(bornes[0]);
            long fin = Long.parseLong(bornes[1]);

            for (long j = debut; j <= fin; j++) {
                if (isMirror(j) || isRepeat(j)) {
                    counter += j;
                    //System.out.println("invalid: " + j);
                }
            }
        }
        return counter;
    }
    // Utilities /////////////////////////////////////
    public static boolean isMirror(long value) {
        return isMirror(Long.toString(value));
    }
    private static boolean isMirror(String value) {
        if (value.length()%2!=0) {
            return false;
        } else {
            for (int i = 0; i < value.length()/2; i++) {
                if (value.charAt(i)!=value.charAt(i+value.length()/2))
                    return false;
            }
            return true;
        }
    }

    public static boolean isRepeat(long value) { return isRepeat(Long.toString(value));}
    private static boolean isRepeat(String value) {
        String current;
        //System.out.println("isRepeat " + value);
        // increment sur les longueurs de la chaine à répéter
        for (int i = 0; i < value.length()/2; i++) {
            //System.out.println("length: " + (i+1));
            if (value.length()%(i+1)==0) {
                current = value.substring(0, i + 1);
                String extract = null;
                for (int j = 1; j < value.length() / (i + 1); j++) {
                    extract = value.substring(j * (i + 1), j * (i + 1) + current.length());
                    //System.out.println("Compare " + current + " to " + extract);
                    if (!current.equals(extract))
                        break;
                }
                if (current.equals(extract)) {
                    //System.out.println("TRUE");
                    return true;
                }
            }
        }
        //System.out.println("FALSE");
        return false;
    }
}
