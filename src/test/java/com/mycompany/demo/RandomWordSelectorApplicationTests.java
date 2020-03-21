package com.mycompany.demo;

import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RandomWordSelectorApplicationTests {
	@Test
	void test1() {
		List<String> words = new ArrayList<>();
		words.add("one");
		RandomWordSelectorApplication rws = new RandomWordSelectorApplication();
		assertEquals("one", rws.generateRandomWord(words));
	}
	
	@Test
	void test2() {
		List<String> words = new ArrayList<>();
		words.add("zero");
		words.add("one");
		words.add("two");
		words.add("three");
		words.add(null);
		RandomWordSelectorApplication rws = new RandomWordSelectorApplication();
		assertTrue(words.contains(rws.generateRandomWord(words)));
	}

	@Test
	void test3() {
		List<String> words = null;
		RandomWordSelectorApplication rws = new RandomWordSelectorApplication();
		assertTrue(null == rws.generateRandomWord(words));
		words = new ArrayList<>();
		assertTrue(null == rws.generateRandomWord(words));
	}

}
