package edu.buaa.vehiclemanagementsystem.view.activity;

import edu.buaa.vehiclemanagementsystem.R;
import edu.buaa.vehiclemanagementsystem.controller.net.DStringRequest;
import edu.buaa.vehiclemanagementsystem.controller.parser.Parser;
import edu.buaa.vehiclemanagementsystem.model.Parameter;
import edu.buaa.vehiclemanagementsystem.model.Result;
import edu.buaa.vehiclemanagementsystem.model.VehicleStateInfo;
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

import java.util.ArrayList;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import com.alibaba.fastjson.JSON;

@EActivity(R.layout.activity_list)
public class LocationListActivity extends BaseActivity {
	@ViewById(R.id.lv)
	ListView lv;

	@AfterViews
	void request() {
		String data = "";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(code);
		stringBuilder.append("!");
		stringBuilder.append(startTime);
		stringBuilder.append("!");
		stringBuilder.append(endTime);
		stringBuilder.append("!");
		stringBuilder.append(filterStopPoint);
		stringBuilder.append("!");
		stringBuilder.append(index);
		stringBuilder.append("!");
		stringBuilder.append(itemPerPage);

		Parameter parameter = new Parameter(8, 4, data);
		String url = Enviroment.URL + JSON.toJSONString(parameter);
		DStringRequest request = new DStringRequest(url, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				try {
					Result result = JSON.parseObject(response, Result.class);
					LogUtil.log(TAG, result.toString());
					switch (result.getResultId()) {
					case 1:
						ToastUtil.shortToast(getApplicationContext(), "下载全部车辆信息成功");
						LogUtil.log(TAG, "下载全部车辆信息成功");
						String data = result.getDataList();
						LogUtil.log(TAG, data);
						ArrayList<VehicleStateInfo> vehicles = Parser
								.parseStateInfo(data);
						LogUtil.log(TAG, vehicles.toString());
						lv.setAdapter(new VehiclesAdapter(vehicles));
						break;
					case 0:
						ToastUtil.shortToast(getApplicationContext(), "下载全部车辆信息失败");
						LogUtil.log(TAG, "下载全部车辆信息失败");
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
			VehicleStateInfo stateInfo = (VehicleStateInfo) list.get(position);
			Holder holder;
			if (convertView == null) {
				holder = new Holder();
				convertView = View.inflate(getApplication(), R.layout.item, null);
				holder.item = (TextView) convertView.findViewById(R.id.tv_item);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.item.setText(Html.fromHtml(stateInfo.toString()));
			return convertView;
		}
	}

	static class Holder {
		TextView item;
	}
}
