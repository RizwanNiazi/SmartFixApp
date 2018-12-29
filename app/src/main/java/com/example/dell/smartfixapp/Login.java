package com.example.dell.smartfixapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import static java.lang.Thread.sleep;

public class Login extends AppCompatActivity implements View.OnClickListener {



    DatabaseReference mdata;
    DatabaseReference ref;

    FirebaseAuth mAuth;

    String email,password,error, continueAs;

    private EditText userEmail;
    private EditText userPassword;


    private TextView forgetPassword;
    private Button login;
    private TextView newAccount;
    public boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth= FirebaseAuth.getInstance();
        continueAs = getIntent().getStringExtra("continue");
        Toast.makeText(getBaseContext(),continueAs,Toast.LENGTH_SHORT).show();

        init();

    }

    public void init()
    {
        userEmail = (EditText)findViewById(R.id.login_txt_email);
        userPassword = (EditText)findViewById(R.id.login_txt_password);

        forgetPassword = (TextView)findViewById(R.id.login_txt_forgetpassword);
        login = (Button)findViewById(R.id.login_btnlogin);
        newAccount = (TextView)findViewById(R.id.login_txtsignup);

        forgetPassword.setOnClickListener(this);
        login.setOnClickListener(this);
        newAccount.setOnClickListener(this);
        //new WorkerTaskFromAsync().execute();


    }
/////////////


    @Override
    public void onClick(View view) {
        Intent intent;

        if (view.getId() == R.id.login_txt_forgetpassword) {
            intent = new Intent(this, forgetPassword.class);
            intent.putExtra("continue", continueAs);
            startActivity(intent);
        } else if (view.getId() == R.id.login_txtsignup) {
            intent = new Intent(this, signup.class);
            intent.putExtra("continue", continueAs);
            startActivity(intent);
        } else if (view.getId() == R.id.login_btnlogin) {
            if (checkValidations() && validate() && flag == true) {

                    Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();

                    intent = new Intent(this, MapsActivity.class);
                    intent.putExtra("continue", continueAs);
                    startActivity(intent);

                }
            else

                {
                 //   Toast.makeText(this, "User does not exist in database", Toast.LENGTH_SHORT).show();
                }

            }

        }





    private boolean validate(){

        //  Toast.makeText(getApplicationContext(),"abcdefghijkl " ,Toast.LENGTH_SHORT).show();
        String emaill=null;
        String passwordd=null;

        SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        if(prefs!=null){

            emaill = prefs.getString("email", null);
            passwordd = prefs.getString("passsword", null);

            // Toast.makeText(getApplicationContext(),"email "+email+"  pass  "+ password ,Toast.LENGTH_SHORT).show();

            if(emaill!= null && passwordd!=null)
            {

                Toast.makeText(getApplicationContext(),"in 1 " ,Toast.LENGTH_SHORT).show();

                if(emaill.equals(userEmail.getText().toString()) && passwordd.equals(userPassword.getText().toString())){
                    flag=true;
                }
            }
        }
        /////////////////////////////

        if(emaill==null || passwordd==null){


            Toast.makeText(getApplicationContext(),"abcdefghijkl " ,Toast.LENGTH_SHORT).show();


            mAuth.signInWithEmailAndPassword(userEmail.getText().toString(), userPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(getApplicationContext(), "Signed innn", Toast.LENGTH_SHORT).show();
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            if (task.isSuccessful()) {

                                // Sign in success, update UI with the signed-in user's information
                                flag=true;
                                //  Log.d(TAG, "signInWithEmail:success");
                                //FirebaseUser user = auth.getCurrentUser();
                                //updateUI(user);
                                Toast.makeText(getApplicationContext(), "Signed in verified", Toast.LENGTH_SHORT).show();

                            } else if (!task.isSuccessful()) {

                                flag=false;
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });





            // Toast.makeText(getApplicationContext(),"after " ,Toast.LENGTH_SHORT).show();


            //Toast.makeText(getApplicationContext(),"In 2 " ,Toast.LENGTH_SHORT).show();

            //else if(email == null || password==null || flag==true){
/*           String a=null;

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("login");
*/

//             FirebaseDatabase database = FirebaseDatabase.getInstance();
            //           DatabaseReference ref = database.getReference().child(user.getUid());



 /*
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    String username=dataSnapshot.child("username").getValue().toString();
                    String password= dataSnapshot.child("password").getValue().toString();

flag=true;

                    Toast.makeText(getApplicationContext(),username+" "+ password  ,Toast.LENGTH_SHORT).show();

                    /*
                    String value= null;
                    for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){

//                        Log.e("TAG", "======="+singleSnapshot.child("email").getValue());
 //                       Log.e("TAG", "======="+singleSnapshot.child("name").getValue());
                       //user u = dataSnapshot.getValue(user.class);
//                          a=u.getName();
//                        Toast.makeText(getApplicationContext(),"value is"  ,Toast.LENGTH_SHORT).show();
//System.out.print(u);
                    }
//*                    //String value = dataSnapshot.getValue(String.class);


//Toast.makeText(getApplicationContext(),"value is "+   ,Toast.LENGTH_SHORT).show();
                    //Log.d(TAG, "Value is: " + value);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //Log.w(TAG, "Failed to read value.", error.toException());

                    Toast.makeText(getApplicationContext(),"Error reading db " ,Toast.LENGTH_SHORT).show();

                }
            });
*/

//method to get the data from firebase
        }

       /* //modeldb aa=new modeldb();
        for(int i=0;i<modeldb.counterr;i++){
            if(modeldb.getUserEmail(i).equals(userEmail.getText().toString())){
                if(modeldb.getPassword(i).equals(userPassword.getText().toString()))
                {

                    flag=true;
                    //           break;
                }
            }

        }*/

        return flag;
    }



    private boolean checkValidations() {

        email=userEmail.getText().toString().trim();
        password=userPassword.getText().toString().trim();

        int color = ContextCompat.getColor(this,R.color.darkgray);


        if (email.length()==0)
        {
            error=getResources().getString(R.string.email_require);
            userEmail.setError(error);
            userEmail.setTextColor(color);
            return false;
        }
        else
        {
            if(!isValidMail(email))
            {
                error=getResources().getString(R.string.invalid_email);
                userEmail.setError(error);

                userEmail.setTextColor(color);
                return false;
            }
        }

        if (password.equals(null))
        {
            error=getResources().getString(R.string.password_missing);
            userPassword.setError(error);
            userEmail.setTextColor(color);
            return false;
        }

        return true;
    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
