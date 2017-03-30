package com.elcomercio.ga_and_gtm.swipe;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.elcomercio.ga_and_gtm.R;
import com.elcomercio.ga_and_gtm.model.AnimalEntity;
import com.elcomercio.ga_and_gtm.model.LoadingEntity;
import com.elcomercio.ga_and_gtm.util.TrackUtils;

import java.util.ArrayList;
import java.util.List;

public class SwipeActivity extends AppCompatActivity {


    RecyclerView rcvAnimals;
    private SwipeAdapter swipeAdapter;
    private List<Object> objectList = new ArrayList<>();
    LinearLayoutManager layoutManager;

    private int lastVisibleItem;
    private int totalItemCount;
    private final int visibleThreshold = 1;

    private boolean setLoading = false;

    private RequestMoreData requestMoreData;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        rcvAnimals = (RecyclerView) findViewById(R.id.rcvAnimals);

        for (int i = 0; i < 10; i++) {
            objectList.add(new AnimalEntity(false, "item" + i));
        }
        addLoadingItem();

        layoutManager = new LinearLayoutManager(this);
        //when you want horizontal
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        swipeAdapter = new SwipeAdapter(this, objectList);
        rcvAnimals.setLayoutManager(layoutManager);
        rcvAnimals.setAdapter(swipeAdapter);

        rcvAnimals.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (totalItemCount <= (lastVisibleItem + visibleThreshold )) {
                    if (!setLoading) {
                        TrackUtils.pushEventAndVariableValuesToDataLayer(SwipeActivity.this, "Swipe","NextPagination","pagination_" + page);
                        setLoading = true;
                        requestMoreData = new RequestMoreData();
                        requestMoreData.execute();
                    }
                }
            }
        });

    }

    private class RequestMoreData extends AsyncTask<Void, Void, Void> {

        private RequestMoreData() {
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(SwipeActivity.this, "Requesting...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            shouldRemoveLoadingItem();
            setLoading = false;
            int tempSize = objectList.size();
            for (int i = tempSize ; i < (tempSize + 10); i++) {
                objectList.add(new AnimalEntity(false, "item" + i));
            }
            addLoadingItem();
            swipeAdapter.notifyDataSetChanged();
            page ++;
        }
    }

    private void addLoadingItem() {
        objectList.add(new LoadingEntity("loading"));
    }

    private void shouldRemoveLoadingItem() {
        if (objectList.get(objectList.size() - 1) instanceof LoadingEntity) {
            objectList.remove(objectList.size() - 1);
            swipeAdapter.notifyItemRemoved(objectList.size());
        }
    }

    private void checkRequestMoreData(){
        if(requestMoreData != null){
            requestMoreData.cancel(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        TrackUtils.pushOpenScreenEventAndScreenNameToDataLayer2(this, "SwipeActivity");
    }
}
