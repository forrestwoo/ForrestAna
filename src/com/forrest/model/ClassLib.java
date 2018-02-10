package com.forrest.model;

import com.sun.org.apache.xml.internal.security.keys.keyresolver.implementations.PrivateKeyResolver;

public class ClassLib {
	private int id;
	private String name;
	private String network_url;
	private String local_path;
	private String intro;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNetwork_url() {
		return network_url;
	}
	public void setNetwork_url(String network_url) {
		this.network_url = network_url;
	}
	public String getLocal_path() {
		return local_path;
	}
	public void setLocal_path(String local_path) {
		this.local_path = local_path;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}

}
