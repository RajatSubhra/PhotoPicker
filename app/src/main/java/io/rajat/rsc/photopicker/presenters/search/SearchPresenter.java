package io.rajat.rsc.photopicker.presenters.search;


import io.rajat.rsc.photopicker.views.search.SearchView;

public interface SearchPresenter {

    void onCreate(SearchView view);
    void onDestroy();
    void onSearchClick(String searchStr);
    void onSuccess();
    void onFailure();

}
