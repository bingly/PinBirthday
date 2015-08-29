package com.bing.pinbirthday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import cn.jpush.android.api.JPushInterface;

public class SplashActivity extends Activity {
	
	public static boolean isForeground = false;
	private final int SPLASH_DISPLAY_LENGTH = 1*1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		JPushInterface.setDebugMode(true); 
		JPushInterface.init(this);
		
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {

				Intent intent = new Intent(SplashActivity.this,MainActivity.class);
				SplashActivity.this.startActivity(intent);
				SplashActivity.this.finish();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}
	
//	private void init(){
//		 JPushInterface.init(getApplicationContext());
//	}
	
	@Override
	protected void onResume() {
		JPushInterface.onResume(this);
		super.onResume();
	}


	@Override
	protected void onPause() {
		JPushInterface.onPause(this);
		super.onPause();
	}


//	@Override
//	protected void onDestroy() {
//		unregisterReceiver(mMessageReceiver);
//		super.onDestroy();
//	}

}
