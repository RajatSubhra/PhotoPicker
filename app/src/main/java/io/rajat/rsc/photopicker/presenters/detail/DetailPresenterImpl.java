package io.rajat.rsc.photopicker.presenters.detail;


import io.rajat.rsc.photopicker.models.ImageResult;
import io.rajat.rsc.photopicker.models.Metadata;
import io.rajat.rsc.photopicker.models.datamanager.DetailDataManager;
import io.rajat.rsc.photopicker.models.datamanager.SearchDataManager;
import io.rajat.rsc.photopicker.views.detail.DetailView;


public class DetailPresenterImpl implements DetailPresenter {

    DetailView detailView;


    @Override
    public void onCreate(DetailView detailView) {
        this.detailView = detailView;
        ImageResult imageResult = SearchDataManager.getInstance().getCurrentImage();
        if (imageResult!= null) {
            detailView.showProgress();
            DetailDataManager.getInstance().fetchDetails(imageResult.getId(), this);
        }
    }

    @Override
    public void onDestroy() {
        detailView = null;
    }

    @Override
    public void onSuccess() {
        if (detailView!= null){
            detailView.hideProgress();
            Metadata metadata = DetailDataManager.getInstance().getDetails();
            detailView.showMetaData(metadata.getTitle(),metadata.getArtist(),metadata.getCaption());
        }
    }


    @Override
    public void onFailure() {
        if (detailView!= null){
            detailView.hideProgress();
            detailView.showError();
        }
    }
}
