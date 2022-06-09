package com.douzone.mysite.vo;

public class GalleryVo {
	private long no;
	private String url;
	private String Commets;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCommets() {
		return Commets;
	}
	public void setCommets(String commets) {
		Commets = commets;
	}
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", url=" + url + ", Commets=" + Commets + "]";
	}
	
	
}
