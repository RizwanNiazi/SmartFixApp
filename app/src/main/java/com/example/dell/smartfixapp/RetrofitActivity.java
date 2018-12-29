package com.example.dell.smartfixapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.smartfixapp.RetrofitModel.ItemsItem;
import com.example.dell.smartfixapp.RetrofitModel.MyResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    private TextView textTitle;
    private TextView textDescription;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);


        textTitle = (TextView) findViewById(R.id.text_title);
        textDescription = (TextView) findViewById(R.id.text_content);
        btnBack = (Button)findViewById(R.id.btn_back);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetrofitActivity.this,GetStarted.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(API.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api=retrofit.create(API.class);

        Call<MyResponse> call=api.getAllPost();


        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {


                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                List<ItemsItem> myList =  response.body().getItems();
                ItemsItem item = myList.get(0);

                Document document = Jsoup.parse(item.getContent());
                Log.d("Text here: ",document.text());

                textTitle.setText(item.getTitle());
                textDescription.setText(document.text());

            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();


            }
        });

    }
}
