package com.bing.listview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bing.pinbirthday.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EventContent extends Activity {
	
	private int id;
	private Bitmap bitmap; 
	private float scaleWidth;  
	private float scaleHeight;  
	
	private TextView event_title;
	private TextView event_content;
	private GridView gd_view;
	private ArrayList<Integer> mDatas = new ArrayList<Integer>(Arrays.asList(
			R.drawable.ejhua1, R.drawable.ejhua2, R.drawable.ejhua3, R.drawable.ejhua4
			));
	private int[][] array = {
			{R.drawable.egli1, R.drawable.egli2},
			{R.drawable.ethang1, R.drawable.ethang2, R.drawable.ethang3, R.drawable.ethang4},
			{R.drawable.ejixi1, R.drawable.ejixi2, R.drawable.ejixi3, R.drawable.ejixi4},
			{R.drawable.exlin1, R.drawable.exlin2, R.drawable.exlin3, R.drawable.exlin4},
			{R.drawable.ejhua1, R.drawable.ejhua2, R.drawable.ejhua3, R.drawable.ejhua4},
	};
	
 	private List<Map<String,Integer>> getData(Integer[] array) {
		List<Map<String,Integer>> list  = new ArrayList<Map<String,Integer>>();
		
		Map<String, Integer> map;
		for (int i = 0; i < array.length; i++) {
			map = new HashMap<String, Integer>();
			map.put("img", array[i]);
			list.add(map);
		}
		
		return list;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_content);
		initView();
	}

	private void initView() {
		
		gd_view = (GridView) findViewById(R.id.adapter_img);
		event_title = (TextView) findViewById(R.id.event_title);
		event_content = (TextView) findViewById(R.id.event_content);

		id = getIntent().getExtras().getInt("position");   //第几个item

		String[] content = {"喜欢走进深山，山不必有名，亦不必繁盛，原生态最好。喜欢在山林的静谧中：静赏一朵花儿开；静听几声鸟欢叫；静观几块奇石峭；静静聆听自己的心儿在胸膛里欢快的跳。",
				"芜湖到太行山，一天一夜的车程让人觉得赏景不易。此时正是深冬，漫山遍野都是枯黄的树木，它们装点石壁，构成的恰是北方的妆容，我们觉得好新鲜。山上甚至有冰瀑，宛如碧玉翡翠一般晶莹，又像冻结了的云朵，悬在半山间。",
				"仲夏的午后，喜欢躲在居室里，避开热辣辣的日头，静享一份夏日的清凉。一盏茶香，一卷书籍，它不言，我不语。在一份完全属于自己的静谧里，聆听着心底的梵音。隔着千年的时光，行走在古人的原乡。江南的青石板，铺满了紫丁香的芬芳，静谧中，我听见花开的声音。",
				"旅行的欢乐不在于风景，而是身边的伙伴。那年的夏天，我们逛奔、追逐，挥洒着青春的汗水，赢得了梦想的荣誉。再次的旅行，选在毕业季，选在夏天，携手出发，让激情迸发。",
				"每逢节假日，总爱避开尘世的喧嚣，去山林里追寻一份静谧的美好。在青山绿水里，拾级而上，走进古老的寺院。诸神静默，屈膝佛前，聆听禅音袅袅。不必静坐青灯古刹，在心底里亦可打坐参禅。"
				};
		String[] title = {"小格里", "太行山" , "徽杭古道", "夏霖&芜湖", "九华山"};
		
		event_title.setText(title[id]);
		event_content.setText(content[id]);
		
//		gd_view.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long innerid) {
//
//				Display display = getWindowManager().getDefaultDisplay();
//				ImageView imageView = (ImageView) findViewById(R.id.iv_full_screen); 
//				bitmap = BitmapFactory.decodeResource(getResources(), array[id][position]);
//				int width = bitmap.getWidth();
//				int heigth = bitmap.getHeight();
//				int dwidth = display.getWidth();
//				int dheight = display.getHeight();
//				scaleWidth = (float)dwidth / width;
//				scaleHeight = (float)dheight / heigth;
//				
//				Matrix matrix = new Matrix();
//				matrix.setScale(scaleWidth, scaleHeight);
//				Bitmap newbitmap = Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//				imageView.setImageBitmap(newbitmap);
//			}
//		});
//		gd_view.setOnTouchListener(new View.OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View view, MotionEvent event) {
//
//				int str = view.getId();
//				Toast.makeText(getApplicationContext(), "touched: " + str, Toast.LENGTH_SHORT).show();
//				return false;
//			}
//		});
		
//		使用ArrarAdapter
//		ArrayAdapter requires the resource ID to be a TextView
//		ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, R.layout.img_single);
//		for (int i = 0; i < mDatas.size(); i++) {
//			adapter.add(mDatas.get(i));
//		}
		
//		NullPointerException  at com.bing.listview.ImageAdapter.getView(ImageAdapter.java:50)
		ImageAdapter adapter = new ImageAdapter(EventContent.this, array[id]);
		
//		不能通过setOnTouchListener找到adapter中对应的图标
//		SimpleAdapter adapter = new SimpleAdapter(this, getData(array[id]), R.layout.img_single, new String[] {"img"}, new int[] {R.id.iv_single});
		gd_view.setAdapter(adapter);
	}
	
	public void manipulateImg(){
		
		Display display = getWindowManager().getDefaultDisplay();
		
	}
}
