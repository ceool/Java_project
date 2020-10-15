import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
	public static void main(String[] args) {

		/**
		 * ���α׷� ����
		 * ���� ����ũ �Ǹ� ���� �޴� [1.json��ü���, 2.���� ���� ��ü���, 3.��������(�౹�ּҸ����, �౹�˻�), 4.�౹ �� ����(����:x), 5.�౹�����ֽ�ȭ]
		 * 1.json��ü��� : ������� API���� �޾ƿ� json �ڵ� ��ü�� ����Ѵ�
		 * 2.���� ���� ��ü��� : ������� API���� json �ڵ带 ���� ������ �����Ͽ� ����Ѵ�.
		 * 3.�������� : �� �� �������� ����� ����� �� �ִ�.
		 *  - 3-1.�౹�ּҸ� ���: ����ũ�� �Ǹ��ϴ� �౹�ּҸ� �� �� �ְ� ��µǰ� �Ѵ�.
		 *  - 3-2.�౹�̸��� �ּ� �˻�: ����ũ�� �Ǹ��ϴ� �౹�� �̸��̳� �ּ� ���� �˻��Ͽ� �ش�Ǵ� ����� ����Ѵ�. (�̸��� �ּҸ� ��µȴ�.)
		 *    - 3-2-a. �˻���� ���������� �˻��� �౹�� �� ������ �˷��ش�.
		 * 4.�౹ �� ����: ������� API���� �޾ƿ� �౹�� �� ������ ����Ѵ�.
		 * 5.�౹�����ֽ�ȭ: ������� API�κ��� json�� ���� �޾Ƽ� result ���� �ֽ�ȭ���ش�.
		 *  - �ּҷκ��� �ڵ带 ���� ���ϸ� ���α׷��� �����Ѵ�.
		 * 
		 * x�� �Է��ϰ� �Ǹ� ���α׷��� ����ȴ�.
		 * ����, �̿��� ���� �Է��� ��� �ٽ� �Է��� �� �ֵ��� �����ϰ� ������ ��쵵 ó���صξ���.
		 */

		// �޾ƿ��ּ�
		// ���⼭�� �ڷγ� ���� ����ũ �Ǹ�ó�� ���� API
		// �ش� API �� https://www.data.go.kr/dataset/15043025/openapi.do������ ����� Ȯ�� ����.
		// �ش� API �� GET ������� ��û�� �����ϸ�, ���� ���� json ���� �Ѿ�´�.
		//String url = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/stores/json?page=1";


		String url = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/stores/json?page=2";
		// ���� ��� �Լ��� �̿��Ͽ� ������Ʈ�� �����Ͽ� ��� ���� �޴´�.
		String result = HttpModule.requestGet(url);

		// ��� ���� ������� ������ ���������� ���� ��.
		if(!result.isEmpty()) {

			BufferedReader in = null;

			try {
				// Ű����κ��� �ޱ� ���� System.in �� �ְ� BufferedReader �� �ʱ�ȭ ��Ŵ.
				in = new BufferedReader(new InputStreamReader(System.in));

				while(true) //�����ϱ� ������ �ݺ�
				{
					System.out.print("���� ����ũ �Ǹ� ���� �޴� [1.json��ü���, 2.���� ���� ��ü���, 3.��������, 4.�౹ �� ����(����:x), 5.�౹�����ֽ�ȭ] : ");

					String tempMenu = in.readLine();

					// ���α׷� ����
					if(tempMenu.equals("x"))
					{
						System.out.println("���α׷��� �����մϴ�.");
						in.close(); //���α׷��� ����Ǳ� ������ ���⼭ BufferedReader �� close �Ͽ� �������ش�.
						return;
					}

					int menu = 0;

					try 
					{
						menu = Integer.parseInt(tempMenu);
					} 

					catch(NumberFormatException e) 
					{
						// ���� �̿��� ���� ������ ���, �ٽ� �Է��϶�� ��.
						System.out.println("���Ḧ ������ ��ɾ�� ���ڷθ� �Է����ּ���. ");
						continue;
					}

					// �޴� ���������� '1.json��ü���'�� ���� ����
					if(menu == 1) {
						// �ش� �����͸���ü���.
						System.out.println(result);
					}

					// �޴� ���������� '2.���� ���� ��ü���'�� ���� ����
					else if(menu == 2) {
						JSONObject jsonResult = new JSONObject(result);
						JSONArray storeInfosArray = (JSONArray)jsonResult.get("storeInfos");

						for(int i=0; i<storeInfosArray.length(); i++)
						{
							System.out.println("--------- ���� ����ũ �Ǹ� "+(i+1)+"��° �౹ ---------");

							//�迭 �ȿ� �ִ°͵� JSON�����̱� ������ JSONObject�� ����
							JSONObject storeInfosObject = (JSONObject) storeInfosArray.get(i);

							//JSON name���� ����
							System.out.println("�ּ� : "+storeInfosObject.get("addr"));
							System.out.println("�ڵ� : "+storeInfosObject.get("code"));
							System.out.println("���� : "+storeInfosObject.get("lat"));
							System.out.println("�浵 : "+storeInfosObject.get("lng"));
							System.out.println("�̸� : "+storeInfosObject.get("name"));
							System.out.println("Ÿ�� : "+storeInfosObject.get("type"));

						}
					}

					// �޴� ���������� '3.��������'�� ���� ����
					else if(menu == 3) {

						System.out.print("���������� ���̽��ϴ�. Ȯ���ϰ� ���� ��ȣ�� �Է�[1.�౹�ּҸ� ���, 2.�౹�̸��� �ּ� �˻�] : ");
						String tempSelect = in.readLine();
						int selectInt = 0;

						try 
						{
							selectInt = Integer.parseInt(tempSelect);
						} 

						catch(NumberFormatException e) 
						{
							// ���� �̿��� ���� ������ ���, �ٽ� �Է��϶�� ��.
							System.out.println("���ڷθ� �Է����ּ���. ó������ ���ư��ϴ�.");
							continue;
						}

						// �޴� ���������� '3.��������'�� ����, '1.�౹�ּҸ����'�� ���� �����.
						if(selectInt == 1) {
							JSONObject jsonResult = new JSONObject(result);
							JSONArray storeInfosArray = (JSONArray)jsonResult.get("storeInfos");

							System.out.println("--------- ���� ����ũ �Ǹ� �౹ ��ü �ּ� ---------");
							for(int i=0; i<storeInfosArray.length(); i++)
							{
								JSONObject storeInfosObject = (JSONObject) storeInfosArray.get(i);
								System.out.println((i+1) + "��° �౹ �ּ� : "+storeInfosObject.get("addr"));
							}
						}

						// �޴� ���������� '3.��������'�� ����, '2.�౹�̸��� �ּ� �˻�'�� ���� �����.
						else if(selectInt == 2) {
							JSONObject jsonResult = new JSONObject(result);
							JSONArray storeInfosArray = (JSONArray)jsonResult.get("storeInfos");

							System.out.print("ã���� �౹�� �̸��̳� �ּҸ� �Է��ϼ���. : ");
							String temp = in.readLine();

							int num = 0; //�˻��� �౹�� ���� �ľ�
							System.out.println("--------- ���� ����ũ �Ǹ� �౹ �˻� ��� ---------");
							for(int i=0; i<storeInfosArray.length(); i++)
							{
								JSONObject storeInfosObject = (JSONObject) storeInfosArray.get(i);
								if(storeInfosObject.get("addr").toString().contains(temp) || storeInfosObject.get("name").toString().contains(temp)) 		
								{	//�ּҿ� �౹�̸��� �˻��Ͽ� �ش�Ǵ� �˻� ����� ������ �౹�� ���� ������ ���
									num++;
									System.out.println("�̸� : "+storeInfosObject.get("name"));
									System.out.println("�ּ� : "+storeInfosObject.get("addr"));
									System.out.println("-----------------------------------------");
								}
							}
							if(num != 0) //�˻� ����� ������
								System.out.println("�� " + num + "���� �౹�� ã�ҽ��ϴ�.");
							else // �˻� ����� ������
								System.out.println("�˻������ �����ϴ�.");
						}

						// �޴� ���������� '3.��������'�� ����, �ùٸ��� ���� ��ɾ �Է��ϸ� ����
						else
							System.out.println("�ùٸ��� �ʴ� ��ɾ� �Դϴ�. ó������ ���ư��ϴ�.");
					}

					// �޴� ���������� '4. �౹ �� ����'�� ���� ����
					else if(menu == 4) {
						System.out.println("�౹ �� ���� : " + new JSONObject(result).getInt("count"));
					}
					// �޴� ����������  5. �౹�����ֽ�ȭ�� ���� ����
					else if(menu == 5)
					{
						// �ֽ�ȭ�� ���� �� �� �ֱ� ������ �ϴ� result���� ��� �ڿ� �ֽ�ȭ�Ѵ�.
						result = "";
						result = HttpModule.requestGet(url);
						if(!result.isEmpty())
							System.out.println("�౹ ������ �ֽ�ȭ�߽��ϴ�.");
						
						else
						{
							// �ֽ�ȭ�� �����߱� ������ ���α׷� ����
							System.out.println("�ֽ�ȭ�� �����߽��ϴ�. ���α׷��� �����մϴ�.");
							in.close(); //���α׷��� ����Ǳ� ������ ���⼭ BufferedReader �� close �Ͽ� �������ش�.
							return;
						}
					}
					else {
						System.out.println("�ùٸ��� �ʴ� ��ɾ� �Դϴ�.");
					}
				}
			}
			catch(Exception e) {
				// ���Ļ� �ʿ�
			}
		}

		//�ּҷκ��� ������ ���� ���ߴٸ�
		else {
			System.out.println("������ ��µ� �����Ͽ����ϴ�.");
		}
	}
}
