package com.amdtelecom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SolutionTest {
	
	private Solution solution = new Solution();
	
	@Test
	public void testFindSeven() {
		int [] n = {1, 2, 3, 4, 5, 6,7};
		assertEquals("Found", solution.findSeven(n));
		
		int [] n1 = {8, 6, 33, 100};
		assertEquals("there is no 7 in the array", solution.findSeven(n1));
	}
	
	@Test
	public void testDigitSum() {
		int a = 10;
		int b = 38;
		int c = 795;
		
		assertEquals(1, solution.digitSum(a));
		assertEquals(2, solution.digitSum(b));
		assertEquals(3, solution.digitSum(c));
	}
	
	@Test
	public void testDoRemake() {
	String s1="Cats are great pets.";
	String s2="He told us a very exciting tale.";
	assertEquals("Atscay areway reatgay etspay.", solution.doRemake(s1));
	assertEquals("Ehay oldtay usway away eryvay excitingway aletay.", solution.doRemake(s2));
	}

}
