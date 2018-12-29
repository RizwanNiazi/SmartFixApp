package com.example.dell.smartfixapp;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity implements View.OnClickListener {
    //public modeldb d = new modeldb();

    DatabaseReference mdata;
    DatabaseReference ref;
    private FirebaseAuth mAuth;

    String Continue;

    private static EditText userName;
    private static EditText userEmail;
    private static EditText userPassword;

    private EditText userConformPassword;
    private Spinner spinner;

    private Button signUp;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth=FirebaseAuth.getInstance();

        Continue = getIntent().getStringExtra("continue");
        Toast.makeText(getApplicationContext(),Continue,Toast.LENGTH_SHORT).show();
        init();

    }

    public void init()
    {
        userName = (EditText)findViewById(R.id.signup_txt_name);
        userEmail = (EditText)findViewById(R.id.signup_txt_email);
        userPassword = (EditText)findViewById(R.id.signup_txt_password);
        userConformPassword = (EditText)findViewById(R.id.signup_txt_cpassword);

        spinner = (Spinner)findViewById(R.id.signup_spin_cities);

        signUp = (Button)findViewById(R.id.btnsignup);
        login = (TextView)findViewById(R.id.signup_txtsignin);


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(signup.this,R.array.cities,R.layout.spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference("user").push();

        spinner.setAdapter(adapter);


        signUp.setOnClickListener(this);
        login.setOnClickListener(this);

    }
    public void regd(View view) {
        Toast.makeText(this, userName.getText().toString() + "User added in database", Toast.LENGTH_SHORT).show();

        /*
        modeldb d=new modeldb();
        d.setPassword(userPassword.getText().toString(), d.counterr);
        d.setUserEmail(userEmail.getText().toString(), d.counterr);
        d.setName(userName.getText().toString(), d.counterr);
        String location = spinner.getSelectedItem().toString();
        d.setLocation(location, d.counterr);
        d.counterr=d.counterr+1;
        */
        //Toast.makeText(this,d.getPassword(d.counterr-1), Toast.LENGTH_SHORT).show();

        //SharedPreferences.Editor editor= getSharedPreferences("MyPreference",MODE_PRIVATE).edit();
        //editor.putString("name", userName.getText().toString());
        //editor.putString("password", userPassword.getText().toString());
        //editor.putString("email", userEmail.getText().toString());
        //editor.apply();

    }
    private static class WorkerTaskFromAsync extends AsyncTask<Void, Void, Integer> {

        interface InnerWorkerInterface{
            void onWorkStarted(boolean status);
            void onWorkFinished(boolean status);
        }
        InnerWorkerInterface workerInterface;
        private void setListener(InnerWorkerInterface innerWorkerInterface){
            workerInterface = innerWorkerInterface;
        }

        void removeListener(){
            workerInterface = null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            workerInterface.onWorkStarted(true);
            Log.i("MainActivity", " Thread from onPreExecute: "+Thread.currentThread().getId());
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            Log.i("MainActivity", " Thread from doInBackground: "+Thread.currentThread().getId());
            for(int i=0; i < 10; i++){
                if(isCancelled()){
                    break;
                }
                try{
                    Log.i("MainActivity", "Id: "+i);
                    Thread.sleep(1000);
                    SharedPreferences.Editor editor=null;
                    editor.putString("name", userName.getText().toString());
                    editor.putString("password", userPassword.getText().toString());
                    editor.putString("email", userEmail.getText().toString());
                    editor.apply();
                    //checkValidations();
                    //validate();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return null;
 .       }

        @Override
        protected void onPostExecute(Integer value) {
            Log.i("MainActivity", " Thread from onPostExecute: "+Thread.currentThread().getId());
            super.onPostExecute(value);
            if(workerInterface!=null){
                workerInterface.onWorkFinished(true);
            }else{
                Log.i("MainActivity","Worker Interface is null");
            }
        }
    }
    @Override
    public void onClick(View view) {
        Intent intent;


        if (view.getId() == R.id.btnsignup) {



            SharedPreferences.Editor editor= getSharedPreferences("MyPreferences",MODE_PRIVATE).edit();
            editor.putString("name", userName.getText().toString());
            editor.putString("password", userPassword.getText().toString());
            editor.putString("email", userEmail.getText().toString());
            editor.apply();

            Toast.makeText(this, "Successfully SignUp", Toast.LENGTH_SHORT).show();

            intent = new Intent(this, Login.class);
            intent.putExtra("continue",Continue);
            startActivity(intent);
            /*
            d.setPassword(userPassword.getText().toString(), d.counterr);
            d.setUserEmail(userEmail.getText().toString(), d.counterr);
            d.setName(userName.getText().toString(), d.counterr);
            String location = spinner.getSelectedItem().toString();
            d.setLocation(location, d.counterr);
            d.counterr = d.counterr + 1;

              */

            double longitud=1.22232312;
            double lattitud=2.51378213;

            ref.child("name").setValue(userName.getText().toString());
            ref.child("email").setValue(userEmail.getText().toString());
            ref.child("usertype").setValue(Continue);
            ref.child("password").setValue(userPassword.getText().toString());
            ref.child("longitude").setValue(longitud);
            ref.child("lattitude").setValue(lattitud);





            //firebase
//            String id=userEmail.getText().toString();
            //          mdata.child(id).setValue(userPassword.getText().toString());
            String id=userName.getText().toString();


/////not to be removed
            /*
            mdata= FirebaseDatabase.getInstance().getReference("login").push();


            mdata.child("email").setValue(userEmail.getText().toString());
            mdata.child("password").setValue(userPassword.getText().toString());
            mdata.child("name").setValue(userName.getText().toString());

*/

            /////////////////////////////
            mAuth.createUserWithEmailAndPassword(userEmail.getText().toString(), userPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        //     Log.d(TAG, "createUserWithEmail:success");
                        Toast.makeText(getApplicationContext(),"user registered successfully"  ,Toast.LENGTH_SHORT).show();

                        //FirebaseUser user = mAuth.getCurrentUser();


                        //user u= new (user.password, );
                    }
                    else {
                        // If sign in fails, display a message to the user.
                        //   Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        if(task.getException() instanceof FirebaseAuthUserCollisionException)
                        {

                            Toast.makeText(getApplicationContext(), "You have already registered",Toast.LENGTH_SHORT).show();

                        }
                        else{


                            Toast.makeText(getApplicationContext(), "Some error occured",Toast.LENGTH_SHORT).show();

                        }
                        //updateUI(null);
                    }

                }
            });


        }


        else if (view.getId() == R.id.signup_txtsignin)
        {
            intent = new Intent(this,Login.class);
            intent.putExtra("continue",Continue);
            startActivity(intent);
        }

    }

        }






