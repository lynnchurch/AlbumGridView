/*
 Copyright (c) 2012 Roman Truba

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial
 portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.lynnchurch.touchview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class UrlTouchImageView extends RelativeLayout
{
	protected ProgressBar mProgressBar;
	protected TouchImageView mImageView;
	private static DisplayImageOptions mLargeImageOptions;
	protected Context mContext;

	public UrlTouchImageView(Context ctx)
	{
		super(ctx);
		mContext = ctx;
		init();

	}

	public UrlTouchImageView(Context ctx, AttributeSet attrs)
	{
		super(ctx, attrs);
		mContext = ctx;
		init();
	}

	public TouchImageView getImageView()
	{
		return mImageView;
	}

	@SuppressWarnings("deprecation")
	protected void init()
	{
		mImageView = new TouchImageView(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		mImageView.setLayoutParams(params);
		this.addView(mImageView);
		mImageView.setVisibility(GONE);

		mProgressBar = new ProgressBar(mContext, null,
				android.R.attr.progressBarStyleHorizontal);
		params = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		params.setMargins(30, 0, 30, 0);
		mProgressBar.setLayoutParams(params);
		mProgressBar.setIndeterminate(false);
		mProgressBar.setMax(100);
		this.addView(mProgressBar);
	}

	public void setUrl(String imageUrl)
	{
		if (null != mLargeImageOptions)
		{
			ImageLoader.getInstance().displayImage(imageUrl, mImageView,
					mLargeImageOptions, mLoadingListener, mProgressListener);
		} else
		{
			ImageLoader.getInstance().displayImage(imageUrl, mImageView,
					mLoadingListener);
			;
		}
	}

	public void setScaleType(ScaleType scaleType)
	{
		mImageView.setScaleType(scaleType);
	}

	private SimpleImageLoadingListener mLoadingListener = new SimpleImageLoadingListener()
	{
		@Override
		public void onLoadingStarted(String imageUri, View view)
		{
			mProgressBar.setProgress(0);
			mProgressBar.setVisibility(View.VISIBLE);
		}

		@Override
		public void onLoadingFailed(String imageUri, View view,
				FailReason failReason)
		{
			mProgressBar.setVisibility(View.GONE);
			mImageView.setVisibility(View.VISIBLE);
			mImageView.setImageDrawable(mLargeImageOptions
					.getImageOnFail(mContext.getResources()));
		}

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage)
		{
			mImageView.setScaleType(ScaleType.MATRIX);
			mImageView.setImageBitmap(loadedImage);
			mImageView.setVisibility(VISIBLE);
			mProgressBar.setVisibility(GONE);
		}
	};

	private ImageLoadingProgressListener mProgressListener = new ImageLoadingProgressListener()
	{
		@Override
		public void onProgressUpdate(String imageUri, View view, int current,
				int total)
		{
			mProgressBar.setProgress(Math.round(100.0f * current / total));
		}
	};

	public static void setLargeImageOptions(
			DisplayImageOptions largeImageOptions)
	{
		mLargeImageOptions = largeImageOptions;
	}
}
