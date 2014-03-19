package edu.buaa.vehiclemanagementsystem;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VehicleManagementSystem extends Application {

	private RequestQueue mRequestQueue;

	@Override
	public void onCreate() {
		super.onCreate();
		setRequestQueue(Volley.newRequestQueue(getApplicationContext()));
	}

	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}

	public void setRequestQueue(RequestQueue mRequestQueue) {
		this.mRequestQueue = mRequestQueue;
	}
}
