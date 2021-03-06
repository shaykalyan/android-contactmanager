package com.akshaykalyan.contactutilities;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;

/**
 * Class extending ImageView to provide custom image view animations. This view 
 * allows the user to expand and collapse an ImageView.
 * 
 * Adapted from :
 * http://im-dexter.blogspot.co.nz/2013/01/android-expandable-collapsible-image.html
 * by @author Sreejith B Naick
 */
public class ImageDrawer extends ImageView {
	private final static int STATE_CLOSED = 0;
	private final static int STATE_BETWEEN = 1;
	private final static int STATE_OPEN = 2;
	private int mClosedHeight = dpToPx(150);
	private int mOpenHeight = dpToPx(300);
	private int mState = STATE_CLOSED;
	private DecelerateInterpolator mInterpolator;
	private boolean mSetOpenHeight = true;

	public ImageDrawer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mInterpolator = new DecelerateInterpolator();
		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mState == STATE_CLOSED) {
					exapand();
				} else if (mState == STATE_OPEN)
					collapse();
			}
		});
	}

	public ImageDrawer(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ImageDrawer(Context context) {
		this(context, null, 0);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()
				- getPaddingRight();
		if (!mSetOpenHeight) {
			mOpenHeight = MeasureSpec.getSize(heightMeasureSpec);
			if (getDrawable() != null) {
				mOpenHeight = mOpenHeight <= getDrawable().getIntrinsicHeight() ? mOpenHeight
						: getDrawable().getIntrinsicHeight();
			}
		}
		if (mState == STATE_CLOSED)
			setMeasuredDimension(resolveSize(width, widthMeasureSpec),
					resolveSize(mClosedHeight, heightMeasureSpec));
		else if (mState == STATE_OPEN)
			setMeasuredDimension(resolveSize(width, widthMeasureSpec),
					resolveSize(mOpenHeight, heightMeasureSpec));
		else {
			setMeasuredDimension(resolveSize(width, widthMeasureSpec),
					resolveSize(getLayoutParams().height, heightMeasureSpec));
		}
	}

	public void setClosedHeight(int height, Boolean isPixel) {
		mClosedHeight = height;
		if (!isPixel) {
			mClosedHeight = dpToPx(mClosedHeight);
		}
	}

	public void setOpenHeight(int height, Boolean isPixel) {
		mSetOpenHeight = true;
		mOpenHeight = height;
		if (!isPixel) {
			mOpenHeight = dpToPx(mOpenHeight);
		}
	}

	private int dpToPx(int dpValue) {
		float density = getResources().getDisplayMetrics().density;
		return (int) (density * dpValue);
	}

	private void exapand() {
		final int targetHeight = mOpenHeight - mClosedHeight;
		Animation a = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime,
					Transformation t) {
				if (interpolatedTime > 0) {
					getLayoutParams().height = interpolatedTime == 1 ? LayoutParams.WRAP_CONTENT
							: (int) mClosedHeight
									+ (int) (targetHeight * interpolatedTime);
					requestLayout();

				}
			}
		};
		a.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				mState = STATE_BETWEEN;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				mState = STATE_OPEN;
			}
		});
		a.setInterpolator(mInterpolator);
		a.setDuration(1000);
		startAnimation(a);
	}

	private void collapse() {
		final int targetHeight = mOpenHeight - mClosedHeight;
		Animation anim = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime,
					Transformation t) {
				if (interpolatedTime == 1) {
					getLayoutParams().height = LayoutParams.WRAP_CONTENT;
				} else {
					int current = mOpenHeight
							- (int) (targetHeight * interpolatedTime);
					if (current >= mClosedHeight) {
						getLayoutParams().height = current;
						requestLayout();
					} else {
						getLayoutParams().height = mClosedHeight;
						requestLayout();
					}
				}
			}
		};
		anim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				mState = STATE_BETWEEN;
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				mState = STATE_CLOSED;
				requestLayout();
			}
		});
		anim.setInterpolator(mInterpolator);
		anim.setDuration(1000);
		startAnimation(anim);
	}
}
