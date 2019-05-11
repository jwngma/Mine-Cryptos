package com.store.minecryps.ViewPagerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.store.minecryps.R;

public class WelcomeIntroViewPagerAdapter extends PagerAdapter {

    private Context context;

    public WelcomeIntroViewPagerAdapter(Context context) {
        this.context = context;
    }

    private int[] images = {R.drawable.btc, R.drawable.eth, R.drawable.dash, R.drawable.bcn, R.drawable.doge, R.drawable.xrp};
    private String[] titles = {" Add", " Collar", "Lethal", "Cloud", "Coin", "Coffe"};
    private String[] descc = {"The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.",
            "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.",
            "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.",
            "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.",
            "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.",
            "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.",};

    @Override
    public int getCount() {
        return descc.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.intro_slider_item, container, false);
        ImageView image = view.findViewById(R.id.slider_image);
        TextView title = view.findViewById(R.id.slider_title);
        TextView desc = view.findViewById(R.id.slider_desc);
        image.setImageResource(images[position]);
        title.setText(titles[position]);
        desc.setText(descc[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
