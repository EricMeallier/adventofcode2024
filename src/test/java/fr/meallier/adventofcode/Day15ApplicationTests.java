package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day15ApplicationTests {

    @Test
    @Disabled
    void test_File() {
        try {
            Day15 day = Day15.buildFromFile("/home/eric/day15.txt");

            day.findStartingPosition();
            day.takeAWake(false);
            Assertions.assertEquals(1349898, day.valueOf());

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testLocalFile() {
        try {
            Day15 day = Day15.buildFromFile(Objects.requireNonNull(Day15ApplicationTests.class.getResource("/day15.txt")).toURI());
            Assertions.assertNotNull(day);

            day.findStartingPosition();
            day.takeAWake(false);
            Assertions.assertEquals(10092, day.valueOf());

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
