package com.example.jessielhacke.finalprojectandroid;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jessiel Hacke on 6/21/2015.
 */
public class LoginMain extends Activity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        final TextView vEmail = (TextView)findViewById(R.id.emailLogin);
        final TextView vPass = (TextView)findViewById(R.id.passwordLogin);

        TextView externalLogin = (TextView) findViewById(R.id.externalLogin);
        final LoginMain login = this;
        externalLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(login, ExternalLogin.class);
                startActivity(intent);
            }
        });
        TextView signup = (TextView) findViewById(R.id.newUser);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(login, SignUp.class);
                startActivity(intent);
            }
        });

        Button btnLogin = (Button) findViewById(R.id.buttonEntrar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(LoginMain.this, ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMessage("Login");
                progressDialog.show();
                new Post().execute(getResources().getString(R.string.URLServer) + "/login.php", vEmail.getText().toString(), vPass.getText().toString());
            }
        });
    }

    private class Post extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String ...param) {
            boolean succed = false;

            HttpClient client = new DefaultHttpClient();
            String postURL = param[0];
            HttpPost post = new HttpPost(postURL);
            try {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(2);
                pairs.add(new BasicNameValuePair("EMAIL", param[1]));
                pairs.add(new BasicNameValuePair("PASS", param[2]));
                UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(pairs);
                post.setEntity(uefe);
                // Send post.
                HttpResponse res = client.execute(post);
                succed = ( res != null && EntityUtils.toString(res.getEntity()).compareTo("1") == 1 );
            } catch( UnsupportedEncodingException uee ) {
                uee.printStackTrace();
            } catch( ClientProtocolException cpe ) {
                cpe.printStackTrace();
            } catch( IOException ioe ) {
                ioe.printStackTrace();
            }

            return succed;
        }

        @Override
        protected void onPostExecute(Boolean succ) {
            super.onPostExecute(succ);
            if( succ ) {
                Intent intent = new Intent(LoginMain.this, MainActivity.class);
                startActivity(intent);
            }else Toast.makeText(LoginMain.this, "Email ou senha incorreta.", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
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
