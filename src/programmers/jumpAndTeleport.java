package programmers;

import java.util.*;

public class jumpAndTeleport {
    public int solution(int n) {
        int ans = 0;

        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        return numberReturn(n);
    }

    public int numberReturn(int n){

        if (n == 1){
            return 1;
        } else if (n == 2){
            return 1;
        } else{
            if(n % 2 == 1){
                return numberReturn((n - 1)) + 1;
            }
            else{
                return numberReturn(n / 2);
            }
        }
    }
}