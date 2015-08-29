package com.bing.pinbirthday;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.Random;

import com.bing.util.Utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MeFragment extends Fragment {
	
	public static boolean isForeground = false;
	TextView msgText;
	ImageView img;
	int num; //随机取一张图片作背景
	
	String[] img_name = {
			"R.drawable.bg1","R.drawable.bg2","R.drawable.bg3","R.drawable.bg4", "R.drawable.bg5",
			"R.drawable.bg6","R.drawable.bg7","R.drawable.bg8","R.drawable.bg9", "R.drawable.bg10",
			"R.drawable.bg11","R.drawable.bg12","R.drawable.bg13"
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("MeFragment", "onCreate -- isForeground: " + isForeground);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.main_tab3_fragment, container, false);
		initView(view);
		registerMessageReceiver();
		Log.i("MeFragment", "onCreateView -- isForeground: " + isForeground);
		return view;
	}
	
	private void initView(View view) {
		msgText = (TextView) view.findViewById(R.id.msg_rec);
		img = (ImageView) view.findViewById(R.id.img_bg);
		
		Random random = new Random();
		num = random.nextInt(12);
		img.setImageResource(R.drawable.bg_main);
		img.setImageResource(Utility.getResId(getActivity(), img_name[num]));
		
	}

	@Override
	public void onResume() {
		isForeground = true;
		super.onResume();
		Log.i("MeFragment", "onResume -- isForeground: " + isForeground);
	}


	@Override
	public void onPause() {
		isForeground = false;
		super.onPause();
		Log.i("MeFragment", "onPause -- isForeground: " + isForeground);
	}


	@Override
	public void onDestroy() {
		this.getActivity().unregisterReceiver(mMessageReceiver);
		super.onDestroy();
		Log.i("MeFragment", "onDestroy -- isForeground: " + isForeground);
	}
	
	//for receive customer msg from jpush server
	private MessageReceiver mMessageReceiver;
	public static final String MESSAGE_RECEIVED_ACTION = "com.bing.pinbirthday.MESSAGE_RECEIVED_ACTION";
	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_EXTRAS = "extras";
	
	public void registerMessageReceiver(){
		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		this.getActivity().registerReceiver(mMessageReceiver, filter);
	}
	
	public class MessageReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {

			if(MESSAGE_RECEIVED_ACTION.equals(intent.getAction())){
				String message = intent.getStringExtra(KEY_MESSAGE);
				String extras = intent.getStringExtra(KEY_EXTRAS);
				StringBuilder showMsg = new StringBuilder();
//				showMsg.append(KEY_MESSAGE + " : " + message + "\n");
//				if(!Utility.isEmpty(extras))
//					showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
//				setCostomMsg(showMsg.toString());
				setCostomMsg(message);
			}
		}
		
	}
	
	private void setCostomMsg(String msg){
		 if (null != msgText) {
			 msgText.setText(msg);
			 msgText.setVisibility(android.view.View.VISIBLE);
        }
	}

}
