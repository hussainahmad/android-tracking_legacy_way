package com.elcomercio.ga_and_gtm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tagmanager.Container;
import com.google.android.gms.tagmanager.ContainerHolder;
import com.google.android.gms.tagmanager.DataLayer;
import com.google.android.gms.tagmanager.TagManager;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static String CONTAINER_ID = "GTM-TFVM352";
    private static int TIMEOUT_FOR_CONTAINER_OPEN_MILLISECONDS = 5;

    private Button btnTrackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTrackButton = (Button) findViewById(R.id.btnTrackButton);

        initGoogleTagManager();

        btnTrackButton.setOnClickListener(this);

    }

    public void initGoogleTagManager(){

        TagManager tagManager = TagManager.getInstance(this);

        tagManager.setVerboseLoggingEnabled(true);

        PendingResult<ContainerHolder> pending =
                tagManager.loadContainerPreferNonDefault(CONTAINER_ID,
                        R.raw.orbismobiletracking_binarie);

        // The onResult method will be called as soon as one of the following happens:
        //     1. a saved container is loaded
        //     2. if there is no saved container, a network container is loaded
        //     3. the 2-second timeout occurs
        pending.setResultCallback(new ResultCallback<ContainerHolder>() {
            @Override
            public void onResult(ContainerHolder containerHolder) {
                //
                ContainerHolderSingleton.setContainerHolder(containerHolder);
                Container container = containerHolder.getContainer();

                if (!containerHolder.getStatus().isSuccess()) {
                    Log.e("Test App", "failure loading container");
                    Log.i("gtm", getString(R.string.load_error));
                    return;
                }
                ContainerHolderSingleton.setContainerHolder(containerHolder);
                ContainerLoadedCallback.registerCallbacksForContainer(container);
                containerHolder.setContainerAvailableListener(new ContainerLoadedCallback());

            }
        }, TIMEOUT_FOR_CONTAINER_OPEN_MILLISECONDS, TimeUnit.SECONDS);
    }

    private static class ContainerLoadedCallback implements
            ContainerHolder.ContainerAvailableListener {
        @Override
        public void onContainerAvailable(ContainerHolder containerHolder, String containerVersion) {
            Container container = containerHolder.getContainer();
            registerCallbacksForContainer(container);
        }

        public static void registerCallbacksForContainer(Container container) {
            Log.e("Logging a Callback!", " registerCallbacksForContainer");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTrackButton:

                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        TagManager.getInstance(this).getDataLayer().push(DataLayer.mapOf("event","openScreen", "screenName", "SwipeActivity"));
    }

    private void pushEventToDataLayer(String appName){
        DataLayer dataLayer = TagManager.getInstance(this).getDataLayer();
        dataLayer.pushEvent("App Name", DataLayer.mapOf("App Name", appName));
    }
}
