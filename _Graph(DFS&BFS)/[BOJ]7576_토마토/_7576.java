import java.io.*;
import java.util.*;

public class _7576 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int M = Integer.parseInt(s[0]);
		int N = Integer.parseInt(s[1]);
		int[][] tomato = new int[N][M];
		
		Queue<int[]> q = new LinkedList<>(); // {x, y, day}
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				tomato[i][j] = Integer.parseInt(s[j]);
				if(tomato[i][j] == 1) // 잘 익은 토마토라면 
					q.add(new int[] {i, j, 0});  // q에 add()
			}
		}
		
		System.out.print(bfs(N, M, tomato, q));
	}
	
	private static int bfs(int N, int M, int[][] tomato, Queue<int[]> q) {
		int res = 0;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int day = now[2];
			res = Math.max(res, day);
			
			for(int k=0; k<4; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				
				if(Valid(rx, ry, N, M) && tomato[rx][ry]==0) {
					q.add(new int[] {rx, ry, day+1});
					tomato[rx][ry] = 1;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tomato[i][j] == 0)
					return -1;
			}
		}
		return res;
	}
	
	private static boolean Valid(int x, int y, int N, int M) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
}
