package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day7ApplicationTests {

    @Test
    void testUnit() {
        try {
            Assertions.assertTrue(Day7.findCombinaison(190,new long[]{10,19}));
            Assertions.assertTrue(Day7.findCombinaison(3267,new long[]{81,40,27}));
            Assertions.assertTrue(Day7.findCombinaison(292,new long[]{11, 6, 16, 20}));
            Assertions.assertFalse(Day7.findCombinaison(21037,new long[]{9, 7, 18, 13}));
            Assertions.assertTrue(Day7.findCombinaisonV2(7290,new long[]{6, 8, 6, 15}));

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Disabled
    void test_File() {
        try {
            Day7 day = Day7.buildFromFile("/home/eric/day7.txt");

            Assertions.assertEquals(4998764814652L, day.computeExisting());
            Assertions.assertEquals(37598910447546L, day.computeExistingV2());

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testLocalFile() {
        try {
            Day7 day = Day7.buildFromFile(Objects.requireNonNull(Day7ApplicationTests.class.getResource("/day7.txt")).toURI());

            Assertions.assertEquals(3749, day.computeExisting());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
