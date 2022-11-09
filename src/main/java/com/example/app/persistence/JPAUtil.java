package com.example.app.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<EntityManager>();

    
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("editorial-manager-db");
        }
        return emf;
    }

    public static EntityManager getCurrentEntityManager() {      
        EntityManager em = currentEntityManager.get();         
        if (em  == null || !em.isOpen()) {
            em = getEntityManagerFactory().createEntityManager();
            currentEntityManager.set(em);
        }
        return em;
    }
    
    public static EntityManager createEntityManager() {
    	
    	return getEntityManagerFactory().createEntityManager();
    }

    public static void transactional(Runnable runnable){
    	
    	EntityManager em = getCurrentEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	
    	runnable.run();
    	
    	tx.rollback();
    	em.close();
    	
    }
    
}
