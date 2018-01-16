package io.rajat.rsc.photopicker.models.datamanager;


import java.util.List;

import javax.inject.Inject;

import io.rajat.rsc.photopicker.MainApplication;
import io.rajat.rsc.photopicker.apis.ImageService;
import io.rajat.rsc.photopicker.models.ImageResult;
import io.rajat.rsc.photopicker.models.MetaDataAndSimilarImages;
import io.rajat.rsc.photopicker.models.Metadata;
import io.rajat.rsc.photopicker.presenters.detail.DetailPresenter;
import io.reactivex.Observable;

public class DetailDataManager {
    private static DetailDataManager sharedInstance;
    private DetailPresenter callBack;
    Observable<MetaDataAndSimilarImages> combined;
    @Inject
    ImageService imageService;

    private Metadata details;
    private List<ImageResult> relatedImages;

    public static DetailDataManager getInstance() {

        if (sharedInstance == null){
            sharedInstance = new DetailDataManager();
        }
        return sharedInstance;
    }

    private DetailDataManager() {
        MainApplication.getInstance().getAppComponent().inject(this);
    }

    private void handleResults(MetaDataAndSimilarImages response) {
        if(response.imageResponse!=null && response.metadataResponse!=null) {
            relatedImages = response.imageResponse.getImages();
            details = response.metadataResponse.getMetadata().get(0);
            callBack.onSuccess();
        }else{
            callBack.onFailure();
        }

    }
    private void handleFailure(Throwable t){
        callBack.onFailure();
    }
    public void fetchDetails(String id,DetailPresenter callBack){
        this.callBack = callBack;
        if(this.callBack!=null) {
            combined = imageService.getCombinedData(id);
            combined.subscribe(this::handleResults, this::handleFailure);
        }
    }

    public List<ImageResult> getRelatedImages() {
        return relatedImages;
    }

    public void setRelatedImages(List<ImageResult> relatedImages) {
        this.relatedImages = relatedImages;
    }

    public Metadata getDetails() {
        return details;
    }

    public void setDetails(Metadata details) {
        this.details = details;
    }
}
