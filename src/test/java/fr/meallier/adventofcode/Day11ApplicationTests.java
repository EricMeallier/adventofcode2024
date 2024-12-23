package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day11ApplicationTests {

    @Test
    @Disabled
    void test_File() {
        try {
            //Day11 day = Day11.buildFromFile("/home/eric/day11.txt");
            Day11 day = Day11.buildFromFile(Objects.requireNonNull(Day11ApplicationTests.class.getResource("/day11.big.txt")).toURI());

            for (int i = 0; i < 25; i++) {
                if (i%10 == 0)
                    System.out.print('|');
                else
                    System.out.print('.');
                day.blink(i);
            }

            Assertions.assertEquals(207683,day.length());

            for (int i = 25; i < 50; i++) {
                if (i%10 == 0)
                    System.out.print('|');
                else
                    System.out.print('.');
                day.blink(i);
            }
            System.out.println();
            Assertions.assertEquals(108525756,day.length()); // pour 40 blinks

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testLocalFile() {
        try {
            Day11 day = Day11.buildFromFile(Objects.requireNonNull(Day11ApplicationTests.class.getResource("/day11.txt")).toURI());

            for (int i = 0; i < 25; i++) {
                day.blink(i);
            }

            Assertions.assertEquals(55312,day.length());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
