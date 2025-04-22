package programmers;

import java.util.*;

class DiskController {

    public int solution(int[][] jobs) {
        int answer = 0;
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Job> pq = new PriorityQueue<>(new Comparator<Job>(){
            @Override
            public int compare(Job o1, Job o2){
                if (o1.spendTime != o2.spendTime){
                    return o1.spendTime - o2.spendTime;
                } else if(o1.startTime != o2.startTime){
                    return o1.startTime - o2.startTime;
                } else {
                    return o1.num - o2.num;
                }
            }
        });
        int time = 0;
        pq.offer(new Job(0,jobs[0][0],jobs[0][1]));
        int[][] timeLine = new int[jobs.length][2];
        int index = 0;

        while(pq.size() > 0){
            Job job = pq.poll();
            if (job.startTime > time){

                time = job.startTime + job.spendTime;

            } else{

                time += job.spendTime;

            }
            boolean put = false;
            timeLine[job.num][0] = job.startTime;
            timeLine[job.num][1] = time;

            if (index + 1 != jobs.length){
                for (int i = index + 1; i < jobs.length; i++){
                    if (jobs[i][0] <= time){
                        pq.offer(new Job(i, jobs[i][0], jobs[i][1]));
                        index = i;

                        put = true;
                    } else {
                        break;
                    }
                }
                if (!put){
                    index += 1;
                    pq.offer(new Job(index, jobs[index][0], jobs[index][1]));
                }
            }


        }
        int sum = 0;
        for(int i = 0; i < jobs.length; i++){
            sum += timeLine[i][1] - timeLine[i][0];
        }
        answer = sum / timeLine.length;

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