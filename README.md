# Slide To Unlock
This library can fast and easy develop slide to unlock function.
* Slide icon and background can support image, shape and color code

ScreenShot
----------------
![ScreenShot](https://github.com/cheekiat/SlideToUnlock/blob/master/appscreenshort.gif)

Java Code
----------------
       SlideView mSlideView = (SlideView) findViewById(R.id.slide_view);
        mSlideView.setOnFinishListener(new SlideView.OnFinishListener() {
            @Override
            public void onFinish() {
                //someting to do
            }
        });

        
Xml Code
----------------
```
   <cheekiat.slideview.SlideView
        android:id="@+id/slide_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:duration="200"
        app:slideBackground="@drawable/orangesquarebutton"
        app:slideSrc="@drawable/slide_image"
        app:slideSrcMarginBottom="10dp"
        app:slideSrcMarginLeft="10dp"
        app:slideSrcMarginRight="10dp"
        app:slideSuccessPercent="50"
        app:slideSrcMarginTop="10dp"
        app:slideText="Slide to unlock"
        app:slideTextColor="#ffffff"
        app:slideTextSize="10dp" />
```
        
How to use?
----------------
### Java
| Public methods | Description |
| ------------- | ------------- |
| reset() | Reset the slide button. |
| setOnFinishListener() | Add slide success listener. |

### Xml
| XML attributes | Description |
| ------------- | ------------- |
| app:slideBackground | Set slide background resource. |
| app:slideSrc | Set slide icon resource. |
| app:slideSrcMarginTop | Set slide icon margin top. |
| app:slideSrcMarginRight | Set slide icon resource right. |
| app:slideSrcMarginBottom | Set slide icon resource bottom. |
| app:slideSrcMarginLeft | Set slide icon resource left. |
| app:slideText | Set slide layout text. |
| app:slideTextColor | Set slide layout text color. |
| app:slideTextSize | Set slide layout text size. |
| app:slideSuccessPercent | When slide more than your set value will animation to success. |

Download
----------------
```
repositories {
  mavenCentral() // jcenter() works as well because it pulls from Maven Central
}

dependencies {
 compile 'com.cheekiat:slideview:1.3'
}
```

Features
===================
* -
* -


## License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)
