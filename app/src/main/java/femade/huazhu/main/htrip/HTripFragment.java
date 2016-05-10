package femade.huazhu.main.htrip;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import femade.huazhu.R;


public class HTripFragment extends Fragment {


    // ========================= onCreateView =========================

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_htrip, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        initScrollView();
        initStars();
        initCar();
    }


    // ========================= CustomScrollView =========================

    @BindView(R.id.scrollView)
    CustomScrollView scrollView;

    private void initScrollView() {
        scrollView.setIvStar(ivStarOne);
    }


    // ========================= stars =========================

    @BindView(R.id.ivStarOne)
    ImageView ivStarOne;

    @BindView(R.id.ivStarTwo)
    ImageView ivStarTwo;

    @BindView(R.id.ivStarThree)
    ImageView ivStarThree;

    private void initStars() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startStarAnim(ivStarOne);
            }
        }, 0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startStarAnim(ivStarTwo);
            }
        }, 1000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startStarAnim(ivStarThree);
            }
        }, 2000);
    }

    private void startStarAnim(final ImageView ivStar) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1, 0);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                ivStar.setAlpha(value);
                if (1 == animation.getAnimatedFraction()) {
                    startStarAnim(ivStar);
                }
            }
        });
        valueAnimator.start();
    }


    // ========================= car =========================

    @BindView(R.id.ivCar)
    ImageView ivCar;

    private void initCar() {
        ivCar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int carWidth = ivCar.getWidth();
                startCarAnim(carWidth);
            }
        });
    }

    private void startCarAnim(final int carWidth) {
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(ivStarOne.getWidth(), -carWidth);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(8000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                ivCar.setLeft(value);
                if (1 == animation.getAnimatedFraction()) {
                    startCarAnim(carWidth);
                }
            }
        });
        valueAnimator.start();
    }


}
