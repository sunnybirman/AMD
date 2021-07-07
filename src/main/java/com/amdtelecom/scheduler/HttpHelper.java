package com.amdtelecom.scheduler;

import java.io.IOException;
import java.util.Base64;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class HttpHelper {

	private String accessToken;
	private OkHttpClient client = new OkHttpClient();


	/**
	 *  Returns a authentication Token
	 *  
	 *  @param appId unique application ID
	 *  @param secret application secret
	 *   
	 *  @return String authToken
	 *  
	 *  */
	public String getRouteeAuthenticationToken(String appId, String secret){

		String encodedString = getEncodedString(appId+":"+secret);
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials");
		Request request = new Request.Builder()
				.url(Constants.BASE_URL_ROUTEE_AUTH)
				.post(body)
				.addHeader("authorization", "Basic "+encodedString)
				.addHeader("content-type", "application/x-www-form-urlencoded")
				.build();

		try {
			Response response = client.newCall(request).execute();
			JSONObject jsonResponse = (JSONObject) new JSONParser().parse(response.body().string());
			accessToken = (String) jsonResponse.get("access_token");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}
		return accessToken;
	}

	/**
	 *  Sends a message to provided phone number
	 *  
	 *  @param messageBody JSON String having values message, to, from
	 * 
	 *  <p> ex: { 'body': 'A new game has been posted to the MindPuzzle. Check it out', 'to' : '+30697ΧΧΧΧΧΧΧ','from': 'amdTelecom'}</p>
	 *  
	 */
	public void sendSms(String messageBody){

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, messageBody);

		String authToken = getRouteeAuthenticationToken(Constants.ROUTEE_APP_ID,Constants.ROUTEE_APP_SECERET);

		if(null!=authToken) {
			Request request = new Request.Builder()
					.url(Constants.BASE_URL_ROUTEE_SMS)
					.post(body)
					.addHeader("authorization", "Bearer "+authToken)
					.addHeader("content-type", "application/json")
					.build();

			try {
				Response response = client.newCall(request).execute();

				System.out.println(response.body().string());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
			return;
	}

	/**
	 *  Calls WEATHER_API to get weather report and parses the response to get temperature
	 *  
	 *  
	 *   
	 *  @return Double Temperature in celcius
	 *  
	 *  */

	public Double getTemperature() {

		HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL_WEATHER).newBuilder();
		urlBuilder.addQueryParameter("q", Constants.LOCATION)
		.addQueryParameter("appid", Constants.WEATHER_APP_ID)
		.addQueryParameter("units", Constants.WEATHER_TEMEPERATURE_UNIT);

		String url = urlBuilder.build().toString();

		Request request = new Request.Builder()
				.url(url)
				.build();
		Call call = client.newCall(request);
		try {
			Response response = call.execute();
			JSONObject jsonResponse = (JSONObject) new JSONParser().parse(response.body().string());
			JSONObject main = (JSONObject) jsonResponse.get("main");
			return (Double) main.get("temp");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 *  Returns a Base.64 encoded String
	 *  
	 *  @param text input text
	 *  
	 *   
	 *  @return String Base.64 encoded String
	 *  
	 *  */
	private String getEncodedString(String text) {
		Base64.Encoder base64encoder = Base64.getEncoder();
		return base64encoder.encodeToString(text.getBytes());

	}

}
