package com.example.dell.smartfixapp.RetrofitModel;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MyResponse{

	@SerializedName("kind")
	private String kind;

	@SerializedName("etag")
	private String etag;

	@SerializedName("items")
	private List<ItemsItem> items;

	public void setKind(String kind){
		this.kind = kind;
	}

	public String getKind(){
		return kind;
	}

	public void setEtag(String etag){
		this.etag = etag;
	}

	public String getEtag(){
		return etag;
	}

	public void setItems(List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"MyResponse{" + 
			"kind = '" + kind + '\'' + 
			",etag = '" + etag + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}