package femade.huazhu.main.htrip;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ScrollView;


public class CustomScrollView extends ScrollView {

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //

    private ImageView ivStar;

    public void setIvStar(ImageView ivStar) {
        this.ivStar = ivStar;
    }

    //

    private float yDown;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 为什么要先调用 super 呢？
        boolean result = super.onTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                yDown = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                int distance = ivStar.getHeight();
                boolean close = getScrollY() > distance;
                if (!close) {
                    if (ev.getY() < yDown) {
                        if (getScrollY() > 0 ) {
                            smoothScrollTo(0, distance);
                        }
                    } else {
                        if (getScrollY() < distance ) {
                            smoothScrollTo(0, 0);
                        }
                    }
                }
        }
        return result;
    }

}