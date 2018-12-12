# Timing
倒计时、验证码倒计时、时钟倒计时、CountDown

## 效果图 
![](https://github.com/yannecer/Timing/blob/master/app/timing1.png)
## 使用方法

#### Gradle
```
implementation 'com.necer:timing:1.0.0'

```
```
    //时钟倒计时
    <com.necer.timing.TimingView
        android:id="@+id/timeView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textColor="@color/colorAccent"
        android:textSize="20sp"
        app:dividerColor="#ff00ff"
        app:dividerTextSize="20sp"
        app:isDividerText="false"
        app:isVerticalCenter="true"
        app:second="359999" />
        
    //验证码倒计时
     <com.necer.timing.CountDownView
        android:id="@+id/countDownView"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:layout_marginTop="30dp"
        android:gravity="center"
        app:suffixText="秒后重新获取"
        app:unClickText="获取验证码" />

```
