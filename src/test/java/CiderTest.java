package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.volkruss.cider.Cider;

import test.java.model.SampleCamel;
import test.java.model.SampleSnake;

class CiderTest {
	
	@Test
	void test_toCamel() {
		
		Cider cider = new Cider();
		
		SampleSnake sampleSnake = new SampleSnake();
		sampleSnake.name = "sample";
		sampleSnake.character_level = 12;
		sampleSnake.boss_first_name = "boss";
		
		SampleCamel sampleCamel = cider.toCamelModel(sampleSnake,SampleCamel.class);
	
		assertEquals("sample", sampleCamel.name);
		assertEquals(12, sampleCamel.characterLevel);
		assertEquals("boss", sampleCamel.bossFirstName);
	}
	
	@Test
	void test_toSnake() {
		
		Cider cider = new Cider();
		
		SampleCamel sampleCamel = new SampleCamel();
		sampleCamel.name = "test";
		sampleCamel.characterLevel = 13;
		sampleCamel.bossFirstName = "tanaka";
		sampleCamel.hoge = "hoge";
		
		SampleSnake sampleSnake = cider.toSnakeModel(sampleCamel,SampleSnake.class);
	
		assertEquals("test", sampleSnake.name);
		assertEquals(13, sampleSnake.character_level);
		assertEquals("tanaka", sampleSnake.boss_first_name);
	}
	
	@Test
	void test_exclusion() {
		Cider cider = new Cider();
		
		SampleCamel sampleCamel = new SampleCamel();
		sampleCamel.name = "test";
		sampleCamel.characterLevel = 13;
		sampleCamel.bossFirstName = "tanaka";
		sampleCamel.hoge = "hoge";
		
		SampleSnake sampleSnake = cider.toSnakeModel(sampleCamel,SampleSnake.class,"characterLevel","name");
	
		assertNull(sampleSnake.name);
		assertEquals(0,sampleSnake.character_level);
		
		assertEquals("tanaka", sampleSnake.boss_first_name);
	}
	
	@Test
	void test_same_definition() {
		Cider cider = new Cider();
		
		SampleCamel sampleCamel = new SampleCamel();
		sampleCamel.name = "test";
		sampleCamel.characterLevel = 13;
		sampleCamel.bossFirstName = "tanaka";
		sampleCamel.hoge = "hoge";
		
		SampleCamel samplemodel = cider.toModel(sampleCamel, SampleCamel.class);
		
		assertEquals("test", samplemodel.name);
		assertEquals(13, samplemodel.characterLevel);
		assertEquals("tanaka", samplemodel.bossFirstName);
		assertEquals("hoge", samplemodel.hoge);
	}


}
