import java.io.*;
import java.util.*;
public class _7569 {
	static int[][][] tomato;
	static int N, M, H;
	static int[] dx = {0, 0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 0, 1, 0, -1};
	static int[] dz = {1, -1, 0, 0, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		M = Integer.parseInt(s[0]);
		N = Integer.parseInt(s[1]);
		H = Integer.parseInt(s[2]);
		tomato = new int[H][N][M];
		
		int unripeCnt = 0;
		Queue<Tomato> q = new LinkedList<>();
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				s = br.readLine().split(" ");
				for(int k=0; k<M; k++) {
					int now = Integer.parseInt(s[k]);
					tomato[i][j][k] = now;
					if(now == 0)	unripeCnt++; // 안익은 토마토
					else if(now == 1)	q.add(new Tomato(i, j, k, 0));
				}
			}
		}
		
		System.out.print(solution(unripeCnt, q));
	}
	
	private static int solution(int unripeCnt, Queue<Tomato> q) {
		int ans = 0;
		while(!q.isEmpty()) {
			Tomato now = q.poll();
			int x = now.x;
			int y = now.y;
			int z = now.z;
			int day = now.day;
			ans = Math.max(ans, day);
			
			for(int k=0; k<6; k++) {
				int rx = x + dx[k];
				int ry = y + dy[k];
				int rz = z + dz[k];
				
				if(Valid(rz, rx, ry) && tomato[rz][rx][ry]==0) {
					tomato[rz][rx][ry] = 1; // 익은 토마토로
					unripeCnt--;
					q.add(new Tomato(rz, rx, ry, day+1));
				}
			}
		}
		
		if(unripeCnt > 0)	ans = -1; // 안익은 토마토가 남아있다면
		return ans;
	}
	
	private static boolean Valid(int z, int x, int y) {
		return (z>=0 && z<H && x>=0 && x<N && y>=0 && y<M);
	}
	
	private static class Tomato{
		int z;
		int x;
		int y;
		int day;
		
		Tomato(int z, int x, int y, int day){
			this.z = z;
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}
}
