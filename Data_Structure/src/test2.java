/*
1의 개수가 홀수개인 비트스트링을 "홀수 패리티"를 가지고 있다고 한다. 또, 짝수개인 경우에는 "짝수 패리티"를 가지고 있다고 한다. 또, 0도 짝수로 간주한다. 따라서, 1이 없는 비트 스트링은 짝수 패리티를 가지고 있다.

마지막 숫자가 지워진 비트 스트링이 주어지고, 이 비트 스트링의 패리티가 주어졌을 때, 마지막 숫자를 올바르게 구하는 프로그램을 작성하시오.
 */

import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {
        String a = "";
        int num;
        Scanner sc = new Scanner(System.in);
        while (true) {
            num = 0;
            a = sc.next();

            if (a.equals("#"))
                break;

            for (int i = 0; i < a.length() - 1; i++) {
                if (a.charAt(i) == '1') {
                    num++;
                }
            }
            if (a.charAt(a.length() - 1) == 'e') {
                System.out.println(a.replace('e', (char) (num % 2 + '0')));
            } else {
                System.out.println(a.replace('o', (char) (1 - num % 2 + '0')));
            }
        }
    }
}


