package io.rajat.rsc.photopicker.presenters.search;

import io.rajat.rsc.photopicker.views.search.ImageRowView;

public interface ImageRowViewPresenter {
    int getImagesCount();
    void setClickedImage(int index);
    void onBindImageRowViewAtPosition(int position,ImageRowView userRowView);
}

