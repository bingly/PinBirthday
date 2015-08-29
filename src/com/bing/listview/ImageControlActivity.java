package com.bing.listview;

import com.bing.pinbirthday.R;
import com.bing.util.ImageControl;
import com.bing.util.ImageControl.ICustomMethod;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ImageControlActivity extends Activity {
	
	ImageControl imgControl;
	long lastUpTime = 0; // 单击离开时间  
	static final int DOUBLE_CLICK_TIME_SPACE = 400; // 双击时间间隔  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_contorl);
		
		imgControl =  (ImageControl) findViewById(R.id.image_controll);
		int id = getIntent().getIntExtra("position", R.drawable.ejhua_main);
		imgControl.setImageResource(id);
		
//		imgControl.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//
//				finish();
//			}
//		});
		
		init();
	}

	private void init() {

		Bitmap bmp;
		if (imgControl.getDrawingCache() != null) {
			bmp = Bitmap.createBitmap(imgControl.getDrawingCache());
		}else{
			bmp = ((BitmapDrawable) imgControl.getDrawable()).getBitmap();
		}
		
		Rect frame = new Rect();
		getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		int screenW = this.getWindowManager().getDefaultDisplay().getWidth();
		int screenH = this.getWindowManager().getDefaultDisplay().getHeight() - statusBarHeight;
		if (bmp != null) {
			imgControl.imageInit(bmp, screenW, screenH, statusBarHeight,
					new ICustomMethod() {
				
				@Override
				public void customMethod(Boolean currentStatus) {
					// TODO Auto-generated method stub
					
				}
			});
		}else{
			Toast.makeText(this, "图片加载失败，请稍候再试！", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			imgControl.mouseDown(event);
			break;
			
		case MotionEvent.ACTION_POINTER_DOWN:
			imgControl.mousePointDown(event);
			break;
			
		case MotionEvent.ACTION_MOVE:
			imgControl.mouseMove(event);
			break;
			
		case MotionEvent.ACTION_UP:
			lastUpTime = imgControl.mouseUp(event);

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	
	@Override
	protected void onResume() {

		if (System.currentTimeMillis() - lastUpTime > DOUBLE_CLICK_TIME_SPACE && lastUpTime !=0) {
			finish();
		}
		super.onResume();
	}

}
