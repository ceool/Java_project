package com.hallym.SP_Project;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpModule {
	private OkHttpClient client = new OkHttpClient();

	String requestGet(String url) {
		Request request = new Request.Builder()
				.url(url)
				.build();
		String result = "";

		try {
			Response response = client.newCall(request).execute();
			result = response.body().string();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}