package programmers;

import java.util.*;

//class DiskController {
class DiskController {

    public int solution(int[][] jobs) {
        int answer = 0;
        //맨처음 풀이는 처음에 요소를 하나 집어넣는 과정이 잘못됐다.
        //애초에 0초가 됐을 때 큐에 요소가 하나만 들어가리란 보장이 없다.
        //0초에 3,4개의 작업이 들어갈 수 있다는 소리.
        //시작부터 time보다 작거나 같은 모든 작업을 큐에 집어넣어줘야한다.
        Comparator<Job> cp = new Comparator<>(){
            @Override
            public int compare(Job a, Job b){
                if (a.spendTime != b.spendTime){
                    return a.spendTime - b.spendTime;
                } else if(a.startTime != b.startTime){
                    return a.startTime - b.startTime;
                } else{
                    return a.num - b.num;
                }
            }
        };

        PriorityQueue<Job> pq = new PriorityQueue<>(cp);

        //먼저 jobs가 시간 순서대로 넣어졌다는 보장이 없으므로 정렬을 시켜줘야한다.
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        //현재 검토가 완료된 위치 index.
        int index = -1;
        int count = 0;
        //현재 진행된 시간
        int time = 0;

        //검토하려는 index위치가 jobs의 길이보다는 작아야한다.
        //만약 같아지면 모든 작업의 검토가 완료됐다는 의미
        while (count < jobs.length){
            //먼저 긁어와야한다.
            //그런데 이 긁어오는 과정은 하나의 작업이 끝날때마다 이뤄져야한다,
            for(int i = index + 1; i < jobs.length; i++){

                if (jobs[i][0] <= time){
                    pq.offer(new Job(i, jobs[i][0], jobs[i][1]));
                    index = i;
                    // System.out.print("인덱스 검사중: " + i + " ");
                } else{
                    break;
                }
            }
            //그런데 만약에 time보다 이전에 신청된 작업이 없다면?
            //time이후의 가장 가까운 시일내 신청된 작업의 시간을 보고 그 시간보다 작거나 같은
            //모든 작업을 넣어줘야한다.


            //그다음에 pq에 있는 작업들을 하나씩 꺼내고 time을 갱신해주고
            //time이 갱신됐으면 time보다 작거나 같은 작업들을 모두 pq에 넣어주고
            //만약 pq가 다 비었으면? 또 긁어주는 루프. 바깥 루프에서 또 긁어서 Pq에 넣어준다.

            if (!pq.isEmpty()){
                // System.out.print("큐에 들어잇는 것." + pq + " ");
                Job popped = pq.poll();
                count ++;
                // System.out.print(time + ", ");
                // System.out.print(popped.startTime + ", ");
                // System.out.print(popped.spendTime + ", ");

                if (time >= popped.startTime){
                    time += popped.spendTime;
                } else {
                    time = popped.startTime;
                    time += popped.spendTime;
                }
                answer += time - popped.startTime;
                // System.out.print(time);
                // System.out.print("index = " + index);
                // System.out.println();
            } else{
                time = jobs[index + 1][0];
            }

        }
        answer = answer / jobs.length;
        return answer;
    }
    public static class Job{
        int num;
        int startTime;
        int spendTime;
        public Job(int num, int startTime, int spendTime){
            this.num = num;
            this.startTime = startTime;
            this.spendTime = spendTime;
        }
        @Override
        public String toString() {
            return "Job{" +
                    "num=" + num +
                    ", startTime=" + startTime +
                    ", spendTime=" + spendTime +
                    '}';
        }
    }
}


//count변수가 중요하다.
//index + 1 <= jobs.length를 하면 pq에 몾든 요소를 집어넣기만 하면 다 끝나버린다.
//하지만 while안에서는 if문으로 Poll을 한다. 매번 요소를 뺄때마다 새로운 요소를 긁어와서 가장 우선순위가 높은 요소를 꺼내올 수 있도록 해야해서
//그런데 Index로 루프종료를 하면 pq에 만약 마지막에 2,3개 넣어서 모든 요소를 Pq에 넣으면
// 한개만 Poll을 한 후에 바로 index + 1 == jobs.length가 돼어서 pq에있는 모든 요소들을 털 수가 없다.
//index도 필요하고 검사완료된 요소 count변수도 필요하다.
//count: 완료된 요소를 정확히 세기 위해서 필요. index보다 이 조건이 더 우선이다.
//index: 시작시간 순서대로 정렬된 jobs에서 요소를 시간이 제일 작은 놈들부터 검사하는데 어디까지 검사했는지 알기 위해서 필요.
