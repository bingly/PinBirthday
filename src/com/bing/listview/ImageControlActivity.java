package com.bing.listview;

import com.bing.pinbirthday.R;
import com.bing.util.ImageControl;
import com.bing.util.ImageControl.ICustomMethod;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class ImageControlActivity extends Activity {
	
	ImageControl imgControl;
	long lastUpTime = 0; // 单击离开时间  
	static final int DOUBLE_CLICK_TIME_SPACE = 400; // 双击时间间隔  
	private Activity mAty;
	private boolean IS_DOUBLE_CLICK = false;  //在规定时间没有点下第二次
	private final int DOUBLE_CLICK = 10;
	private final int SINGLE_CLICK = 11;
	private final int CLICK = 1;
	
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
			new Thread(new TimeDelayThread()).start();  
//			lastUpTime = imgControl.mouseUp(event);
			if (imgControl.mouseUp(event)) {   //保证双击时不会关闭
				IS_DOUBLE_CLICK = true;
			}else{
				IS_DOUBLE_CLICK = false;
			}

		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	
	@Override
	protected void onResume() {
		
		mAty = this;
//		if (System.currentTimeMillis() - lastUpTime > DOUBLE_CLICK_TIME_SPACE && lastUpTime !=0) {
//			finish();
//		}
		super.onResume();
	}
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (!IS_DOUBLE_CLICK) {
				mAty.finish();
			}
//			switch (msg.what) {
//			case DOUBLE_CLICK:
//				break;
//			case SINGLE_CLICK:
//				if (!IS_DOUBLE_CLICK) {
//					mAty.finish();
//				}
//				break;
//			}
		}
	};
	
	public class TimeDelayThread implements Runnable{

		@Override
		public void run() {

				try {
					Thread.sleep(ImageControlActivity.DOUBLE_CLICK_TIME_SPACE + 200);
					if (!IS_DOUBLE_CLICK) {
						mAty.finish();
					}
//					Message msg = new Message();
//					if (IS_DOUBLE_CLICK) {
//						msg.what = DOUBLE_CLICK;
//					}else{
//						msg.what = SINGLE_CLICK;
//					}
//					handler.sendMessage(msg);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

}
