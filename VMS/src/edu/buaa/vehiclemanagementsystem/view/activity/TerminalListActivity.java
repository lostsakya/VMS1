package edu.buaa.vehiclemanagementsystem.view.activity;

import edu.buaa.vehiclemanagementsystem.R;
import edu.buaa.vehiclemanagementsystem.controller.StringCookieRequest;
import edu.buaa.vehiclemanagementsystem.controller.parser.Parser;
import edu.buaa.vehiclemanagementsystem.model.Parameter;
import edu.buaa.vehiclemanagementsystem.model.Result;
import edu.buaa.vehiclemanagementsystem.model.Vehicle;
import edu.buaa.vehiclemanagementsystem.util.environment.Enviroment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.alibaba.fastjson.JSON;

@EActivity(R.layout.activity_terminal_list)
public class TerminalListActivity extends BaseActivity {

	@ViewById(R.id.lv)
	ListView lv;
	private StringRequest request;

	@AfterViews
	void request() {
		String data = null;
		Parameter parameter = new Parameter(8, 2, data);
		String url = Enviroment.URL + JSON.toJSONString(parameter);
		request = new StringCookieRequest(url, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				try {
					Result result = JSON.parseObject(response, Result.class);
					String data = result.getDataList();
					ArrayList<Vehicle> vehicles = Parser.parseVehicles(data);
					lv.setAdapter(new VehiclesAdapter(vehicles));
				} catch (Exception e) {
				}
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		});
		mRequestQueue.add(request);
	}

	class VehiclesAdapter extends BaseAdapter {

		private List list;

		public VehiclesAdapter(List list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
