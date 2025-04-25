package baekjoon;

import java.io.*;
import java.util.*;

public class Num10819{

    static int answer = 0;
    static boolean[] visited;
    static int[] arr;
    static int N;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //12 34랑 34 12랑 결과는 같을 것이다.
        //이러면 startIndex를 넣어서 순서에 관계없이 순회
        //그런데, 34랑 43도 계산결과는 같을 것이지만
        //87654321다 곱한게 1억보단 작다.
        //중복되는 수가 나오면 어떻하지?
        //00111100이렇게
        //startIndex를 넣으면 순열이 되지만 12345678이 있을 때 18을 고려하면 그 뒤로는 탐색이 불가해진다.
        //startIndex가 8을 넘어 탐색이 불가해지니까
        //모든 경우의 수를 찾는게 조금 비효율적이지만 시간 초과는 일어나지 않는다.
        //대신 같은 수를 고려하지않도록 visited를 사용한다.
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visited = new boolean[N];
        String[] s = br.readLine().trim().split(" ");
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        backtracking(0, new ArrayList<Integer>());
        System.out.println(answer);
    }
    public static void backtracking(int cnt, ArrayList<Integer> current){
        if(cnt == N){
            int cand = 0;
            for (int i = 0; i < current.size() - 1; i++){
                cand += Math.abs(current.get(i) - current.get(i + 1));
            }
            answer = Math.max(cand, answer);

            return;
        }

        for (int i = 0; i < N; i++){
            if (!visited[i]){
                visited[i] = true;
                current.add(arr[i]);
                backtracking(cnt + 1, current);
                current.remove(current.size() - 1);
                visited[i] = false;
            }
        }
    }
}