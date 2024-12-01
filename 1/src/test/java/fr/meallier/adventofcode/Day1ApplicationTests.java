package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class Day1ApplicationTests {

	@Test
	void testDay1Distance() {
		int[] list1 = {3,4,2,1,3,3};
		int[] list2 = {4,3,5,3,9,3};

		Day1 day1 = new Day1(list1,list2);

		Assertions.assertEquals(11,day1.findTotalDistance());
	}

	@Test
	void testDay1Similarity() {
		int[] list1 = {3,4,2,1,3,3};
		int[] list2 = {4,3,5,3,9,3};

		Day1 day1 = new Day1(list1,list2);

		Assertions.assertEquals(31,day1.findSimilarity());
	}

	@Test
	void testDAy1_file() {
		Day1 day1 = new Day1();
        try {
            day1.readFromFile("/home/eric/day1.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

		Assertions.assertEquals(2970687,day1.findTotalDistance());
        Assertions.assertEquals(23963899,day1.findSimilarity());
	}
}
