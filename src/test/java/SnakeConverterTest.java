package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.volkruss.cider.converter.SnakeConverter;

class SnakeConverterTest {

	@Test
	void test() {
		SnakeConverter converter = new SnakeConverter();
		String test1 = "sampleCamel";
		String test2 = "sampleCamelStr";
		
		String result1 = converter.getSnake(test1);
		String result2 = converter.getSnake(test2);
		
		assertEquals("sample_camel", result1);
		assertEquals("sample_camel_str", result2);
	}

}
