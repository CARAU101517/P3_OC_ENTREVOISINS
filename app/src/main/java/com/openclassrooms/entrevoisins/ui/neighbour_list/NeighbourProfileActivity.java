package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import static android.net.Uri.parse;
import static java.lang.Integer.*;

public class NeighbourProfileActivity extends AppCompatActivity {

    public ImageView mAvatar;
    public FloatingActionButton mFavoriteButton;
    public TextView mName;
    public TextView mPhoneNumber;
    public TextView mAddress;
    public TextView mAboutMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile);

        Intent neighbourProfileActivityIntent = getIntent();
        Neighbour neighbourProfile = neighbourProfileActivityIntent.getParcelableExtra("neighbourProfile");

        String avatarUrl = "";
        if (neighbourProfile != null)
            avatarUrl = neighbourProfile.getAvatarUrl();
        mAvatar = findViewById(R.id.avatarUrl);
        mAvatar.setImageURI(Uri.parse(avatarUrl));

        String name = "";
        if (neighbourProfile != null)
            name = neighbourProfile.getName();
        mName = findViewById(R.id.name);
        mName.setText(name);

        String phoneNumber = "";
        if (neighbourProfile != null)
            phoneNumber = neighbourProfile.getPhoneNumber();
        mPhoneNumber = findViewById(R.id.phoneNumber);
        mPhoneNumber.setText(phoneNumber);

        String address = "";
        if (neighbourProfile != null)
            address = neighbourProfile.getAddress();
        mAddress = findViewById(R.id.address);
        mAddress.setText(address);

        String aboutMe = "";
        if (neighbourProfile != null)
            aboutMe = neighbourProfile.getAboutMe();
        mAboutMe = findViewById(R.id.aboutMe);
        mAboutMe.setText(aboutMe);


        mFavoriteButton = findViewById(R.id.item_favorite_button);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (mFavoriteButton != null)
                    setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public static void openNeighbourProfile(FragmentActivity activity, Neighbour neighbour) {
        Intent neighbourProfileActivityIntent = new Intent(activity, NeighbourProfileActivity.class);
        neighbourProfileActivityIntent.putExtra("neighbourProfile", neighbour);
        ActivityCompat.startActivity(activity, neighbourProfileActivityIntent, null);
    }

}



