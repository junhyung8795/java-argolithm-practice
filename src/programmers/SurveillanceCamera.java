package programmers;

import java.util.*;
class SurveillanceCamera {
    public int solution(int[][] routes) {
        int answer = 0;
        //차량은 1대 이상 10^4대이하
        //차량의 진입 출입 지점의 구간은 -3 * 10^4 <= x <=3 * 10^4
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        int currentLastPoint = -30000;
        for (int i = 0; i < routes.length; i++){
            if(currentLastPoint < routes[i][0]){
                currentLastPoint = routes[i][1];
                answer +=1;
            }
        }
        return answer;
    }
}