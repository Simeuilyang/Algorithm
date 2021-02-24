import java.io.*;

public class _1463 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.print(Solution(N));
	}
	
	private static int Solution(int N) {
		int[] dp = new int[N+1];
		dp[1] = 0;
		
		for(int i=2; i<=N; i++) {
			int min = dp[i-1] + 1; // 3¹ø
			
			if((i%3) == 0) { // 1¹ø
				min = Math.min(min, (dp[i/3]+1));
			}
			
			if((i%2) == 0) { // 2¹ø
				min = Math.min(min, (dp[i/2]+1));
			}
			
			dp[i] = min;
		}
		
		return dp[N];
	}
}
