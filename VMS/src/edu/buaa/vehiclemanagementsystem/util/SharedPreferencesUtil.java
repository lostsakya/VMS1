package edu.buaa.vehiclemanagementsystem.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.amap.api.maps.model.LatLng;

public class SharedPreferencesUtil {

	private static SharedPreferences getSharedPreferences(Context context) {
		return context.getSharedPreferences("VehicleManagementSystem",
				Context.MODE_PRIVATE);
	}

	public static void clear(Context context) {
		SharedPreferences sharedPreferences = getSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		editor.clear();
		editor.commit();
	}

	public static void savePosition(Context context, LatLng position) {
		if (position == null) {
			throw new IllegalArgumentException("position cannot be null");
		}
		SharedPreferences sharedPreferences = getSharedPreferences(context);
		Editor editor = sharedPreferences.edit();
		double latitude = position.latitude;
		double longitude = position.longitude;
		editor.putFloat("latitude", (float) latitude);
		editor.putFloat("longitude", (float) longitude);
		editor.commit();

	}

	public static LatLng getPosition(Context context) {
		SharedPreferences sharedPreferences = getSharedPreferences(context);
		double latitude = sharedPreferences.getFloat("latitude", -1f);
		double longitude = sharedPreferences.getFloat("longitude", -1f);
		if (latitude == -1f || longitude == -1f) {
			return null;
		}
		return new LatLng(latitude, longitude);
	}
}
