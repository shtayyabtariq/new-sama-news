package mopub.xeon.com.mopub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mopub.common.privacy.PersonalInfoManager;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;
import com.mopub.nativeads.MoPubRecyclerAdapter;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MoPubView.BannerAdListener {


    private static final String AD_UNIT_ID = "c272fff4e8584d0ebe3b9069ea865e78"; // A sample MoPub native ad unit ID
    @Nullable
    PersonalInfoManager mPersonalInfoManager;
    private RecyclerView mRecyclerView;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
//
//        List<String> networksToInit = new ArrayList<String>();
//        networksToInit.add("com.google.android.gms.ads.AdActivity");
//
//        SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder("c054cf0534304e77bc799cae885f367e")
//                .withNetworksToInit(networksToInit)
//                .build();
//        MoPub.initializeSdk(this, sdkConfiguration, null);
//
//
//
//
//        MoPubView moPubView = (MoPubView) findViewById(R.id.adview);
//        moPubView.setAdUnitId("ba396178fd544173bfa1d3757fa8592c"); // Enter your Ad Unit ID from www.mopub.com
//        moPubView.loadAd();
//        moPubView.setBannerAdListener(this);
//    }


    //    static {
//        REQUIRED_DANGEROUS_PERMISSIONS.add(ACCESS_COARSE_LOCATION);
//        REQUIRED_DANGEROUS_PERMISSIONS.add(WRITE_EXTERNAL_STORAGE);
//    }
//
//    // Sample app web views are debuggable.
//    static {
//        setWebDebugging();
//    }
//
//    @TargetApi(Build.VERSION_CODES.KITKAT)
//    private static void setWebDebugging() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WebView.setWebContentsDebuggingEnabled(true);
//        }
//    }
    private MoPubRecyclerAdapter mMoPubRecyclerAdapter;

    @Override
    protected void onPostResume() {
        super.onPostResume();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getApplication().registerActivityLifecycleCallbacks(ApplicationController.getInstance());

        MopubManager.Create(new MopubManager.MopubCallback() {
            @Override
            public void onSuccess() {
                MopubManager.LoadBanner(findViewById(R.id.adview));
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d("Activity", ApplicationController.getInstance().currentactivity.toString());
//        List<String> permissionsToBeRequested = new ArrayList<>();
//        for (String permission : REQUIRED_DANGEROUS_PERMISSIONS) {
//            if (!DeviceUtils.isPermissionGranted(this, permission)) {
//                permissionsToBeRequested.add(permission);
//            }
//        }
//
//        // Request dangerous permissions
//        if (!permissionsToBeRequested.isEmpty()) {
//            ActivityCompat.requestPermissions(this, permissionsToBeRequested.toArray(
//                    new String[permissionsToBeRequested.size()]), UNUSED_REQUEST_CODE);
//        }
//
//        // Set location awareness and precision globally for your app:
//        MoPub.setLocationAwareness(MoPub.LocationAwareness.TRUNCATED);
//        MoPub.setLocationPrecision(4);
//
//
//
//        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
//        SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder("b195f8dd8ded45fe847ad89ed1d016da")
//                .build();
//        MoPub.initializeSdk(this, sdkConfiguration, initSdkListener());
//
//        mPersonalInfoManager = MoPub.getPersonalInformationManager();
//        if (mPersonalInfoManager != null) {
//            mPersonalInfoManager.subscribeConsentStatusChangeListener(initConsentChangeListener());
//        }


//
//        MopubManager.LoadIntersitial();
//
//


        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        LoggingUtils.enableCanaryLogging(this);


//
//        final RecyclerView.Adapter originalAdapter = new DemoRecyclerAdapter();
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.native_recycler_view);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
//
//
//
//
//        MopubManager.LoadNativeRecyclerAdds(originalAdapter);
//        mRecyclerView.setAdapter(MopubManager.mMoPubRecyclerAdapter);


    }


//
//    private SdkInitializationListener initSdkListener() {
//        return new SdkInitializationListener() {
//
//            @Override
//            public void onInitializationFinished() {
//                Utils.logToast(MainActivity.this, "SDK initialized.");
//                if (mPersonalInfoManager != null && mPersonalInfoManager.shouldShowConsentDialog()) {
//                    mPersonalInfoManager.loadConsentDialog(initDialogLoadListener());
//                }
//            }
//        };
//    }
//
//    private ConsentStatusChangeListener initConsentChangeListener() {
//        return new ConsentStatusChangeListener() {
//
//            @Override
//            public void onConsentStateChange(@NonNull ConsentStatus oldConsentStatus,
//                                             @NonNull ConsentStatus newConsentStatus,
//                                             boolean canCollectPersonalInformation) {
//                Utils.logToast(MainActivity.this, "Consent: " + newConsentStatus.name());
//                if (mPersonalInfoManager != null && mPersonalInfoManager.shouldShowConsentDialog()) {
//                    mPersonalInfoManager.loadConsentDialog(initDialogLoadListener());
//                }
//            }
//        };
//    }
//
//    private ConsentDialogListener initDialogLoadListener() {
//        return new ConsentDialogListener() {
//
//            @Override
//            public void onConsentDialogLoaded() {
//                if (mPersonalInfoManager != null) {
//                    mPersonalInfoManager.showConsentDialog();
//                }
//            }
//
//            @Override
//            public void onConsentDialogLoadFailed(@NonNull MoPubErrorCode moPubErrorCode) {
//                Utils.logToast(MainActivity.this, "Consent dialog failed to load.");
//            }
//        };
//    }


    @Override
    public void onBannerLoaded(MoPubView banner) {

    }

    @Override
    public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {

    }

    @Override
    public void onBannerClicked(MoPubView banner) {

    }

    @Override
    public void onBannerExpanded(MoPubView banner) {

    }

    @Override
    public void onBannerCollapsed(MoPubView banner) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mMoPubRecyclerAdapter.destroy();
        mMoPubRecyclerAdapter.setAdLoadedListener(null);
        mRecyclerView = null;
    }

    private static class DemoRecyclerAdapter extends RecyclerView.Adapter<DemoViewHolder> {
        private static final int ITEM_COUNT = 75;

        @Override
        public DemoViewHolder onCreateViewHolder(final ViewGroup parent,
                                                 final int viewType) {
            final View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_list_item, parent, false);
            return new DemoViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final DemoViewHolder holder, final int position) {
            holder.setIsRecyclable(false);

            if (holder.textView.getText().equals(""))
                holder.textView.setText(String.format(Locale.US, "Content Item #%d", position));
        }

        @Override
        public long getItemId(final int position) {
            return (long) position;
        }

        @Override
        public int getItemCount() {
            return ITEM_COUNT;
        }
    }

    private static class DemoViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        public DemoViewHolder(final View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text1);
        }
    }
}
