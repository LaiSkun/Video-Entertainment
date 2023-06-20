package com.poly.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Videos")
@NamedQuery(name="Video.findAll" ,query = "Select v From User v")
public class Video {
	@Id
	@Column(name="Id")
	private String videoId;
	
	@Column(name="Active")
	private Boolean active;
	
	@Column(name="Poster")
	private String poster;
	
	@Column(name="Titile")
	private String title;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Views")
	private int views;
	
	@Column(name="Link")
	private String link;
	
	@OneToMany(mappedBy = "video")
	private List<Favorite> favorites;
	
	@OneToMany(mappedBy = "video")
	private List<Share> shares;

	public Video() {
		
	}
	
	
	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public String getVideoId() {
		return videoId;
	}


	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}


	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public List<Share> getShares() {
		return shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}
	
	
}
