package io.rajat.rsc.photopicker.presenters.search;


import io.rajat.rsc.photopicker.models.datamanager.SearchDataManager;
import io.rajat.rsc.photopicker.views.search.SearchView;

public class SearchPresenterImpl implements SearchPresenter {

    SearchView searchView;

    @Override
    public void onCreate(SearchView view) {
        searchView = view;
    }

    @Override
    public void onDestroy() {
        searchView = null;
    }

    @Override
    public void onSearchClick(String searchStr) {
        searchView.showProgress();
        SearchDataManager.getInstance().search(searchStr,this);
    }

    @Override
    public void onSuccess() {
        searchView.hideProgress();
        searchView.showSuccess();
    }

    @Override
    public void onFailure() {
        searchView.hideProgress();
        searchView.showError();
    }
}
