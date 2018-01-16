package io.rajat.rsc.photopicker.presenters.search;


import io.rajat.rsc.photopicker.models.ImageResult;
import io.rajat.rsc.photopicker.models.datamanager.SearchDataManager;
import io.rajat.rsc.photopicker.views.search.ImageRowView;

public class ImageRowViewPresenterImpl implements ImageRowViewPresenter {

    ImageRowView rowView;
    public ImageRowViewPresenterImpl(){
    }

    @Override
    public int getImagesCount() {
       if (SearchDataManager.getInstance().getImages()!= null)
           return SearchDataManager.getInstance().getImages().size();
       else
           return 0;
    }

    @Override
    public void setClickedImage(int index) {
        SearchDataManager.getInstance().setCurrentImage(index);
        rowView.goToDetailFragment();
    }

    @Override
    public void onBindImageRowViewAtPosition(int position, ImageRowView imageRowView) {
        ImageResult imageResult = SearchDataManager.getInstance().getImages().get(position);
        this.rowView = imageRowView;
        this.rowView.setImageURL(imageResult.getThumbUri());
        this.rowView.setImageTitle(imageResult.getTitle());
    }
}
