package fr.meallier.adventofcode;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    private String message;
    private final Pattern patternMul = Pattern.compile("mul[(]([0-9][0-9]?[0-9]?),([0-9][0-9]?[0-9]?)[)]");
    private final Pattern patternDo = Pattern.compile("do[(][)]");
    private final Pattern patternDont = Pattern.compile("don't[(][)]");

    private Matcher matcherMul;
    private Matcher matcherDo ;
    private Matcher matcherDont ;

    private int currentPosition;

    private Day3() {}

    // Builders /////////////////////////////////////
    public static Day3 buildFromString(String message) {
        Day3 day3 = new Day3();
        day3.message=message;
        return day3;
    }

    private static Day3 buildFromLines(String[] lines) {
        Day3 day3 = new Day3();
        day3.message="";

        for (int i = 0; i < lines.length; i++) {
            if (i==0)
                day3.message=day3.message.concat(lines[i]);
            else
                day3.message=day3.message.concat("\n").concat(lines[i]);
        }
        return day3;
    }

    public static Day3 buildFromFile(URI filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    public static Day3 buildFromFile(String filepath) throws Exception {
        return buildFromLines(Utils.readFile(filepath));
    }

    // Business /////////////////////////////////////
    public long multiplicity() {

        matcherMul = patternMul.matcher(message);

        long cumul=0;
        while ( matcherMul.find()) {
            cumul+=getCurrentMul();
        }

        return cumul;
    }

    public long multiplicityEnabledDisabled() {
        State currentToken;
        boolean countEnabled = true;
        long cumul=0;

        initParsing();
        do {
            currentToken = getNextToken();
            switch (currentToken) {
                case DO:
                    countEnabled = true;
                    break;
                case DONT:
                    countEnabled = false;
                    break;
                case MUL:
                    if (countEnabled)
                        cumul += getCurrentMul();
                    break;
                case END:
                    break;
            }
        } while ( currentToken!=State.END);

        return cumul;
    }

    // Utilities /////////////////////////////////////
    private void initParsing() {
        matcherMul = patternMul.matcher(message);
        matcherDo = patternDo.matcher(message);
        matcherDont = patternDont.matcher(message);
    }

    private State getNextToken() {

        boolean doing = matcherDo.find(currentPosition);
        boolean dont = matcherDont.find(currentPosition);
        boolean mul = matcherMul.find(currentPosition);

        if (mul) {
            if (doing) {
                if (matcherDo.start() > matcherMul.start()) {
                    if (dont) {
                        if (matcherDont.start() > matcherMul.start()) {
                            currentPosition= matcherMul.start()+3;
                            return State.MUL;
                        } else {
                            currentPosition=3+matcherDont.start();
                            return State.DONT;
                        }
                    } else {
                        currentPosition=3+ matcherMul.start();
                        return State.MUL;
                    }
                } else {
                    if (dont) {
                        if (matcherDont.start() > matcherDo.start()) {
                            currentPosition=3+matcherDo.start();
                            return State.DO;
                        } else {
                            currentPosition=3+matcherDont.start();
                            return State.DONT;
                        }
                    } else {
                        currentPosition=3+matcherDo.start();
                        return State.DO;
                    }
                }
            } else {
                if (dont) {
                    if (matcherDont.start() > matcherMul.start()) {
                        currentPosition=3+ matcherMul.start();
                        return State.MUL;
                    } else {
                        currentPosition=3+matcherDont.start();
                        return State.DONT;
                    }
                } else {
                    currentPosition=3+ matcherMul.start();
                    return State.MUL;
                }
            }
        } else {
            currentPosition+=3;
            return State.END;
        }
    }

    private long getCurrentMul() {
        return Long.parseLong(matcherMul.group(1))*Integer.parseInt(matcherMul.group(2));
    }
}

enum State {
    DO,DONT,MUL,END
}