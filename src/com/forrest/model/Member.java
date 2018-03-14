package com.forrest.model;

public class Member {
	private int userid;
	private String name;
	private String city;
	private String followers;
	private String fans;
	private String contribution_value;
	private String register_date;
	private String photos;
	private String tags;
	private String community_level;

	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getCommunity_level() {
		return community_level;
	}
	public void setCommunity_level(String community_level) {
		this.community_level = community_level;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFollowers() {
		return followers;
	}
	public void setFollowers(String followers) {
		this.followers = followers;
	}
	public String getFans() {
		return fans;
	}
	public void setFans(String fans) {
		this.fans = fans;
	}
	public String getContribution_value() {
		return contribution_value;
	}
	public void setContribution_value(String contribution_value) {
		this.contribution_value = contribution_value;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
}
