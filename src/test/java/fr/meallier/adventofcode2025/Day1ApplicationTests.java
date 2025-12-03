package fr.meallier.adventofcode2025;

import fr.meallier.adventofcode2025.Day1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day1ApplicationTests {


    @Test
    void test_File() {
        try {
            Day1 day = Day1.buildFromFile("/home/eric/day1.txt");

            Assertions.assertEquals(999L, day. computeSequence());
            Assertions.assertEquals(6099L, day. computeSequencePassword());

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testLocalFile() {
        try {
            Day1 day = Day1.buildFromFile(Objects.requireNonNull(Day1ApplicationTests.class.getResource("/2025/day1.txt")).toURI());

            Assertions.assertEquals(3L, day. computeSequence());
            Assertions.assertEquals(6L, day. computeSequencePassword());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
