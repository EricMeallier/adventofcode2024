package fr.meallier.adventofcode2025;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day2ApplicationTests {


    @Test
    @Disabled
    void test_File() {
        try {
            Day2 day = Day2.buildFromFile("/home/eric/day2.txt");

            Assertions.assertEquals(23039913998L, day. computeSequence());
            Assertions.assertEquals(35950619148L, day. computeSequenceRepeat());

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testLocalFile() {
        try {
            Day2 day = Day2.buildFromFile(Objects.requireNonNull(Day2ApplicationTests.class.getResource("/2025/day2.txt")).toURI());

            Assertions.assertEquals(1227775554L, day. computeSequence());
            Assertions.assertEquals(4174379265L, day. computeSequenceRepeat());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testMirror() {
        Assertions.assertFalse(Day2.isMirror(2121212118));

        Assertions.assertTrue(Day2.isMirror(11));
        Assertions.assertTrue(Day2.isMirror(1212));
        Assertions.assertTrue(Day2.isMirror(84568456));
        Assertions.assertFalse(Day2.isMirror(113));
        Assertions.assertFalse(Day2.isMirror(12));
        Assertions.assertFalse(Day2.isMirror(113216545));
    }

    @Test
    void testRepeat() {
        Assertions.assertFalse(Day2.isRepeat(2121212118));

        Assertions.assertTrue(Day2.isRepeat(11));
        Assertions.assertTrue(Day2.isRepeat(113113));
        Assertions.assertTrue(Day2.isRepeat(1188511885));
        Assertions.assertFalse(Day2.isRepeat(113));
        Assertions.assertFalse(Day2.isRepeat(1113));
    }
}
