package com.hallym.SP_Project;

import org.json.JSONObject;

public class App {
	public static void main(String[] args) {
		long total = 0; // 비트코인 현재가의 총합
		int num = 0; //불러온 횟수
		int opening_price; //매수 가격
		int closing_price; //매도 가격
		int current_price = 0;
		
		String url = String.format("https://api.bithumb.com/public/ticker/BTC_KRW");

		while(true)
		{
			//url 불러오기
			String result = HttpModule.requestGet(url);

			if(!result.isEmpty()) {

				JSONObject jsonResult = new JSONObject(result);
				opening_price = Integer.parseInt((String) jsonResult.getJSONObject("data").get("opening_price"));
				closing_price = Integer.parseInt((String) jsonResult.getJSONObject("data").get("closing_price"));

				System.out.println(opening_price + ", " + closing_price);
			
				
				
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//주소로부터 정보를 얻지 못했다면
			else {
				System.out.println("정보를 얻는데 실패하였습니다.");

			}
		}

	}
}