package fr.meallier.adventofcode;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Day5 {
    int[] before;
    int[] after;
    int [][] listPrinting;

    private Day5() {}

    // Builders /////////////////////////////////////
    private static Day5 buildFromLines(String[] lines) {
        Day5 day = new Day5();

        int mode=0; // 0 for order lines, 1 for printing scenarii
        List<Integer> beforeL = new ArrayList<>();
        List<Integer> afterL = new ArrayList<>();
        List<int[]> printings = new ArrayList<>();

        for (String line:lines) {
            if (line.isEmpty()) {
                mode = 1;
            } else if (mode==0) {
                int b=Integer.parseInt(line.substring(0, line.indexOf('|')));
                int a=Integer.parseInt(line.substring(line.indexOf('|')+1));
                beforeL.add(b);
                afterL.add(a);
            } else if (mode==1) {
                List<Integer> printing = new ArrayList<>();
                int currentIndex=0;
                while (line.indexOf(',',currentIndex) != -1) {
                    printing.add(Integer.valueOf(line.substring(currentIndex,line.indexOf(',',currentIndex))));
                    currentIndex= line.indexOf(',',currentIndex)+1;
                }
                printing.add(Integer.valueOf(line.substring(currentIndex)));
                int[] resultInteger = new int[printing.size()];
                for (int i = 0; i < printing.size(); i++) {
                    resultInteger[i]=printing.get(i);
                }
                printings.add(resultInteger);
            }
        }

        day.before = new int[beforeL.size()];
        day.after = new int[afterL.size()];
        for (int i = 0; i <beforeL.size(); i++) {
            day.before[i]=beforeL.get(i);
            day.after[i]=afterL.get(i);
        }

        day.listPrinting=new int[printings.size()][];
        for (int i = 0; i < printings.size(); i++) {
            day.listPrinting[i]=printings.get(i);
        }

        return day;
    }

    public static Day5 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day5 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    // Business /////////////////////////////////////
    public int computeMiddleValues() {
        int cumul = 0;
        for (int[] printing : listPrinting) {
            cumul += computeOnePrintingMiddleValue(printing);
        }
        return cumul;
    }

    public int computeReorderedMiddleValue() {
        int cumul = 0;
        for (int[] printing : listPrinting) {
            if (!analyseOnePrintingValidity(printing)) {
                cumul += orderPrinting(printing)[(printing.length - 1) / 2];
            }
        }
        return cumul;
    }

    public int computeOnePrintingMiddleValue(int[] printing) {
        if (analyseOnePrintingValidity(printing)) {
            return printing[(printing.length-1)/2];
        } else return 0;
    }
    public boolean analyseOnePrintingValidity(int[] printing) {

        for (int index = 0; index < printing.length; index++) {
            for (int rule = 0; rule < before.length; rule++) {
                if (printing[index] == after[rule]) {
                    for (int nextIndex = index+1; nextIndex < printing.length; nextIndex++) {
                        if (printing[nextIndex] == before[rule]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public int[] orderPrinting(int[] printing) {
        // Tri à bulle
        boolean moving ;

        do {
            moving = false;
            for (int i = 0; i < printing.length - 1; i++) {
                if (!compare(printing[i], printing[i + 1])) {
                    int tempo = printing[i];
                    printing[i] = printing[i + 1];
                    printing[i + 1] = tempo;
                    moving = true;
                }
            }
        } while (moving);

        return printing;
    }

    public boolean compare(int a, int b) {
        for (int i = 0; i < after.length; i++) {
            if (a == after[i] && b == before[i]) {
                return false;
            }
        }
        return true;
    }

    // Utilities /////////////////////////////////////

}
