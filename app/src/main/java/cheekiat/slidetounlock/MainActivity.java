package cheekiat.slidetounlock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import custom.ui.SlideView;

public class MainActivity extends AppCompatActivity {

    SlideView mSlideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSlideView = (SlideView) findViewById(R.id.slide_view);
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
    }
}
