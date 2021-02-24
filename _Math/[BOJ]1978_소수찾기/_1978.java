import java.io.*;

public class _1978 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		
		System.out.print(ComputeCnt(N, s));
	}
	
	private static int ComputeCnt(int N, String[] s) {
		int cnt = 0;
		boolean flag;
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(s[i]);
			if(num == 1)	continue; // 1은 소수가 아님
			flag = true;
			
			for(int j=num-1; j>1; j--) {
				if((num%j) == 0) {
					flag = false;
					break;
				}
			}
			
			if(flag)	cnt++;
		}
		
		return cnt;
	}
}
