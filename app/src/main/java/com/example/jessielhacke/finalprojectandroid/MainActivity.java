package com.example.jessielhacke.finalprojectandroid;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando...");
        progressDialog.show();
        new Post().execute("http://kvmae.c3local/users.php");
    }

    private class Profile {
        private int id;
        private String name;
        private int lat, lon;
        private int distance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private class Post extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String ...parameter) {
            String resStrEntity = "";

            HttpClient client = new DefaultHttpClient();
            String postURL = parameter[0];
            HttpPost post = new HttpPost(postURL);
            try {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
                pairs.add(new BasicNameValuePair("GETALL", ""));
                UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(pairs);
                post.setEntity(uefe);
                // Send post.
                HttpResponse res = client.execute(post);
                // Get answer.
                HttpEntity resEntity = res.getEntity();
                if( resEntity != null ) {
                    resStrEntity = EntityUtils.toString(resEntity);
                    Log.i("RESPONSE", resStrEntity);
                }
            } catch( UnsupportedEncodingException uee ) {
                uee.printStackTrace();
            } catch( ClientProtocolException cpe ) {
                cpe.printStackTrace();
            } catch( IOException ioe ) {
                ioe.printStackTrace();
            }

            return resStrEntity;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.cancel();
            // Parse entries
        }
    }

    private class CSVParser extends BufferedReader {
        private InputStreamReader in;

        public CSVParser(InputStreamReader _in) {
            super(_in);
            this.in = _in;
        }

        public void parser() {
            private String value;

            try {
                String line;
                while ((line = this.in.read readLine()) != null) {
                    String[] RowData = line.split(",");
                    value = RowData[0];
                    value = RowData[1];
                    // do something with "data" and "value"
                }
            } catch (IOException ex) {
                // handle exception
                ex.printStackTrace();
            } finally {
                try {
                    this.in.close();
                } catch (IOException e) {
                    // handle exception
                    e.printStackTrace();
                }
            }
        }
    }
}
