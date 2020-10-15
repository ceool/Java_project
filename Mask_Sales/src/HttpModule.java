import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpModule {

	// ��û�� ril�� GET ������� ��û�ϴ� ���
	// GET ����� url ��ü�� �����͸� �ִ� ����̶�� �����ϸ� ���ϴ�.
	// �� ����Ʈ���� ���� �׳� �˻��ÿ� ���� ǥ�õǴ� �� �ּҸ� �����ϸ� ��.

	public static String requestGet(String url) {
		// Libary�� �ʱ�ȭ �Ѵ�.
		OkHttpClient client = new OkHttpClient();

		// GET ��û�� ���� request header �����.
		Request request = new Request.Builder()
				.url(url)
				.build();

		// �⺻ result �� "" �� �����Ͽ�, request �� ������ ���, �� ���� �����ϵ��� �ϱ� ���� result ������.
		String result = "";
		try {
			// �񵿱������� request �� �����Ѵ�.
			// �����κ��� ��û�� �ö����� �Ʒ��� �ڵ忡�� �ӹ��� ����.
			Response response = client.newCall(request).execute();
			result = response.body().string();
		}catch(Exception e) {
			// ������ �� ���, �α׷� ����ϵ��� �Ѵ�.
			e.printStackTrace();
		}
		
		return result;
	}
}