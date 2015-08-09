package com.lynnchurch.albumgridviewsample;

import java.util.ArrayList;

import com.lynnchurch.albumgridview.AlbumGridView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	private AlbumGridView agv_album;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DisplayImageOptions options = new DisplayImageOptions.Builder().build();
		ArrayList<String> imageUrls = new ArrayList<String>();
		imageUrls
				.add("http://img.hb.aicdn.com/93fce6871c233ca167d03bd0b3f70b664a5b97ee12411-DneVuu_fw236");
		imageUrls
				.add("http://img.hb.aicdn.com/4824ac3c5f088335fa1146b963d5612835dca65e3ae6c-F9pOIt_fw236");
		imageUrls
				.add("http://img.hb.aicdn.com/befa162700c0969e3bfb85634f21f85914c3d4b21556c-7glclL_fw236");
		imageUrls
				.add("http://img.hb.aicdn.com/b8c0a42500aec4dd08e417b4cb6f2aa688049b9924b56-flhaSG_fw236");
		imageUrls
				.add("http://img.hb.aicdn.com/84f1d709e58021ff7d1ac588851f89a3a62f1aa11387a-dT69hl_fw236");
		imageUrls
				.add("http://img.hb.aicdn.com/2f01718ce597e3e815caea56335a77ca97b59d69363b4-iAYDo7_fw658");
		imageUrls
				.add("http://img.hb.aicdn.com/12ff9bf0a76b5821dea69435d21a9ca58ea2bb2f418e1-41MbAO_fw658");
		imageUrls
				.add("http://img.hb.aicdn.com/36beb4446b6bfbe1c939899f2ccc7c82ef315348dd7e2-zE2FCT_fw658");

		agv_album = (AlbumGridView) findViewById(R.id.agv_album);
		agv_album.setDisplayImageOptions(options);
		agv_album.addLargeImages(imageUrls);
		agv_album.addThumbnails(imageUrls);

	}
}
