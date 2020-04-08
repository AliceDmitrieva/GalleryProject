package com.alisadmitrieva.galleryproject.ui.gallery;

import com.alisadmitrieva.galleryproject.data.model.Picture;

import java.util.List;

public class GalleryContract {

    interface View {
        void showPictures(List<Picture> pictures);

        void showProgress(boolean isLoading);

        void showErrorMessage(String message);
    }

    interface Presenter {
        void attachView(GalleryContract.View view);

        void loadPictures();

        void detachView();
    }

}
