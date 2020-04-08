package com.alisadmitrieva.galleryproject.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alisadmitrieva.galleryproject.R;
import com.alisadmitrieva.galleryproject.data.model.Picture;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryRecyclerViewAdapter extends RecyclerView.Adapter<GalleryRecyclerViewAdapter.PictureViewHolder> {

    private List<Picture> pictures;
    private static OnPictureClickListener onPictureClickListener;

    public interface OnPictureClickListener {
        void onPictureClick(Picture picture);
    }

    static void setOnPictureClickListener(OnPictureClickListener listener) {
        onPictureClickListener = listener;
    }

    GalleryRecyclerViewAdapter(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        holder.bindPicture(pictures.get(position));
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    class PictureViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        Picture picture;

        PictureViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }

        void bindPicture(Picture picture) {
            this.picture = picture;
            Picasso.get().load(picture.getPictureURL()).placeholder(R.drawable.ic_launcher_foreground).into(imageView);
            imageView.setOnClickListener(v -> onPictureClickListener.onPictureClick(picture));
        }
    }

}
