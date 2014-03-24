package edu.buaa.vehiclemanagementsystem.view.activity;

import edu.buaa.vehiclemanagementsystem.R;
import edu.buaa.vehiclemanagementsystem.controller.StringCookieRequest;
import edu.buaa.vehiclemanagementsystem.controller.parser.Parser;
import edu.buaa.vehiclemanagementsystem.model.VehicleGroup;
import edu.buaa.vehiclemanagementsystem.model.Parameter;
import edu.buaa.vehiclemanagementsystem.model.Result;
import edu.buaa.vehiclemanagementsystem.model.Vehicle;
import edu.buaa.vehiclemanagementsystem.util.LogUtil;
import edu.buaa.vehiclemanagementsystem.util.ToastUtil;
import edu.buaa.vehiclemanagementsystem.util.environment.Enviroment;

import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import com.alibaba.fastjson.JSON;

@EActivity(R.layout.activity_list)
public class GroupListActivity extends BaseActivity {

	@ViewById(R.id.lv)
	ListView lv;
	private StringRequest request;

	@AfterViews
	void request() {
		String data = null;
		Parameter parameter = new Parameter(8, 3, data);
		String url = Enviroment.URL + JSON.toJSONString(parameter);
		request = new StringCookieRequest(url, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				try {
					Result result = JSON.parseObject(response, Result.class);
					LogUtil.log(TAG, result.toString());
					switch (result.getResultId()) {
					case 1:
						ToastUtil.shortToast(getApplicationContext(), "下载车辆分组信息成功");
						LogUtil.log(TAG, "下载车辆分组信息成功");
						String data = result.getDataList();
						LogUtil.log(TAG, data);
						ArrayList<VehicleGroup> groups = Parser.parseGroupInfo(data);
						LogUtil.log(TAG, groups.toString());
						lv.setAdapter(new VehiclesAdapter(groups));
						break;
					case 0:
						ToastUtil.shortToast(getApplicationContext(), "下载车辆分组信息失败");
						LogUtil.log(TAG, "下载车辆分组信息失败");
						break;
					case 2:
						ToastUtil.shortToast(getApplicationContext(), "未登录");
						LogUtil.log(TAG, "未登录");
						break;
					default:
						break;
					}

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

	@ItemClick(R.id.lv)
	void itemClick(int position) {
		LogUtil.log(TAG, String.valueOf(position));

	}

	class VehiclesAdapter extends BaseAdapter {

		private List list;

		public VehiclesAdapter(List list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Vehicle vehicle = (Vehicle) list.get(position);
			Holder holder;
			if (convertView == null) {
				holder = new Holder();
				convertView = View.inflate(getApplication(), R.layout.item, null);
				holder.license = (TextView) convertView.findViewById(R.id.tv_item);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.license.setText(Html.fromHtml(vehicle.toString()));
			return convertView;
		}
	}

	static class Holder {
		TextView license;
	}
}
