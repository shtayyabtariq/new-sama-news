package mopub.xeon.com.mopub;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by tayyab on 6/6/18.
 */

public class ApplicationController extends Application implements Application.ActivityLifecycleCallbacks {


    private static ApplicationController sInstance;


    public Activity currentactivity;

    public static synchronized ApplicationController getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        // Toast.makeText(getApplicationContext(),"it is called", Toast.LENGTH_LONG).show();

    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        super.registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        ApplicationController.getInstance().currentactivity = activity;

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
