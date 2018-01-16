package io.rajat.rsc.photopicker.presenters.detail;

import io.rajat.rsc.photopicker.views.detail.RelatedImageRowView;

public interface RelatedRowViewPresenter {
    int getRelatedImagesCount();
    void onBindImageRowViewAtPosition(int position,RelatedImageRowView imageRowView);
}
