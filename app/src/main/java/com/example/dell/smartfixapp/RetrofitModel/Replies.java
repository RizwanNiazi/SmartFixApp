package com.example.dell.smartfixapp.RetrofitModel;

import com.google.gson.annotations.SerializedName;


public class Replies{

	@SerializedName("totalItems")
	private String totalItems;

	@SerializedName("selfLink")
	private String selfLink;

	public void setTotalItems(String totalItems){
		this.totalItems = totalItems;
	}

	public String getTotalItems(){
		return totalItems;
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
			"Replies{" + 
			"totalItems = '" + totalItems + '\'' + 
			",selfLink = '" + selfLink + '\'' + 
			"}";
		}
}