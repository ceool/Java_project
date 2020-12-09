package com.hallym.SP_Project;

import org.json.JSONObject;

public class App {
	public static void main(String[] args) {
		RecordLog rl = new RecordLog(); //로그 객체
		String path = "Log";

		LED led = new LED(2); // LED 객체
		led.start();

		try {
			led.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//매수의 격차 1*priDif
		float priDif = (float) 0.003;
		int money = 100000000; // 초기자금 1억원
		float bitcoin = 0; // 초기 비트코인 0개

		long total = 0; // 비트코인 현재가의 총합
		int avg = 0; //최근 777번 불러온 평균 비트코인 가격
		int num = 0; //1800번 마다 토탈 계산
		int chNum = 0; // 변한 가격 횟수
		int buytotal = 0; // 매수했을 때 가격 총합
		float buyCnt = 0; // 매수했던 코인 개수
		float buyAvg = 0; // 매수평균

		int opening_price; //매수 가격
		int closing_price; //매도 가격

		int pre_open = 0; //이전 매수 가격
		int pre_close = 0; // 이전 매도 가격

		//매수 매도 tmp 값들
		float bittmp, buytmp;
		int low=0, max=0, tmp;

		String result;
		String url = String.format("https://api.bithumb.com/public/ticker/BTC_KRW");
		//JsonObject jsonResult;
		JSONObject jsonResult;

		HttpModule module = new HttpModule();

		while(true)
		{
			//url 불러오기
			result = module.requestGet(url);

			if(!result.isEmpty()) 
			{

				//jsonResult = new Gson().fromJson(result, JsonObject.class);
				jsonResult = new JSONObject(result);

				//서버 오류코드 확인: 0000이면 정상
				if (Integer.parseInt((String) jsonResult.get("status")) == 0000)
				{
					//opening_price = jsonResult.getAsJsonObject("data").get("opening_price").getAsInt();
					//closing_price = jsonResult.getAsJsonObject("data").get("closing_price").getAsInt();
					opening_price = Integer.parseInt((String) jsonResult.getJSONObject("data").get("opening_price"));
					closing_price = Integer.parseInt((String) jsonResult.getJSONObject("data").get("closing_price"));
					//System.out.println(opening_price + ", " + closing_price);

					
					if(num >= 777)
					{
						if(chNum != 0)
						{
							avg = (int) (Math.round((total/chNum) * 0.001) / 0.001); // 백의자리에서 반올림
							num = 0;
							chNum = 0;
							total = 0;
							low = 0;
							max = 0;
							System.out.println("777번 불러온 값의 평균: " + avg);
						}
					}
					num++;

					if(pre_open == opening_price && pre_close == closing_price)
					{
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						continue;
					}

					//이전 가격과 매수가와 다르면
					if(pre_open != opening_price)
					{
						pre_open = opening_price;
						total += pre_open;
						chNum++;
						
						rl.present(path, pre_open); //현재가
					}

					//이전 가격과 매도가와 다르면
					if(pre_close != closing_price)
					{
						pre_close = closing_price;
						total += pre_close;
						chNum++;
						
						rl.present(path, pre_close); //현재가
					}


					//매수
					if(avg != 0 && money >= 300000)
					{					
						if (avg * (1-priDif) >= pre_close || avg * (1-priDif) >= pre_open)
						{
							if(pre_close == low || pre_open == low)
							{
								if(pre_close >= pre_open)
									tmp = pre_open;
								else
									tmp = pre_close;

								if(tmp == low || avg * (1-priDif) < tmp)
								{
									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									continue;
								}
							}

							if(pre_close >= pre_open)
								low = pre_open;
							else
								low = pre_close;

							buytmp = (float) (money * 0.2);
							bittmp = (float) (Math.round((buytmp / low) * 1000) / 1000.0);

							//소지금과 비트코인 돈 정리
							tmp = (int) (low*bittmp);
							money -= tmp;
							bitcoin = (float) (Math.round((bitcoin + bittmp) * 1000) / 1000.0);

							//매수했던 가격 총합과 횟수
							buytotal += tmp;
							buyCnt = (float) (Math.round((buyCnt + bittmp) * 1000) / 1000.0);
							buyAvg = buytotal / buyCnt;

							System.out.println("--------------------- 매수 ---------------------");
							System.out.println("구매가격: " + low + ", 구입 비트코인 수: " + bittmp + ", 총 구매금액: " + tmp);
							System.out.println("소지금: " + money + ", 총 소유 비트코인 수: " + bitcoin);
							System.out.printf("구매 평균 가격: %.1f\n", buyAvg);

							rl.buyLog(path, money, bitcoin, low, bittmp, tmp, buyAvg);

							led = new LED(3);
							led.start();							
						}
					}

					//매도
					if(buyAvg != 0 && bitcoin >= 0.01)
					{						
						if (buyAvg * 1.006 <= pre_close || buyAvg * 1.006 <= pre_open)
						{
							if(pre_close == max || pre_open == max)
							{
								if(pre_close <= pre_open)
									tmp = pre_open;
								else
									tmp = pre_close;

								if(tmp == max || buyAvg * 1.006 > tmp)
								{
									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									continue;
								}
							}


							if(pre_close <= pre_open)
								max = pre_open;
							else
								max = pre_close;

							bittmp = (float) (Math.round((bitcoin *0.2) * 1000) / 1000.0);
							tmp = (int) (bittmp * max);

							//소지금과 비트코인 돈 정리
							money += tmp;
							bitcoin = (float) (Math.round((bitcoin - bittmp) * 1000) / 1000.0);
							System.out.println("--------------------- 매도 ---------------------");
							System.out.println("판매가격: " + max + ", 판매 비트코인 수: " + bittmp + ", 총 판매금액: " + tmp);
							System.out.println("소지금: " + money + ", 총 소유 비트코인 수: " + bitcoin);

							rl.sellLog(path, money, bitcoin, max, bittmp, tmp);

							led = new LED(1);
							led.start();

							if(bitcoin < 0.01)
							{
								buytotal = 0;
								buyCnt = 0;
							}
						}
					}
				}//if(status)
				//빗썸 API가 현재 오류로 작동되지 않을 경우
				else
					System.out.println("오류코드: " + jsonResult.get("status") + ", 서버 오류로 일시적으로 불러올 수 없습니다.");
			}//is.empty
			//주소로부터 정보를 얻지 못했다면
			else {
				System.out.println("정보를 얻는데 실패하였습니다.");

			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} // while
	}
}