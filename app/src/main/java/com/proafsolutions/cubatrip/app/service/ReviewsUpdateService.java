package com.proafsolutions.cubatrip.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.proafsolutions.cubatrip.domain.model.Review;
import com.proafsolutions.cubatrip.domain.service.BLServiceCatalog;
import com.proafsolutions.cubatrip.infrastructure.broker.ServiceBroker;
import com.proafsolutions.cubatrip.infrastructure.converter.JsonConverter;

import java.util.List;

public class ReviewsUpdateService extends Service implements IReviewsUpdateService {

    public ReviewsUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public void onCreate() {
        updateReviews();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void updateReviews(){
        try {
           // AppSetup.initDatabase(this);
            pullReviewsFromServer();
            pushReviewsToServer();
        }catch (Exception e){
            Log.i("ProductsUpdateService", e.getMessage(), e);
        }finally {
            stopSelf();
        }
    }

    @Override
    public void pushReviewsToServer() {
        Log.i("ReviewsUpdateService", "**************Reviews pushed to server******************");
    }

    @Override
    public void pullReviewsFromServer() {
        List<Review> serverReviews = ServiceBroker.requestReviews();
        Log.i("ReviewsUpdateService", "**************Reviews pulled from server******************");
    }
}
