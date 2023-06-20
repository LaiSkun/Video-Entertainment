package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.poly.domain.FavoriteReport;
import com.poly.domain.FavoriteUserReport;
import com.poly.entity.Favorite;

public class FavoriteDao extends DAO<Favorite> {
	public FavoriteDao() {
		super(Favorite.class);
	}
	public List<FavoriteReport> reportFavoriteByVideo(){
		String jpql="select new com.poly.domain.FavoriteReport(f.video.title,count(f),min(f.likeDate),max(f.likeDate)) "+
				" from Favorite f group by f.video.title ";
		EntityManager em = JpaUltils.getEntityManager();
		List<FavoriteReport> list = null;
		try {
			TypedQuery<FavoriteReport> query = em.createQuery(jpql, FavoriteReport.class);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}
	public List<FavoriteUserReport> reportFavoriteUsersByVideo(String videoId){
		String jpql = "select new com.poly.domain.FavoriteUserReport(f.user.username,f.user.fullname,f.user.email, "
				+ "f.likeDate) from Favorite f where f.video.videoId = :videoId ";
		EntityManager em = JpaUltils.getEntityManager();
		List<FavoriteUserReport> list = null;
		try {
			TypedQuery<FavoriteUserReport> query = em.createQuery(jpql, FavoriteUserReport.class);
			query.setParameter("videoId", videoId);
			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}
}
