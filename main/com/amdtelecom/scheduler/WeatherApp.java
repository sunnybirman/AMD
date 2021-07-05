package com.amdtelecom.scheduler;

import java.util.Timer;
import java.util.TimerTask;

public class WeatherApp extends TimerTask{

	private int count=0;
	private Timer timer;

	public WeatherApp(Timer t) {
		timer=t;
	}


	@Override 
	public void run() {

		if(count<Constants.SCHEDULER_COUNT)
		{
			System.out.println("Getting weather report for "+Constants.LOCATION);
			HttpHelper httpHelper = new HttpHelper();
			Double temeperature = httpHelper.getTemperature();
			String messageBody=null;
			System.out.println(Constants.LOCATION+ "current temeperature : "+ temeperature);
			if(null!=temeperature) {
				if(temeperature>20)
					messageBody = "{ \"body\": \""+Constants.MESSAGE_ABOVE_20+temeperature+"\",\"to\" : \"+306922222222\",\"from\": \"amdTelecom\"}";
				else
					messageBody = "{ \"body\": \""+Constants.MESSAGE_BELOW_20+temeperature+"\",\"to\" : \"+306922222222\",\"from\": \"amdTelecom\"}";
				System.out.println("Sending message to user mobile : +306922222222");
				httpHelper.sendSms(messageBody);
				count++;
			}else
				return;
		}
		else {
			timer.cancel();
			timer.purge();
		}
	}
}
