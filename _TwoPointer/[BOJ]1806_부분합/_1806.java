import java.io.*;
public class _1806 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int S = Integer.parseInt(s[1]);
		
		int[] num = new int[N];
		s = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(s[i]);
		}
		
		System.out.print(solution(num, N, S));
	}
	
	private static int solution(int[] num, int N, int S) {
		int res = Integer.MAX_VALUE;
		int l=0, r=0;
		int sum = num[0];
		while(r < N) {
			if(sum < S) {
				r++;
				if(r < N)	sum += num[r];
				else	break;
			}else {
				res = Math.min(res, (r-l+1));
				sum -= num[l];
				l++;
			}
			
			if(l > r)	break;
		}
		
		if(res == Integer.MAX_VALUE)	res = 0;
		return res;
	}
}
