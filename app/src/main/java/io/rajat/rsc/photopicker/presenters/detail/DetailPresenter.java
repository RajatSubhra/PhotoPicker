package io.rajat.rsc.photopicker.presenters.detail;


import io.rajat.rsc.photopicker.views.detail.DetailView;

public interface DetailPresenter {
    void onCreate(DetailView detailView);
    void onDestroy();
    void onSuccess();
    void onFailure();
}
