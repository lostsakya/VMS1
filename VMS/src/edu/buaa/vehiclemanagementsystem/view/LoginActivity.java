package edu.buaa.vehiclemanagementsystem.view;

import edu.buaa.vehiclemanagementsystem.R;
import edu.buaa.vehiclemanagementsystem.bean.Parameters;
import edu.buaa.vehiclemanagementsystem.environment.Enviroment;
import edu.buaa.vehiclemanagementsystem.util.LogUtil;
import edu.buaa.vehiclemanagementsystem.util.ToastUtil;
import edu.buaa.vehiclemanagementsystem.view.activity.BaseActivity;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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

	@Click(R.id.btn_login)
	void login() {
		String username = etUsername.getText().toString();
		String password = etPassword.getText().toString();
		if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
			ToastUtil.shortToast(this, "用户名或密码不能为空");
			return;
		}
		String data = "PhoneTest!654321";
		Parameters parameters = new Parameters(8, 1, data);
		StringRequest request = new StringRequest(Enviroment.URL, this, this);
		mRequestQueue.add(request);
	}

	@Override
	public void onResponse(String response) {
		LogUtil.log(TAG, response);
	}

	@Override
	public void onErrorResponse(VolleyError error) {

	}
}
