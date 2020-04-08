package com.alisadmitrieva.galleryproject.ui.fullsizeimage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alisadmitrieva.galleryproject.R;
import com.alisadmitrieva.galleryproject.data.model.Picture;
import com.squareup.picasso.Picasso;

public class FullsizeImageFragment extends Fragment {

    private static final String ARGUMENT_PICTURE_URL = "picture_url";

    public static FullsizeImageFragment newInstance(Picture picture) {
        FullsizeImageFragment fragment = new FullsizeImageFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_PICTURE_URL, picture.getPictureURL());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.fullsizeimage_fragment, null);
        ImageView fullsizeImageView = view.findViewById(R.id.fullsizeImage);

        Bundle arguments = getArguments();
        if (arguments == null) {
            throw new IllegalArgumentException("arguments == null");
        }

        String pictureURL = arguments.getString(ARGUMENT_PICTURE_URL);
        if (pictureURL == null) {
            throw new IllegalArgumentException("pictureURL == null");
        }

        Picasso.get().load(pictureURL).placeholder(R.drawable.ic_launcher_foreground).into(fullsizeImageView);
        return view;
    }

}
