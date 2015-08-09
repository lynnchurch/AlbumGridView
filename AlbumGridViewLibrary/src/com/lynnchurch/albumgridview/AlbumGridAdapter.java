package com.lynnchurch.albumgridview;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 
 * @author LynnChurch
 * 
 */
public class AlbumGridAdapter extends BaseAdapter {

	private ArrayList<String> mThumbnailUrls = new ArrayList<String>();
	private Context mContext;
	private DisplayImageOptions mOptions;
	private ViewHolder mHolder;

	public AlbumGridAdapter(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
		if (!ImageLoader.getInstance().isInited()) {
			ImageLoader.getInstance().init(
					ImageLoaderConfiguration.createDefault(mContext));
		}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mThumbnailUrls.size();
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return mThumbnailUrls.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (null == convertView) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.image,
					null);
			mHolder = new ViewHolder();
			mHolder.imageView = (ImageView) convertView
					.findViewById(R.id.image);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}

		String url = getItem(position);
		if (null == mOptions) {
			return convertView;
		}
		ImageLoader.getInstance()
				.displayImage(url, mHolder.imageView, mOptions);
		return convertView;
	}

	public void setDisplayImageOptions(DisplayImageOptions options) {
		this.mOptions = options;
	}

	/**
	 * 添加缩略图并显示
	 * 
	 * @param thumbnailUrls
	 */
	public void addThumbnails(ArrayList<String> thumbnailUrls) {
		mThumbnailUrls.addAll(thumbnailUrls);
		notifyDataSetChanged();
	}

	static class ViewHolder {
		ImageView imageView;
	}
}
