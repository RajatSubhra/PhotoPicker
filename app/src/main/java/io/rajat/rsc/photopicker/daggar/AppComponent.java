package io.rajat.rsc.photopicker.daggar;


import javax.inject.Singleton;

import dagger.Component;
import io.rajat.rsc.photopicker.apis.ImageService;
import io.rajat.rsc.photopicker.models.datamanager.DetailDataManager;
import io.rajat.rsc.photopicker.models.datamanager.SearchDataManager;
import io.rajat.rsc.photopicker.views.detail.DetailFragment;
import io.rajat.rsc.photopicker.views.detail.RelatedImageRecyclerAdapter;
import io.rajat.rsc.photopicker.views.search.ListImageRecyclerAdapter;
import io.rajat.rsc.photopicker.views.search.SearchFragment;

@Singleton
@Component(modules = {AppModule.class,NetworkModule.class,PresenterModule.class})
public interface AppComponent {
    void inject(SearchFragment target);
    void inject(DetailFragment target);
    void inject(ImageService target);
    void inject(SearchDataManager target);
    void inject(DetailDataManager target);
    void inject(ListImageRecyclerAdapter target);
    void inject(RelatedImageRecyclerAdapter target);
}
