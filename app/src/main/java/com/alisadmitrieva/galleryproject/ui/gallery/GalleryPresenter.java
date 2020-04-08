package com.alisadmitrieva.galleryproject.ui.gallery;

import com.alisadmitrieva.galleryproject.data.model.Picture;
import com.alisadmitrieva.galleryproject.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class GalleryPresenter implements GalleryContract.Presenter {

    private GalleryContract.View view;
    private CompositeDisposable disposables = new CompositeDisposable();
    private List<Picture> pictures = new ArrayList<>();

    @Override
    public void attachView(GalleryContract.View view) {
        this.view = view;
    }

    @Override
    public void loadPictures() {
        Repository repository = new Repository();
        pictures.clear();

        disposables.add(
                repository.getDataFromAPI()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<List<String>>() {
                                           @Override
                                           public void onNext(List<String> pictureUrls) {
                                               for (int i = 0; i < pictureUrls.size(); i++) {
                                                   pictures.add(new Picture(pictureUrls.get(i)));
                                               }
                                               view.showPictures(pictures);
                                               view.showProgress(false);
                                           }

                                           @Override
                                           public void onError(Throwable e) {
                                               view.showErrorMessage(e.getLocalizedMessage());
                                               view.showProgress(false);
                                           }

                                           @Override
                                           public void onComplete() {

                                           }
                                       }
                        )
        );
    }

    @Override
    public void detachView() {
        view = null;
        disposables.clear();
        disposables.dispose();
    }

}
