import java.util.Scanner;

public class _11726 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dp = new int[n+1];
		dp[0] = 1; // 계산편의를 위함 (아예 못채우는 경우도 1로 친다)
		dp[1] = 1; // (2x1)타일 채우는 방법 수는 1
		
		for(int i=2; i<=n; i++) {
			dp[i] = dp[i-1]%10007 + dp[i-2]%10007;
			dp[i] %= 10007;
		}
		System.out.print(dp[n]);
		sc.close();
	}

}
