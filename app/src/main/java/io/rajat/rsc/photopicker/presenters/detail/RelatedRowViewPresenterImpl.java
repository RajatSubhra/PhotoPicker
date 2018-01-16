package io.rajat.rsc.photopicker.presenters.detail;

import io.rajat.rsc.photopicker.models.ImageResult;
import io.rajat.rsc.photopicker.models.datamanager.DetailDataManager;
import io.rajat.rsc.photopicker.views.detail.RelatedImageRowView;

public class RelatedRowViewPresenterImpl implements RelatedRowViewPresenter {
    RelatedImageRowView rowView;

    @Override
    public int getRelatedImagesCount() {
        if(DetailDataManager.getInstance().getRelatedImages()!=null)
            return DetailDataManager.getInstance().getRelatedImages().size();
        else
            return 0;
    }

    @Override
    public void onBindImageRowViewAtPosition(int position, RelatedImageRowView imageRowView) {
        ImageResult imageResult = DetailDataManager.getInstance().getRelatedImages().get(position);
        this.rowView = imageRowView;
        this.rowView.setImageURL(imageResult.getThumbUri());
    }
}
