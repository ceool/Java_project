/*
문제
최대 5개의 조각이 있는 5*5 크기의 보드가 있다. 김지민은 조각을 적절히 움직여서 모든 조각이 연결 요소를 이루게 하려고 한다. 즉 상하좌우로 인접한 조각을 모두 연결했을 때, 모든 쌍의 조각이 적어도 하나의 경로로 연결되어 있어야 한다.

한 번의 이동으로 하나의 조각을 상하좌우로 인접한 칸으로 옮길 수 있다. 보드의 상태가 주어질 때, 최소 몇 번 이동해야 모든 조각이 연결 요소를 이루게 되는지 구하는 프로그램을 작성하시오.

입력
첫째 줄부터 다섯째 줄까지 보드의 상태가 주어진다. 빈 곳은 "."이고, 조각은 "*"이다. 조각은 1개 이상 5개 이하이다.

출력
첫째 줄에 문제의 정답을 출력한다.
 */
import java.util.*;
import java.io.*;

public class test4 {
    static int N = 5;
    static int[][] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int idx = 0;
        arr = new int[5][2];
        for(int i = 0; i < N; i++) {
            String s = sc.next();
            for(int j = 0; j < N; j++) {
                if(s.charAt(j) == '*') {
                    arr[idx][0] = i;
                    arr[idx++][1] = j;
                }
            }
        }

        t = new int[5][2];
        used = new boolean[N][N];
        ans = Integer.MAX_VALUE;

        solve(0,idx,0);

        System.out.println(ans);
        sc.close();

    }

    static int[][] t;
    static boolean[][] used;
    static int ans;
    static void solve(int idx, int max, int sum) {
        if(sum >= ans) return;
        if(idx == max) {
            if(bfs(t[0][0],t[0][1],max)) {
                ans = Math.min(ans, sum);
            }
            return;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(used[i][j]) continue;
                used[i][j] = true;
                t[idx][0] = i;
                t[idx][1] = j;
                solve(idx+1,max,sum+Math.abs(arr[idx][0]-t[idx][0])+Math.abs(arr[idx][1]-t[idx][1]));
                used[i][j] = false;
            }
        }
    }

    static boolean[][] visited;
    static int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
    static boolean bfs(int r, int c, int max) {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][N];
        q.add(new int[] {r,c});
        visited[r][c] = true;

        int cnt = 1;

        while(!q.isEmpty()) {
            int[] p = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = p[0]+dx[i];
                int ny = p[1]+dy[i];
                if(OOB(nx,ny) || visited[nx][ny] || !used[nx][ny]) continue;
                cnt++;
                q.add(new int[] {nx,ny});
                visited[nx][ny] = true;
            }
        }

        if(cnt==max) return true;
        return false;
    }

    static boolean OOB(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }

}