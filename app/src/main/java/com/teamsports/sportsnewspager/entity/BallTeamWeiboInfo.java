package com.teamsports.sportsnewspager.entity;

/**
 * Created by Administrator on 2015/3/30.
 */
public class BallTeamWeiboInfo {

	private String name;
	private String imageUrl;
	private String text;
	private String vReason;
	private String comment;
	private String thumbnail;
	private String reposts;

	public String getReposts() {
		return reposts;
	}

	public void setReposts(String reposts) {
		this.reposts = reposts;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getvReason() {
		return vReason;
	}

	public void setvReason(String vReason) {
		this.vReason = vReason;
	}
}
