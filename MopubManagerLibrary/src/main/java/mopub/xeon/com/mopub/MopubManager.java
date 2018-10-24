package mopub.xeon.com.mopub;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;

import com.google.android.gms.ads.MobileAds;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.privacy.ConsentDialogListener;
import com.mopub.common.privacy.ConsentStatus;
import com.mopub.common.privacy.ConsentStatusChangeListener;
import com.mopub.common.privacy.PersonalInfoManager;
import com.mopub.common.util.DeviceUtils;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubView;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import com.mopub.nativeads.MoPubRecyclerAdapter;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.ViewBinder;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.mopub.common.Constants.UNUSED_REQUEST_CODE;

/**
 * Created by tayyab on 6/6/18.
 */

public class MopubManager {
    static final String AppID = "b195f8dd8ded45fe847ad89ed1d016da";
    static final String BannerID = "3cbf00650b274ad387dd71bb2c711d6a";
    static final String IntersitialID = "878999c182e74171b241e5a26426d379";
    private static final List<String> REQUIRED_DANGEROUS_PERMISSIONS = new ArrayList<>();
    private static final String AD_UNIT_ID = "11a17b188668469fb0412708c3d16813"; // A sample MoPub native ad unit ID
    public static boolean IsIntialized = false;
    public static MoPubRecyclerAdapter mMoPubRecyclerAdapter;
    static MoPubStaticNativeAdRenderer moPubStaticNativeAdRenderer;
    private static MoPubInterstitial mInterstitial;
    @Nullable
    private static PersonalInfoManager mPersonalInfoManager;
    private static MoPubView.BannerAdListener bannerAdListener = new MoPubView.BannerAdListener() {
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
    };
    private static MoPubInterstitial.InterstitialAdListener interstitialAdListener = new MoPubInterstitial.InterstitialAdListener() {
        @Override
        public void onInterstitialLoaded(MoPubInterstitial interstitial) {


        }

        @Override
        public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {

        }

        @Override
        public void onInterstitialShown(MoPubInterstitial interstitial) {

        }

        @Override
        public void onInterstitialClicked(MoPubInterstitial interstitial) {

        }

        @Override
        public void onInterstitialDismissed(MoPubInterstitial interstitial) {

        }
    };

    static {
        REQUIRED_DANGEROUS_PERMISSIONS.add(ACCESS_COARSE_LOCATION);
        REQUIRED_DANGEROUS_PERMISSIONS.add(WRITE_EXTERNAL_STORAGE);
    }

    // Sample app web views are debuggable.
    static {
        setWebDebugging();
    }

    public static void LoadBanner(Object moPubView) {


        MoPubView moPubView1 = (MoPubView) moPubView;
        // moPubView = moPubView;
        moPubView1.setAdUnitId(BannerID); // Enter your Ad Unit ID from www.mopub.com
        moPubView1.loadAd();
        moPubView1.setBannerAdListener(bannerAdListener);


    }

    public static void LoadIntersitial() {

        mInterstitial = new MoPubInterstitial(ApplicationController.getInstance().currentactivity, IntersitialID);
        mInterstitial.load();
        mInterstitial.setInterstitialAdListener(interstitialAdListener);
    }

    public static void ShowIntersitial() {

        if (mInterstitial.isReady()) {
            mInterstitial.show();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void setWebDebugging() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    public static void Create(MopubCallback mopubCallback) {


//        Toast.makeText(ApplicationController.getInstance().getApplicationContext(),"SDK CALLED",Toast.LENGTH_LONG).show();
        List<String> permissionsToBeRequested = new ArrayList<>();
        for (String permission : REQUIRED_DANGEROUS_PERMISSIONS) {
            if (!DeviceUtils.isPermissionGranted(ApplicationController.getInstance().getApplicationContext(), permission)) {
                permissionsToBeRequested.add(permission);
            }
        }

        // Request dangerous permissions
        if (!permissionsToBeRequested.isEmpty()) {
            ActivityCompat.requestPermissions(ApplicationController.getInstance().currentactivity, permissionsToBeRequested.toArray(
                    new String[permissionsToBeRequested.size()]), UNUSED_REQUEST_CODE);
        }

        // Set location awareness and precision globally for your app:
        MoPub.setLocationAwareness(MoPub.LocationAwareness.TRUNCATED);
        MoPub.setLocationPrecision(4);

        //MoPubRewardedVideos.initializeRewardedVideo(ApplicationController.getInstance().getApplicationContext());
        //   MoPub.onCreate(ApplicationController.getInstance().currentactivity);

        MobileAds.initialize(ApplicationController.getInstance().getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");
        SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(AppID)
                .build();
        MoPub.initializeSdk(ApplicationController.getInstance().getApplicationContext(), sdkConfiguration, initSdkListener(mopubCallback));

        mPersonalInfoManager = MoPub.getPersonalInformationManager();
        if (mPersonalInfoManager != null) {
            mPersonalInfoManager.subscribeConsentStatusChangeListener(initConsentChangeListener());
        }


        //native adds


        ViewBinder mViewBinder = new ViewBinder.Builder(R.layout.native_ad_layout)
                .mainImageId(R.id.native_main_image)
                .iconImageId(R.id.native_icon_image)
                .titleId(R.id.native_title)
                .textId(R.id.native_text)
                .privacyInformationIconImageId(R.id.native_privacy_information_icon_image)
                .build();

        moPubStaticNativeAdRenderer = new MoPubStaticNativeAdRenderer(mViewBinder);


    }

    private static SdkInitializationListener initSdkListener(final MopubCallback mopubCallback) {
        return new SdkInitializationListener() {

            @Override
            public void onInitializationFinished() {
                IsIntialized = true;
                mopubCallback.onSuccess();
                Utils.logToast(ApplicationController.getInstance().getApplicationContext(), "SDK initialized.");
                if (mPersonalInfoManager != null && mPersonalInfoManager.shouldShowConsentDialog()) {
                    mPersonalInfoManager.loadConsentDialog(initDialogLoadListener());
                }
            }
        };
    }

    private static ConsentStatusChangeListener initConsentChangeListener() {
        return new ConsentStatusChangeListener() {

            @Override
            public void onConsentStateChange(@NonNull ConsentStatus oldConsentStatus,
                                             @NonNull ConsentStatus newConsentStatus,
                                             boolean canCollectPersonalInformation) {
                Utils.logToast(ApplicationController.getInstance().getApplicationContext(), "Consent: " + newConsentStatus.name());
                if (mPersonalInfoManager != null && mPersonalInfoManager.shouldShowConsentDialog()) {
                    mPersonalInfoManager.loadConsentDialog(initDialogLoadListener());
                }
            }
        };
    }

    private static ConsentDialogListener initDialogLoadListener() {
        return new ConsentDialogListener() {

            @Override
            public void onConsentDialogLoaded() {
                if (mPersonalInfoManager != null) {
                    mPersonalInfoManager.showConsentDialog();
                }
            }

            @Override
            public void onConsentDialogLoadFailed(@NonNull MoPubErrorCode moPubErrorCode) {
                Utils.logToast(ApplicationController.getInstance().getApplicationContext(), "Consent dialog failed to load.");
            }
        };
    }

    public static void LoadNativeRecyclerAdds(RecyclerView.Adapter originalAdapter) {

        mMoPubRecyclerAdapter = new MoPubRecyclerAdapter(ApplicationController.getInstance().currentactivity, originalAdapter, new MoPubNativeAdPositioning.MoPubClientPositioning().enableRepeatingPositions(5));

        mMoPubRecyclerAdapter.registerAdRenderer(moPubStaticNativeAdRenderer);

        mMoPubRecyclerAdapter.loadAds(AD_UNIT_ID);

    }


    public interface MopubCallback {
        void onSuccess();


    }
}
