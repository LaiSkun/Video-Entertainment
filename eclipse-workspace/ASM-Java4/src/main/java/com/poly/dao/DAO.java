package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class DAO<E> {
	private Class<E> entityClass;
	
	public DAO(Class<E> cls) {
		this.entityClass = cls ;
	}
	public void insert(E entity) {
		EntityManager em = JpaUltils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	public void update(E entity) {
		EntityManager em = JpaUltils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	public void delete(Object id) {
		EntityManager em = JpaUltils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			E entity = em.find(entityClass, id);
			em.remove(entity);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		}finally {
			em.close();
		}
	}
	public E findById(Object id) {
		EntityManager em = JpaUltils.getEntityManager();
		
		E entity = em.find(entityClass, id);
		
		return entity;
	}
	public List<E> findAll() {
		EntityManager em = JpaUltils.getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			
			cq.select(cq.from(entityClass));
			return em.createQuery(cq).getResultList();
		}finally {
			// TODO Auto-generated catch block
			em.close();
		}
	}
	public List<E> findAll(boolean all, int firstResult, int maxResult) {
		EntityManager em = JpaUltils.getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(entityClass));
			Query q = em.createQuery(cq);
			if(!all) {
				q.setFirstResult(firstResult);
				q.setMaxResults(maxResult);
			}
			return em.createQuery(cq).getResultList();
		} finally  {
			// TODO Auto-generated catch block
			em.close();
		}
	}
	public Long count() {
		EntityManager em = JpaUltils.getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			
			Root<E> rt = cq.from(entityClass);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return (Long) q.getSingleResult();
		} finally {
			// TODO Auto-generated catch block
			em.close();
		}
		
	}
}
