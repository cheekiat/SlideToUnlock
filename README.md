# Slide To Unlock
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[ ![Download](https://api.bintray.com/packages/kiathee/maven/slideview/images/download.svg) ](https://bintray.com/kiathee/maven/slideview/_latestVersion)

This library can fast and easy develop slide to unlock function.
* Slide icon and background can support image, shape and color code

ScreenShot
----------------
![ScreenShot](https://github.com/cheekiat/SlideToUnlock/blob/master/appscreenshort.gif)

Java Code
----------------
       SlideView mSlideView = (SlideView) findViewById(R.id.slide_view);

        mSlideView.setOnChangeListener(new OnChangeListener(){

            @Override
            public void onProgressChanged(int progress) {
                 //Show progress 0 ~ 100
            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this,"Complete!",Toast.LENGTH_SHORT).show();
            }
        });

        
Xml Code
----------------
```
   <cheekiat.slideview.SlideView
        android:id="@+id/slide_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:slideBackground="@drawable/orangesquarebutton"
        app:slideSrc="@drawable/slide_image"
        app:slideText="Slide to unlock"
        />
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
| app:slideText | Set slide layout text. |
| app:slideTextColor | Set slide layout text color. |
| app:slideTextSize | Set slide layout text size. |
| app:slideSuccessPercent | When slide more than your set value will animation to success. |
| app:duration | Set slide icon moveing duration. |
| app:slideSrcMarginTop | Set slide icon margin top. |
| app:slideSrcMarginRight | Set slide icon resource right. |
| app:slideSrcMarginBottom | Set slide icon resource bottom. |
| app:slideSrcMarginLeft | Set slide icon resource left. |

Download
----------------
```
repositories {
  mavenCentral() // jcenter() works as well because it pulls from Maven Central
}

dependencies {
 implementation 'com.cheekiat:slideview:1.5'
}
```

Features
===================
* If you've experienced any Bug. Please let me know, thanks.


