import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpModule {

	// 요청한 ril로 GET 방식으로 요청하는 방식
	// GET 방식은 url 자체에 데이터를 주는 방식이라고 생각하면 편하다.
	// 웹 사이트에서 보면 그냥 검색시에 위에 표시되는 긴 주소를 생각하면 됨.

	public static String requestGet(String url) {
		// Libary를 초기화 한다.
		OkHttpClient client = new OkHttpClient();

		// GET 요청을 위한 request header 만들기.
		Request request = new Request.Builder()
				.url(url)
				.build();

		// 기본 result 를 "" 로 설정하여, request 에 실패할 경우, 빈 값을 리턴하도록 하기 위한 result 리턴함.
		String result = "";
		try {
			// 비동기적으로 request 를 실행한다.
			// 웹으로부터 요청이 올때까지 아래의 코드에서 머물러 있음.
			Response response = client.newCall(request).execute();
			result = response.body().string();
		}catch(Exception e) {
			// 에러가 난 경우, 로그로 출력하도록 한다.
			e.printStackTrace();
		}
		
		return result;
	}
}