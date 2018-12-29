package com.example.dell.smartfixapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class GetStarted extends AppCompatActivity implements View.OnClickListener {

    private TextView customer;
    private TextView technician;
    private TextView changeLanguage;
    private TextView AboutApp;
    private broadcastListener r;
String emaill;
String passwordd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadLocale();
        setContentView(R.layout.activity_get_started);

        technician = (TextView)findViewById(R.id.start_as_technician);
        customer = (TextView)findViewById(R.id.start_as_customer);
        changeLanguage = (TextView) findViewById(R.id.startas_txtlanguage);

        AboutApp = (TextView) findViewById(R.id.aboutSmartFix);

        technician.setOnClickListener(this);
        customer.setOnClickListener(this);
        changeLanguage.setOnClickListener(this);
        AboutApp.setOnClickListener(this);
        r=new broadcastListener();
    }

    @Override
    public void onClick(View view) {

        Intent intent;
        if (view.getId() == R.id.start_as_technician)
        {
            SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
            emaill = prefs.getString("email", null);
            passwordd = prefs.getString("password", null);



            if(emaill!=null || passwordd!=null){

                    intent=new Intent(this,MapsActivity.class);
                    intent.putExtra("continue","technician");
                    startActivity(intent);


                }

                else{
                    intent=new Intent(this,Login.class);
                    intent.putExtra("continue","technician");
                    startActivity(intent);

                }

         }


        else if(view.getId() == R.id.start_as_customer) {
            SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
            emaill = prefs.getString("email", null);
            passwordd = prefs.getString("password", null);
            if (emaill != null || passwordd!=null) {

                intent = new Intent(this, MapsActivity.class);
                intent.putExtra("continue", "customer");
                startActivity(intent);
            }

            else{
                intent=new Intent(this,Login.class);
                intent.putExtra("continue","technician");
                startActivity(intent);

            }

        }

        else if (view.getId() == R.id.startas_txtlanguage)
        {
            ShowChangeLanguageDialog();

        }
        else if (view.getId() == R.id.aboutSmartFix)
        {
            intent = new Intent(this,RetrofitActivity.class);
            startActivity(intent);

        }




    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentfilter=new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(r,intentfilter);
    }


    private void ShowChangeLanguageDialog() {

        final String[] languages = {"English", "Deutsch"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(GetStarted.this);
        mBuilder.setTitle("choose Langiage");
        mBuilder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0)
                {
                    setLocale("en");
                    recreate();
                }
                else if (which == 1)
                {
                    setLocale("de");
                    recreate();
                }


                dialog.dismiss();


            }
        });


        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());


        //save data to shared preferrence
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My Lang", lang);
        editor.apply();
    }



    // Load language saved in Shared Preference
    public void loadLocale()
    {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My Lang","");
        setLocale(language);


    }


}

