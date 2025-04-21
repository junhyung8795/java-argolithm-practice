package programmers;


import java.util.*;
class GymGlothes {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        //먼저 lost에 있는 인원과 reserve에 있는 인원들중 겹치는 인원이 있는지 탐색해야함.
        //겹치는 인원 있으면 해당 인원은 reserve에서 제외
        for(int i = 0; i < lost.length; i++){
            for (int j = 0; j < reserve.length; j++){
                if (lost[i] == reserve[j]){
                    reserve[j] = -1;
                    lost[i] = -1;
                }
            }
        }

        Arrays.sort(lost);
        Arrays.sort(reserve);
        System.out.println(Arrays.toString(lost));
        System.out.println(Arrays.toString(reserve));

        answer = n - lost.length;
        for(int i = 0; i < lost.length; i++){
            if (lost[i] == -1){
                answer += 1;
                continue;
            }
            for(int j = 0; j < reserve.length; j++){
                if(lost[i] - 1 == reserve[j] && reserve[j] != -1){
                    answer += 1;
                    reserve[j] = -1;
                    break;
                } else if (lost[i] + 1 == reserve[j] && reserve[j] != -1){
                    answer += 1;
                    reserve[j] = -1;
                    break;
                }
            }
            System.out.println(Arrays.toString(reserve));
        }
        return answer;
    }
}