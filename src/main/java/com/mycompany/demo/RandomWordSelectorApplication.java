package com.mycompany.demo;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RandomWordSelectorApplication implements CommandLineRunner {
	private static Logger LOG = LoggerFactory.getLogger(RandomWordSelectorApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RandomWordSelectorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String fileName = "words_alpha.txt";
		ClassLoader classLoader = new RandomWordSelectorApplication().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile()); 
        BufferedReader br = null ;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            LOG.debug(ex.getMessage());
            return;
        }
        List<String> words = new ArrayList<>();
        String word;
        try {
            while((word = br.readLine()) != null) {
                words.add(word);
            }
        } catch (IOException ex) {
            LOG.debug(ex.getMessage());
            return;
        }
        LOG.debug("The random word is " + generateRandomWord(words));
		
	}

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
