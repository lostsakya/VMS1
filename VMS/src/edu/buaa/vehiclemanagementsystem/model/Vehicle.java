package edu.buaa.vehiclemanagementsystem.model;

public class Vehicle {
	// 车牌号,终端编号,在线状态（0离线，1在线）,速度,是否报警（0正常，1异常）,分组编号,车牌颜色,驾驶员
	private String license;
	private String code;
	private int state;
	private float speed;
	private int alarm;
	private String groupCode;
	private String color;
	private String driver;

	public Vehicle(String license, String code, int state, float speed, int alarm, String groupCode, String color) {
		this.license = license;
		this.code = code;
		this.state = state;
		this.speed = speed;
		this.alarm = alarm;
		this.groupCode = groupCode;
		this.color = color;
		this.driver = driver;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getAlarm() {
		return alarm;
	}

	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	// @Override
	// public String toString() {
	// return "Vehicle [license=" + license + ", code=" + code + ", state=" +
	// state + ", speed=" + speed + ", alarm=" + alarm + ", groupCode=" +
	// groupCode + ", color=" + color
	// + ", driver=" + driver + "]";
	// }
	// 车牌号,终端编号,在线状态（0离线，1在线）,速度,是否报警（0正常，1异常）,分组编号,车牌颜色,驾驶员
	@Override
	public String toString() {
		return "车辆信息：<br/><br/>车牌号=" + license + ", 终端编号=" + code + ", 在线状态=" + (state == 1 ? "在线" : "离线") + ", 速度=" + speed + ", 是否报警=" + (alarm == 0 ? "正常" : "异常") + ", 分组编号="
				+ groupCode + ", 车牌颜色=" + color + ", 驾驶员=" + driver;
	}
}
