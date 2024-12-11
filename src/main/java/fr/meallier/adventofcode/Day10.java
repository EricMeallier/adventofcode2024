package fr.meallier.adventofcode;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day10 {
    private char[][] tab;
    public Position[] departure;
    public Position[] arrival;

    private Day10() {}

    // Builders /////////////////////////////////////
    public static Day10 buildFromTab(char[][] tab) {
        Day10 day = new Day10();
        day.tab=tab;
        return day;
    }

    private static Day10 buildFromLines(String[] lines) {
        Day10 day = new Day10();
        day.tab=new char[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            day.tab[i]=lines[i].toCharArray();
        }
        return day;
    }

    public static Day10 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day10 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    // Business /////////////////////////////////////
    public void findDepartureArriva() {
        List<Position> dep = new ArrayList<>();
        List<Position> arr = new ArrayList<>();

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] == '0')
                    dep.add(new Position(i,j));
                if (tab[i][j] == '9')
                    arr.add(new Position(i,j));
            }
        }

        arrival = new Position[arr.size()];
        arr.toArray(arrival);
        departure = new Position[dep.size()];
        dep.toArray(departure);
    }

    public int computeTrails() {
        int cumul =0;

        for (Position position : arrival) {
            Set<Position> result = new HashSet<>();
            findNextStep(result,position.i(), position.j());
            cumul+=result.size();
        }

        return cumul;
    }

    public int computeTrailsRating() {
        int cumul =0;
        int bigCumul = 0;

        for (Position position : arrival) {
            Set<Position> result = new HashSet<>();
            bigCumul+=findNextStep(result,position.i(), position.j());
            cumul+=result.size();
        }

        return bigCumul;
    }


    private int findNextStep(Set<Position> positions,int i, int j) {
        int cumul=0;
        char target = Character.forDigit(Character.digit(tab[i][j],10)-1,10);

        if (tab[i][j]=='0') {
            positions.add(new Position(i,j));
            return 1;
        }

        if (i>0 && tab[i-1][j]==target) {
            cumul+=findNextStep(positions,i-1,j);
        }
        if (i<tab.length-1 && tab[i+1][j]==target) {
            cumul+=findNextStep(positions,i+1,j);
        }
        if (j>0 && tab[i][j-1]==target) {
            cumul+=findNextStep(positions,i,j-1);
        }
        if (j<tab.length-1 && tab[i][j+1]==target) {
            cumul+=findNextStep(positions,i,j+1);
        }

        return cumul;
    }
}

record Position(int i, int j) {}
