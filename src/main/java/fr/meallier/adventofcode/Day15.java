package fr.meallier.adventofcode;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 {
    private char[][] tab;
    private char[] road;
    private int currentI=0;
    private int currentJ=0;

    private Day15() {
    }

    // Builders /////////////////////////////////////
    public static Day15 buildFromTab(char[][] tab, char[] road) {
        Day15 day = new Day15();
        day.tab = tab;
        day.road=road;
        return day;
    }

    private static Day15 buildFromLines(String[] lines) {
        Day15 day = new Day15();
        boolean firstPart = true;

        List<char[]> tempo = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for(String line: lines) {
            if (line.isEmpty()) {
                firstPart=false;
                continue;
            } else if (firstPart) {
                tempo.add(line.toCharArray());
            } else {
                sb.append(line);
            }
        }

        day.tab = tempo.toArray(new char[tempo.size()][]);
        day.road = sb.toString().toCharArray();
        return day;
    }

    public static Day15 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day15 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    // Business /////////////////////////////////////
    public void doubleTab() {
        char[][] result = new char[tab.length][];
        for (int i = 0; i < tab.length; i++) {
            char[] tempo = new char[tab.length * 2];
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j] == '@') {
                    tempo[j * 2] = tab[i][j];
                    tempo[j * 2 + 1] = '.';
                } else if (tab[i][j] == 'O') {
                    tempo[j * 2] = 'o';
                    tempo[j * 2 + 1] = 'O';
                } else {
                    tempo[j * 2] = tab[i][j];
                    tempo[j * 2 + 1] = tab[i][j];
                }
            }
            result[i] = tempo;
        }
        tab = result;
    }
    
    public void findStartingPosition() {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == '@') {
                    currentI=i;
                    currentJ=j;
                    tab[i][j]='.';
                    return;
                }
            }
        }
    }

    public void takeAWake(boolean part2) {
        for (int i = 0; i < road.length; i++) {
            //printTab(road[i]);
            stepForward(road[i],part2);

        }
    }

    public void stepForward(char dir, boolean part2) {
        try {
            if (dir == '>') {
                if (tab[currentI][currentJ + 1] == '.') {
                    currentJ++;
                    return;
                } else if (tab[currentI][currentJ + 1] == '#') {
                    return;
                } else if (tab[currentI][currentJ + 1] == 'o' || tab[currentI][currentJ + 1] == 'O') {
                    int plus = 1;
                    while (tab[currentI][currentJ + plus] == 'o' || tab[currentI][currentJ + plus] == 'O') {
                        plus++;
                    }
                    if (tab[currentI][currentJ + plus] == '.') {
                        if (!part2) {
                            // On pousse
                            tab[currentI][currentJ + plus] = 'O';
                            tab[currentI][currentJ + 1] = '.';
                            currentJ++;
                        } else {
                            for (int k = 2; k <= plus; k++) {
                                tab[currentI][currentJ + k] = (k % 2 == 0) ? 'o' : 'O';
                            }
                            tab[currentI][currentJ + 1] = '.';
                            currentJ++;
                        }
                    }
                }
            } else if (dir == '<') {
                if (tab[currentI][currentJ - 1] == '.') {
                    currentJ--;
                    return;
                } else if (tab[currentI][currentJ - 1] == '#') {
                    return;
                } else if (tab[currentI][currentJ - 1] == 'O' || tab[currentI][currentJ - 1] == 'o') {
                    int plus = 1;
                    while (tab[currentI][currentJ - plus] == 'O' || tab[currentI][currentJ - plus] == 'o') {
                        plus++;
                    }
                    if (tab[currentI][currentJ - plus] == '.') {
                        if (!part2) {
                            // On pousse
                            tab[currentI][currentJ - plus] = 'O';
                            tab[currentI][currentJ - 1] = '.';
                            currentJ--;
                        } else {
                            // On pousse mais attention
                            for (int k = 2; k <= plus; k++) {
                                tab[currentI][currentJ - k] = (k % 2 == 0) ? 'O' : 'o';
                            }
                            tab[currentI][currentJ - 1] = '.';
                            currentJ--;
                        }
                    }
                }
            } else if (dir == 'v') {
                if (tab[currentI + 1][currentJ] == '.') {
                    currentI++;
                    return;
                } else if (tab[currentI + 1][currentJ] == '#') {
                    return;
                } else if (tab[currentI + 1][currentJ] == 'O' || tab[currentI + 1][currentJ] == 'o') {
                    int plus = 1;
                    char c = tab[currentI + 1][currentJ];
                    while (tab[currentI + plus][currentJ] == 'O' || tab[currentI + plus][currentJ] == 'o') {
                        if (part2 && c != tab[currentI + plus][currentJ])
                            return;
                        plus++;
                    }
                    if (tab[currentI + plus][currentJ] == '.') {
                        if (!part2) {
                            // On pousse
                            tab[currentI + plus][currentJ] = 'O';
                            tab[currentI + 1][currentJ] = '.';
                            currentI++;
                        } else {
                            if (tab[currentI + 1][currentJ] == 'O' && tab[currentI + plus][currentJ - 1] == '.') {
                                // on pousse
                                tab[currentI + plus][currentJ] = 'O';
                                tab[currentI + 1][currentJ] = '.';
                                tab[currentI + plus][currentJ - 1] = 'o';
                                tab[currentI + 1][currentJ - 1] = '.';
                                currentI++;
                            } else if (tab[currentI + 1][currentJ] == 'o' && tab[currentI + plus][currentJ + 1] == '.') {
                                // on pousse
                                tab[currentI + plus][currentJ] = 'o';
                                tab[currentI + 1][currentJ] = '.';
                                tab[currentI + plus][currentJ + 1] = 'O';
                                tab[currentI + 1][currentJ + 1] = '.';
                                currentI++;
                            }
                        }
                    }
                }
            } else if (dir == '^') {
                if (tab[currentI - 1][currentJ] == '.') {
                    currentI--;
                    return;
                } else if (tab[currentI - 1][currentJ] == '#') {
                    return;
                } else if (tab[currentI - 1][currentJ] == 'O' || tab[currentI - 1][currentJ] == 'o') {
                    int plus = 1;
                    char c = tab[currentI - 1][currentJ];
                    while (tab[currentI - plus][currentJ] == 'O' || tab[currentI - plus][currentJ] == 'o') {
                        if (part2 && c != tab[currentI - plus][currentJ])
                            return;
                        plus++;
                    }
                    if (tab[currentI - plus][currentJ] == '.') {
                        if (!part2) {
                            // On pousse
                            tab[currentI - plus][currentJ] = 'O';
                            tab[currentI - 1][currentJ] = '.';
                            currentI--;
                        } else {
                            if (tab[currentI - 1][currentJ] == 'O' && tab[currentI - plus][currentJ - 1] == '.') {
                                // on pousse
                                tab[currentI - plus][currentJ] = 'O';
                                tab[currentI - 1][currentJ] = '.';
                                tab[currentI - plus][currentJ - 1] = 'o';
                                tab[currentI - 1][currentJ - 1] = '.';
                                currentI--;
                            } else if (tab[currentI - 1][currentJ] == 'o' && tab[currentI - plus][currentJ + 1] == '.') {
                                // on pousse
                                tab[currentI - plus][currentJ] = 'o';
                                tab[currentI - 1][currentJ] = '.';
                                tab[currentI - plus][currentJ + 1] = 'O';
                                tab[currentI - 1][currentJ + 1] = '.';
                                currentI--;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            printTab('&');
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public long valueOf() {
        long cumul = 0;

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (tab[i][j]=='O') {
                    cumul=cumul + 100*i + j;
                }
            }

        }
        return cumul;
    }

    public long valueOfV2() {
        long cumul = 0;

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j]=='o') {
                    cumul=cumul + 100*i + j;
                }
            }

        }
        return cumul;
    }

    // Utilities /////////////////////////////////////
    public void printTab(char dir) {
        System.out.println("Position apres: " + dir);
        for (int i = 0; i < tab.length; i++) {
            if (currentI == i) {
                tab[i][currentJ] = '@';
                System.out.println(String.valueOf(tab[i]));
                tab[i][currentJ] = '.';
            } else {
                System.out.println(String.valueOf(tab[i]));
            }
        }
    }

}
