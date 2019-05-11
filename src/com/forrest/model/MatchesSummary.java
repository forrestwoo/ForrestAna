package com.forrest.model;

public class MatchesSummary {
	private int mid;
	//犯规次数
	private int fouls;
	//红牌
	private int redcards;
//	黄牌
	private int yellowcards;
//	越位
	private int offsides;
//	角球
	private int cornerkicks;
//	控球率
	private String possession;
//	射门次数
	private int shot;
//	射正次数
	private int ongoal;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getFouls() {
		return fouls;
	}
	public void setFouls(int fouls) {
		this.fouls = fouls;
	}
	public int getRedcards() {
		return redcards;
	}
	public void setRedcards(int redcards) {
		this.redcards = redcards;
	}
	public int getYellowcards() {
		return yellowcards;
	}
	public void setYellowcards(int yellowcards) {
		this.yellowcards = yellowcards;
	}
	public int getOffsides() {
		return offsides;
	}
	public void setOffsides(int offsides) {
		this.offsides = offsides;
	}
	public int getCornerkicks() {
		return cornerkicks;
	}
	public void setCornerkicks(int cornerkicks) {
		this.cornerkicks = cornerkicks;
	}
	public String getPossession() {
		return possession;
	}
	public void setPossession(String possession) {
		this.possession = possession;
	}
	public int getShot() {
		return shot;
	}
	public void setShot(int shot) {
		this.shot = shot;
	}
	public int getOngoal() {
		return ongoal;
	}
	public void setOngoal(int ongoal) {
		this.ongoal = ongoal;
	} 
}
