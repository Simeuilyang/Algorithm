import java.io.*;
import java.util.Arrays;

public class _11399 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] p = new int[N];
		String[] s = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			p[i] = Integer.parseInt(s[i]);
		}
		
		Arrays.sort(p);
		int[] dp = new int[N];
		dp[0] = p[0];
		int sum = dp[0];
		for(int i=1; i<N; i++) {
			dp[i] = dp[i-1] + p[i];
			sum += dp[i];
		}
		
		System.out.print(sum);
	}

}
