package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day5ApplicationTests {

    @Test
    void testPrintingValidity() {
        try {
            Day5 day = Day5.buildFromFile(Objects.requireNonNull(Day5ApplicationTests.class.getResource("/day5.txt")).toURI());
            Assertions.assertTrue(day.analyseOnePrintingValidity(new int[]{75,47,61,53,29}));
            Assertions.assertFalse(day.analyseOnePrintingValidity(new int[]{75,97,47,61,53}));
            Assertions.assertEquals(143,day.computeMiddleValues());
            Assertions.assertEquals(123,day.computeReorderedMiddleValue());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testSorting() {
        try {
            Day5 day = Day5.buildFromFile(Objects.requireNonNull(Day5ApplicationTests.class.getResource("/day5.txt")).toURI());
            Assertions.assertArrayEquals(new int[]{97,75,47,29,13},(day.orderPrinting(new int[]{97,13,75,29,47})));
            Assertions.assertArrayEquals(new int[]{97,75,47,61,53},(day.orderPrinting(new int[]{75,97,47,61,53})));
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }



    @Test
    @Disabled
    void testPrintingMiddle_File() {
        try {
            Day5 day = Day5.buildFromFile("/home/eric/day5.txt");

            Assertions.assertEquals(4872,day.computeMiddleValues());
            Assertions.assertEquals(5564,day.computeReorderedMiddleValue());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
