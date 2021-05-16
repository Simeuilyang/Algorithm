import java.io.*;
import java.util.Arrays;
public class _11053 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		String[] s = br.readLine().split(" ");
		
		for(int i=0; i<N; i++)
			numbers[i] = Integer.parseInt(s[i]);
		
		System.out.print(solution(N, numbers));
	}

	private static int solution(int N, int[] numbers) {
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		
		for(int i=1; i<N; i++) {
			int now = numbers[i];
			for(int j=0; j<i; j++) {
				if(numbers[j] < now) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		
		int max = 0;
		for(int i=0; i<N; i++)	max = Math.max(dp[i], max);
		return max;
	}
}
