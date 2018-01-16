package io.rajat.rsc.photopicker.views.detail;

public interface DetailView {
    // on success
    void showMetaData(String imageTitle,String artistName,String summary);

    // on error
    void showError();

    // show/hide progress
    void hideProgress();
    void showProgress();
}
