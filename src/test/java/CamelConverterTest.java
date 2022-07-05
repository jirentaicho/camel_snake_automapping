package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.volkruss.cider.converter.CamelConverter;

class CamelConverterTest {

	@Test
	void test() {
		CamelConverter camelConverter = new CamelConverter();
		String test1 = "snake_sample";
		String test2 = "snake_sample_str";
		
		String result1 = camelConverter.getCamel(test1);
		String result2 = camelConverter.getCamel(test2);
		
		assertEquals("snakeSample", result1);
		assertEquals("snakeSampleStr", result2);
	}

}
