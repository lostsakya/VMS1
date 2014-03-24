package edu.buaa.vehiclemanagementsystem.view.activity;

import edu.buaa.vehiclemanagementsystem.R;

import android.content.Intent;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ToggleButton;

import java.util.Calendar;

import org.androidannotations.annotations.CheckedChange;
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
	@ViewById(R.id.np_index)
	NumberPicker npIndex;
	@ViewById(R.id.np_item_per_page)
	NumberPicker npItemPerPage;

	protected String startTime;
	protected String endTime;

	@CheckedChange(R.id.tb_filter_stop_point)
	void onCheckeChane(CompoundButton button, boolean isChecked) {
		if (isChecked) {
			button.setText("过滤");
		} else {
			button.setText("不过滤");
		}
	}

	@Click(R.id.btn_commit)
	void sendRequest() {
		String data = "";

		String code = etCode.getText().toString().trim();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int monthOfYear = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		dpStartTime.init(year, monthOfYear, dayOfMonth - 1, new OnDateChangedListener() {

			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				startTime = year + "-" + monthOfYear + "-" + dayOfMonth;
			}

		});
		dpEndTime.init(year, monthOfYear, dayOfMonth, new OnDateChangedListener() {

			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				endTime = year + "-" + monthOfYear + "-" + dayOfMonth;
			}

		});

		String filterStopPoint = tbFilterStopPoint.isChecked() ? "1" : "0";
		String index = npIndex.getValue() + "";
		String itemPerPage = npItemPerPage.getValue() + "";
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
