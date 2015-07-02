package com.example.jessielhacke.finalprojectandroid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jessiel Hacke on 6/21/2015.
 */
public class Profile extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);

        Intent intent = getIntent();
        String profile_name = intent.getStringExtra("profile_name");
        TextView name = (TextView) findViewById(R.id.profileName);
        name.setText(profile_name);

        Float profile_distance = intent.getFloatExtra("profile_distance", 0);
        TextView distance = (TextView) findViewById(R.id.profileDistance);
        distance.setText(profile_name);

    }
}
