package com.example.group14_ic09;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;



import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class SignUp extends AppCompatActivity {

    EditText email;
    EditText fname;
    EditText lname;
    EditText password1;
    EditText password2;
    String password,firstname,lastname,emailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email =findViewById(R.id.et_email);
        fname =findViewById(R.id.et_fname);
        lname =findViewById(R.id.et_lname);
        password1 =findViewById(R.id.et_password_1);
        password2 =findViewById(R.id.et_password_2);

        if(password1.getText().toString().equals(password2.getText().toString())){
            password = password1.getText().toString();
        }


        Log.d("demo",email.getText().toString());
        Log.d("demo",fname.getText().toString());
        Log.d("demo",lname.getText().toString());
        Log.d("demo",password);
        findViewById(R.id.buttonSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    firstname = fname.getText().toString();
                    lastname = lname.getText().toString();
                    emailid = email.getText().toString();
                    new getNewAysnc().execute(" ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }





    private class getNewAysnc extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("email", emailid)
                    .add("password", password)
                    .add("fname", firstname)
                    .add("lname", lastname)
                    .build();
                Log.d("demo", emailid);

            Request request = new Request.Builder()
                    .url("http://ec2-18-234-222-229.compute-1.amazonaws.com/api/signup")
                    .post(formBody)
                    .build();
            Log.d("demo", String.valueOf(request));
            try {
                Response response = client.newCall(request).execute();
//                Log.d("demo", String.valueOf(response));
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//                System.out.println(response.body().string());
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("demo",""+s);
            super.onPostExecute(s);
        }
    }


}
