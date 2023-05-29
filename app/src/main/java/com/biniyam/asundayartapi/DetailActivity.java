package com.biniyam.asundayartapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView);
        titleTextView = findViewById(R.id.titleTextView);

        // Get the artwork details from the intent
        String title = getIntent().getStringExtra("title");
        String imageId = getIntent().getStringExtra("imageId");
        String iiifUrl = "https://www.artic.edu/iiif/2/" + imageId + "/full/843,/0/default.jpg";

        // Set the artwork details in the views
        titleTextView.setText(title);
        // Load the image using your preferred image loading library or method
        // For example, Glide or Picasso to load the image from the URL
        Glide.with(this)
                .load(iiifUrl)
                .into(imageView);
    }
}
