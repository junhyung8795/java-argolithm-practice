package programmers;

import java.util.*;
class FindingTheBiggestSquare
{
    public int solution(int [][]board)
    {
        int answer = 0;

        int[][] dp = new int[board.length][board[0].length];
        //dp를 이용하고자 했던 이유:
        //큰 정사각형은 작은 정사각형들의 모임으로 구성되기 때문이다.
        //3X3의 정사각형은 사실 2X2의 정사각형들이 모여서 만들어진다.
        //각 칸을 포함하는 가장 큰 정사각형의 한 변의 길이를 dp[row][col]에 담고
        //dp테이블을 다 채우고 나서 혹은 채우는 과정에서 Answer를 Dp의 최댓값으로 갱신하고 answer를 마지막에 제곱한다.

        //dp 테이블을 채울 때 정사각형의 가장 오른쪽 아래칸을 기준으로 해당 칸을 포함할 때 가장 큰 정사각형을 구할 것이다.
        //아래는 dp테이블에서 row가 0이거나, col이 0이면서 board에 적힌 수가 1일 때 1로 초기화하는 과정이다.
        //row가 0이거나, col이 0알땐 해당 칸을 포함하는 가장 큰 정사각형의 변은 1이 될 수밖에 없기 때문에.
        //대신에 이 1로 초기화된 정사각형들은 다른 정사각형의 변을 2,3,4로 키우는 기반이된다.
        for(int row = 0; row < board.length; row++){
            for (int col = 0; col < board[0].length; col++){
                if ((row == 0 || col == 0) && board[row][col] == 1){
                    dp[row][col] = 1;
                    answer = 1;
                }
            }
        }


        //본격적으로 dp테이블을 채우는 코드
        //해당 칸 기준, 왼쪽 한칸,  위로 한칸, 왼쪽, 위로 한칸씩 떨어진 칸 3개를 탐색한다.
        //해당 칸들이 모두 1이면 dp[row][col]은 2가 되어 한 변의 길이가 2인 정사각형이 된다.
        //해당 칸들이 모두 2이면 dp[row][col]은 3이 되어 한 변의 길이가 3인 정사각형이 된다.
        //그러나 한칸이라도 1이면 dp[row][col]은 1에다가 1을 더한 2가 된다.
        for(int row = 1; row < board.length; row++){
            for (int col = 1; col < board[0].length; col++){
                if (board[row][col] == 1){
                    dp[row][col] = Math.min(
                            Math.min(dp[row - 1][col], dp[row][col - 1]),
                            dp[row - 1][col - 1]
                    ) + 1;
                    answer = Math.max(answer, dp[row][col]);
                }
            }
        }




        return (int) Math.pow(answer, 2);
    }
}