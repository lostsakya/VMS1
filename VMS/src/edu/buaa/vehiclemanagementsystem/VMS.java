package edu.buaa.vehiclemanagementsystem;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.androidannotations.annotations.EApplication;

@EApplication
public class VMS extends Application {

	private RequestQueue mRequestQueue;

	@Override
	public void onCreate() {
		super.onCreate();
		setRequestQueue(Volley.newRequestQueue(this));
	}

	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}

	public void setRequestQueue(RequestQueue mRequestQueue) {
		this.mRequestQueue = mRequestQueue;
	}
}
