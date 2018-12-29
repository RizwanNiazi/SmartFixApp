package com.example.dell.smartfixapp.RetrofitModel;


import com.google.gson.annotations.SerializedName;

public class Image{

	@SerializedName("url")
	private String url;

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"Image{" + 
			"url = '" + url + '\'' + 
			"}";
		}
}