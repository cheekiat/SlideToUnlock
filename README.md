# SlideToUnlock
This library can fast and easy develop slide to unlock function.

# Samples Code?
       SlideView mSlideView = (SlideView) findViewById(R.id.slide_view);
        mSlideView.setOnFinishListener(new SlideView.OnFinishListener() {
            @Override
            public void onFinish() {
                //someting to do
            }
        });

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideView.reset();
            }
        });
        
# Samples Xml
```
   <custom.ui.SlideView
        android:id="@+id/slide_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:duration="200"
        app:slideBackground="@drawable/orangesquarebutton"
        app:slideSrc="@drawable/slide_image"
        app:slideSrcMarginBottom="10dp"
        app:slideSrcMarginLeft="10dp"
        app:slideSrcMarginRight="10dp"
        app:slideSrcMarginTop="10dp"
        app:slideText="Slide to unlock"
        app:slideTextColor="#ffffff"
        app:slideTextSize="10dp" />
        ```
        
# How to use?
```
//Java
//Reset the slide button
reset();

//Add slide success listener
setOnFinishListener();

//Xml
//Set slide background resource
app:slideBackground="@drawable/orangesquarebutton"

//Set slide icon resource
app:slideSrc="@drawable/slide_image"

//Set slide icon margin
app:slideSrcMarginBottom="10dp"
app:slideSrcMarginLeft="10dp"
app:slideSrcMarginRight="10dp"
app:slideSrcMarginTop="10dp"

//Set slide text value
app:slideText="Slide to unlock"
app:slideTextColor="#ffffff"
app:slideTextSize="10dp" 
```

# Download
```
repositories {
  mavenCentral() // jcenter() works as well because it pulls from Maven Central
}

dependencies {
 compile 'com.cheekiat:slideview:1.1'
}
```
