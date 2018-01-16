package io.rajat.rsc.photopicker.apis;


import javax.inject.Inject;

import io.rajat.rsc.photopicker.MainApplication;
import io.rajat.rsc.photopicker.models.ImageResponse;
import io.rajat.rsc.photopicker.models.MetaDataAndSimilarImages;
import io.rajat.rsc.photopicker.models.MetadataResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class ImageService {
    public static final String FIELDS = "id,title,thumb";
    public static final String SORT_ORDER = "best";

    @Inject
    Api api;

    public ImageService(){
        MainApplication.getInstance().getAppComponent().inject(this);
    }


    public Observable<ImageResponse> searchImages(String phrase) {
        return api.searchImages(phrase, FIELDS, SORT_ORDER);
    }


    public Observable<MetadataResponse> getImageMetadata(String id) {
        return api.getImageMetadata(id);
    }

    public Observable<ImageResponse> getSimilarImages(String id) {
        return api.getSimilarImages(id);
    }

    public Observable<MetaDataAndSimilarImages> getCombinedData(String id){
        Observable<MetadataResponse> detailCall = this.getImageMetadata(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Observable<ImageResponse> call = this.getSimilarImages(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Observable<MetaDataAndSimilarImages> combined  = Observable.zip(detailCall, call, new BiFunction<MetadataResponse, ImageResponse, MetaDataAndSimilarImages>() {
            @Override
            public MetaDataAndSimilarImages apply(@NonNull MetadataResponse metadataResponse, @NonNull ImageResponse imageResponse) throws Exception {
                return new MetaDataAndSimilarImages(imageResponse,metadataResponse);
            }
        });
        return combined;
    }
}
