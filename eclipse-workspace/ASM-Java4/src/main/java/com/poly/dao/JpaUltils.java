package com.poly.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class JpaUltils {
	public static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("ASM_Java4").createEntityManager();
	}
}
