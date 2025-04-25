package programmers;

import java.util.*;

class HoanoiTower {
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    public int[][] solution(int n) {
        int[][] answer;
        moving(1, 3, n);
        answer = new int[arr.size()][2];
        for (int i =0; i < arr.size() ; i++){
            answer[i][0] = arr.get(i).get(0);
            answer[i][1] = arr.get(i).get(1);
        }
        return answer;
    }

    public static void moving(int start, int end, int ring){
        int middlePoint = 6 - start - end;
        if (ring > 1){
            //링 개수가 1개 보다 많은 경우에는
            //링 - 1 개를 중간 경유지에 놓고 크기가 제일 큰 원판을 종착지에 놓은다음
            //경유지에 있던 링들을 다시 end로 보내야한다.
            //처음에 링 - 1개를 경유지로 옮길 때와 경유지에 있던 링들을 다시 End로 옮길 때에도 위 과정들이 똑같이 반복되어
            //재귀적으로 Moving함수를 호출한다.
            moving(start, middlePoint, ring - 1);
            moving(start, end, 1);
            moving(middlePoint, end, ring - 1);
        } else {
            // System.out.println(start + " " + end + " " + 1);
            ArrayList<Integer> arr1 = new ArrayList<>();
            arr1.add(start);
            arr1.add(end);
            arr.add(arr1);
        }
    }
}
