package com.cafe24.jblog.vo;

import org.hibernate.validator.constraints.NotBlank;

public class BlogVo {

	private String blogId;
	@NotBlank
	private String title;
	private String logo;

	public BlogVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", title=" + title + ", logo=" + logo + "]";
	}

}
