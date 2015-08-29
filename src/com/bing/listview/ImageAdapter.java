package com.bing.listview;

import com.bing.pinbirthday.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	
	/**
	 * 触摸条目时
	 * 
	 * @author bingcai
	 *
	 */
//	public interface OnItemTouchListener
//	{
//		void onTouchEvent(View view, int position);
//	}
	
//	private OnItemTouchListener mOnTouchListener;
	
	private int[] mDatas;
	private LayoutInflater inflator;
	private Context mContext;
	
	public ImageAdapter(Context context, int[] mDatas) {

		this.mDatas = mDatas;
		this.inflator = LayoutInflater.from(context); 
		this.mContext = context;
	}
	
	private class ViewHolder{
		ImageView mImageView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mDatas[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = new ViewHolder();
		
		if (convertView == null) {
			convertView = inflator.inflate(R.layout.single_image, null);
			viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.iv_single_img);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.mImageView.setImageResource(mDatas[position]);
		
		viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(mContext, ImageControlActivity.class);
				intent.putExtra("position", mDatas[position]);
				mContext.startActivity(intent);
				
			}
		});
		
		return convertView;
	}
	

//	public void setOnItemTouchListenner(OnItemTouchListener mOnTouchListener)
//	{
//		this.mOnTouchListener = mOnTouchListener;
//	}

}
