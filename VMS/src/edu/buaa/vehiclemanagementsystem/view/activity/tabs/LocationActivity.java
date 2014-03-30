package edu.buaa.vehiclemanagementsystem.view.activity.tabs;

import org.androidannotations.annotations.EActivity;

import android.content.Intent;
import android.os.Bundle;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;

import edu.buaa.vehiclemanagementsystem.R;
import edu.buaa.vehiclemanagementsystem.model.Vehicle;
import edu.buaa.vehiclemanagementsystem.util.Constants;
import edu.buaa.vehiclemanagementsystem.view.activity.base.BaseActivity;

@EActivity
public class LocationActivity extends BaseActivity {
	private MapView mapView;
	private AMap map;
	private Vehicle vehicle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		map = mapView.getMap();

		Intent intent = getIntent();
		vehicle = (Vehicle) intent.getSerializableExtra(Constants.VEHICLE);
	}
}
