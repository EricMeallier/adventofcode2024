package fr.meallier.adventofcode;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {
    private char[][] tab;
    public Map<Character, List<Integer>> parcelles;

    private Day12() {
    }

    // Builders /////////////////////////////////////
    public static Day12 buildFromTab(char[][] tab) {
        Day12 day = new Day12();
        day.tab = tab;
        return day;
    }

    private static Day12 buildFromLines(String[] lines) {
        Day12 day = new Day12();
        day.tab = new char[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            day.tab[i] = lines[i].toCharArray();
        }
        return day;
    }

    public static Day12 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day12 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    // Business /////////////////////////////////////
    public void buildGraph() {
        parcelles = new HashMap<>();
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                Character key = Character.valueOf(tab[i][j]);
                List<Integer> list;
                if (!parcelles.containsKey(key)) {
                    list = new ArrayList<>();
                    parcelles.put(key, list);
                } else {
                    list = parcelles.get(key);
                }
                list.add(countConnexion(key,i,j));
            }
        }
    }

    private Integer countConnexion(Character val, int i, int j) {
        int cumul = 0;
        if (i > 0 && tab[i-1][j] == val) {
            cumul++;
        }
        if (j > 0 && tab[i][j-1] == val) {
            cumul++;
        }
        if (i < tab.length-1 && tab[i+1][j] == val) {
            cumul++;
        }
        if (j < tab.length-1 && tab[i][j+1] == val) {
            cumul++;
        }

        return Integer.valueOf(cumul);
    }

    public long valueParcelles() {
        long cumul=0;

        for (Map.Entry<Character, List<Integer>> entry : parcelles.entrySet()) {
            cumul += entry.getValue().size() * computeCountSide(entry.getValue());
        }
        return cumul;
    }
    // Utilities /////////////////////////////////////
    private long computeCountSide(List<Integer> list) {
        long cumul = 0;
        for (Integer val: list) {
            cumul+= val;
        }

        return 4*list.size()-cumul;
    }
}
