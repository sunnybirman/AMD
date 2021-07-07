package com.amdtelecom.scheduler;

import java.util.Timer;

public class Starter {

	public static void main(String[] args) {
		 Timer t = new Timer();
		 WeatherApp mTask = new WeatherApp(t);
		 t.scheduleAtFixedRate(mTask, 0, 100000);
	}

}
