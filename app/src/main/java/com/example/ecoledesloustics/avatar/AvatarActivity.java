package com.example.ecoledesloustics.avatar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.ecoledesloustics.R;
import com.example.ecoledesloustics.users_data.UserAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AvatarActivity extends AppCompatActivity {
    List<Integer> images = Arrays.asList(
            R.drawable.ic_fille,
            R.drawable.ic_garcon,
            R.drawable.ic_fille_2,
            R.drawable.ic_garcon_2,
            R.drawable.ic_fille_3,
            R.drawable.ic_garcon_3,
            R.drawable.ic_fille_4,
            R.drawable.ic_garcon_4,
            R.drawable.ic_fille_5,
            R.drawable.ic_garcon_5,
            R.drawable.ic_fille_6,
            R.drawable.ic_garcon_6,
            R.drawable.ic_garcon_7
    );

    private RecyclerView avatarRV;
    private AvatarAdapter avatarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        avatarRV = findViewById(R.id.idRVAvatar);

        // we are initializing our adapter class and passing our arraylist to it.
        avatarAdapter = new AvatarAdapter(this, images);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        avatarRV.setLayoutManager(linearLayoutManager);
        avatarRV.setAdapter(avatarAdapter);

        // previous button
        ImageButton previousIB = findViewById(R.id.idIBPrevious);
        previousIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



}