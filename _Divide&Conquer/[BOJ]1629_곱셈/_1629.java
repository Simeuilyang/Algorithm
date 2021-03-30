import java.io.*;
public class _1629 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		long A = Integer.parseInt(s[0]);
		long B = Integer.parseInt(s[1]);
		long C = Integer.parseInt(s[2]);
		
		System.out.println(solution(A%C, B, C));
	}
	
	private static long solution(long A, long B, long C) {
		if(B == 1)	return A % C;
		
		long tmp = solution(A, B/2, C) % C; // Àý¹Ý °ª
		
		if(B%2 == 0) { // Â¦¼ö°³
			return (tmp*tmp)%C;
		}else { // È¦¼ö°³
 			return (((tmp*tmp)%C)*A)%C;
		}
	}
}
