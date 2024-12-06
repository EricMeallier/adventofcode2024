package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day6ApplicationTests {

    @Test
    void testPartOne() {
        try {
            Day6 day = Day6.buildFromFile(Objects.requireNonNull(Day6ApplicationTests.class.getResource("/day6.txt")).toURI());
            day.findStartPosition();

            Assertions.assertEquals(4,day.startX);
            Assertions.assertEquals(6,day.startY);
            Assertions.assertEquals(1,day.countMarked());

            day.makeAllWalk();
            Assertions.assertEquals(41,day.countMarked());

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Disabled
    void test_File() {
        try {
            Day6 day = Day6.buildFromFile("/home/eric/day6.txt");
            day.findStartPosition();
            day.makeAllWalk();
            Assertions.assertEquals(4433,day.countMarked());
            Assertions.assertEquals(1516,day.countGuard());

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testPartTwo() {
        try {
            Day6 day = Day6.buildFromFile(Objects.requireNonNull(Day6ApplicationTests.class.getResource("/day6.txt")).toURI());

            day.findStartPosition();
            Assertions.assertEquals(6,day.countGuard());

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
