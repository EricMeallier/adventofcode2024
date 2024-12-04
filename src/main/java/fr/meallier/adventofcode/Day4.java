package fr.meallier.adventofcode;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    private char[][] tab;
    private int size;
    private static final Pattern patternXmas = Pattern.compile("XMAS");
    private static final Pattern patternSamx = Pattern.compile("SAMX");

    private Day4() {}

    // Builders /////////////////////////////////////
    public static Day4 buildFromTab(char[][] tab) {
        Day4 day4 = new Day4();
        day4.tab=tab;
        day4.size=tab.length;
        return day4;
    }

    private static Day4 buildFromLines(String[] lines) {
        Day4 day4 = new Day4();
        day4.tab=new char[lines.length][];
        day4.size=lines.length;

        for (int i = 0; i < lines.length; i++) {
            day4.tab[i]=lines[i].toCharArray();
        }
        return day4;
    }

    public static Day4 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day4 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    // Business /////////////////////////////////////
    public int findXmasamX() {
        String[] data = buildAllCombinaison();
        int count = 0;

        for (String str: data) {
            count+=countPattern(str);
        }

        return count;
    }

    public String[] buildAllCombinaison() {

        List<String> result = new ArrayList<>();

        // Horizontal
        for (int i = 0; i < size; i++) {
            result.add(new String(tab[i]));
        }

        // Vertical
        char[] tempo = new char[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tempo[j] = tab[j][i];
            }
            result.add(new String(tempo));
        }

        // First diagonal
        for (int i = 0; i < size; i++) {
            tempo[i]=tab[i][i];
        }
        result.add(new String(tempo));

        // Second diagonal
        for (int i = 0; i < size; i++) {
            tempo[i]=tab[i][size-i-1];
        }
        result.add(new String(tempo));

        // Triangle left low AND right high
        for (int i = 1; i < size-3; i++) {

            tempo = new char[size-i];
            for (int j = 0; j < size-i; j++) {
                tempo[j]=tab[i+j][j];
            }
            result.add(new String(tempo));
            for (int j = 0; j < size-i; j++) {
                tempo[j]=tab[j][i+j];
            }
            result.add(new String(tempo));
        }

        // Triangle left high AND right low
        for (int i = 1; i < size-3; i++) {

            tempo = new char[size-i];
            for (int j = 0; j < size-i; j++) {
                tempo[j]=tab[i+j][size-j-1];
            }
            result.add(new String(tempo));

            for (int j = 0; j < size-i; j++) {
                tempo[j]=tab[j][size-i-j-1];
            }
            result.add(new String(tempo));
        }

        String[] resultString = new String[result.size()];
        return result.toArray(resultString);
    }

    public int findBigXmas() {
        int cumul = 0;

        for (int i = 1; i < size-1; i++) {
            for (int j = 1; j < size-1; j++) {
                cumul+=countBigXmax(i,j);
            }
        }

        return cumul;
    }

    public int countBigXmax(int i, int j) {
        if (tab[i][j] != 'A')
            return 0;
        if (
                ((tab[i-1][j-1] == 'M' && tab[i+1][j+1] == 'S') || (tab[i-1][j-1] == 'S' && tab[i+1][j+1] == 'M'))
            &&  ((tab[i-1][j+1] == 'M' && tab[i+1][j-1] == 'S') || (tab[i-1][j+1] == 'S' && tab[i+1][j-1] == 'M'))
        )   return 1;
        return 0;
    }

    // Utilities /////////////////////////////////////
    public static int countPattern(String str) {
        int result = 0;

        Matcher matcherXmas = patternXmas.matcher(str);
        Matcher matcherSamx = patternSamx.matcher(str);

        while (matcherXmas.find())
            result++;
        while (matcherSamx.find())
            result++;

        return result;
    }
}
