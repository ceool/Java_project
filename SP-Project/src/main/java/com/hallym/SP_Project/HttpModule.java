package com.hallym.SP_Project;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpModule {
	private OkHttpClient client = new OkHttpClient();
	private Request request;
	private String result = "";
	
	String requestGet(String url) {
		request = new Request.Builder()
				.url(url)
				.build();

		try {
			Response response = client.newCall(request).execute();
			ResponseBody reponseBody = response.body();
            assert reponseBody != null;
            
			result = reponseBody.string();
			response.close();
			reponseBody.close();
			request = null;
		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}