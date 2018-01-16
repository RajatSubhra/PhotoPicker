package io.rajat.rsc.photopicker.views.detail;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rajat.rsc.photopicker.MainApplication;
import io.rajat.rsc.photopicker.R;
import io.rajat.rsc.photopicker.presenters.detail.RelatedRowViewPresenter;

public class RelatedImageRecyclerAdapter extends RecyclerView.Adapter<RelatedImageRecyclerAdapter.RelatedImageViewHolder> {

    Context context;

    @Inject
    RelatedRowViewPresenter relatedRowViewPresenter;

    RelatedImageRecyclerAdapter(){
        MainApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public RelatedImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context)
                .inflate(R.layout.layout_image_related_item, parent, false);
        RelatedImageViewHolder imageViewHolder = new RelatedImageViewHolder(v);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(RelatedImageViewHolder holder, int position) {
        relatedRowViewPresenter.onBindImageRowViewAtPosition(position,holder);
    }

    @Override
    public int getItemCount() {
        return relatedRowViewPresenter.getRelatedImagesCount();
    }

    public class RelatedImageViewHolder extends RecyclerView.ViewHolder implements RelatedImageRowView{


        @BindView(R.id.similarImageView1)
        ImageView imageView;

        public RelatedImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void setImageURL(String imageURL) {
            Glide.with(context)
                    .load(imageURL)
                    .into(imageView);
        }
    }
}
