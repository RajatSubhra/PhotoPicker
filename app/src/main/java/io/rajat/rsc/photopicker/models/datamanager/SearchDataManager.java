package io.rajat.rsc.photopicker.models.datamanager;


import java.util.List;

import javax.inject.Inject;

import io.rajat.rsc.photopicker.MainApplication;
import io.rajat.rsc.photopicker.apis.ImageService;
import io.rajat.rsc.photopicker.models.ImageResponse;
import io.rajat.rsc.photopicker.models.ImageResult;
import io.rajat.rsc.photopicker.presenters.search.SearchPresenter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchDataManager {
    private static SearchDataManager sharedInstance;
    Observable<ImageResponse> call;
    private List<ImageResult> images;
    private ImageResult currentImage;
    private SearchPresenter callBack;

    @Inject
    ImageService imageService;
    public static SearchDataManager getInstance() {

        if (sharedInstance == null){
            sharedInstance = new SearchDataManager();
        }
        return sharedInstance;
    }

    private SearchDataManager() {
        MainApplication.getInstance().getAppComponent().inject(this);
    }
    private void handleResults(ImageResponse response) {
        if (response.getImages()!=null) {
            images = response.getImages();
            callBack.onSuccess();
        } else {
            callBack.onFailure();
        }

    }
    private void handleFailure(Throwable t){
        callBack.onFailure();
    }

    public void search(String searchStr,SearchPresenter callBack) {
        this.callBack = callBack;
        call = imageService.searchImages(searchStr);
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleFailure );

    }


    public List<ImageResult> getImages() {
        return images;
    }

    public void setImages(List<ImageResult> images) {
        this.images = images;
    }

    public ImageResult getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(int index) {
        if(images!=null)
            this.currentImage = this.images.get(index);
    }
}
