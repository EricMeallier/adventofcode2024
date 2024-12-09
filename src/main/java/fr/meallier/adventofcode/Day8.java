package fr.meallier.adventofcode;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.*;

public class Day8 {
    private char [][] tab;
    private Day8() {}
    private Map<Character, List<Antenna>> listAntennas;
    private int antennaCount = 0;

    // Builders /////////////////////////////////////
    private static Day8 buildFromLines(String[] lines) {
        Day8 day = new Day8();

        day.tab=new char[lines.length][];

        for (int index = 0; index < lines.length; index++) {
            day.tab[index]=lines[index].toCharArray();
        }
        return day;
    }

    public static Day8 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day8 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    // Business /////////////////////////////////////
    public int countInterfered(boolean v2) {
        int cumul = 0;

        if (v2) {
            for (int i = 0; i < tab.length; i++) {
                for (int j = 0; j < tab.length; j++) {
                    if (tab[i][j] != '.') {
                        cumul++;
                    }
                }
            }
        } else {
            for (int i = 0; i < tab.length; i++) {
                for (int j = 0; j < tab.length; j++) {
                    if (tab[i][j] == '#') {
                        cumul++;
                    }
                }
            }
        }

        return cumul;
    }

    public void buildInteraction() {
        for (Character code:listAntennas.keySet()) {
            Antenna[] list = new Antenna[listAntennas.get(code).size()];
            listAntennas.get(code).toArray(list);

            for (int i = 0; i < list.length; i++) {
                for (int j = i+1; j <list.length; j++) {
                    // interaction between i and j, in both direction
                    markInTab(2 * list[i].x() - list[j].x(),2 * list[i].y() - list[j].y());
                    markInTab(2 * list[j].x() - list[i].x(),2 * list[j].y() - list[i].y());
                }
            }
        }
    }

    public void buildInteractionV2() {
        for (Character code:listAntennas.keySet()) {
            Antenna[] list = new Antenna[listAntennas.get(code).size()];
            listAntennas.get(code).toArray(list);

            for (int i = 0; i < list.length; i++) {
                for (int j = i+1; j <list.length; j++) {
                    // interaction between i and j, in both direction
                    int multiplicity=1;
                    while (markInTab((multiplicity+1) * list[i].x() - multiplicity * list[j].x(),(multiplicity+1) * list[i].y() - multiplicity * list[j].y())) {
                        multiplicity++;
                    }

                    multiplicity=1;
                    while (markInTab((multiplicity+1) * list[j].x() - multiplicity *list[i].x(),(multiplicity + 1) * list[j].y() - multiplicity * list[i].y())) {
                        multiplicity++;
                    }
                }
            }
        }
    }

    public boolean markInTab(int i, int j) {
        if (i>=0 && j>=0 && i <tab.length && j<tab.length) {
            tab[i][j]='#';
            return true;
        } else {
            return false;
        }
    }

    public void buildListAntennas() {
        listAntennas = new HashMap<>();
        antennaCount=0;

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                char current =tab[i][j];
                if (
                        (current>='a' && current<='z')
                    ||  (current>='A' && current<='Z')
                    ||  (current>='0' && current<='9')
                ) {
                    antennaCount++;
                    List<Antenna> l;
                    if (listAntennas.containsKey(current)) {
                        l=listAntennas.get(current);
                    } else {
                        l = new ArrayList<>();
                        listAntennas.put(current,l);
                    }
                    l.add(new Antenna(i,j));
                }
            }
        }
    }

    public char[] buildListAntennasCode() {

        char[] result = new char[listAntennas.keySet().size()];
        int index=0;
        for (Character type:listAntennas.keySet()) {
            result[index++]=type.charValue();
        }
        return result;
    }

    public void printTab() {
        for (int i = 0; i < tab.length; i++) {
            System.out.println(String.valueOf(tab[i]));
        }
    }
    // Utilities /////////////////////////////////////

}

record Antenna(int x, int y){}
