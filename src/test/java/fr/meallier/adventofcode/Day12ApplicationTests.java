package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day12ApplicationTests {

    @Test
    void test_File() {
        try {
            //Day12 day = Day12.buildFromFile("/home/eric/day11.txt");
            Day12 day = Day12.buildFromFile(Objects.requireNonNull(Day12ApplicationTests.class.getResource("/day11.big.txt")).toURI());

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testLocalFile() {
        try {
            Day12 day = Day12.buildFromFile(Objects.requireNonNull(Day12ApplicationTests.class.getResource("/day12.txt")).toURI());

            day.buildGraph();

            //Assertions.assertEquals(1930,day.valueParcelles());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
