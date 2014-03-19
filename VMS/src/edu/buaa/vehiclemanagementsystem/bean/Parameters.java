package edu.buaa.vehiclemanagementsystem.bean;

public class Parameters {
	private int func;
	private int type;
	private String data;

	public Parameters(int func, int type, String data) {
		this.func = func;
		this.type = type;
		this.data = data;
	}

	public int getFunc() {
		return func;
	}

	public void setFunc(int func) {
		this.func = func;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Parameters [func=" + func + ", type=" + type + ", data=" + data + "]";
	}
}
