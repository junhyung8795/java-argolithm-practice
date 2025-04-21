package baekjoon;

import java.io.*;
import java.util.*;

public class Num1931{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        int answer = 0;
        for (int i = 0 ; i < N ; i++){
            String[] s = br.readLine().trim().split(" ");
            arr[i][0] = Integer.parseInt(s[0]);
            arr[i][1] = Integer.parseInt(s[1]);
        }
        Arrays.sort(arr, (a, b) -> {if (a[1] != b[1]) return a[1] - b[1]; return a[0] - b[0]; });
        int prev = 0;
        for (int i = 0 ; i < N ; i++){
            if (prev <= arr[i][0]){
                prev = arr[i][1];
                answer++;
            }
        }
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();

        //

    }
}
