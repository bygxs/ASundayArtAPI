package com.biniyam.asundayartapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.biniyam.asundayartapi.DetailActivity;
import com.biniyam.asundayartapi.R;
import com.biniyam.asundayartapi.response.ArtworkData;
import com.bumptech.glide.Glide;

import java.util.List;

public class ArtworkAdapter extends RecyclerView.Adapter<ArtworkAdapter.ViewHolder> {

    private List<ArtworkData> artworkList;
    private Context context;

    public ArtworkAdapter(List<ArtworkData> artworkList, Context context) {
        this.artworkList = artworkList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artwork, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArtworkData artwork = artworkList.get(position);
        holder.bind(artwork);

        // Set click listener on the itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked artwork
                ArtworkData clickedArtwork = artworkList.get(holder.getAdapterPosition());

                // Create an intent to start the DetailActivity
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("title", clickedArtwork.getTitle());
                intent.putExtra("imageId", clickedArtwork.getImageId());

                // Start the detail activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artworkList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }

        public void bind(ArtworkData artwork) {
            String title = artwork.getTitle();
            String imageId = artwork.getImageId();
            String iiifUrl = "https://www.artic.edu/iiif/2/" + imageId + "/full/843,/0/default.jpg";
            textView.setText(title);
            // Load the image using your preferred image loading library or method
            // For example, Glide or Picasso to load the image from the URL
            Glide.with(itemView.getContext())
                    .load(iiifUrl)
                    .into(imageView);
        }
    }
}
