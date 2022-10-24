package com.example.app.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.example.app.persistence.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class AccountTest {


	private EntityManager em;

	@BeforeEach
	public void setup(){
		em = JPAUtil.getCurrentEntityManager();
	}

	@AfterEach
	public void tearDown(){
		em.close();
	}

	@Test
	void saveAccount() {

		Account acc = new Account("12345");
		persistAccount(acc);
		assertNotNull(acc.getId());
		em.clear();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Account savedAccount = em.find(Account.class, acc.getId());
		assertEquals("12345", savedAccount.getAccountNumber());
		tx.commit();

	}

	private void persistAccount(Account account){
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(account);
		tx.commit();
	}

}
