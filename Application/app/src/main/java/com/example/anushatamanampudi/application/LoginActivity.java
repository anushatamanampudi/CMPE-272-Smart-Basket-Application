package com.example.anushatamanampudi.application;

/**
 * Created by anushatamanampudi on 11/25/15.
 */
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


public class LoginActivity extends AppCompatActivity{

    private EditText editTextUserName;
    private EditText editTextPassword;
    public static String line;

    public static  String USER_NAME = "USERNAME";

    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    public void invokeLogin(View view){
        username = editTextUserName.getText().toString();
        password = editTextPassword.getText().toString();

        login(username, password);

    }
    public void invokeRegister(View view){
        Intent i=new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);

    }

    /*public void onClick(View v) {

        if (v.getId() == R.id.registerbutton) {
            Intent i=new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);

        }
    }*/
    private void login(final String username, String password) {

        class LoginAsync extends AsyncTask<String, Void, String>{

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(LoginActivity.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String username = params[0];
                    String password = params[1];
                    Log.v("uabdbhj",username);
                    Log.v("pwd",password);
                    String link = "http://smartbasketapp.mybluemix.net/login.php";
                    String data = ("username") + "=" + (username);
                      data += "&" + ("password") + "=" + (password);

                    URL url = new URL(link);

                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);

                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();


                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");

                    }
                    return sb.toString();
                }
                catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }



            }

            @Override
            protected void onPostExecute(String result){
                String s = result.trim();
                loadingDialog.dismiss();
                Log.v("jefhwjfhewk",s);
                if(s.equalsIgnoreCase("success")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    USER_NAME=username;
                    intent.putExtra(USER_NAME, username);
                    finish();
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(username, password);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }







}