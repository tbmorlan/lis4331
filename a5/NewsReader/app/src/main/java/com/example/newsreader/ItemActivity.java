package com.example.newsreader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        // show app icon in emulator
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // get references to widgets
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        TextView pubDateTextView = (TextView) findViewById(R.id.pubDateTextView);
        TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        TextView linkTextView = (TextView) findViewById(R.id.linkTextView);

        // get intent
        Intent intent = getIntent();

        // get data from intent
        String pubDate = intent.getStringExtra("pubDate");
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description").replace("\"", "");

        // display data on widgets
        pubDateTextView.setText(pubDate);
        titleTextView.setText(title);
        descriptionTextView.setText(description);

        // set listener
        linkTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // get implicit Intent
        Intent intent = getIntent();

        // get url for link
        String link = intent.getStringExtra("link");
        Uri uri = Uri.parse(link);

        // create intent and start it (using default browser)
        Intent viewIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(viewIntent);
    }
}