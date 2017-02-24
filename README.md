# SlideToUnlock
This library can fast and easy develop slide to unlock function.

# How to use?
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
        
# Download
repositories {

  mavenCentral() // jcenter() works as well because it pulls from Maven Central

}

dependencies {

  compile 'com.cheekiat:slideview:1.1'

  }
