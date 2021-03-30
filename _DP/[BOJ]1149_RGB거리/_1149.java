import java.io.*;
public class _1149 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] cost = new int[N+1][3];
		String[] s;
		for(int i=1; i<=N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<3; j++)	cost[i][j] = Integer.parseInt(s[j]);
		}
		
		int[][] dp = new int[3][N+1];
		for(int i=0; i<3; i++)	dp[i][1] = cost[1][i];
		
		for(int i=2; i<=N; i++) {
			dp[0][i] = Math.min(dp[1][i-1], dp[2][i-1]) + cost[i][0];
			dp[1][i] = Math.min(dp[0][i-1], dp[2][i-1]) + cost[i][1];
			dp[2][i] = Math.min(dp[0][i-1], dp[1][i-1]) + cost[i][2];
		}
		
		int res = Math.min(dp[0][N], dp[1][N]);
		res = Math.min(res, dp[2][N]);
		
		System.out.print(res);
		
	}

}
