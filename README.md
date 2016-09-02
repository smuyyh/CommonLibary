# CommonLibrary项目简介
CommonLibrary主要是自己整理的一些项目开发中常用的工具类、通用UI的集合，目前在不断的更新中，尽可能的覆盖Android开发中通用的一些东西<img src="https://github.com/arvida/emoji-cheat-sheet.com/blob/master/public/graphics/emojis/smirk.png?raw=true" width="18"/>
<img src="https://github.com/arvida/emoji-cheat-sheet.com/blob/master/public/graphics/emojis/smirk.png?raw=true" width="18"/>
<img src="https://github.com/arvida/emoji-cheat-sheet.com/blob/master/public/graphics/emojis/smirk.png?raw=true" width="18"/>。

---

> anim
> --
AnimationUtils、ViewAnimationUtils。动画工具类，也可为视图创建动画效果。

> data
> --
AESUtils、Base64、ByteUtil、DESUtils、HexUtil、MD5Utils、RSAEncrypt、SharePreUtils。用于数据的安全性及数据持久化

> io
> --
BitmapUtils、FileUtils、IOUtils、PhotoUtils、StorageUtils。用于图片、文件、Bitmap、IO流之间的转换

> log
> --
LogUtils。Log工具类，可控制Log输出开关、保存Log到文件、过滤输出等级

> network
> --
用于判断网络连接类型、监听网络变化、设置网络、开启GPS等。

> toast
> --
DialogUtils、NotificationUtils、ToastUtils。Toast工具类，解决连续弹出问题，发送通知栏信息、弹出对话框等。

> utils
> --
各类常用工具类，包括AlarmUtils，AsyncExecutor，CheckUtils，ClipboardUtils，DateUtils，DeviceUtils，FieldUtils，FlashLightUtils，InputMethodUtils，KeyguardLockUtils，NumberUtils，PackageUtils，PatternUtils，ShellUtils，StringUtils，VibrateUtils，WakeLockUtils，ConvertUtils、DimenUtils、JSONUtils、SerializeUtils

> view
> --
各类自定义控件。例如密码输入框、下拉回弹ListView、带删除键的EditText、自动匹配邮箱输入框...

> > ####text:
1、闪烁的文字ShimmerTextView及ShimmerButton，修改自[Shimmer-android](https://github.com/RomainPiel/Shimmer-android)，xml下新增相关动画属性，可直接启动动画。使用方式：
例如`ShimmerTextView`
```xml
<com.yuyh.library.view.text.ShimmerTextView
        android:id="@+id/tvShimmer"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:gravity="center"
        android:text="发现没，文字会发亮哦~~"
        android:textColor="#AAAAAA"
        android:textSize="20sp"
        app:direction="ANIMATION_DIRECTION_RTL"
        app:duration="2000"
        app:model="START_RIGHT_AWAY"
        app:reflectionColor="#FFFFFF"
        app:repeatCount="-1"
        app:startDelay="0"
        android:background="#000000"/>
```
当然，也可以在Java直接启动
```java
Shimmer shimmer = new Shimmer().setDirection(direction).setDuration(duration).setRepeatCount(repeatCount).setStartDelay(startDelay);
shimmer.start((ShimmerTextView) view);
```
2、SearchEditText。仿iOS的搜索框，默认搜索图标在中间，编辑模式下搜索图标移动到左边。示例：
```xml
<com.yuyh.library.view.text.SearchEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@drawable/edit_bg_white"
        android:hint="仿ios输入框"
        android:padding="50dp" />
```
3、ClearEditText。右侧带有清除文字按钮的EditText，是个挺实用的功能

> > 4、EmailAutoCompleteTextView。输入邮箱前缀，会相应生成一些常用的邮箱地址列表，供用户选择，实现快速输入

> > 5、PasswordEditText。默认限定六位密码、类似支付宝、微信支付密码输入框。

> > 6、... ...

> > ####progress
集成了[CircleProgress](https://github.com/lzyzsd/CircleProgress)这个进度条开源库以及[Android-SVProgressHUD](https://github.com/saiwu-bigkoo/Android-SVProgressHUD),便于显示进度条和Loadding动画

> > ####list
1、BorderScrollView。到达顶部或底部触发事件的ScrollView

> > 2、NoScrollListView、NotScrollGridView：全部展开的ListView，解决与ScrollView，由于这两款控件都自带滚动条，嵌套便会出问题，即GridView或ListView会显示不全。

> > 3、ReboundListView、ReboundScrollView：弹性ListView和ScrollView，类似iOS实现上下拉可以超出，手指离开后弹回的“阻尼”效果

> > 4、RefreshListView：轻量级下拉刷新ListView控件

> > ####layout
AutoLineFeedLayout：自动换行的容器。本质为ViewGroup

> > ####common
SafePopupWindow：弹窗之前检测Activity是否销毁，防止异常

> > ViewHolder：ListView或GridView等的适配器视图缓存公共类，在adapter的getView()中使用

> > ####button
1、SmoothCheckBox：自定义CheckBox，示例：
````xml
<com.yuyh.library.view.button.SmoothCheckBox
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="15dp"
            app:smoothCheckBox_color_checked="#FF0000"
            app:smoothCheckBox_color_unchecked_stroke="#FF0000"
            app:smoothCheckBox_is_rect="true" />
````
SwitchButton：自定义SwitchButton动画效果,示例：
````xml
<com.yuyh.library.view.button.SwitchButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:onColor="#aa66cc"
            app:swBorderWidth="2dp"/>
````
####image
CircleImageView：自定义圆形图片控件，可用于头像

> > GifView：轻量级可播放GIF动画的控件

> > RotateImageView：自动旋转的imageveiw，可用于Loadding

> > RounderImageView：圆角的ImageView

> > SimpleTagImageView：四个角斜边带标签的ImageView

> other
--
AppUtils:用于获取全局Context、执行UI线程...

> CrashHandler:全局异常捕获
