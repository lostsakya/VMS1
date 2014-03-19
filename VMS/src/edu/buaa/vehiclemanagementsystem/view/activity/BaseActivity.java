package edu.buaa.vehiclemanagementsystem.view.activity;

import edu.buaa.vehiclemanagementsystem.VehicleManagementSystem;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public abstract class BaseActivity extends Activity implements Listener<String>, ErrorListener {
	protected RequestQueue mRequestQueue;
	protected String TAG = getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mRequestQueue = ((VehicleManagementSystem) getApplication()).getRequestQueue();

	}
}
