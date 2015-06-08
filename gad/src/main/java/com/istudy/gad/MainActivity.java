package com.istudy.gad;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private static final long EXPIRED_TIME = 2000L;
    private long lastBackPressedTimeMillis;

    private AdView bannerTop;
    private PublisherAdView bannerBottom;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadAllAd();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bannerTop.pause();
        bannerBottom.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bannerTop.destroy();
        bannerBottom.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bannerTop.resume();
        bannerBottom.resume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_refresh_top:
                loadTopBanner();
                return true;
            case R.id.action_refresh_bottom:
                loadBottomBanner();
                return true;
            case R.id.action_refresh_main:
                loadMainInterstitial();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastBackPressedTimeMillis < EXPIRED_TIME) {
            super.onBackPressed();
        } else {
            lastBackPressedTimeMillis = currentTimeMillis;
            if (interstitialAd.isLoaded()) {
                interstitialAd.show();
            } else {
                Toast.makeText(MainActivity.this, "再按一次，退出应用", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void init() {
        bannerTop = (AdView) findViewById(R.id.bannerTop);
        bannerTop.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
            }
        });

        bannerBottom = (PublisherAdView) findViewById(R.id.bannerBottom);
        bannerBottom.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });

        interstitialAd = new InterstitialAd(MainActivity.this);
        interstitialAd.setAdUnitId(getString(R.string.unit_id_interstitial_main));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });
    }

    private void loadAllAd() {
        loadTopBanner();
        loadBottomBanner();
        loadMainInterstitial();
    }

    private void loadTopBanner() {
        bannerTop.loadAd(new AdRequest.Builder().build());
    }

    private void loadBottomBanner() {
        bannerBottom.loadAd(new PublisherAdRequest.Builder().build());
    }

    private void loadMainInterstitial() {
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }
}
