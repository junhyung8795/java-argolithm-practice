package programmers;

import java.util.*;

class TruckPassingBridge {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        //최대 올라갈 수 있는 트럭의 수와
        //다리가 견딜 수 있는 무게가 있다.
        //너무 많이 올라도 안되고 설령 수가 만족되도 무게를 넘기면 안된다.
        //무게와 현재 올라간 트럭의 수를 동시에 체크해야한다.
        int time = 0;

        //트럭들을 큐에서 꺼내느걸로 하자.
        Integer[] IntegerTruck = Arrays.stream(truck_weights).boxed().toArray(Integer[]::new);
        Queue<Integer> truckQ = new LinkedList<>(Arrays.asList(IntegerTruck));

        Queue<Integer> bridgeQ = new LinkedList<>();
        for(int i = 0; i < bridge_length; i++){
            bridgeQ.offer(0);
        }

        int sumOfWeight = 0;
        while (!bridgeQ.isEmpty()){
            sumOfWeight = sumOfWeight - bridgeQ.poll();
            time += 1;

            if(!truckQ.isEmpty()){
                if(sumOfWeight + truckQ.peek() <= weight){
                    int popped = truckQ.poll();
                    bridgeQ.add(popped);
                    sumOfWeight += popped;
                } else{
                    bridgeQ.add(0);
                }
            }

        }


        return time;
    }
}