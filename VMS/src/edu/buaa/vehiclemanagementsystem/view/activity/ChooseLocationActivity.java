package edu.buaa.vehiclemanagementsystem.view.activity;

import edu.buaa.vehiclemanagementsystem.R;
import edu.buaa.vehiclemanagementsystem.util.LogUtil;

import android.content.Intent;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Date;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_choose_location)
public class ChooseLocationActivity extends BaseActivity {

	@ViewById(R.id.dp_start_time)
	DatePicker dpStartTime;
	@ViewById(R.id.dp_end_time)
	DatePicker dpEndTime;
	@ViewById(R.id.et_code)
	EditText etCode;
	@ViewById(R.id.tb_filter_stop_point)
	ToggleButton tbFilterStopPoint;
	@ViewById(R.id.et_index)
	EditText etIndex;
	@ViewById(R.id.et_item_per_page)
	EditText etItemPerPage;

	protected String startTime;
	protected String endTime;

	@AfterViews
	void initDataPicker() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int monthOfYear = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		Date date = new Date();
		dpStartTime.setMaxDate(date.getTime());
		dpEndTime.setMaxDate(date.getTime());
		dpStartTime.init(year, monthOfYear, dayOfMonth, new OnDateChangedListener() {

			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				startTime = year + "-" + monthOfYear + "-" + dayOfMonth;
				LogUtil.log(TAG, "startTime " + startTime);
			}
		});

		dpEndTime.init(year, monthOfYear, dayOfMonth, new OnDateChangedListener() {

			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				endTime = year + "-" + monthOfYear + "-" + dayOfMonth;
				LogUtil.log(TAG, "endTime " + endTime);
			}
		});
	}

	@Click(R.id.btn_commit)
	void sendRequest() {
		String data = "";
		String code = etCode.getText().toString().trim();

		String filterStopPoint = tbFilterStopPoint.isChecked() ? "1" : "0";

		String index = etIndex.getText().toString().trim();

		String itemPerPage = etItemPerPage.getText().toString().trim();

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

		data = stringBuilder.toString();
		Intent intent = new Intent(this, LocationListActivity_.class);
		intent.putExtra("data", data);
		startActivity(intent);
	}
}
