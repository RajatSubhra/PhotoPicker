package io.rajat.rsc.photopicker.daggar;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rajat.rsc.photopicker.presenters.detail.DetailPresenter;
import io.rajat.rsc.photopicker.presenters.detail.DetailPresenterImpl;
import io.rajat.rsc.photopicker.presenters.detail.RelatedRowViewPresenter;
import io.rajat.rsc.photopicker.presenters.detail.RelatedRowViewPresenterImpl;
import io.rajat.rsc.photopicker.presenters.search.ImageRowViewPresenter;
import io.rajat.rsc.photopicker.presenters.search.ImageRowViewPresenterImpl;
import io.rajat.rsc.photopicker.presenters.search.SearchPresenter;
import io.rajat.rsc.photopicker.presenters.search.SearchPresenterImpl;

@Module
public class PresenterModule {
    @Provides
    @Singleton
    SearchPresenter provideSearchPresenter(){
        return new SearchPresenterImpl();
    }

    @Provides
    @Singleton
    DetailPresenter provideDetailPresenter(){
        return new DetailPresenterImpl();
    }

    @Provides
    @Singleton
    ImageRowViewPresenter provideImageRowViewPresenter(){
        return new ImageRowViewPresenterImpl();
    }

    @Provides
    @Singleton
    RelatedRowViewPresenter provideRelatedRowViewPresenter(){
        return new RelatedRowViewPresenterImpl();
    }
}
