package edu.buaa.vehiclemanagementsystem.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	public static void shortToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}
