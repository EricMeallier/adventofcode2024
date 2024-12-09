package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day8ApplicationTests {

    @Test
    @Disabled
    void test_File() {
        try {
            Day8 day = Day8.buildFromFile("/home/eric/day8.txt");

            day.buildListAntennas();
            Assertions.assertEquals(44,day.buildListAntennasCode().length);
            day.buildInteraction();
            Assertions.assertEquals(299,day.countInterfered(false));

            day = Day8.buildFromFile("/home/eric/day8.txt");
            day.buildListAntennas();
            Assertions.assertEquals(44,day.buildListAntennasCode().length);
            day.buildInteractionV2();
            Assertions.assertEquals(1032,day.countInterfered(true));

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void testLocalFile() {
        try {
            Day8 day = Day8.buildFromFile(Objects.requireNonNull(Day8ApplicationTests.class.getResource("/day8.txt")).toURI());

            day.buildListAntennas();
            Assertions.assertEquals(2,day.buildListAntennasCode().length);
            day.buildInteraction();
            Assertions.assertEquals(14,day.countInterfered(false));

            day = Day8.buildFromFile(Objects.requireNonNull(Day8ApplicationTests.class.getResource("/day8.txt")).toURI());

            day.buildListAntennas();
            Assertions.assertEquals(2,day.buildListAntennasCode().length);
            day.buildInteractionV2();
            Assertions.assertEquals(34,day.countInterfered(true));

        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}
