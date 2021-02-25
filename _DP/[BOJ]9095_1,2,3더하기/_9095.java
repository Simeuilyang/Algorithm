import java.io.*;
public class _9095 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		
		int max = 0;
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, num[i]);
		}
		
		int[][] dp = new int[max+1][4];
		Solution(dp, max);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			if(i!=0)
				sb.append("\n" + dp[num[i]][0]);
			else
				sb.append(dp[num[i]][0]);
		}
		System.out.print(sb.toString());
	}
	
	private static void Solution(int[][] dp, int n) {
		dp[1][0] = 1;	dp[1][1] = 1;
		dp[1][2] = 0;	dp[1][3] = 0;
		
		for(int i=2; i<dp.length; i++) {
			dp[i][1] = dp[i-1][0]; // 1을 하나 추가한 가지 수
			if((i-2) >= 0) {
				if(i == 2)
					dp[i][2] = 1;
				else
					dp[i][2] = dp[i-2][0]; // 2를 하나 추가한 가지 수
			}
			
			if((i-3) >= 0) {
				if(i == 3)
					dp[i][3] = 1;
				else
					dp[i][3] = dp[i-3][0];
			}
			
			dp[i][0] = dp[i][1] + dp[i][2] + dp[i][3];			
		}
		
	}
}
