import java.io.*;
public class _2467 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		String[] s = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(s[i]);
		}
		
		int[] res = solution(N, num);
		System.out.print(res[0] + " " + res[1]);
	}
	
	private static int[] solution(int N, int[] num) {
		int l=0, r=N-1;
		int Min = Integer.MAX_VALUE;
		int A=0, B=0;
		
		while(l < r) {
			int diff = num[l] + num[r];
			int abs = Math.abs(diff);
			if(abs < Min) {
				A = num[l];
				B = num[r];
				Min = abs;
			}
			
			if(diff > 0)	r--;
			else if(diff < 0)	l++;
			else	break; // 0ÀÏ¶§
		}
		
		return new int[] {A, B};
	}
}
