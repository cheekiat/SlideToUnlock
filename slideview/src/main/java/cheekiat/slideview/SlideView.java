package cheekiat.slideview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
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
    private int slideSuccessPercent;
    private int getPercent;
    private boolean slideAutocomplete;

    OnFinishListener onFinishListener;
    OnChangeListener onChangeListener;
    MutableLiveData<Integer> progress = new MutableLiveData<>();

    int progressMin, progressMax;

    public void setOnFinishListener(OnFinishListener listener) {
        this.onFinishListener = listener;
    }

    public void setOnChangeListener(OnChangeListener listener) {
        this.onChangeListener = listener;
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
            slideSuccessPercent = a.getInteger(R.styleable.SlideData_slideSuccessPercent, 0);
            mSlideBackground = a.getDrawable(R.styleable.SlideData_slideBackground);
            duration = a.getInteger(R.styleable.SlideData_duration, 200);
            slideAutocomplete = a.getBoolean(R.styleable.SlideData_slideAutocomplete, true);


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

        Log.d(this.getClass().getName(), "getWidth()) : " + getWidth());
        Log.d(this.getClass().getName(), "mSlideIcon()) : " + mSlideIcon.getWidth());

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                if (getPercent == 0) {
                    if (slideSuccessPercent == 0) {
                        getPercent = ((getWidth()) - mSlideIcon.getWidth()) / 2;
                    } else {
                        getPercent = (((getWidth() * slideSuccessPercent) / 100)) - (mSlideIcon.getWidth() / 2);
                    }
                }

                if (isCanTouch) {
                    progressMax = (getWidth()) - mSlideIcon.getWidth();

                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:
                            if (!slideAutocomplete) {
                                storeX = mSlideIcon.getX()-event.getRawX();
                            } else {
                                storeX = event.getRawX();
                            }
                            if (mSlideIcon.getTag() == null) {
                                mSlideIcon.setTag(mSlideIcon.getX());

                                progressMin = (int) ((float) mSlideIcon.getTag());
                            }
                            break;

                        case MotionEvent.ACTION_MOVE:

                            if (!slideAutocomplete) {
                                float sum = Math.abs(storeX + event.getRawX());
                                if (progressMin > (storeX + event.getRawX())) {
                                    mSlideIcon.animate().setDuration(0).x((float) mSlideIcon.getTag()).start();
                                } else if (sum > progressMax) {
                                    mSlideIcon.animate().setDuration(0).x(progressMax).start();
                                } else {
                                    mSlideIcon.animate().setDuration(0).x(sum).start();
                                }
                            } else {

                                float sum = Math.abs(storeX - event.getRawX());
                                if (event.getRawX() < storeX) {
                                    mSlideIcon.animate().setDuration(0).x((float) mSlideIcon.getTag()).start();
                                } else if (sum > progressMax) {
                                    mSlideIcon.animate().setDuration(0).x(progressMax).start();
                                } else {
                                    mSlideIcon.animate().setDuration(0).x(sum).start();
                                }
                            }

                            progress.setValue((int) mSlideIcon.getX());
                            break;
                        case MotionEvent.ACTION_UP:

                            if (!slideAutocomplete) {
                                isCanTouch = true;
                                return false;
                            } else {
                                isCanTouch = false;
                            }

                            if (mSlideIcon.getX() < getPercent) {

                                ViewCompat.animate(mSlideIcon).setDuration(duration).x((float) mSlideIcon.getTag()).setListener(new ViewPropertyAnimatorListener() {
                                    @Override
                                    public void onAnimationStart(View view) {

                                    }

                                    @Override
                                    public void onAnimationEnd(View view) {
                                        isCanTouch = true;
                                    }

                                    @Override
                                    public void onAnimationCancel(View view) {

                                    }
                                }).setUpdateListener(new ViewPropertyAnimatorUpdateListener() {
                                    @Override
                                    public void onAnimationUpdate(View view) {
                                        progress.setValue((int) view.getX());
                                    }
                                }).start();

                            } else {

                                ViewCompat.animate(mSlideIcon).setDuration(duration).x(progressMax).setListener(new ViewPropertyAnimatorListener() {
                                    @Override
                                    public void onAnimationStart(View view) {

                                    }

                                    @Override
                                    public void onAnimationEnd(View view) {
                                        if (onFinishListener != null) {
                                            onFinishListener.onFinish();
                                        }

                                        if (onChangeListener != null) {
                                            onChangeListener.onComplete();
                                        }
                                    }

                                    @Override
                                    public void onAnimationCancel(View view) {

                                    }
                                }).setUpdateListener(new ViewPropertyAnimatorUpdateListener() {
                                    @Override
                                    public void onAnimationUpdate(View view) {
                                        progress.setValue((int) view.getX());
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

        progress.observe((LifecycleOwner) getContext(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {

                if (onChangeListener != null) {

                    int progress = integer == 0 ? 0 : ((integer * 100) / progressMax);

                    onChangeListener.onProgressChanged(progress);
                }
            }
        });

    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    public void reset() {
        if (mSlideIcon != null && mSlideIcon.getTag() != null) {
            mSlideIcon.animate().setListener(null);
            mSlideIcon.animate().setDuration(0).x((float) mSlideIcon.getTag()).start();
            isCanTouch = true;
        }
    }
}
