package com.example;

import org.junit.jupiter.api.BeforeEach;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Βασική κλάση για integration test 
 * Αρχικοποιεί τη βάση δεδομένων πριν από κάθε έλεγχο
 */
public class IntegrationBase {
	@Inject
	EntityManager em;
	
	@Transactional
	@BeforeEach
	public void initDb()  {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("import.sql");
		String sql = convertStreamToString(in);
		try {
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		em.createNativeQuery(sql).executeUpdate();
	}
	
	
	private String convertStreamToString(InputStream in) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(in,"UTF-8").useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
}
