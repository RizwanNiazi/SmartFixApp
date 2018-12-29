package com.example.dell.smartfixapp.RetrofitModel;

import com.google.gson.annotations.SerializedName;

public class Author{

	@SerializedName("image")
	private Image image;

	@SerializedName("displayName")
	private String displayName;

	@SerializedName("id")
	private String id;

	@SerializedName("url")
	private String url;

	public void setImage(Image image){
		this.image = image;
	}

	public Image getImage(){
		return image;
	}

	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}

	public String getDisplayName(){
		return displayName;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Author{" + 
			"image = '" + image + '\'' + 
			",displayName = '" + displayName + '\'' + 
			",id = '" + id + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}