package com.poly.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Users")
@NamedQuery(name="User.findAll" ,query = "Select u From User u")
public class User {
	@Id
	@Column(name="Id")
	private String username;
	
	@Column(name="Admin")
	private Boolean admin = false;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Fullname")
	private String fullname;
	
	@Column(name="Password")
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<Favorite> favorites;
	
	@OneToMany(mappedBy = "user")
	private List<Share> shares;

	public User() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
