package fr.meallier.adventofcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day1ApplicationTests {

	@Test
	void testDAy1() {
		int[] list1 = {3,4,2,1,3,3};
		int[] list2 = {4,3,5,3,9,3};

		Day1 day1 = new Day1();

		Assertions.assertEquals(11,day1.findTotalDistance(list1,list2));
	}

}
