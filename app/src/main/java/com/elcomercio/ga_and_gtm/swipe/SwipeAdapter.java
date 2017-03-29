package com.elcomercio.ga_and_gtm.swipe;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.elcomercio.ga_and_gtm.R;
import com.elcomercio.ga_and_gtm.model.AnimalEntity;
import com.elcomercio.ga_and_gtm.model.LoadingEntity;

import java.util.List;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 29/03/17.
 *
 */

public class SwipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final SwipeActivity swipeActivity;
    private static LayoutInflater inflater = null;

    private List<Object> objectList;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOADING = 1;
    private static final int TYPE_ERROR = 2;

    public SwipeAdapter(SwipeActivity swipeActivity, List<Object> objectList) {
        this.swipeActivity = swipeActivity;
        inflater = (LayoutInflater) swipeActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objectList = objectList;
    }


    @Override
    public int getItemViewType(int position) {
        if (objectList.get(position) instanceof AnimalEntity) {
            return TYPE_ITEM;
        } else if (objectList.get(position) instanceof LoadingEntity) {
            return TYPE_LOADING;
        } else {
            return TYPE_ERROR;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_ITEM:
                view = inflater.inflate(R.layout.item_vertical, parent, false);
                return new ItemViewHolder(view);
            case TYPE_LOADING:
                view = inflater.inflate(R.layout.item_vertical_progress, parent, false);
                return new LoadingViewHolder(view);
            case TYPE_ERROR:
                view = inflater.inflate(R.layout.item_vertical_error, parent, false);
                return new ErrorViewHolder(view);
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_ITEM:
                AnimalEntity animalEntity = (AnimalEntity) objectList.get(position);
                int imgBackground = animalEntity.isFavorite() ? R.drawable.ic_android_green_500_24dp : R.drawable.ic_android_blue_grey_500_24dp;
                ((SwipeAdapter.ItemViewHolder) holder).imgFavorite.setImageResource(imgBackground);
                ((ItemViewHolder) holder).lblTitle.setText(animalEntity.getTitle());
                break;
            case TYPE_LOADING:

                break;
            default:

                break;
        }
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView lblTitle;
        private final AppCompatImageView imgFavorite;

        ItemViewHolder(View view) {
            super(view);
            lblTitle = (TextView) view.findViewById(R.id.lblTitle);
            imgFavorite = (AppCompatImageView) view.findViewById(R.id.imgFavorite);
            lblTitle.setOnClickListener(this);
            imgFavorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.lblTitle:
                    //swipeActivity.goToDetail(getAdapterPosition());
                    break;

            }

        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        final ProgressBar pbLoading;

        LoadingViewHolder(View v) {
            super(v);
            pbLoading = (ProgressBar) v.findViewById(R.id.pbLoading);
        }
    }

    private class ErrorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView lblError;
        final Button btnError;

        ErrorViewHolder(View itemView) {
            super(itemView);
            lblError = (TextView) itemView.findViewById(R.id.lblError);
            btnError = (Button) itemView.findViewById(R.id.btnError);
            btnError.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnError:

                    break;
            }
        }
    }

}
