package edu.buaa.vehiclemanagementsystem.view.activity;

import edu.buaa.vehiclemanagementsystem.R;
import edu.buaa.vehiclemanagementsystem.environment.Enviroment;
import edu.buaa.vehiclemanagementsystem.model.Parameter;
import edu.buaa.vehiclemanagementsystem.util.LogUtil;

import android.widget.ListView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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
		request = new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				LogUtil.log(TAG, response);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {

			}
		});

		mRequestQueue.add(request);

	}

}
