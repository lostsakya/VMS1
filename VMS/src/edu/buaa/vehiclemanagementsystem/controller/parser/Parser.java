package edu.buaa.vehiclemanagementsystem.controller.parser;

import edu.buaa.vehiclemanagementsystem.model.Vehicle;

import android.text.TextUtils;

import java.util.ArrayList;

public class Parser {
	// 车牌号,终端编号,在线状态（0离线，1在线）,速度,是否报警（0正常，1异常）,分组编号,车牌颜色,驾驶员
	public static ArrayList<Vehicle> parseVehicles(String data) {
		if (TextUtils.isEmpty(data)) {
			return null;
		}
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		String[] rawVehicles = data.split("|");
		for (int i = 0; i < rawVehicles.length; i++) {
			String rawVehicle = rawVehicles[i];
			String[] properties = rawVehicle.split(",");
			Vehicle vehicle = new Vehicle(properties[0], properties[1], Integer.parseInt(properties[2]), Float.parseFloat(properties[3]), Integer.parseInt(properties[4]),
					properties[5], properties[6], properties[7]);
			vehicles.add(vehicle);
		}
		return vehicles;

	}
}
