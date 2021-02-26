import java.util.Scanner;

public class _11726 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dp = new int[n+1];
		dp[0] = 1; // ������Ǹ� ���� (�ƿ� ��ä��� ��쵵 1�� ģ��)
		dp[1] = 1; // (2x1)Ÿ�� ä��� ��� ���� 1
		
		for(int i=2; i<=n; i++) {
			dp[i] = dp[i-1]%10007 + dp[i-2]%10007;
			dp[i] %= 10007;
		}
		System.out.print(dp[n]);
		sc.close();
	}

}
