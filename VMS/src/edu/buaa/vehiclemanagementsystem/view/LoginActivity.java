package edu.buaa.vehiclemanagementsystem.view;

import edu.buaa.vehiclemanagementsystem.R;
import edu.buaa.vehiclemanagementsystem.environment.Enviroment;
import edu.buaa.vehiclemanagementsystem.model.Parameter;
import edu.buaa.vehiclemanagementsystem.model.Result;
import edu.buaa.vehiclemanagementsystem.util.LogUtil;
import edu.buaa.vehiclemanagementsystem.util.ToastUtil;
import edu.buaa.vehiclemanagementsystem.view.activity.BaseActivity;
import edu.buaa.vehiclemanagementsystem.view.activity.TerminalListActivity_;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.alibaba.fastjson.JSON;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
	@ViewById(R.id.et_username)
	EditText etUsername;

	@ViewById(R.id.et_password)
	EditText etPassword;

	@ViewById(R.id.btn_login)
	Button btnLogin;

	@ViewById(R.id.btn_logout)
	Button btnLogout;

	/**
	 * 1、登录验证<br/>
	 * 申请参数：{"Func":8,"Type":1,"Data":"用户名 ! 密码"} <br/>
	 * 返回结果：{"ResultID":结果编号} <br/>
	 * 说明：<br/>
	 * 结果编号：0：成功，1：用户不存在，2：密码错误，3：没有权限<br/>
	 */
	@Click(R.id.btn_login)
	void login() {
		String username = etUsername.getText().toString();
		String password = etPassword.getText().toString();
		if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
			ToastUtil.shorToast(this, "用户名或密码不能为空");
			return;
		}
		String data = username + "!" + password;
		Parameter parameter = new Parameter(8, 1, data);
		String url = Enviroment.URL + JSON.toJSONString(parameter);
		StringRequest request = new StringRequest(url, new com.android.volley.Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				if (response != null) {
					Result result = JSON.parseObject(response, Result.class);
					switch (result.getResultId()) {
					case 0:
						ToastUtil.shorToast(getApplicationContext(), "登录成功");
						startActivity(new Intent(getApplicationContext(), TerminalListActivity_.class));
						break;
					case 1:
						ToastUtil.shorToast(getApplicationContext(), "用户不存在");
						break;
					case 2:
						ToastUtil.shorToast(getApplicationContext(), "密码错误");
						break;
					case 3:
						ToastUtil.shorToast(getApplicationContext(), "没有权限");
						break;
					default:
						break;
					}
				}
				LogUtil.log(TAG, response);
			}

		}, new com.android.volley.Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		});
		mRequestQueue.add(request);
	}
}
