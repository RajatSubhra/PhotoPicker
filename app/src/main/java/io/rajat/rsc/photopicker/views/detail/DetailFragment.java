package io.rajat.rsc.photopicker.views.detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.victor.loading.newton.NewtonCradleLoading;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import io.rajat.rsc.photopicker.MainApplication;
import io.rajat.rsc.photopicker.R;
import io.rajat.rsc.photopicker.presenters.detail.DetailPresenter;
import io.rajat.rsc.photopicker.views.utillities.HorizontalItemDecorator;

public class DetailFragment extends Fragment implements DetailView{


    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar)
    @Nullable
    Toolbar toolbar;

    @BindView(R.id.imageTitle)
    TextView imageTitle;

    @BindView(R.id.image_artist)
    TextView imageArtist;

    @BindView(R.id.summary_description)
    TextView summaryDescription;

    @Inject
    DetailPresenter detailPresenter;

    @BindView(R.id.related_images_recycler)
    RecyclerView relatedImagesRecyclerView;

    @BindView(R.id.customSpinner)
    NewtonCradleLoading customSpinner;

    @BindView(R.id.detailLinearLayout)
    LinearLayout detailLinearLayout;

    RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this,view);
        MainApplication.getInstance().getAppComponent().inject(this);
        setToolbar();
        detailPresenter.onCreate(this);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.detailPresenter.onDestroy();
    }

    private void setToolbar()
    {
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        collapsingToolbar.setTitle(getString(R.string.image_details));
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbar.setTitleEnabled(true);

        if (toolbar != null)
        {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null)
            {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
        else {
            // Don't inflate. Tablet is in landscape mode.
        }
    }

    @Override
    public void showMetaData(String imageTitle, String artistName, String summary) {
        this.imageTitle.setText(imageTitle);
        this.imageArtist.setText(artistName);
        this.summaryDescription.setText(summary);


        final LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        adapter = new RelatedImageRecyclerAdapter();
        relatedImagesRecyclerView.setAdapter(adapter);
        relatedImagesRecyclerView.addItemDecoration(new HorizontalItemDecorator((int) getContext().getResources().getDimension(R.dimen.padding_size_small)));
        relatedImagesRecyclerView.setLayoutManager(layoutManager);
        relatedImagesRecyclerView.setHasFixedSize(true);

    }

    @Override
    public void showError() {
        Toasty.error(getContext(),"Unexpected error. Check Network Connection!!", Toast.LENGTH_LONG).show();
        getActivity().onBackPressed();
    }

    @Override
    public void hideProgress() {
        if(customSpinner.isStart())
            customSpinner.stop();
        customSpinner.setVisibility(View.GONE);
        detailLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        customSpinner.setVisibility(View.VISIBLE);
        detailLinearLayout.setVisibility(View.GONE);
        customSpinner.start();
        customSpinner.setLoadingColor(R.color.colorPrimary);
    }
}
