package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day10ApplicationTests {

    @Test
    @Disabled
    void test_File() {
        try {
            Day10 day = Day10.buildFromFile("/home/eric/day10.txt");

            day.findDepartureArriva();
            Assertions.assertEquals(274,day.arrival.length);
            Assertions.assertEquals(337,day.departure.length);
            Assertions.assertEquals(789,day.computeTrails());
            Assertions.assertEquals(1735,day.computeTrailsRating());

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testLocalFile() {
        try {
            Day10 day = Day10.buildFromFile(Objects.requireNonNull(Day10ApplicationTests.class.getResource("/day10.txt")).toURI());
            day.findDepartureArriva();
            Assertions.assertEquals(7,day.arrival.length);
            Assertions.assertEquals(9,day.departure.length);
            Assertions.assertEquals(36,day.computeTrails());
            Assertions.assertEquals(81,day.computeTrailsRating());

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
