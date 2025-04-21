package programmers;


import java.util.*;
class TheMostBiggestNumber {
    public String solution(int[] numbers) {
        String[] numbers2 = new String[numbers.length];
        boolean test0 = false;
        for (int i = 0; i < numbers.length; i++){
            numbers2[i] = String.valueOf(numbers[i]);
            if (numbers[i] != 0){
                test0 = true;
            }
        }
        Arrays.sort(numbers2, (a, b) -> {
            int a1 = Integer.parseInt(String.valueOf(a.charAt(0)));
            int b1 = Integer.parseInt(String.valueOf(b.charAt(0)));
            if(a1 != b1) {
                return b1 - a1;
            } else {
                return (b + a).compareTo(a + b);
            }
        });

        if (!test0){
            return String.valueOf("0");
        }
        return String.join("", numbers2);
    }
}