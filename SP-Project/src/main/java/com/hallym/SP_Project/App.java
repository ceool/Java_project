package com.hallym.SP_Project;

import org.json.JSONObject;

public class App {
	public static void main(String[] args) {

		int money = 100000000; // 초기자금 1억
		float bitcoin = 0; // 초기 비트코인 0개

		long total = 0; // 비트코인 현재가의 총합
		int avg = 0; //최근 3분 동안의 평균 비트코인 가격
		int num = 0; //1800번 마다 토탈 계산
		int chNum = 0; // 변한 가격 횟수
		int buytotal = 0; // 매수했을 때 가격 총합
		float buyCnt = 0; // 매수했던 코인 개수
		float buyAvg = 0; // 매수평균

		int opening_price; //매수 가격
		int closing_price; //매도 가격
		int pre_open = 0; //이전 매수 가격
		int pre_close = 0; // 이전 매도 가격
		int status; // status

		String url = String.format("https://api.bithumb.com/public/ticker/BTC_KRW");

		while(true)
		{
			//url 불러오기
			String result = HttpModule.requestGet(url);

			if(!result.isEmpty()) 
			{

				JSONObject jsonResult = new JSONObject(result);
				//서버 오류코드 확인: status가 0000이면 정상
				status = Integer.parseInt((String) jsonResult.get("status"));
				if (status == 0000)
				{
					opening_price = Integer.parseInt((String) jsonResult.getJSONObject("data").get("opening_price"));
					closing_price = Integer.parseInt((String) jsonResult.getJSONObject("data").get("closing_price"));

					//System.out.println(opening_price + ", " + closing_price);

					//이전 가격과 매수가와 다르면
					if(pre_open != opening_price)
					{
						pre_open = opening_price;
						total += pre_open;
						chNum++;
					}

					//이전 가격과 매도가와 다르면
					if(pre_close != closing_price)
					{
						pre_close = closing_price;
						total += pre_close;
						chNum++;
					}

					//매수
					if(avg != 0 && money >= 300000)
					{
						if (avg * 0.999 >= pre_close || avg * 0.999 >= pre_open)
						{
							int low;
							float bittmp;

							if(pre_close >= pre_open)
								low = pre_open;
							else
								low = pre_close;

							float tmp = (float) (money * 0.2);
							bittmp = (float) (Math.round((tmp / low) * 1000) / 1000.0);

							//소지금과 비트코인 돈 정리
							tmp = (int) (low*bittmp);
							money -= tmp;
							bitcoin += bittmp;

							//매수했던 가격 총합과 횟수
							buytotal += tmp;
							buyCnt += bittmp;
							buyAvg = buytotal / buyCnt;
							
							System.out.println("--------------------- 매수 ---------------------");
							System.out.println("구매가격: " + low + ", 구입 비트코인 수: " + bittmp + ", 총 구매금액: " + tmp);
							System.out.println("소지금: " + money + ", 총 소유 비트코인 수: " + bitcoin);
							System.out.println("구매 평균 가격: " + buyAvg);
							try {
								Thread.sleep(5000);
								num += 50;
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

					//매도
					if(buyAvg != 0 && bitcoin >= 0.002)
					{
						if (buyAvg * 1.001 <= pre_close || buyAvg * 1.001 <= pre_open)
						{
							int max, tmp;

							if(pre_close <= pre_open)
								max = pre_open;
							else
								max = pre_close;

							float bittmp = (float) (Math.round((bitcoin *0.2) * 1000) / 1000.0);
							tmp = (int) (bittmp * max);

							//소지금과 비트코인 돈 정리
							money += tmp;
							bitcoin -= bittmp;
							System.out.println("--------------------- 매도 ---------------------");
							System.out.println("판매가격: " + max + ", 판매 비트코인 수: " + bittmp + ", 총 판매금액: " + tmp);
							System.out.println("소지금: " + money + ", 총 소유 비트코인 수: " + bitcoin);
							try {
								Thread.sleep(5000);
								num += 50;
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

					num++;
					if(num >= 900)
					{
						if(chNum != 0)
						{
							avg = (int) (Math.round((total/chNum) * 0.001) / 0.001); // 백의자리에서 반올림
							num = 0;
							chNum = 0;
							total = 0;
							System.out.println("3분동안의 평균: " + avg);
						}
					}
				}
				//빗썸 API가 현재 오류로 작동되지 않을 경우
				else
					System.out.println("오류코드: " + status + ", 서버 오류로 일시적으로 불러올 수 없습니다.");
			}
			//주소로부터 정보를 얻지 못했다면
			else {
				System.out.println("정보를 얻는데 실패하였습니다.");

			}

			try {
				Thread.sleep(110);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private static void sleep(int i) {
		// TODO Auto-generated method stub

	}
}