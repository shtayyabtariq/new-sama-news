package xeon.com.youtube;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.ui.PlayerUIController;

import mopub.xeon.com.mopub.MopubManager;

public class MainActivity extends AppCompatActivity {

    YouTubePlayerView youTubePlayerView;
    String values = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MopubManager.ShowIntersitial();


        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // Toast.makeText(getApplicationContext(),"it is called",Toast.LENGTH_LONG).show();
                MopubManager.ShowIntersitial();
                MopubManager.LoadIntersitial();
                //Do something after 100ms
                handler.postDelayed(this, 950000);
            }
        }, 5000);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("geonews");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                final String value = dataSnapshot.getValue(String.class);
                Log.d("val", "Value is: " + value);


                //Toast.makeText(getApplication(),value,Toast.LENGTH_LONG).show();


                values = value;
                Initializeyoutube(value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("val", "Failed to read value.", error.toException());
            }
        });


        //Toast.makeText(getApplicationContext(),"resume called",Toast.LENGTH_LONG).show();
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

    }


    public void Initializeyoutube(final String videoId) {

        youTubePlayerView.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {

                        initializedYouTubePlayer.loadVideo(videoId, 0);
                        initializedYouTubePlayer.addListener(new YouTubePlayerListener() {
                            @Override
                            public void onReady() {

                            }

                            @Override
                            public void onStateChange(@NonNull PlayerConstants.PlayerState state) {

                            }

                            @Override
                            public void onPlaybackQualityChange(@NonNull PlayerConstants.PlaybackQuality playbackQuality) {

                            }

                            @Override
                            public void onPlaybackRateChange(@NonNull PlayerConstants.PlaybackRate playbackRate) {

                            }

                            @Override
                            public void onError(@NonNull PlayerConstants.PlayerError error) {

                            }

                            @Override
                            public void onApiChange() {

                            }

                            @Override
                            public void onCurrentSecond(float second) {

                            }

                            @Override
                            public void onVideoDuration(float duration) {

                            }

                            @Override
                            public void onVideoLoadedFraction(float loadedFraction) {

                            }

                            @Override
                            public void onVideoId(@NonNull String videoId) {

                            }
                        });
                    }
                });
            }
        }, true);


        PlayerUIController playerUIController = youTubePlayerView.getPlayerUIController();
        playerUIController.enableLiveVideoUI(true);
        playerUIController.showMenuButton(false);
        playerUIController.showFullscreenButton(false);
        playerUIController.showUI(false);
        getLifecycle().addObserver(youTubePlayerView);


    }
}
