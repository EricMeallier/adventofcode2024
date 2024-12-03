package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day3ApplicationTests {

    @Test
    void testDay3Multiplicity() {
        Day3 day3=Day3.buildFromString("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))");

        Assertions.assertEquals(161,day3.multiplicity());
    }
    @Test
    void testDay3MultiplicityEnabledDisabled() {
        Day3 day3=Day3.buildFromString("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))");

        Assertions.assertEquals(48,day3.multiplicityEnabledDisabled());
    }

    @Test
    void testDay3MultiplicityFile() {
        try {
            Day3 day3 = Day3.buildFromFile(Objects.requireNonNull(Day3ApplicationTests.class.getResource("/day3.txt")).toURI());
            Assertions.assertEquals(170778545, day3.multiplicity());
            Assertions.assertEquals(82868252, day3.multiplicityEnabledDisabled());
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
