import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
	public static void main(String[] args) {

		/**
		 * 프로그램 설명
		 * 공적 마스크 판매 정보 메뉴 [1.json전체출력, 2.보기 쉬운 전체출력, 3.세부정보(약국주소만출력, 약국검색), 4.약국 총 개수(종료:x), 5.약국정보최신화]
		 * 1.json전체출력 : 공공기관 API에서 받아온 json 코드 전체를 출력한다
		 * 2.보기 쉬운 전체출력 : 공공기관 API에서 json 코드를 보기 쉽도록 변형하여 출력한다.
		 * 3.세부정보 : 좀 더 세부적인 기능을 사용할 수 있다.
		 *  - 3-1.약국주소만 출력: 마스크를 판매하는 약국주소만 볼 수 있게 출력되게 한다.
		 *  - 3-2.약국이름과 주소 검색: 마스크를 판매하는 약국의 이름이나 주소 등을 검색하여 해당되는 목록을 출력한다. (이름과 주소만 출력된다.)
		 *    - 3-2-a. 검색결과 마지막에는 검색된 약국의 총 개수를 알려준다.
		 * 4.약국 총 개수: 공공기관 API에서 받아온 약국의 총 개수를 출력한다.
		 * 5.약국정보최신화: 공공기관 API로부터 json을 새로 받아서 result 값을 최신화해준다.
		 *  - 주소로부터 코드를 받지 못하면 프로그램을 종료한다.
		 * 
		 * x를 입력하게 되면 프로그램이 종료된다.
		 * 또한, 이외의 값을 입력할 경우 다시 입력할 수 있도록 유도하고 예외의 경우도 처리해두었다.
		 */

		// 받아울주소
		// 여기서는 코로나 공적 마스크 판매처에 대한 API
		// 해당 API 는 https://www.data.go.kr/dataset/15043025/openapi.do〉에서 사용방법 확인 가능.
		// 해당 API 는 GET 방식으로 요청이 가능하며, 리턴 값이 json 으로 넘어온다.
		//String url = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/stores/json?page=1";


		String url = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/stores/json?page=2";
		// 만든 모듈 함수를 이용하여 웹사이트에 접속하여 결과 값은 받는다.
		String result = HttpModule.requestGet(url);

		// 결과 값이 비어있지 않으면 정상적으로 받은 것.
		if(!result.isEmpty()) {

			BufferedReader in = null;

			try {
				// 키보드로부터 받기 위해 System.in 을 넣고 BufferedReader 를 초기화 시킴.
				in = new BufferedReader(new InputStreamReader(System.in));

				while(true) //종료하기 전까지 반복
				{
					System.out.print("공적 마스크 판매 정보 메뉴 [1.json전체출력, 2.보기 쉬운 전체출력, 3.세부정보, 4.약국 총 개수(종료:x), 5.약국정보최신화] : ");

					String tempMenu = in.readLine();

					// 프로그램 종료
					if(tempMenu.equals("x"))
					{
						System.out.println("프로그램을 종료합니다.");
						in.close(); //프로그램이 종료되기 때문에 여기서 BufferedReader 를 close 하여 종료해준다.
						return;
					}

					int menu = 0;

					try 
					{
						menu = Integer.parseInt(tempMenu);
					} 

					catch(NumberFormatException e) 
					{
						// 숫자 이외의 값이 나왔을 경우, 다시 입력하라고 함.
						System.out.println("종료를 제외한 명령어는 숫자로만 입력해주세요. ");
						continue;
					}

					// 메뉴 선택지에서 '1.json전체출력'을 고르면 실행
					if(menu == 1) {
						// 해당 데이터를전체출력.
						System.out.println(result);
					}

					// 메뉴 선택지에서 '2.보기 쉬운 전체출력'을 고르면 실행
					else if(menu == 2) {
						JSONObject jsonResult = new JSONObject(result);
						JSONArray storeInfosArray = (JSONArray)jsonResult.get("storeInfos");

						for(int i=0; i<storeInfosArray.length(); i++)
						{
							System.out.println("--------- 공적 마스크 판매 "+(i+1)+"번째 약국 ---------");

							//배열 안에 있는것도 JSON형식이기 때문에 JSONObject로 추출
							JSONObject storeInfosObject = (JSONObject) storeInfosArray.get(i);

							//JSON name으로 추출
							System.out.println("주소 : "+storeInfosObject.get("addr"));
							System.out.println("코드 : "+storeInfosObject.get("code"));
							System.out.println("위도 : "+storeInfosObject.get("lat"));
							System.out.println("경도 : "+storeInfosObject.get("lng"));
							System.out.println("이름 : "+storeInfosObject.get("name"));
							System.out.println("타입 : "+storeInfosObject.get("type"));

						}
					}

					// 메뉴 선택지에서 '3.세부정보'를 고르면 실행
					else if(menu == 3) {

						System.out.print("세부정보를 고르셨습니다. 확인하고 싶은 번호를 입력[1.약국주소만 출력, 2.약국이름과 주소 검색] : ");
						String tempSelect = in.readLine();
						int selectInt = 0;

						try 
						{
							selectInt = Integer.parseInt(tempSelect);
						} 

						catch(NumberFormatException e) 
						{
							// 숫자 이외의 값이 나왔을 경우, 다시 입력하라고 함.
							System.out.println("숫자로만 입력해주세요. 처음으로 돌아갑니다.");
							continue;
						}

						// 메뉴 선택지에서 '3.세부정보'를 고르고, '1.약국주소만출력'을 고르면 실행됨.
						if(selectInt == 1) {
							JSONObject jsonResult = new JSONObject(result);
							JSONArray storeInfosArray = (JSONArray)jsonResult.get("storeInfos");

							System.out.println("--------- 공적 마스크 판매 약국 전체 주소 ---------");
							for(int i=0; i<storeInfosArray.length(); i++)
							{
								JSONObject storeInfosObject = (JSONObject) storeInfosArray.get(i);
								System.out.println((i+1) + "번째 약국 주소 : "+storeInfosObject.get("addr"));
							}
						}

						// 메뉴 선택지에서 '3.세부정보'를 고르고, '2.약국이름과 주소 검색'을 고르면 실행됨.
						else if(selectInt == 2) {
							JSONObject jsonResult = new JSONObject(result);
							JSONArray storeInfosArray = (JSONArray)jsonResult.get("storeInfos");

							System.out.print("찾으실 약국의 이름이나 주소를 입력하세요. : ");
							String temp = in.readLine();

							int num = 0; //검색된 약국의 개수 파악
							System.out.println("--------- 공적 마스크 판매 약국 검색 결과 ---------");
							for(int i=0; i<storeInfosArray.length(); i++)
							{
								JSONObject storeInfosObject = (JSONObject) storeInfosArray.get(i);
								if(storeInfosObject.get("addr").toString().contains(temp) || storeInfosObject.get("name").toString().contains(temp)) 		
								{	//주소와 약국이름만 검색하여 해당되는 검색 결과가 있으면 약국에 대한 정보를 출력
									num++;
									System.out.println("이름 : "+storeInfosObject.get("name"));
									System.out.println("주소 : "+storeInfosObject.get("addr"));
									System.out.println("-----------------------------------------");
								}
							}
							if(num != 0) //검색 결과가 있으면
								System.out.println("총 " + num + "개의 약국을 찾았습니다.");
							else // 검색 결과가 없으면
								System.out.println("검색결과가 없습니다.");
						}

						// 메뉴 선택지에서 '3.세부정보'를 고르고, 올바르지 않은 명령어를 입력하면 실행
						else
							System.out.println("올바르지 않는 명령어 입니다. 처음으로 돌아갑니다.");
					}

					// 메뉴 선택지에서 '4. 약국 총 개수'를 고르면 실행
					else if(menu == 4) {
						System.out.println("약국 총 개수 : " + new JSONObject(result).getInt("count"));
					}
					// 메뉴 선택지에서  5. 약국정보최신화를 고르면 실행
					else if(menu == 5)
					{
						// 최신화를 실패 할 수 있기 때문에 일단 result값을 비운 뒤에 최신화한다.
						result = "";
						result = HttpModule.requestGet(url);
						if(!result.isEmpty())
							System.out.println("약국 정보를 최신화했습니다.");
						
						else
						{
							// 최신화에 실패했기 때문에 프로그램 종료
							System.out.println("최신화에 실패했습니다. 프로그램을 종료합니다.");
							in.close(); //프로그램이 종료되기 때문에 여기서 BufferedReader 를 close 하여 종료해준다.
							return;
						}
					}
					else {
						System.out.println("올바르지 않는 명령어 입니다.");
					}
				}
			}
			catch(Exception e) {
				// 형식상 필요
			}
		}

		//주소로부터 정보를 얻지 못했다면
		else {
			System.out.println("정보를 얻는데 실패하였습니다.");
		}
	}
}
