package music;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestRating {
	
	Rating rate = Rating.ONE;

	@Test
	void testGetValue() {
		assertEquals(1, rate.getValue());
	}
	
	@Test
	void testConvert() {
		rate = Rating.convert(1);
		assertEquals(1, rate.getValue());
		rate = Rating.convert(2);
		assertEquals(2, rate.getValue());
		rate = Rating.convert(3);
		assertEquals(3, rate.getValue());
		rate = Rating.convert(4);
		assertEquals(4, rate.getValue());
		rate = Rating.convert(5);
		assertEquals(5, rate.getValue());
		rate = Rating.convert(7);
		assertEquals(0, rate.getValue());
	}

}
