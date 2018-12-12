# Timing
倒计时、验证码倒计时、时钟倒计时、CountDown
TimingView和CountDownView继承于TextView ，理论上支持TextView的所有属性

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
        
        dividerColor 分割文本的颜色
        dividerTextSize  分割文本的字体大小
        isDividerText  分割是 时分秒或 ：
        isVerticalCenter 分割文本是否居中
        second 倒计时的描述，单位是秒
        dividerDistance 分割文本的距离
        
        
        
        
    //验证码倒计时
     <com.necer.timing.CountDownView
        android:id="@+id/countDownView"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:layout_marginTop="30dp"
        android:gravity="center"
        app:suffixText="秒后重新获取"
        app:unClickText="获取验证码" />
        
        millisInFuture 总时间
        countDownInterval 间隔时间
        unClickColor 未点击时的颜色
        unClickText 未点击时text
        clickedNumColor 点击之后数字的颜色
        suffixText 点击之后秒后面的文字
        suffixColor suffixText 字体颜色 

```
