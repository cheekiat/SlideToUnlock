package cheekiat.slidetounlock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cheekiat.slideview.OnChangeListener;
import cheekiat.slideview.OnFinishListener;
import cheekiat.slideview.SlideView;

public class MainActivity extends AppCompatActivity {

    SlideView mSlideView, mSlideViewImage,mSlideViewAutocompleteImage;

    TextView progressView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSlideView = (SlideView) findViewById(R.id.slide_view);
        mSlideViewImage = (SlideView) findViewById(R.id.slide_view2);
        mSlideViewAutocompleteImage = (SlideView) findViewById(R.id.slide_view_autocomplete);
        progressView = (TextView)findViewById(R.id.progress);

        mSlideView.setOnChangeListener(new OnChangeListener(){

            @Override
            public void onProgressChanged(int progress) {
                progressView.setText("Prgress : "+progress);
            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this,"Shape background success!",Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideView.reset();
                mSlideViewImage.reset();
                mSlideViewAutocompleteImage.reset();
            }
        });

        mSlideViewImage.setOnFinishListener(new OnFinishListener() {
            @Override
            public void onFinish() {
                //someting to do
                Toast.makeText(MainActivity.this,"Image background success!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
