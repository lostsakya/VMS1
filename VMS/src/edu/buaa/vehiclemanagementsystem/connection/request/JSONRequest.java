package edu.buaa.vehiclemanagementsystem.connection.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import java.io.UnsupportedEncodingException;

public class JSONRequest<T> extends Request<T> {

	private Listener<T> listener;

	public JSONRequest(int method, String url, T object, Listener<T> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		this.listener = listener;
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		String string;
		try {
			string = new String(response.data, getParamsEncoding());
		} catch (UnsupportedEncodingException e) {
			string = new String(response.data);
		}
		return null;
	}

	@Override
	protected void deliverResponse(T response) {

	}
}
