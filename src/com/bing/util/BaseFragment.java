package com.bing.util;


import android.content.Context;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
	
	Context mContext;

	public static final String getResId = null;

	public int getResId(String packageName) {
		return Utility.getResId(mContext, packageName);
	}
}
