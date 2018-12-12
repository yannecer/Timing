package com.necer.timing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

/**
 * 倒计时，短信验证码
 * Created by necer on 2018/12/12.
 */
public class CountDownView extends AppCompatTextView {

    private CountDownTimer countDownTimer;
    private long millisInFuture, countDownInterval;//总时间，间隔时间
    private int unClickColor;//未点击时的颜色
    private String unClickText;//未点击时text
    private int clickedNumColor;//点击之后数字的颜色
    private String suffixText;//后缀
    private int suffixColor;//后缀字体颜色


    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CountDown);
        millisInFuture = ta.getInteger(R.styleable.CountDown_millisInFuture, 60 * 1000);
        countDownInterval = ta.getInteger(R.styleable.CountDown_countDownInterval, 1000);
        unClickColor = ta.getColor(R.styleable.CountDown_unClickColor, Color.parseColor("#333333"));
        unClickText = ta.getString(R.styleable.CountDown_unClickText);
        suffixText = ta.getString(R.styleable.CountDown_suffixText);
        clickedNumColor = ta.getColor(R.styleable.CountDown_clickedNumColor, Color.RED);
        suffixColor = ta.getColor(R.styleable.CountDown_suffixColor, Color.parseColor("#666666"));

        ta.recycle();

        init();
    }

    private void init() {
        reset();
        countDownTimer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long l) {
                String text = (l / 1000 < 10 ? "0" + l / 1000 : l / 1000) + suffixText;
                setContent(text);
            }

            @Override
            public void onFinish() {
                if (onCountDownListener != null) {
                    onCountDownListener.onCountDownFinish();
                }
                reset();
            }
        };
    }

    public void setMillis(long millisInFuture, long countDownInterval) {
        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;
        init();
    }

    public void setStyle(String unClickText, int unClickColor, int clickedNumColor, String suffixText, int suffixColor) {
        this.unClickText = unClickText;
        this.unClickColor = unClickColor;
        this.clickedNumColor = clickedNumColor;
        this.suffixText = suffixText;
        this.suffixColor = suffixColor;
        reset();
    }

    //还原
    private void reset() {
        setEnabled(true);
        setTextColor(unClickColor);
        setText(unClickText);
    }

    private void setContent(String timeText) {
        SpannableStringBuilder style = new SpannableStringBuilder(timeText);
        style.setSpan(new ForegroundColorSpan(clickedNumColor), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(suffixColor), 2, timeText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(style);
    }


    public void start() {
        setEnabled(false);
        countDownTimer.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        countDownTimer.cancel();
    }

    private OnCountDownListener onCountDownListener;

    public void setOnCountDownListener(OnCountDownListener onCountDownListener) {
        this.onCountDownListener = onCountDownListener;
    }

    public interface OnCountDownListener {
        void onCountDownFinish();
    }


}
