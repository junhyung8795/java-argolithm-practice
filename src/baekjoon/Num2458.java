package baekjoon;

import java.io.*;
import java.util.*;

public class Num2458{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = br.readLine().trim().split(" ");
        int N = Integer.parseInt(s1[0]);
        int M = Integer.parseInt(s1[1]);
        int[][] arr = new int[N + 1][N + 1];
        int answer = 0;

        for(int i = 0; i < M; i++){
            String[] s2 = br.readLine().trim().split(" ");
            int shorter = Integer.parseInt(s2[0]);
            int taller = Integer.parseInt(s2[1]);
            arr[taller][shorter] = 1;
            arr[shorter][taller] = -1;
        }

        for (int i = 1; i < N + 1; i++){
            for (int j = 1; j < N + 1; j++){
                for (int k = 1;k < N + 1; k++){
                    if (arr[i][k] == 1 && arr[k][j] == 1){
                        arr[i][j] = 1;
                        arr[j][i] = -1;
                    }
                    if (arr[i][k] == -1 && arr[k][j] == -1){
                        arr[i][j] = -1;
                        arr[j][i] = 1;
                    }
                }
            }
        }

        for(int i = 1; i < N + 1; i++){
            int cnt = 0;
            for (int j = 1; j < N + 1; j++){
                if (arr[i][j] != 0){
                    cnt += 1;
                }
            }
            if (cnt == N - 1){
                answer += 1;
                // System.out.println(i);
            }
        }

        System.out.println(answer);
        br.close();
    }
}
