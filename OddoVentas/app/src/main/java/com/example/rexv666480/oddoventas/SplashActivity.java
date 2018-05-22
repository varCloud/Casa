package com.example.rexv666480.oddoventas;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private View mCardFrontLayout;
    private View mCardBackLayout;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible = false;

    @BindView(R.id.imageGris)
    ImageView imageGris;

    @BindView(R.id.imageRosa)
    ImageView imageRosa;

    @BindView(R.id.imageO3)
    ImageView imageO3;

    @BindView(R.id.card_logo)
    FrameLayout card_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this,IniciarSesionActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

       /*
        */

        findViews();
        loadAnimations();
        changeCameraDistance();
        flipCard();
        Animation fadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);
        imageRosa.startAnimation(fadeout);
        imageGris.setVisibility(View.GONE);
        imageRosa.setVisibility(View.INVISIBLE);
        imageGris.startAnimation(fadeout);

        try {

            Thread.sleep(1000);
            imageO3.setVisibility(View.VISIBLE);
            Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
            imageO3.startAnimation(fadeIn);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
    private void findViews() {
        mCardBackLayout = findViewById(R.id.card_back);
        mCardFrontLayout = findViewById(R.id.card_front);
    }

    private void changeCameraDistance() {
        int distance = 8000;
        float scale = getResources().getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    private void loadAnimations() {
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.in_animation);
    }

    public void flipCard() {
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = true;
        } else {
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            mIsBackVisible = false;
        }
    }
}
