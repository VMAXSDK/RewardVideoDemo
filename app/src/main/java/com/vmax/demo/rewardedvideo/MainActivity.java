package com.vmax.demo.rewardedvideo;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vmax.android.ads.api.VmaxAdReward;
import com.vmax.android.ads.api.VmaxAdView;
import com.vmax.android.ads.api.VmaxSdk;
import com.vmax.android.ads.common.VmaxAdListener;
import com.vmax.android.ads.exception.VmaxAdError;



/** Its Recommended To Use VMAX plugin For Android Studio To Add Your Dependencies
 and Manage Changes in AndroidManifest as Well as Proguard,
 However You Can Manually Do This By Referring To Our Documentation Or following this Demo Project  */


public class MainActivity extends AppCompatActivity {


    private  LinearLayout UI_Container;
    public   VmaxAdView vmaxAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /** UI Container */
        UI_Container=(LinearLayout) findViewById(R.id.UI_Container);
        cacheRewardedVideo();
        UI_Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(vmaxAdView.getAdState().equals(VmaxAdView.AdState.STATE_AD_READY))
                    vmaxAdView.showAd();
                else
                    Toast.makeText(getApplicationContext(),"Ad Not Loaded Please Re-run The App",Toast.LENGTH_LONG).show();
            }
        });

    }


    /** Method for Loading Reward Video */
    public void cacheRewardedVideo()
    {


/** Initializing vmaxAdView with an Adspot, Repalce With the adspot Configured by you */
     vmaxAdView = new VmaxAdView(this,"b35fcf77",VmaxAdView.UX_INTERSTITIAL);

        /** To Fetch Your AdvId you can check your device's Google settings under ads subMenu Or You can Run this app Once and check
         * the logs for 'AdRequested with url' under the tag vmax, from the url your Advid
         * would be one of the parameters in the post request eg. advid=2cf626f0-08ac-4a4d-933c-00ecd0256cf4*/

   /** DON'T INCLUDE vmaxAdView.setTestDevices() WHILE GOING LIVE WITH YOUR PROJECT AS THIS SERVES ONLY TEST ADS;*/
        vmaxAdView.setTestDevices(VmaxAdView.TEST_via_ADVID,"2cf626f0-08ac-4a4d-933c-00ecd0256cf4");


        vmaxAdView.setAdListener(new VmaxAdListener() {
            @Override
            public void onAdError(VmaxAdError error) {

            }

            @Override
            public void onAdClose() {

            }

            @Override
            public void onAdMediaEnd(boolean b, long l) {
                Toast.makeText(getApplicationContext(),"You Have Earned :- "+String.valueOf(l)+" Points",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onAdReady(VmaxAdView adView) {

            }

        });

        vmaxAdView.cacheAd();


    }


   
    /** Handle vmaxAdView object for Activity Lifecycle changes */


    @Override
    protected void onDestroy() {
        if (vmaxAdView != null) {
       /** To Destroy vmaxAdView when Activity Is No Longer Available  */
            vmaxAdView.onDestroy();
        }
        super.onDestroy();
    }






}
