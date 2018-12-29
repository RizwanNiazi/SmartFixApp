package com.example.dell.smartfixapp.RetrofitModel;


import com.google.gson.annotations.SerializedName;

public class ItemsItem{

	@SerializedName("replies")
	private Replies replies;

	@SerializedName("kind")
	private String kind;

	@SerializedName("author")
	private Author author;

	@SerializedName("etag")
	private String etag;

	@SerializedName("id")
	private String id;

	@SerializedName("published")
	private String published;

	@SerializedName("blog")
	private Blog blog;

	@SerializedName("title")
	private String title;

	@SerializedName("updated")
	private String updated;

	@SerializedName("url")
	private String url;

	@SerializedName("content")
	private String content;

	@SerializedName("selfLink")
	private String selfLink;

	public void setReplies(Replies replies){
		this.replies = replies;
	}

	public Replies getReplies(){
		return replies;
	}

	public void setKind(String kind){
		this.kind = kind;
	}

	public String getKind(){
		return kind;
	}

	public void setAuthor(Author author){
		this.author = author;
	}

	public Author getAuthor(){
		return author;
	}

	public void setEtag(String etag){
		this.etag = etag;
	}

	public String getEtag(){
		return etag;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPublished(String published){
		this.published = published;
	}

	public String getPublished(){
		return published;
	}

	public void setBlog(Blog blog){
		this.blog = blog;
	}

	public Blog getBlog(){
		return blog;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUpdated(String updated){
		this.updated = updated;
	}

	public String getUpdated(){
		return updated;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setSelfLink(String selfLink){
		this.selfLink = selfLink;
	}

	public String getSelfLink(){
		return selfLink;
	}

	@Override
 	public String toString(){
		return 
			"ItemsItem{" + 
			"replies = '" + replies + '\'' + 
			",kind = '" + kind + '\'' + 
			",author = '" + author + '\'' + 
			",etag = '" + etag + '\'' + 
			",id = '" + id + '\'' + 
			",published = '" + published + '\'' + 
			",blog = '" + blog + '\'' + 
			",title = '" + title + '\'' + 
			",updated = '" + updated + '\'' + 
			",url = '" + url + '\'' + 
			",content = '" + content + '\'' + 
			",selfLink = '" + selfLink + '\'' + 
			"}";
		}
}