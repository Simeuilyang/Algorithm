import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class _1931 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] s;
		int[][] time = new int[N][2];
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<2; j++)
				time[i][j] = Integer.parseInt(s[j]);
		}
		
		Arrays.sort(time, new Comparator<int[]>(){
			@Override
			public int compare(int[] a, int[] b) {
				if(a[1] == b[1])
					return a[0] - b[0];
				else
					return a[1] - b[1]; // a가 크면 양수, 작으면 음수, 같으면 0
			}
		});
		
		int cnt = 0;
		int endTime = time[0][1]; // 가장 먼저 끝나는 회의 끝나는 시간
		cnt++;
		for(int i=1; i<N; i++) {
			if(time[i][0] >= endTime) {
				endTime = time[i][1];
				cnt++;
			}
		}
		System.out.print(cnt);
	}

}
