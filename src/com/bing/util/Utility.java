package com.bing.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

public class Utility {
	
	static Class<?> r = null;
	
	static Map<String, Class<?>> cache = new HashMap<String, Class<?>>();
	
	static Map<String, Integer> mIdCache = new HashMap<String, Integer>();

    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }
    
    public static int getResId(Context context, String packageName){
    	String[] items = packageName.split("\\.");
    	// 0:"R" 1:"array" "2":name
    	
    	if(mIdCache.containsKey(packageName)){
    		Log.i("Utility", packageName);
    		return mIdCache.get(packageName);
    	}
    	
    	int id = Utility.getIdByName(context, items[1], items[2]);
    	mIdCache.put(packageName, id);
    	
    	return id;
    }

	private static int getIdByName(Context context, String className, String name) {

		String packageName = context.getPackageName();
		
		int id = 0;
		
		try {
			if(r == null)
				r = Class.forName(packageName + ".R");
			Class<?>[] classes = r.getClasses();
			Class<?> desireClass = null;
			
			if(cache.containsKey(className))
				desireClass = cache.get(className);
			else{
				for (int i = 0; i < classes.length; i++) {
					if (classes[i].getName().split("\\$")[1].equals(className)) {
						desireClass = classes[i];
						break;
					}
				}
				cache.put(className, desireClass);
			}
			
			if (desireClass != null) {
				id = desireClass.getField(name).getInt(desireClass);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
    
}
