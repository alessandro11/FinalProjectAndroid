package com.example.jessielhacke.finalprojectandroid;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        new Post().execute(getResources().getString(R.string.URLServer) + "users.php");
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

        public int getLat() {
            return lat;
        }

        public void setLat(int lat) {
            this.lat = lat;
        }

        public int getLon() {
            return lon;
        }

        public void setLon(int lon) {
            this.lon = lon;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }

    private class Post extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String ...param) {
            InputStream is = null;

            HttpClient client = new DefaultHttpClient();
            String postURL = param[0];
            HttpPost post = new HttpPost(postURL);
            try {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
                pairs.add(new BasicNameValuePair("GETALL", ""));
                UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(pairs);
                post.setEntity(uefe);
                // Send post.
                HttpResponse res = client.execute(post);
                if( res != null ) {
                    //Log.i("RESPONSE", EntityUtils.toString(res.getEntity()));
                    is = res.getEntity().getContent();
                }
            } catch( UnsupportedEncodingException uee ) {
                uee.printStackTrace();
            } catch( ClientProtocolException cpe ) {
                cpe.printStackTrace();
            } catch( IOException ioe ) {
                ioe.printStackTrace();
            }

            return is;
        }

        @Override
        protected void onPostExecute(InputStream is) {
            super.onPostExecute(is);

            // Parse entries
            List<Profile> profiles = new CSVParser(is).parser();
            //Log.i("PROFILES", String.valueOf(profiles.size()));
            if( profiles.size() > 0 ) {
                MyArrayAdapter aa = new MyArrayAdapter(MainActivity.this, profiles);
                setListAdapter(aa);
            }

            progressDialog.cancel();
        }
    }


    private class GetPicture extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        private GetPicture(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... param) {
            Bitmap img = null;
            try {
                InputStream in = new java.net.URL(param[0]).openStream();
                img = BitmapFactory.decodeStream(in);
            }catch( Exception e ) {
                Log.e("ERROR", e.getMessage());
            }

            return img;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            bmImage.setImageBitmap(bitmap);
        }
    }

    private class CSVParser extends BufferedReader {
        private InputStream is;
        private List<Profile> profiles;

        public List<Profile> getProfiles() {
            return profiles;
        }

        public CSVParser(InputStream _is) {
            super(new InputStreamReader(_is));
            this.is = _is;
            profiles = new ArrayList<Profile>();
        }

        public List<Profile> parser() {
            String value;

            try {
                String line;
                while( (line = readLine()) != null && ! line.isEmpty() ) {
                    String[] RowData = line.split(",");
                    Profile p = new Profile();
                    p.setId(Integer.parseInt(RowData[0]));
                    p.setName(RowData[1]);
                    p.setLat(Integer.parseInt(RowData[2]));
                    p.setLon(Integer.parseInt(RowData[3]));
                    profiles.add(p);
                }
            } catch (IOException ex) {
                // handle exception
                ex.printStackTrace();
            } finally {
                try {
                    this.is.close();
                } catch (IOException e) {
                    // handle exception
                    e.printStackTrace();
                }
            }

            return profiles;
        }
    }

    private class MyArrayAdapter extends ArrayAdapter<List> {
        private final Context context;
        private final List profiles;

        @SuppressWarnings("unchecked")
        public MyArrayAdapter(Context c, List p) {
            super(c, R.layout.activity_main, p);
            this.context = c;
            this.profiles = p;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.activity_main, parent, false);

            TextView name = (TextView)rowView.findViewById(R.id.name);
            ImageView img = (ImageView)rowView.findViewById(R.id.picture);

            Profile p = (Profile)profiles.get(position);
            name.setText(p.getName());

            new GetPicture(img).execute(getResources().getString(R.string.URLServer) + p.getId());

            return rowView;
        }
    }
}
