package com.bing.pinbirthday;

import com.bing.listview.EventAdapter;
import com.bing.listview.EventContent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class WantKnowFragment extends Fragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.main_tab2_fragment, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {

		ListView lv_event = (ListView) view.findViewById(R.id.lv_event);
		
		lv_event.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(view.getContext(), EventContent.class);
				intent.putExtra("position", position);
//				Bundle bundle = new Bundle();
//				bundle.putLong("position", position);
				startActivity(intent);
			}
		});
		
		EventAdapter adapter = new EventAdapter(this.getActivity());
		lv_event.setAdapter(adapter);
	}

}
