package com.example.jessielhacke.finalprojectandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
public class SignUp extends Activity {
    private String name;
    private String email;
    private String pass;
    private TextView txt_name;
    private TextView txt_email;
    private TextView txt_pass;
    private TextView txt_pass_confirm;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_screen);

        txt_name = (TextView)findViewById(R.id.editSignupName);
        txt_email = (TextView)findViewById(R.id.editSignupEmail);
        txt_pass = (TextView)findViewById(R.id.editSignupPass);
        txt_pass_confirm = (TextView)findViewById(R.id.editSignupPassConfirm);
    }

    public void onClick_Register(View v) {
        String name, email, pass1, pass2;

        name = txt_name.getText().toString();
        email = txt_email.getText().toString();
        pass1 = txt_pass.getText().toString();
        pass2 = txt_pass_confirm.getText().toString();
        if( pass1.contentEquals(pass2) ) {
            new Post().execute(getResources().getString(R.string.URLServer) + "/cad_user.php", name, email, pass1);
        }else {
            Toast.makeText(SignUp.this, "Senha não confere", Toast.LENGTH_SHORT).show();
            Log.d("DBG", "WRONG PASSWORD!");
        }
    }

    private class Post extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String ...param) {
            String ret = null;

            HttpClient client = new DefaultHttpClient();
            String postURL = param[0];
            HttpPost post = new HttpPost(postURL);
            try {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
                pairs.add(new BasicNameValuePair("NAME", param[1]));
                pairs.add(new BasicNameValuePair("EMAIL", param[2]));
                pairs.add(new BasicNameValuePair("PASS", param[3]));

                UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(pairs);
                post.setEntity(uefe);
                // Send post.
                HttpResponse res = client.execute(post);
                if( res != null ) {
                    //Log.i("RESPONSE", EntityUtils.toString(res.getEntity()));
                    ret = EntityUtils.toString(res.getEntity());
                }
            } catch( UnsupportedEncodingException uee ) {
                uee.printStackTrace();
            } catch( ClientProtocolException cpe ) {
                cpe.printStackTrace();
            } catch( IOException ioe ) {
                ioe.printStackTrace();
            }

            return ret;
        }

        @Override
        protected void onPostExecute(String ret) {
            super.onPostExecute(ret);
            Log.d("DBG", ret);
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
