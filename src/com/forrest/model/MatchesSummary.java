package com.forrest.model;

public class MatchesSummary {
	private int mid;
	//�������
	private int fouls;
	//����
	private int redcards;
//	����
	private int yellowcards;
//	Խλ
	private int offsides;
//	����
	private int cornerkicks;
//	������
	private String possession;
//	���Ŵ���
	private int shot;
//	��������
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
