package com.douzone.mysite.vo;

public class SiteVo {
	private Long no;
	private String title;
	private String welcome_message;
	private String profile_url;
	private String discription;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWelcome_message() {
		return welcome_message;
	}
	public void setWelcome_message(String welcome_message) {
		this.welcome_message = welcome_message;
	}
	public String getProfile_url() {
		return profile_url;
	}
	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	@Override
	public String toString() {
		return "SiteVo [no=" + no + ", title=" + title + ", welcome_message=" + welcome_message + ", profile_url="
				+ profile_url + ", discription=" + discription + "]";
	}	

}
