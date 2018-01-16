package io.rajat.rsc.photopicker.views.search;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rajat.rsc.photopicker.MainApplication;
import io.rajat.rsc.photopicker.R;
import io.rajat.rsc.photopicker.presenters.search.ImageRowViewPresenter;
import io.rajat.rsc.photopicker.views.detail.DetailFragment;
import io.rajat.rsc.photopicker.views.main.MainActivity;

public class ListImageRecyclerAdapter extends RecyclerView.Adapter<ListImageRecyclerAdapter.ImageViewHolder> {

    @Inject
    ImageRowViewPresenter imageRowViewPresenter;
    Context context;
    int lastPosition = -1;

    public ListImageRecyclerAdapter(){
        MainApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_image, parent, false);
        ImageViewHolder userViewHolder = new ImageViewHolder(v);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        if(position >lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.up_from_bottom);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
        imageRowViewPresenter.onBindImageRowViewAtPosition(position,holder);
    }


    @Override
    public int getItemCount() {
        return imageRowViewPresenter.getImagesCount();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder implements ImageRowView{

        @BindView(R.id.image_view)
        ImageView imageView;

        @BindView(R.id.title_background)
        View titleBackground;

        @BindView(R.id.image_title)
        TextView imageTitle;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setImageURL(String imageURL) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .priority(Priority.HIGH);
            Glide.with(context)
                    .asBitmap()
                    .load(imageURL)
                    .apply(options)
                    .into(new BitmapImageViewTarget(imageView) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                            super.onResourceReady(bitmap, transition);
                            Palette.from(bitmap).generate(palette -> setBackgroundColor(palette));
                        }
                    });


        }
        private void setBackgroundColor(Palette palette) {
            this.titleBackground.setBackgroundColor(palette.getVibrantColor(context
                    .getResources().getColor(R.color.black_translucent_60)));
        }

        @Override
        public void goToDetailFragment() {
            ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, new DetailFragment()).addToBackStack(null).commit();
        }

        @Override
        public void setImageTitle(String imageTitle) {
            this.imageTitle.setText(imageTitle);
        }

        @OnClick(R.id.item)
        public void onClickImage(){
            imageRowViewPresenter.setClickedImage(getAdapterPosition());
        }
    }
}
