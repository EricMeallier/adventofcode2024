package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day4ApplicationTests {

    @Test
    void testDay4CountPattern() {
        Assertions.assertEquals(2,Day4.countPattern("XXMASAMXX"));
    }

    @Test
    void testDay4CombineCharacters() {
        try {
            Day4 day = Day4.buildFromFile(Objects.requireNonNull(Day3ApplicationTests.class.getResource("/day4.txt")).toURI());
            String[] combinaison = day.buildAllCombinaison();

            Assertions.assertEquals(46,combinaison.length);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    void testDay4FindXmas() {
        try {
            Day4 day = Day4.buildFromFile(Objects.requireNonNull(Day3ApplicationTests.class.getResource("/day4.txt")).toURI());

            Assertions.assertEquals(18,day.findXmasamX());
            Assertions.assertEquals(9,day.findBigXmas());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    @Disabled
    void testDay4FindXmas_File() {
        try {
            Day4 day = Day4.buildFromFile("/home/eric/day4.txt");

            Assertions.assertEquals(2569,day.findXmasamX());
            Assertions.assertEquals(1998,day.findBigXmas());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
