package com.voice.activity;


import com.voice.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import com.voice.MainActivity;
import com.voice.test.SDManager;
import com.voice.util.SpUtil;

public class WelcomeActivity extends Activity {
	protected static final String TAG = "WelcomeActivity";
	private Context mContext;
	private ImageView mImageView;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		mContext = this;
		findView();
		init();
          
    }
    


	private void findView() {
		mImageView = (ImageView) findViewById(R.id.iv_welcome);
	}

	@SuppressWarnings("static-access")
	private void init() {
		mImageView.postDelayed(new Runnable() {
			@Override
			public void run() {
				sp = SpUtil.getInstance().getSharePerference(mContext);
				boolean isFirst = SpUtil.getInstance().isFirst(sp);
				if (!isFirst) {
					SDManager manager = new SDManager(mContext);
					manager.moveUserIcon();
					manager.movePronunciation();
					SpUtil.getInstance().setBooleanSharedPerference(sp,
							"isFirst", true);
					Intent intent = new Intent(mContext, LoginActivity.class);
					startActivity(intent);
					finish();
				} else {
					Intent intent = new Intent(mContext, LoginActivity.class);
					startActivity(intent);
					finish();
				}
			}
		},3000);
		
	}
}
