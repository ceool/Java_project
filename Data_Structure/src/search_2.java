/*
소수 찾기
        문제 설명
        한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.

        각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.

        제한사항
        numbers는 길이 1 이상 7 이하인 문자열입니다.
        numbers는 0~9까지 숫자만으로 이루어져 있습니다.
        "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
        입출력 예
        numbers	return
        "17"	3
        "011"	2
        입출력 예 설명
        예제 #1
        [1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.

        예제 #2
        [0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.

        11과 011은 같은 숫자로 취급합니다.*/

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

class search_2 {
    Set<Integer> set = new LinkedHashSet<>();

    public int search_2(String numbers) {
        int answer = 0;
        int[] arr = new int[numbers.length()];
        int[] output = new int[numbers.length()];
        boolean[] visited = new boolean[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) {
            arr[i] = Integer.parseInt(String.valueOf(numbers.charAt(i)));
        }
        for (int r = 1; r <= numbers.length(); r++) {
            perm(arr, output, visited, 0, numbers.length(), r);
        }
        Iterator<Integer> itr = set.iterator();
        while (itr.hasNext()) {
            Integer next = itr.next();
            if (isPrime(next)) answer++;
        }
        return answer;
    }

    void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < r; i++) {
                s.append(output[i]);
            }
            set.add(Integer.parseInt(s.toString()));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] != true) {
                visited[i] = true;
                output[depth] = arr[i];
                perm(arr, output, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }

    boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}