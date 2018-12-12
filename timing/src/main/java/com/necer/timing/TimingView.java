package com.necer.timing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;

/**
 * Created by necer on 2018/12/12.
 */
public class TimingView extends AppCompatTextView {


    private int dividerColor;
    private float dividerTextSize;
    private boolean isDividerText;//是否显示文字 时分秒
    private boolean isVerticalCenter;
    private int second; //秒
    private float dividerDistance; //间隔距离

    private Handler handler = new Handler();


    public TimingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Timing);

        dividerColor = ta.getColor(R.styleable.Timing_dividerColor, Color.parseColor("#8A000000"));
        second = ta.getInteger(R.styleable.Timing_second, 0);
        isDividerText = ta.getBoolean(R.styleable.Timing_isDividerText, false);
        isVerticalCenter = ta.getBoolean(R.styleable.Timing_isVerticalCenter, true);
        dividerDistance = ta.getDimension(R.styleable.Timing_dividerDistance, Util.dp2px(context,2));
        dividerTextSize = ta.getDimension(R.styleable.Timing_dividerTextSize, Util.sp2px(context, 14));
        ta.recycle();


    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            second--;
            if (second > 0) {
                handler.postDelayed(runnable, 1000);
            } else if (second == 0 && onTimeFinishListener != null) {
                onTimeFinishListener.onTimeFinish();
            }
            setContent(Util.changeTime(second,isDividerText));
        }
    };


    private void setContent(String timeText) {

        SpannableStringBuilder style = new SpannableStringBuilder(timeText);
        style.setSpan(new DividerSpan(dividerTextSize, dividerColor, dividerDistance, isVerticalCenter), 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new DividerSpan(dividerTextSize, dividerColor, dividerDistance, isVerticalCenter), 5, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        if (timeText.length() > 8) {
            style.setSpan(new DividerSpan(dividerTextSize, dividerColor, dividerDistance, isVerticalCenter), 8, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        setText(style);
    }




    public TimingView setSecondTime(int second) {
        this.second = second;
        return this;
    }

    public TimingView start() {
        if (second == 0) {
            throw new RuntimeException("未设置倒计时时间!");
        } else if (second > 359999) {
            throw new RuntimeException("倒计时时间不能大于359999秒!");
        }
        setContent(Util.changeTime(second,isDividerText));
        handler.postDelayed(runnable, 1000);
        return this;
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(runnable);
    }

    private OnTimeFinishListener onTimeFinishListener;

    public TimingView setOnTimeFinishListener(OnTimeFinishListener onTimeFinishListener) {
        this.onTimeFinishListener = onTimeFinishListener;
        return this;
    }

    public interface OnTimeFinishListener {
        void onTimeFinish();
    }
}
