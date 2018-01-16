package io.rajat.rsc.photopicker.views.search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import io.rajat.rsc.photopicker.MainApplication;
import io.rajat.rsc.photopicker.R;
import io.rajat.rsc.photopicker.presenters.search.SearchPresenter;
import io.rajat.rsc.photopicker.views.utillities.SpaceItemDecoration;

public class SearchFragment extends Fragment implements SearchView {

    @Inject
    SearchPresenter searchPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView imageRecyclerView;

    @BindView(R.id.animation_view)
    LottieAnimationView animationView;

    @BindView(R.id.search_phrase)
    EditText searchInputEditText;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    RecyclerView.Adapter adapter;

    @BindDimen(R.dimen.image_space)
    int space;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        MainApplication.getInstance().getAppComponent().inject(this);
        ButterKnife.bind(this,view);
        animationView.bringToFront();
        searchPresenter.onCreate(this);
        imageRecyclerView.setAdapter(adapter);
        searchInputEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchImages();
                return true;
            }
            return false;
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(imageRecyclerView!=null)
            imageRecyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.imageButton)
    void searchImages(){
        searchPresenter.onSearchClick(searchInputEditText.getText().toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        searchPresenter.onDestroy();
    }

    @Override
    public void showProgress() {
        animationView.setVisibility(View.GONE);
        imageRecyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        imageRecyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSuccess() {
        animationView.setVisibility(View.GONE);
        imageRecyclerView.addItemDecoration(new SpaceItemDecoration(space,space,space,space));
        imageRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        imageRecyclerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.cardview_dark_background));
        adapter = new ListImageRecyclerAdapter();
        imageRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showError() {
        animationView.setVisibility(View.VISIBLE);
        Toasty.error(getContext(),"Unexpected error", Toast.LENGTH_LONG).show();
    }
}
