package com.lynnchurch.albumgridview;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * 
 * @author LynnChurch
 * 
 */
public class AlbumGridView extends GridView
{
	private ArrayList<String> mLargeImageUrls = new ArrayList<String>();
	private AlbumGridAdapter mAdapter;

	public AlbumGridView(Context context)
	{
		this(context, null, 0);
		// TODO Auto-generated constructor stub
	}

	public AlbumGridView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public AlbumGridView(final Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		mAdapter = new AlbumGridAdapter(context);
		setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3)
			{
				// TODO Auto-generated method stub
				Activity activity=(Activity)context;
				Intent intent = new Intent(activity, GalleryUrlActivity.class);
				intent.putExtra(GalleryUrlActivity.IMAGE_URLS, mLargeImageUrls);
				intent.putExtra(GalleryUrlActivity.CURRENT_ITEM, position);
				activity.startActivity(intent);
				activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
				
			}
		});
		setAdapter(mAdapter);
	}

	/**
	 * 添加缩略图并显示
	 * 
	 * @param thumbnailUrls
	 */
	public void addThumbnails(ArrayList<String> thumbnailUrls)
	{
		mAdapter.addThumbnails(thumbnailUrls);
	}

	public void addLargeImages(ArrayList<String> largeImages)
	{
		mLargeImageUrls.addAll(largeImages);
	}

	public void setDisplayImageOptions(DisplayImageOptions thumbnailOptions,DisplayImageOptions largeImageOptions)
	{
		mAdapter.setDisplayImageOptions(thumbnailOptions,largeImageOptions);
	}

}
