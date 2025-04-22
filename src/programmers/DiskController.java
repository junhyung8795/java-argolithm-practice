package programmers;

import java.util.*;

//class DiskController {
//
//    public int solution(int[][] jobs) {
//        int answer = 0;
//        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
//        PriorityQueue<Job> pq = new PriorityQueue<>(new Comparator<Job>(){
//            @Override
//            public int compare(Job o1, Job o2){
//                if (o1.spendTime != o2.spendTime){
//                    return o1.spendTime - o2.spendTime;
//                } else if(o1.startTime != o2.startTime){
//                    return o1.startTime - o2.startTime;
//                } else {
//                    return o1.num - o2.num;
//                }
//            }
//        });
//        int time = 0;
//        pq.offer(new Job(0,jobs[0][0],jobs[0][1]));
//        int[][] timeLine = new int[jobs.length][2];
//        int index = 0;
//
//        while(pq.size() > 0){
//            Job job = pq.poll();
//            if (job.startTime > time){
//
//                time = job.startTime + job.spendTime;
//
//            } else{
//
//                time += job.spendTime;
//
//            }
//            boolean put = false;
//            timeLine[job.num][0] = job.startTime;
//            timeLine[job.num][1] = time;
//
//            if (index + 1 != jobs.length){
//                for (int i = index + 1; i < jobs.length; i++){
//                    if (jobs[i][0] <= time){
//                        pq.offer(new Job(i, jobs[i][0], jobs[i][1]));
//                        index = i;
//
//                        put = true;
//                    } else {
//                        break;
//                    }
//                }
//                if (!put){
//                    index += 1;
//                    pq.offer(new Job(index, jobs[index][0], jobs[index][1]));
//                }
//            }
//
//
//        }
//        int sum = 0;
//        for(int i = 0; i < jobs.length; i++){
//            sum += timeLine[i][1] - timeLine[i][0];
//        }
//        answer = sum / timeLine.length;
//
//        return answer;
//    }
//    public static class Job{
//        int num;
//        int startTime;
//        int spendTime;
//        public Job(int num, int startTime, int spendTime){
//            this.num = num;
//            this.startTime = startTime;
//            this.spendTime = spendTime;
//        }
//        @Override
//        public String toString() {
//            return "Job{" +
//                    "num=" + num +
//                    ", startTime=" + startTime +
//                    ", spendTime=" + spendTime +
//                    '}';
//        }
//    }
//}

import java.util.*;

class DiskController {

    public int solution(int[][] jobs) {
        int answer = 0;
        int time = 0; // 현재 시간
        int jobsIdx = 0; // jobs 배열 인덱스
        int count = 0; // 완료된 작업 수

        Arrays.sort(jobs, (a, b) -> a[0] - b[0]); // 요청 시간 기준 정렬

        PriorityQueue<Job> pq = new PriorityQueue<>(
                (a, b) -> a.spendTime - b.spendTime
        );

        while (count < jobs.length) {
            // 현재 시간 이전에 들어온 job들 PQ에 삽입
            while (jobsIdx < jobs.length && jobs[jobsIdx][0] <= time) {
                pq.offer(new Job(jobsIdx, jobs[jobsIdx][0], jobs[jobsIdx][1]));
                jobsIdx++;
            }

            if (!pq.isEmpty()) {
                Job job = pq.poll();
                time = Math.max(time, job.startTime); // 작업 시작시간 갱신
                time += job.spendTime;
                answer += (time - job.startTime); // 요청부터 종료까지의 시간
                count++;
            } else {
                // 대기할 job이 없는 경우: 다음 작업의 요청시간으로 점프
                time = jobs[jobsIdx][0];
            }
        }

        return answer / jobs.length;
    }

    public static class Job {
        int num;
        int startTime;
        int spendTime;

        public Job(int num, int startTime, int spendTime) {
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