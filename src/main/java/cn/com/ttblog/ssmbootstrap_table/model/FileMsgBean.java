package cn.com.ttblog.ssmbootstrap_table.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FileMsgBean {
	private String name;
	private long size;
	private String url;
	private String thumbnailUrl;
	private String deleteUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}