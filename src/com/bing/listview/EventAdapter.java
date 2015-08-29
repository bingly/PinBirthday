package com.bing.listview;

import com.bing.pinbirthday.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter {
	
	private LayoutInflater inflator;
	
	public EventAdapter(Context context) {

		this.inflator = LayoutInflater.from(context);
	}

	private EventData[] data =new EventData[]{
		new EventData("静谧小乡", "安静的山林，清澈的湖泊，蓝蓝的天空，仿佛置世外桃源。这里人迹罕至，一切都属于自己，尽情地享受。", R.drawable.egli_main),
		new EventData("结缘太行", 
				"北方的山，多是峭壁，像被斧砍刀劈过，棱角分明。春节过后人烟稀少，山上的小家小院却别有一番热闹的景象。",
				R.drawable.ethang_main),
		new EventData("徽杭古道", "前世不修，生在徽州。过去，徽杭古道是徽州人通往外界的道路，演绎了数不清的故事；今日，我们踏上这里，追随先人的脚步，感受自然的气息。", R.drawable.ejixi_main),
		new EventData("毕业旅行", "毕业，我们正青春！一群小伙伴来到深林，爬山、涉水，黑夜压马路、逗萤火，玩着游戏，尽情欢畅！", R.drawable.exlin_main),
		new EventData("朝圣之旅", "赶在八月的开头，暑气正盛，山里却格外凉快。吹着山风，赏着美景，沐浴在佛香下，好一次别样的旅行!", R.drawable.ejhua_main),
	};
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public EventData getItem(int position) {
		// TODO Auto-generated method stub
		return data[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null){
			convertView = inflator.inflate(R.layout.lv_event, null);
		}
		EventData data = getItem(position);
		ImageView icon = (ImageView) convertView.findViewById(R.id.lv_event_img);
		TextView name = (TextView) convertView.findViewById(R.id.lv_event_name);
		TextView dec = (TextView) convertView.findViewById(R.id.lv_event_dec);
		
		icon.setImageResource(data.iconID);
		name.setText(data.name);
		dec.setText(data.dec);
		
		return convertView;
	}

}
