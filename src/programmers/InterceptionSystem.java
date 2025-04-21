package programmers;

import java.util.*;

class InterceptionSystem {
    public int solution(int[][] targets) {
        int answer = 0;
        //구간에서 숫자가 높다고 관통을 많이하리란 보장이 없다.
        //구간에서 숫자가 낮다고 관통을 적게하리란 보장도 없다.
        //이진탐색이 아닌것 같다.
        //공통된 부분수열을 찾나..?
        //각 구간의 시작부분을 탐색해서 각 시작부분마다 몇개가 겹치는지 탐색하려면 5 * 10^5 * 10*8이라서
        //시간초과걸림.
        //요격 쏠때마다 몇개가 정말 요격당하는지 세는 과정이 못해도 5 * 10^5인데 이걸 줄일 방법이 없는거같다.
        //적절한 정렬을 통해서 문제를 해결할 수 있다.
        //각 구간들이 겹치는 원인이 뭘까?
        //한구간의 끝점이 다른 구간의 시작점보단 뒤에 있고, 끝점보단 앞에 있기 때문이다.
        //위 조건을 만족하려면 각 구간의 끝점을 기준으로 오름차순 정렬해야한다.
        //각 구간의 끝점이 다른 구간의 사이에 존재하기만 하면 겹치는 것이 되기에 만약
        //한 구간의 끝점을 기준으로 다른 구간들을 순회할 때, 해당 구간의 시작점이 만약 끝점보다 밖이라면 어쩔 수 없이 미사일을 한발 더 쏴야한다. 그리고 기준 끝점을 해당 구간의 끝점으로한다.
        Arrays.sort(targets, (a, b) -> a[1] - b[1]);
        int currentLastPoint = 0;
        for (int i = 0; i < targets.length; i++){
            if(currentLastPoint <= targets[i][0]){
                currentLastPoint = targets[i][1];
                answer +=1;
            }
        }


        return answer;
    }
}