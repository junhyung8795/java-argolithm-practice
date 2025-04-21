package programmers;

import java.util.*;
class TakeAGroupPhoto {
    static HashMap<String, Integer> map;
    static int N;
    static String[] friends;
    static String[] data2;
    static int answer;
    public int solution(int n, String[] data) {
        map = new HashMap<>();
        N = n;
        data2 = data;
        answer = 0;
        String[] fi = {"A", "C", "F", "J", "M", "N", "R", "T"};
        friends =fi;
        //data의 길이는 n, data안에는 각각 5개의 요소 N~C=3라는 식.
        //8명이 서는 모든 경우의 수는 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1개이다.
        //720 * 56 = 40320
        //각 경우의 수마다 최대 100개의 조건을 검사하면 4032000인데,
        //각 조건을 검사할때는 1만 걸릴까?
        //각 조건의 1번 케릭터가 위치한 인덱스를 알아내려면 최대 n이 걸린다.
        //2번 케릭터가 위치한 인덱스를 알아내려면 이거도 n이걸린다.
        //각 조건마다 2n이 걸리니까 100이면 200n
        //4032000에 2 * 8이 걸린다. = 645120
        backtracking(0, new String[8]);


        return answer;
    }

    public static void backtracking(int cnt, String[] current){
        if (cnt == 8){
            //조건들 for문돌면서 비교하는 로직
            boolean test = true;

            for (int i = 0; i < N; i++){
                String oper = String.valueOf(data2[i].charAt(3));
                String start = String.valueOf(data2[i].charAt(0));
                String end = String.valueOf(data2[i].charAt(2));
                int val = data2[i].charAt(4)-'0';
                int diff = Math.abs(map.get(start) - map.get(end)) - 1;
                //Diff를 구할때 끝에 -1해주는게 중요했다.
                if (oper.equals("=")){

                    if(diff != (val)){
                        test = false;
                        break;
                    }
                } else if (oper.equals(">")){
                    if(diff <= val) {
                        test = false;
                        break;
                    }
                } else if (oper.equals("<")){
                    if(diff >= val) {
                        test = false;
                        break;
                    }
                }
            }
            if (test == true){
                answer += 1;
            }
            return;
        }
        for (int i = 0 ; i < 8; i++){
            if (!map.containsKey(friends[i])){
                map.put(friends[i], cnt);
            } else continue;
            current[cnt] = friends[i];
            backtracking(cnt + 1, current);
            current[cnt] = null;
            map.remove(friends[i]);
        }
    }
}
