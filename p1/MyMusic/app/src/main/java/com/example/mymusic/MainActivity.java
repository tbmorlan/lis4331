package com.example.mymusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button currentButton;
    private Button song1Button, song2Button, song3Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // initialize buttons
        song1Button = findViewById(R.id.txtSong1);
        song2Button = findViewById(R.id.txtSong2);
        song3Button = findViewById(R.id.txtSong3);

        // set listeners for buttons
        song1Button.setOnClickListener(v -> handleButtonClick(song1Button, R.raw.bathory));
        song2Button.setOnClickListener(v -> handleButtonClick(song2Button, R.raw.venom));
        song3Button.setOnClickListener(v -> handleButtonClick(song3Button, R.raw.death));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void handleButtonClick(Button clickedButton, int songResId) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            if (clickedButton == currentButton) {
                // if the same button is clicked again, reset visibility
                showAllButtons();
                return;
            }
        }

        // hide other buttons
        hideOtherButtons(clickedButton);

        // play the selected song
        mediaPlayer = MediaPlayer.create(this, songResId);
        mediaPlayer.start();

        // set current button
        currentButton = clickedButton;

        // release MediaPlayer when done
        mediaPlayer.setOnCompletionListener(mp -> {
            mp.release();
            mediaPlayer = null;
            showAllButtons();
        });
    }

    private void hideOtherButtons(Button activeButton) {
        if (activeButton != song1Button) song1Button.setVisibility(View.INVISIBLE);
        if (activeButton != song2Button) song2Button.setVisibility(View.INVISIBLE);
        if (activeButton != song3Button) song3Button.setVisibility(View.INVISIBLE);
    }

    private void showAllButtons() {
        song1Button.setVisibility(View.VISIBLE);
        song2Button.setVisibility(View.VISIBLE);
        song3Button.setVisibility(View.VISIBLE);
        currentButton = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
