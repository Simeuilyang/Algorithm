import java.io.*;
import java.util.Arrays;

public class _1920 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		String[] s = br.readLine().split(" ");
		for(int i=0; i<N; i++)
			A[i] = Integer.parseInt(s[i]);
		
		Arrays.sort(A);
		int M = Integer.parseInt(br.readLine());
		int[] check = new int[N];
		String[] d = br.readLine().split(" ");
		
		for(int i=0; i<M; i++) {
			int find = Integer.parseInt(d[i]);
			if(checkExist(find, A))
				System.out.println("1");
			else
				System.out.println("0");
		}	
	}
	
	static boolean checkExist(int find, int[] A) {
		int left = 0;
		int right = A.length-1;
		int mid;
		
		int flag = 0;
		while(left <= right) {
			mid = (left + right) / 2;
			if(find > A[mid]) {
				left = mid + 1;
			}else if(find < A[mid]) {
				right = mid - 1;
			}else { //find == A[mid]
				flag = 1;
				break;
			}	
		}
		
		if(flag == 1)
			return true;
		else
			return false;
	}
	
}
