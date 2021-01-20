package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Intent;
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
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class NeighbourProfileActivity extends AppCompatActivity {

    public ImageView mAvatar;
    public FloatingActionButton mFavoriteButton;
    public TextView mNameTitle;
    public TextView mName;
    public TextView mPhoneNumber;
    public TextView mAddress;
    public TextView mFacebook;
    public TextView mAboutMeDescription;
    public TextView mAboutMe;
    private Neighbour neighbourProfile;
    private NeighbourApiService mApiService;
    public static final String PREF_KEY_FAVORITE_STATUS = "PREF_KEY_FAVORITE_STATUS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile);

        mAvatar = findViewById(R.id.avatarUrlProfile);
        mFavoriteButton = findViewById(R.id.item_favorite_button);
        mNameTitle = findViewById(R.id.nameTitle);
        mName = findViewById(R.id.name);
        mAddress = findViewById(R.id.address);
        mPhoneNumber = findViewById(R.id.phoneNumber);
        mFacebook = findViewById(R.id.facebook);
        mAboutMe = findViewById(R.id.aboutMe);
        mAboutMeDescription = findViewById(R.id.à_propos_de_moi);

        mApiService = DI.getNeighbourApiService();
        // on recupère l'intent envoyer de la liste des voisins
        Intent neighbourProfileActivityIntent = getIntent();

        // cette methode initialise la vue du voisin
        initView(neighbourProfileActivityIntent);


        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (neighbourProfile.getFavorite()) {
                    mFavoriteButton.setImageResource(R.drawable.ic_baseline_star_yellow_border_24);
                    //saveFavStatus(false);
                    neighbourProfile.setFavorite(false);
                } else {
                    mFavoriteButton.setImageResource(R.drawable.ic_baseline_yellow_star_24);
                    //saveFavStatus(true);
                    neighbourProfile.setFavorite(true);
                }
            }
        });


        mApiService.modifyNeighbour(neighbourProfile);

        }

    public static void openNeighbourProfile(FragmentActivity activity, Neighbour neighbour) {
            Intent neighbourProfileActivityIntent = new Intent(activity, NeighbourProfileActivity.class);
            neighbourProfileActivityIntent.putExtra(PREF_KEY_FAVORITE_STATUS, neighbour);
            ActivityCompat.startActivity(activity, neighbourProfileActivityIntent, null);
        }

    private void initView(Intent neighbourProfileActivityIntent) {
        neighbourProfile = neighbourProfileActivityIntent.getParcelableExtra(PREF_KEY_FAVORITE_STATUS);
        if (neighbourProfile != null) {

            String avatarUrl = neighbourProfile.getAvatarUrl();
            if(avatarUrl != null) {
                Glide.with(this)
                        .load(avatarUrl)
                        .timeout(60000)
                        .into(mAvatar);
            }

            String nameTitle = neighbourProfile.getName();
            if (nameTitle != null) {
                mNameTitle.setText(nameTitle);
            }

            String name = neighbourProfile.getName();
            if (name != null) {
                mName.setText(name);
            }

            String address = neighbourProfile.getAddress();
            if (address != null) {
                mAddress.setText(address);
            }

            String phoneNumber = neighbourProfile.getPhoneNumber();
            if (phoneNumber != null) {
                mPhoneNumber.setText(phoneNumber);
            }

            String facebook = neighbourProfile.getName();
            if (name != null) {
                mFacebook.setText("www.facebook.fr/"+name);
            }

            String aboutMe = neighbourProfile.getAboutMe();
            if (neighbourProfile != null) {
                mAboutMe.setText(aboutMe);
            }

            if (neighbourProfile.getFavorite()) {
                mFavoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
            } else {
                mFavoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
            }
        }
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

    }


