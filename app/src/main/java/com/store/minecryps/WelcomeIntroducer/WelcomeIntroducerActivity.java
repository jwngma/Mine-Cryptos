package com.store.minecryps.WelcomeIntroducer;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.store.minecryps.Activities.MainActivity;
import com.store.minecryps.PreferenceManager.PreferenceManager;
import com.store.minecryps.R;
import com.store.minecryps.ViewPagerAdapter.WelcomeIntroViewPagerAdapter;
import com.store.minecryps.WelccomeScreen.WelcomeScreenActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeIntroducerActivity extends AppCompatActivity {
    private static final String TAG = "WelcomeIntroducerActivi";

    private ViewPager welcome_viewpager;
    private WelcomeIntroViewPagerAdapter adapter;
    private Button skipBtn;
    private Button nextBtn;
    private LinearLayout dots_layout;
    private ImageView[] dots;
    private int currentPage = 0;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_introducer);

        if (new PreferenceManager(this).checkPreference()){
            SplashIntent();
            finish();
        }

        /*if (Build.VERSION.SDK_INT>=19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/
        initAll();
        initViewpager();
        initButton();
    }


    private void initAll() {
        welcome_viewpager = findViewById(R.id.intro_viewpager);
        skipBtn = findViewById(R.id.skip_btn);
        nextBtn = findViewById(R.id.next_btn);
        dots_layout = findViewById(R.id.dots_layout);


    }

    private void initButton() {
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToHome();
                new PreferenceManager(WelcomeIntroducerActivity.this).writePreference();

            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextSlide();

            }
        });

    }

    private void sendToHome() {
        Intent intent = new Intent(WelcomeIntroducerActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    private void SplashIntent(){
        Intent intent= new Intent(WelcomeIntroducerActivity.this, WelcomeScreenActivity.class);
        startActivity(intent);
    }
    private void nextSlide() {

        int nextSlide = welcome_viewpager.getCurrentItem() + 1;
        if (nextSlide < 6) {
            welcome_viewpager.setCurrentItem(nextSlide);
        } else {
            sendToHome();
            new PreferenceManager(WelcomeIntroducerActivity.this).writePreference();

        }
    }

    private void initViewpager() {
        adapter = new WelcomeIntroViewPagerAdapter(this);
        welcome_viewpager.setAdapter(adapter);
        CreateDots(currentPage);
        welcome_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                CreateDots(i);

                if (i == 5) {
                    skipBtn.setVisibility(View.INVISIBLE);
                    nextBtn.setText("Start");

                } else {
                    skipBtn.setVisibility(View.VISIBLE);
                    nextBtn.setText("Next");
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {


            }
        });
        startViewpgerSlideshow();

    }

    private void startViewpgerSlideshow() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {

                if (currentPage >= 6) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            currentPage = 1;
                        }
                    },7000);

                }
                welcome_viewpager.setCurrentItem(currentPage++, false);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);

            }
        }, 3000, 3000);
    }

    private void CreateDots(int current_position) {

        if (dots_layout != null) {
            dots_layout.removeAllViews();

            dots = new ImageView[6];

            for (int i = 0; i < 6; i++) {
                dots[i] = new ImageView(this);

                if (i == current_position) {
                    dots[i].setImageResource(R.drawable.active_dots);

                } else {
                    dots[i].setImageResource(R.drawable.inactive_dots);
                }

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dots_layout.addView(dots[i], params);
            }

        }

    }

}
