package femade.huazhu.main;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import femade.huazhu.R;


public class MainActivity extends AppCompatActivity {


    // ========================= onCreate =========================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        if (savedInstanceState == null) {
            bottomTabs.get(0).setChecked(true);
        }
    }

    private void initViews() {
        initViewPager();
        initBottomTabs();
    }


    // ========================= viewpager =========================

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private void initViewPager() {
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomTabs.get(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    // ========================= bottomTabs =========================

    @BindViews({R.id.rbHome, R.id.rbRecommendHotel, R.id.rbTripAssist, R.id.rbWallet, R.id.rbProfile})
    List<RadioButton> bottomTabs;

    final private int[] drawableResUnchecked = {R.drawable.tab_icon_home, R.drawable.tab_icon_recommend_hotel,
            R.drawable.tab_icon_htrip, R.drawable.tab_icon_wallet, R.drawable.tab_icon_profile};
    final private int[] drawableResChecked = {R.drawable.tab_icon_home_pressed, R.drawable.tab_icon_recommend_hotel_pressed,
            R.drawable.tab_icon_htrip_pressed, R.drawable.tab_icon_wallet_pressed, R.drawable.tab_icon_profile_pressed};

    private void initBottomTabs() {
        for (RadioButton bottomTab : bottomTabs) {
            bottomTab.setTextColor(getBottomTabTextColor());
            int index = bottomTabs.indexOf(bottomTab);
            Drawable top = getBottomTabTopDrawable(drawableResUnchecked[index], drawableResChecked[index]);
            bottomTab.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
        }
        addBottomTabCheckedChangeListener();
    }

    private ColorStateList getBottomTabTextColor() {
        return new ColorStateList(
                new int[][]{{-android.R.attr.state_checked}, {android.R.attr.state_checked}},
                new int[]{ContextCompat.getColor(this, R.color.md_black), ContextCompat.getColor(this, R.color.colorPrimary)}
        );
    }

    @SuppressWarnings("deprecation")
    private Drawable getBottomTabTopDrawable(@DrawableRes int unchecked, @DrawableRes int checked) {
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{-android.R.attr.state_checked}, getResources().getDrawable(unchecked));
        drawable.addState(new int[]{android.R.attr.state_checked}, getResources().getDrawable(checked));
        return drawable;
    }

    @BindView(R.id.bottomTabGroup)
    RadioGroup bottomTabGroup;

    private void addBottomTabCheckedChangeListener() {
        bottomTabGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton bottomTabChecked = (RadioButton) group.findViewById(checkedId);
                int index = bottomTabs.indexOf(bottomTabChecked);
                viewPager.setCurrentItem(index, false);
            }
        });
    }

}
