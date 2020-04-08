package com.alisadmitrieva.galleryproject.ui.gallery;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alisadmitrieva.galleryproject.R;
import com.alisadmitrieva.galleryproject.data.model.Picture;
import com.alisadmitrieva.galleryproject.ui.fullsizeimage.FullsizeImageFragment;

import java.util.List;

import static com.alisadmitrieva.galleryproject.ui.gallery.GalleryRecyclerViewAdapter.setOnPictureClickListener;
import static com.alisadmitrieva.galleryproject.utils.CommonUtils.getColumnCount;

public class GalleryActivity extends AppCompatActivity implements GalleryContract.View, GalleryRecyclerViewAdapter.OnPictureClickListener {

    private GalleryPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView galleryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        galleryRecyclerView = findViewById(R.id.galleryRecyclerView);
        int columnCount = getColumnCount(this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), columnCount);
        galleryRecyclerView.setLayoutManager(layoutManager);
        setOnPictureClickListener(this);

        presenter = new GalleryPresenter();
        presenter.attachView(this);
        presenter.loadPictures();
    }

    @Override
    public void showPictures(List<Picture> pictures) {
        GalleryRecyclerViewAdapter galleryRecyclerViewAdapter = new GalleryRecyclerViewAdapter(pictures);
        galleryRecyclerView.setAdapter(galleryRecyclerViewAdapter);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadPictures());
    }

    @Override
    public void showProgress(boolean isLoading) {
        swipeRefreshLayout.setRefreshing(isLoading);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPictureClick(Picture picture) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FullsizeImageFragment fragment = FullsizeImageFragment.newInstance(picture);
        fragmentManager.beginTransaction()
                .add(R.id.mainLayout, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

}
