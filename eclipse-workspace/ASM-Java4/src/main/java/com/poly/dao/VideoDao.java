package com.poly.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.poly.entity.Video;


public class VideoDao extends DAO<Video> {

	public VideoDao() {
		super(Video.class);
	}
	
}
