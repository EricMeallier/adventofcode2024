package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day2ApplicationTests {

    @Test
    void testDay2Safeness() {
        Assertions.assertTrue(Day2.safeness(new int[]{7, 6, 4, 2, 1}));
        Assertions.assertFalse(Day2.safeness(new int[]{1, 2, 7, 8, 9}));
        Assertions.assertFalse(Day2.safeness(new int[]{9, 7, 6, 2, 1}));
        Assertions.assertFalse(Day2.safeness(new int[]{1, 3, 2, 4, 5}));
        Assertions.assertFalse(Day2.safeness(new int[]{8, 6, 4, 4, 1}));
        Assertions.assertTrue(Day2.safeness(new int[]{1, 3, 6, 7, 9}));
    }

    @Test
    void testDay2SafenessCount() {
        Day2 day2 = Day2.buildFromArray(new int[][]{{7, 6, 4, 2, 1},
                {1, 2, 7, 8, 9},
                {9, 7, 6, 2, 1},
                {1, 3, 2, 4, 5},
                {8, 6, 4, 4, 1},
                {1, 3, 6, 7, 9}});
        Assertions.assertEquals(2, day2.countSafeness());
    }

    @Test
    void testDay2SafenessCountFile() {
        try {
            Day2 day2 = Day2.buildFromFile(Objects.requireNonNull(Day2ApplicationTests.class.getResource("/day2.txt")).toURI());
            Assertions.assertEquals(2, day2.countSafeness());
            Assertions.assertEquals(4, day2.countSafenessV2());
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void testDay2_file() {
        try {
            Day2 day2 = Day2.buildFromFile("/home/eric/day2.txt");
            Assertions.assertEquals(371, day2.countSafeness());
            Assertions.assertEquals(426, day2.countSafenessV2());
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void testDay2_removeFromArray() {

        Assertions.assertArrayEquals(new int[]{2,3,4,5},Day2.removeElementFromArray(new int[]{1,2,3,4,5},0));
        Assertions.assertArrayEquals(new int[]{1,3,4,5},Day2.removeElementFromArray(new int[]{1,2,3,4,5},1));
        Assertions.assertArrayEquals(new int[]{1,2,4,5},Day2.removeElementFromArray(new int[]{1,2,3,4,5},2));
        Assertions.assertArrayEquals(new int[]{1,2,3,5},Day2.removeElementFromArray(new int[]{1,2,3,4,5},3));
        Assertions.assertArrayEquals(new int[]{1,2,3,4},Day2.removeElementFromArray(new int[]{1,2,3,4,5},4));
    }
}
