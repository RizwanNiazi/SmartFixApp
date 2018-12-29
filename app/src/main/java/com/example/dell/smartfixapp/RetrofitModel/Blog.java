package com.example.dell.smartfixapp.RetrofitModel;

import com.google.gson.annotations.SerializedName;


public class Blog{

	@SerializedName("id")
	private String id;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Blog{" + 
			"id = '" + id + '\'' + 
			"}";
		}
}