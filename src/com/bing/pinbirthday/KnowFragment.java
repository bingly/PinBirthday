package com.bing.pinbirthday;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bing.pinbirthday.MyHorizontalScrollView.CurrentImageChangeListener;
import com.bing.pinbirthday.MyHorizontalScrollView.OnItemClickListener;

public class KnowFragment extends Fragment {
	
	private Context mContext;
	
	private MyHorizontalScrollView mHorizontalScrollView;
	private HorizontalScrollViewAdapter mAdapter;
	private ImageView mImg;
	private List<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
			R.drawable.geli1, R.drawable.geli2, R.drawable.taih1, R.drawable.taih2,
			R.drawable.jixi1, R.drawable.jixi2, R.drawable.jixi3, R.drawable.duanwu1,
			R.drawable.duanwu2, R.drawable.duanwu3, R.drawable.jiuhua1, R.drawable.jiuhua2,
			R.drawable.jiuhua3));
	private List<String> mString = new ArrayList<String>(Arrays.asList(
			"小格里", "小格里", "太行山","太行山",
			"绩溪", "绩溪", "绩溪","夏霖",
			"学弟学妹", "端午节", "九华山","秀肌肉", "美人"
			));
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.main_tab1_fragment, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {

		mImg = (ImageView) view.findViewById(R.id.id_content);

		mHorizontalScrollView = (MyHorizontalScrollView) view.findViewById(R.id.id_horizontalScrollView);
		mAdapter = new HorizontalScrollViewAdapter(mContext, mDatas, mString);
		//添加滚动回调
		mHorizontalScrollView
				.setCurrentImageChangeListener(new CurrentImageChangeListener()
				{
					@Override
					public void onCurrentImgChanged(int position,
							View viewIndicator)
					{
						mImg.setImageResource(mDatas.get(position));
						viewIndicator.setBackgroundColor(Color
								.parseColor("#AA024DA4"));
					}
				});
		//添加点击回调
		mHorizontalScrollView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onClick(View view, int position)
			{
				mImg.setImageResource(mDatas.get(position));
				view.setBackgroundColor(Color.parseColor("#AA024DA4"));
			}
		});
		//设置适配器
		mHorizontalScrollView.initDatas(mAdapter);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mContext = this.getActivity();
	}

}
