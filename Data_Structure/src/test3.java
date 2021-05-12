import java.util.Scanner;

/*
농부 존의 식당은 N마리의 소들에게 M개의 음식을 제공해 주고 있다.

소들은 자신들이 선호하는 음식 Pi를 가지고 있는데, 농부 존은 다음 방법으로 소들에게 음식을 공급한다.

들어오는 소들을 순서대로 그룹으로 나눈다. [1 ~ 4] / [5 ~ 7] / [8 ~ 10] 이런 식으로.
그룹에 있는 소들에게 음식을 제공하는 데 드는 비용은 (해당 그룹에서 선호하는 음식의 합집합 크기)^2 이다. 음식을 수로 생각하면, 서로 다른 수들의 개수의 제곱이다.
최소 비용으로 음식을 제공하려면 어떻게 해야 할까?

입력
첫 번째 줄에 N, M이 주어진다. (1 ≤ M ≤ N ≤ 40000)

이후 N개의 줄에 Pi가 주어진다. (1 ≤ Pi ≤ M)
 */
public class test3 {

    private static int N;
    private static int M;
    private static int[] food;
    private static int[] d;
    private static int[] foodOccurred;
    private static Link root;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        food = new int[N+1];
        for (int i=1; i<=N; i++) {
            food[i] = sc.nextInt();
        }

        d = new int[N+1];
        foodOccurred = new int[M+1];

        d[0] = 0;
        d[1] = 1;
        foodOccurred[d[1]] = 1;
        root = new Link();
        root.num = food[1];
        root.firstIndex = 1;

        for (int i=2; i<=N; i++) {

            if (foodOccurred[food[i]] == 0) {
                d[i] = d[i-1] + 1;
                foodOccurred[food[i]] = 1;
                Link temp = new Link();
                temp.num = food[i];
                temp.firstIndex = i;
                temp.nextLink = root;
                root = temp;
            } else {
                d[i] = d[i-1]+1;
                Link prev = new Link();
                prev.num = food[i];
                prev.firstIndex = i;
                prev.nextLink = root;

                Link current = root;
                root = prev;
                int depth = 1;
                while (current != null) {
                    if (food[i] == current.num) {
                        prev.firstIndex = current.firstIndex;
                        prev.nextLink = current.nextLink;

                        if (d[i] > d[current.firstIndex-1] + depth*depth) {
                            d[i] = d[current.firstIndex-1] + depth*depth;
                        }

                        current = current.nextLink;
                    } else {
                        depth++;
                        if (d[i] > d[current.firstIndex-1] + depth*depth) {
                            d[i] = d[current.firstIndex-1] + depth*depth;
                        }
                        prev = current;
                        current = current.nextLink;
                    }
                }
            }
            //System.out.println(Arrays.toString(d));
        }

        System.out.println(d[N]);
    }
}

class Link {
    int num;
    int firstIndex;
    Link nextLink;
}