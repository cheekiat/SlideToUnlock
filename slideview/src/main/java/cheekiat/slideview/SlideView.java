package cheekiat.slideview;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Chee Kiat on 23/02/2017.
 */

public class SlideView extends RelativeLayout {

    private Drawable mSlideImage, mSlideBackground;
    private ImageView mSlideIcon;
    private TextView mSlideTextView;
    private float storeX;
    private String slideText;
    private Integer duration, slideTextColor;
    private boolean isCanTouch = true;
    private int slideTextSize, slideSrcMargin, slideSrcMarginLeft, slideSrcMarginTop, slideSrcMarginRight, slideSrcMarginBottom;

    OnFinishListener onFinishListener;

    public interface OnFinishListener {

        void onFinish();
    }

    public void setOnFinishListener(OnFinishListener listener) {
        this.onFinishListener = listener;
    }

    public SlideView(Context context) {
        super(context);
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SlideData,
                0, 0);

        try {


            mSlideImage = a.getDrawable(R.styleable.SlideData_slideSrc);
            slideSrcMargin = a.getDimensionPixelSize(R.styleable.SlideData_slideSrcMargin, 0);
            slideSrcMarginLeft = a.getDimensionPixelSize(R.styleable.SlideData_slideSrcMarginLeft, 0);
            slideSrcMarginTop = a.getDimensionPixelSize(R.styleable.SlideData_slideSrcMarginTop, 0);
            slideSrcMarginBottom = a.getDimensionPixelSize(R.styleable.SlideData_slideSrcMarginBottom, 0);
            slideSrcMarginRight = a.getDimensionPixelSize(R.styleable.SlideData_slideSrcMarginRight, 0);

            mSlideBackground = a.getDrawable(R.styleable.SlideData_slideBackground);
            duration = a.getInteger(R.styleable.SlideData_duration, 200);


            slideText = a.getString(R.styleable.SlideData_slideText);
            slideTextSize = a.getDimensionPixelSize(R.styleable.SlideData_slideTextSize, 20);
            slideTextColor = a.getColor(R.styleable.SlideData_slideTextColor, Color.BLACK);
        } finally {
            a.recycle();
        }

        if (mSlideBackground != null) {
            setBackground(mSlideBackground);
        }

        mSlideIcon = new ImageView(getContext());
        if (mSlideImage != null) {
            mSlideIcon.setImageDrawable(mSlideImage);
            mSlideIcon.setPadding(slideSrcMarginLeft, slideSrcMarginTop, slideSrcMarginRight, slideSrcMarginBottom);
        }

        if (slideText != null && !slideText.isEmpty()) {
            mSlideTextView = new TextView(getContext());

            mSlideTextView.setText(slideText);
            if (slideTextColor != null) {
                mSlideTextView.setTextColor(slideTextColor);
            }
            mSlideTextView.setTextSize(slideTextSize);
            addView(mSlideTextView);

            LayoutParams layoutParams =
                    (LayoutParams) mSlideTextView.getLayoutParams();
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            mSlideTextView.setLayoutParams(layoutParams);
        }

        addView(mSlideIcon);
        LayoutParams layoutParams =
                (LayoutParams) mSlideIcon.getLayoutParams();
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        mSlideIcon.setLayoutParams(layoutParams);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                if (isCanTouch) {
                    float maxRight = (getWidth()) - mSlideIcon.getWidth();

                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            storeX = event.getRawX();
                            if (mSlideIcon.getTag() == null) {
                                mSlideIcon.setTag(mSlideIcon.getX());
                            }
                            break;

                        case MotionEvent.ACTION_MOVE:

                            float sum = Math.abs(storeX - event.getRawX());

                            if (event.getRawX() < storeX) {
                                mSlideIcon.animate().setDuration(0).x((float) mSlideIcon.getTag()).start();
                            } else if (sum > maxRight) {
                                mSlideIcon.animate().setDuration(0).x(maxRight).start();
                            } else {
                                mSlideIcon.animate().setDuration(0).x(sum).start();
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            isCanTouch = false;
                            float maxSum2 = (getWidth()) - mSlideIcon.getWidth();
                            if (mSlideIcon.getX() < (maxSum2 / 2)) {
                                mSlideIcon.animate().setDuration(duration).x((float) mSlideIcon.getTag()).setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animator) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animator) {
                                        isCanTouch = true;
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animator) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animator) {

                                    }
                                }).start();
                            } else {

                                mSlideIcon.animate().setDuration(duration).x(maxRight).setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {
                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        if (onFinishListener != null) {
                                            onFinishListener.onFinish();
                                        }
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                }).start();
                            }

                            break;
                        default:
                            return false;
                    }

                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void reset() {
        if (mSlideIcon != null && mSlideIcon.getTag() != null) {
            mSlideIcon.animate().setListener(null);
            mSlideIcon.animate().setDuration(0).x((float) mSlideIcon.getTag()).start();
            isCanTouch = true;
        }
    }
}
