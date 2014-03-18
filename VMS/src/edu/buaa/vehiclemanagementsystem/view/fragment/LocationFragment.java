package edu.buaa.vehiclemanagementsystem.view.fragment;

import edu.buaa.vehiclemanagementsystem.R;
import edu.buaa.vehiclemanagementsystem.util.Constants;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.SeekBarProgressChange;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;

@EFragment(R.layout.fragment_location)
public class LocationFragment extends Fragment implements Listener<JSONObject>, ErrorListener {
	@FragmentById(R.id.fragment_map)
	SupportMapFragment fragment;

	@ViewById(R.id.rl_locus)
	RelativeLayout rlLocus;

	@ViewById(R.id.btn_pause)
	Button btnPause;

	@ViewById(R.id.btn_play)
	Button btnPlay;

	@ViewById(R.id.rb_location)
	Button btnLocation;

	@ViewById(R.id.rb_locus)
	Button btnLocus;

	@ViewById(R.id.seek_bar)
	SeekBar seekBar;

	private AMap map;
	// 存放所有坐标的数组
	private ArrayList<LatLng> latlngList = new ArrayList<LatLng>();

	private Timer timer;

	private TimerTask timerTask;

	private Handler handler;

	protected int count;

	private Marker marker;

	private ArrayList<LatLng> pathList;

	private RequestQueue requestQueue;

	private JsonRequest request;

	@AfterViews
	void init() {
		requestQueue = Volley.newRequestQueue(getActivity());

		map = fragment.getMap();
		map.setMapType(AMap.MAP_TYPE_NORMAL);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlngList.get(0), 4));
		latlngList.add(Constants.marker1);
		latlngList.add(Constants.marker2);
		latlngList.add(Constants.marker3);
		latlngList.add(Constants.marker4);
		latlngList.add(Constants.marker5);
		latlngList.add(Constants.marker6);
		latlngList.add(Constants.marker7);
		latlngList.add(Constants.marker8);
		latlngList.add(Constants.marker9);
		latlngList.add(Constants.marker10);

		seekBar.setMax(latlngList.size());

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				int progress = seekBar.getProgress();
				if (progress != seekBar.getMax()) {
					seekBar.setProgress(progress + 1);
				} else {
					timer.cancel();
				}
			}
		};
		timer = new Timer();
		timerTask = new TimerTask() {

			@Override
			public void run() {
				handler.sendEmptyMessage(0);
			}
		};
	}

	@CheckedChange(R.id.rb_location)
	void location(CompoundButton button, boolean isChecked) {
		if (isChecked) {
			rlLocus.setVisibility(View.GONE);
			map.clear();
		}
	}

	@CheckedChange(R.id.rb_locus)
	void locus(CompoundButton button, boolean isChecked) {
		if (isChecked) {
			rlLocus.setVisibility(View.VISIBLE);
			map.clear();
		}
	}

	@Click(R.id.btn_play)
	void play() {
		timer.schedule(timerTask, 0, 500);
	}

	@SeekBarProgressChange(R.id.seek_bar)
	void progressChange(SeekBar seekBar, int progress, boolean fromUser) {
		pathList = new ArrayList<LatLng>(latlngList.subList(0, progress));
	}

	private void drawLine(ArrayList<LatLng> list, int current) {
		map.clear();
		LatLng replayGeoPoint = latlngList.get(current - 1);
		if (marker != null) {
			marker.destroy();
		}
		// 添加汽车位置
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(replayGeoPoint).title("起点").snippet(" ").icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.car)))
				.anchor(0.5f, 0.5f);
		marker = map.addMarker(markerOptions);
		// 增加起点开始
		map.addMarker(new MarkerOptions().position(latlngList.get(0)).title("起点")
				.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.nav_route_result_start_point))));
		// 增加起点结束
		if (pathList.size() > 1) {
			PolylineOptions polylineOptions = (new PolylineOptions()).addAll(pathList).color(Color.rgb(9, 129, 240)).width(6.0f);
			map.addPolyline(polylineOptions);
		}
		if (pathList.size() == latlngList.size()) {
			map.addMarker(new MarkerOptions().position(latlngList.get(latlngList.size() - 1)).title("终点")
					.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.nav_route_result_end_point))));
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (map == null) {
			map = fragment.getMap();
		}
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResponse(JSONObject response) {
		// TODO Auto-generated method stub

	}
}
