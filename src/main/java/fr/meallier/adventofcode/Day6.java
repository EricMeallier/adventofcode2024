package fr.meallier.adventofcode;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Day6 {
    private char[][] tab;
    public int startX;
    public int startY;
    private int currentX;
    private int currentY;
    private int direction;
    List<Tuple> history = new ArrayList<>();

    private Day6() {}

    // Builders /////////////////////////////////////
    public static Day6 buildFromTab(char[][] tab) {
        Day6 day6 = new Day6();
        day6.tab=tab;
        return day6;
    }

    private static Day6 buildFromLines(String[] lines) {
        Day6 day6 = new Day6();
        day6.tab=new char[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            day6.tab[i]=lines[i].toCharArray();
        }
        return day6;
    }

    public static Day6 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day6 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    // Business /////////////////////////////////////
    public void findStartPosition() {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] ==  '^') {
                    startX=j;
                    startY=i;
                    currentX=j;
                    currentY=i;
                    direction=2;
                    tab[i][j]='X';
                    return;
                }
            }
        }
    }

    public int countMarked() {
        int cumul = 0;

        for (char[] line : tab) {
            for (char box : line) {
                if (box == 'X') {
                    cumul++;
                }
            }
        }
        return cumul;
    }

    public void wipeTab() {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j]!='#')
                    tab[i][j]='.';
            }
        }
    }

    public void makeAllWalk() {
        while (makeAStep());
    }

    public int countGuard() {
        int cumul=0;

        for (int x = 0; x < tab.length; x++) {
            for (int y = 0; y < tab.length; y++) {
                if (tab[y][x] != '#' && !(startX==x && startY==y)) {
                    tab[y][x]='#';
                    if (isCircularWalk(startX,startY,2)){
                        cumul++;
                    }
                    tab[y][x]='.';
                }
            }
        }
        return cumul;
    }

    public boolean isCircularWalk(int startPositionX, int startPositionY, int startDirection) {
        currentX=startPositionX;
        currentY=startPositionY;
        direction=startDirection;
        wipeTab();
        history = new ArrayList<>();

        while (makeAStep()) {
            if (!addToHistory(currentX, currentY, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean makeAStep() {
        if (   (direction==0 && tab[currentY+1][currentX]=='#')
            || (direction==1 && tab[currentY][currentX+1]=='#')
            || (direction==2 && tab[currentY-1][currentX]=='#')
            || (direction==3 && tab[currentY][currentX-1]=='#')
        ) {
            direction = (direction+3)%4;
            return ! (   (direction==0 && currentY==tab.length-1)
                    || (direction==1 && currentX==tab.length-1)
                    || (direction==2 && currentY==0)
                    || (direction==3 && currentX==0)
            );
        } else {
            // step forward
            switch (direction) {
                case 0:
                    currentY++;
                    break;
                case 1:
                    currentX++;
                    break;
                case 2:
                    currentY--;
                    break;
                case 3:
                    currentX--;
                    break;
            }
            tab[currentY][currentX]='X';
            return  ! (   (direction==0 && currentY==tab.length-1)
                || (direction==1 && currentX==tab.length-1)
                || (direction==2 && currentY==0)
                || (direction==3 && currentX==0)
            );
        }
    }

    // Utilities /////////////////////////////////////
    public boolean addToHistory(int x, int y, int direction) {
        Tuple pos = new Tuple(x, y, direction);
        if (history.contains(pos)) {
            return false;
        } else {
            history.add(pos);
            return true;
        }
    }

    private static class Tuple {
        int x;
        int y;
        int direction;

        public Tuple(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Tuple tuple = (Tuple) o;
            return x == tuple.x && y == tuple.y && direction == tuple.direction;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + direction;
            return result;
        }
    }
}
