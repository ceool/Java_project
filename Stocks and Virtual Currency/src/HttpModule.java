
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpModule {
	// 라이브러리 초기화
	private static OkHttpClient client = new OkHttpClient();
	
	// 요청한 url 로 GET 방식으로 요청하는 방식
	public static String requestGet(String url) {
		// GET 요청을 위한 request header 만들기.
		Request request = new Request.Builder()
				.url(url)
				.build();
		
		// 기본 result 를 "" 로 설정하여, request 에 실패한 경우, 빈 값을 리턴하도록 하기 위한 result 를 리턴함.
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

