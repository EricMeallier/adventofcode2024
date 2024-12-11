package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day9ApplicationTests {

    @Test
    @Disabled
    void test_File() {
        try {
            Day9 day = Day9.buildFromFile("/home/eric/day9.txt");
            day.expandModele();
            day.reduceModele();
            Assertions.assertEquals(6241633730082L,day.checkSum());

            day = Day9.buildFromFile("/home/eric/day9.txt");
            day.expandModele();
            day.compactModele();
            Assertions.assertEquals(6265268809555L,day.checkSum());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testLocalFile() {
        try {
            Day9 day = Day9.buildFromFile(Objects.requireNonNull(Day9ApplicationTests.class.getResource("/day9.txt")).toURI());
            day.expandModele();
            day.reduceModele();
            Assertions.assertEquals(1928L,day.checkSum());

            day = Day9.buildFromFile(Objects.requireNonNull(Day9ApplicationTests.class.getResource("/day9.txt")).toURI());
            day.expandModele();
            day.compactModele();
            Assertions.assertEquals(2858L,day.checkSum());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
