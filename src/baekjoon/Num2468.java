package baekjoon;

public class Num2468 {
}
import java.io.*;
        import java.util.*;

public class Main{
    static int N;
    static int[][] arr;
    static int answer = 0;//비가 최대높이로 와서 다 잠길 수 잇어서
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, - 1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++){
            String[] s = br.readLine().trim().split(" ");
            for (int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }
        for(int k = 0; k < 101; k++){
            //비의 높이를 0일때부터 100일때까지 가정
            boolean[][] visited = new boolean[N][N];
            //각 비의 높이마다 Dfs를 실행하기 위해 전역이 아닌 각 K 마다 visited 배열이 필요하다.
            int cand = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(!visited[i][j] && arr[i][j] > k){
                        dfs(i, j, k, visited);
                        cand += 1;
                        //dfs탐색은 새로운 영역이 탐지될 때마다 실행되므로 dfs로 visited에서 같은 영역은 true로 만들고
                        //이후 dfs가 끝나면 cand를 +1시킨다. cand는 K일때 안전 영역의 개수다.
                        //이 cand와 answer를 비교해서 안전 영역의 개수가 제일 클 때를 answer로 갱신한다.
                    }
                }
            }
            answer = Math.max(answer, cand);
        }

        System.out.println(answer);
    }
    public static void dfs(int row, int col, int k, boolean[][] visited){
        visited[row][col] = true;
        for (int[] dir: dirs){
            int dr = row + dir[0];
            int dc = col + dir[1];
            if (dr >= 0 && dr < N && dc >= 0 && dc < N && !visited[dr][dc] && arr[dr][dc] > k){
                visited[dr][dc] = true;
                dfs(dr, dc, k, visited);
            }
        }
    }
}
