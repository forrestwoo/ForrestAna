package com.forrest.model;

/**
 * 赛事类  西甲 意甲
 * @author Administrator
 *
 */
public class Matches {
	private int fid;
	private String stime;
	private String hname;
	private String gname;
	private int hscore;
	private int gscore;
	private int hhalfscore;
	private int ghalfscore;
	private int hstanding;
	private int gstanding;
	private int round;
	private int hid;
	private int gid;
	private int status;
	private float win;
	private float draw;
	private float lost;
	private String pan;
	private String handline;
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getHscore() {
		return hscore;
	}
	public void setHscore(int hscore) {
		this.hscore = hscore;
	}
	public int getGscore() {
		return gscore;
	}
	public void setGscore(int gscore) {
		this.gscore = gscore;
	}
	public int getHhalfscore() {
		return hhalfscore;
	}
	public void setHhalfscore(int hhalfscore) {
		this.hhalfscore = hhalfscore;
	}
	public int getGhalfscore() {
		return ghalfscore;
	}
	public void setGhalfscore(int ghalfscore) {
		this.ghalfscore = ghalfscore;
	}
	public int getHstanding() {
		return hstanding;
	}
	public void setHstanding(int hstanding) {
		this.hstanding = hstanding;
	}
	public int getGstanding() {
		return gstanding;
	}
	public void setGstanding(int gstanding) {
		this.gstanding = gstanding;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public float getWin() {
		return win;
	}
	public void setWin(float win) {
		this.win = win;
	}
	public float getDraw() {
		return draw;
	}
	public void setDraw(float draw) {
		this.draw = draw;
	}
	public float getLost() {
		return lost;
	}
	public void setLost(float lost) {
		this.lost = lost;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getHandline() {
		return handline;
	}
	public void setHandline(String handline) {
		this.handline = handline;
	}
}
