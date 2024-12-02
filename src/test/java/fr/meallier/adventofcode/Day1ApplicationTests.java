package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class Day1ApplicationTests {

	@Test
	void testDay1Distance() {
		int[] list1 = {3,4,2,1,3,3};
		int[] list2 = {4,3,5,3,9,3};

		Day1 day1 = Day1.buildFromArray(list1,list2);

		Assertions.assertEquals(11,day1.findTotalDistance());
	}

	@Test
	void testDay1Similarity() {
		int[] list1 = {3,4,2,1,3,3};
		int[] list2 = {4,3,5,3,9,3};

		Day1 day1 = Day1.buildFromArray(list1,list2);

		Assertions.assertEquals(31,day1.findSimilarity());
	}

	@Test
	void testDay1DistanceSimilarityFile() {
		try {
			Day1 day1 = Day1.buildFromFile(Objects.requireNonNull(Day1ApplicationTests.class.getResource("/day1.txt")).toURI());
			Assertions.assertEquals(11, day1.findTotalDistance());
			Assertions.assertEquals(31, day1.findSimilarity());
		} catch (Exception e) {
			Assertions.fail();
		}
	}


	@Test
	@Disabled
	void testDay1_file() {
		try {
			Day1 day1 = Day1.buildFromFile("/home/eric/day1.txt");
			Assertions.assertEquals(2970687,day1.findTotalDistance());
			Assertions.assertEquals(23963899,day1.findSimilarity());
        } catch (Exception e) {
            Assertions.fail();
        }
	}
}
