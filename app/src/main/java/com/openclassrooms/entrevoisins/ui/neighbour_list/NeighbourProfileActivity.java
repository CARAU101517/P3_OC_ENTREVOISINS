package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Intent;
import android.content.SharedPreferences;
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

public class NeighbourProfileActivity extends AppCompatActivity {

    public ImageView mAvatar;
    public FloatingActionButton mFavoriteButton;
    public TextView mName;
    public TextView mPhoneNumber;
    public TextView mAddress;
    public TextView mAboutMe;
    private SharedPreferences mPreferences;
    private Neighbour neighbourProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile);

        mPreferences = getPreferences(MODE_PRIVATE);

        Intent neighbourProfileActivityIntent = getIntent();
        neighbourProfile = neighbourProfileActivityIntent.getParcelableExtra("neighbourProfile");

        String avatarUrl = "";
        mAvatar = findViewById(R.id.avatarUrl);
        avatarUrl = neighbourProfile.getAvatarUrl();
        if (neighbourProfile != null)
        Glide.with(this)
                .load(avatarUrl)
                .timeout(60000)
                .into(mAvatar);

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
        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean isFavorite = readStatus();
               if (isFavorite) {
                   mFavoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
                   saveStatus(false);
                   neighbourProfile.setFavorite(false);
               }
               else {
                   mFavoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
                   saveStatus(true);
                   neighbourProfile.setFavorite(true);
               }

            }

            private boolean readStatus() {
                return mPreferences.getBoolean("Status", true);
            }

            private void saveStatus(boolean isFavorite) {
                mPreferences.edit().putBoolean("Status", isFavorite).commit();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
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



