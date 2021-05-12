import java.util.Scanner;

/*
상근이는 2863번에서 표를 너무 열심히 돌린 나머지 5와 6을 헷갈리기 시작했다.

상근이가 숫자 5를 볼 때, 5로 볼 때도 있지만, 6으로 잘못 볼 수도 있고, 6을 볼 때는, 6으로 볼 때도 있지만, 5로 잘못 볼 수도 있다.

두 수 A와 B가 주어졌을 때, 상근이는 이 두 수를 더하려고 한다. 이때, 상근이가 구할 수 있는 두 수의 가능한 합 중, 최솟값과 최댓값을 구해 출력하는 프로그램을 작성하시오.
 */
public class test {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();

        String minA = "";
        String maxA = "";

        String minB = "";
        String maxB = "";

        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) == '6') {
                minA = minA + "5";
            }else {
                minA = minA + a.charAt(i);
                //6이 아닐 경우 그냥 대입
            }

            if(a.charAt(i) == '5') {
                maxA =  maxA + "6";
            }else {
                maxA = maxA + a.charAt(i);
                //5가 아닐 경우 그냥대입
            }
            //문자열로 입력 받은 A의 char의 순서대로 비교
            //각각 최소 최대 변수와 비교하여 항을 변경

        }


        for(int i = 0; i < b.length(); i++) {
            if(b.charAt(i) == '6') {
                minB = minB + "5";
            }else {
                minB = minB + b.charAt(i);
                //6이 아닐 경우 그냥 대입
            }

            if(b.charAt(i) == '5') {
                maxB =  maxB + "6";
            }else {
                maxB = maxB + b.charAt(i);
                //5가 아닐 경우 그냥대입
            }
            //문자열로 입력 받은 B의 char의 순서대로 비교
            //각각 최소 최대 변수와 비교하여 항을 변경

        }

        int max = Integer.parseInt(maxA) +  Integer.parseInt(maxB);
        int min = Integer.parseInt(minA) +  Integer.parseInt(minB);
        //int 치한 후 각각 더해줌

        System.out.println(min + " " + max);
    }
}
