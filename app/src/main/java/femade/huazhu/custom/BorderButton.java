package femade.huazhu.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;


public class BorderButton extends Button {

    public BorderButton(Context context) {
        super(context);
        init();
    }

    public BorderButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BorderButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BorderButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    //

    private void init() {
        final int cornerRadius = 10;
        final int strokeWidth = 3;
        GradientDrawable background = new GradientDrawable();
        background.setCornerRadius(cornerRadius);
        background.setStroke(strokeWidth, getCurrentTextColor());
        background.setColor(Color.WHITE);
        setBackground(background);
    }

}
