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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class NeighbourProfileActivity extends AppCompatActivity {

    public ImageView mAvatar;
    public ImageButton mReturnButton;
    public FloatingActionButton mFavoriteButton;
    public TextView mNameTitle;
    public TextView mName;
    public TextView mPhoneNumber;
    public TextView mAddress;
    public TextView mAboutMeDescription;
    public TextView mAboutMe;
    private Neighbour neighbourProfile;
    private NeighbourApiService mApiService;
    public static final String PREF_KEY_FAVORITE_STATUS = "PREF_KEY_FAVORITE_STATUS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_profile);

        mAvatar = findViewById(R.id.avatarUrl);
        mReturnButton = findViewById(R.id.return_button);
        mFavoriteButton = findViewById(R.id.item_favorite_button);
        mNameTitle = findViewById(R.id.nameTitle);
        mName = findViewById(R.id.name);
        mAddress = findViewById(R.id.address);
        mPhoneNumber = findViewById(R.id.phoneNumber);
        mAboutMe = findViewById(R.id.aboutMe);
        mAboutMeDescription = findViewById(R.id.à_propos_de_moi);

        mApiService = DI.getNeighbourApiService();
        // on recupère l'intent envoyer de la liste des voisins
        Intent neighbourProfileActivityIntent = getIntent();

        // cette methode initialise la vue du voisin
        initView(neighbourProfileActivityIntent);

        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(NeighbourProfileActivity.this, ListNeighbourActivity.class);
                startActivity(intent);
            }
        });

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (neighbourProfile.getFavorite()) {
                    mFavoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
                    //saveFavStatus(false);
                    neighbourProfile.setFavorite(false);
                } else {
                    mFavoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
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
        String avatarUrl = "";
        avatarUrl = neighbourProfile.getAvatarUrl();
        if (neighbourProfile != null)
            Glide.with(this)
                    .load(avatarUrl)
                    .timeout(60000)
                    .into(mAvatar);

        String nameTitle = "";
        if (neighbourProfile != null)
            nameTitle = neighbourProfile.getName();
        mNameTitle.setText(nameTitle);

        String name = "";
        if (neighbourProfile != null)
            name = neighbourProfile.getName();
        mName.setText(name);

        String address = "";
        if (neighbourProfile != null)
            address = neighbourProfile.getAddress();
        mAddress.setText(address);

        String phoneNumber = "";
        if (neighbourProfile != null)
            phoneNumber = neighbourProfile.getPhoneNumber();
        mPhoneNumber.setText(phoneNumber);

        String aboutMe = "";
        if (neighbourProfile != null)
            aboutMe = neighbourProfile.getAboutMe();
        mAboutMe.setText(aboutMe);

        if (neighbourProfile.getFavorite()) {
            mFavoriteButton.setImageResource(R.drawable.ic_star_white_24dp);
        } else {
            mFavoriteButton.setImageResource(R.drawable.ic_star_border_white_24dp);
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


