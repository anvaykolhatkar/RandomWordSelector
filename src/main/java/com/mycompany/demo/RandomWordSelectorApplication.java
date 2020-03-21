package com.mycompany.demo;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RandomWordSelectorApplication implements CommandLineRunner {
	private static Logger LOG = LoggerFactory.getLogger(RandomWordSelectorApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RandomWordSelectorApplication.class, args);
	}

	/*
	 * This is a run method of CommandLineRunner
	 * It reads the list of words from the classpath resources & calls method that returns random word  
	 */
	@Override
	public void run(String... args) throws Exception {
        List<String> words = new ArrayList<>();
        String word;
        try {
    		InputStream inputStream = getClass().getResourceAsStream("/config/words_alpha.txt");
    		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            
            while((word = reader.readLine()) != null) {
                words.add(word);
            }
            reader.close();

        } catch (FileNotFoundException ex) {
            LOG.debug(ex.getMessage());
            return;
        }
        LOG.debug("The random word is " + generateRandomWord(words));
		
	}

	/*
	 * This method instantiates SecureRandom with bound equals the size of the list
	 * and generates the random index in the list, eventually returning the string  
	 */
	public String generateRandomWord(List<String> words) {
        if (words == null || 
        		words.size() == 0) {
        	LOG.debug("The list is null or empty");
        	return null;
        }
        SecureRandom rand = new SecureRandom(); 
        int randomIndex = rand.nextInt(words.size());
        String randomValue = words.get(randomIndex);
        LOG.debug("The random index is " + randomIndex + " with value " + randomValue);
        return randomValue;
	}

}
