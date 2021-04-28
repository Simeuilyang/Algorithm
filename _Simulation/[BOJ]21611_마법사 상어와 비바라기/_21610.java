import java.io.*;
import java.util.*;
public class _21610 {
	static int[][] map;
	static ArrayList<Cloud> cloud = new ArrayList<>();
	static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		map = new int[N+1][N+1]; // 1~N
		for(int i=1; i<=N; i++) {
			s = br.readLine().split(" ");
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(s[j-1]);
			}
		}
		int[][] info = new int[M][2]; // 방향, 속력
		for(int i=0; i<M; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<2; j++)	info[i][j] = Integer.parseInt(s[j]);
		}
		
		System.out.print(solution(N, M, info));
	}
	
	private static int solution(int N, int M, int[][] info) {
		int remain = 0;
		InitCloud(N);
		for(int i=0; i<M; i++){
			int d = info[i][0]; //방향
			int s = info[i][1]; //속력
			boolean[][] rain = new boolean[N+1][N+1];
			MoveCloud(N, d, s, rain);
			ChkRainOrNot(N, rain);
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++)	
				remain += map[i][j];
		}
		
		return remain;
	}
	
	private static void ChkRainOrNot(int N, boolean[][] rain) {
		int[][] plus = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(rain[i][j]) { // 비가 왔다면
					int cnt = 0;
					for(int k=2; k<=8; k+=2) {
						int rx = i + dx[k];
						int ry = j + dy[k];
						if(Valid(rx, ry, N) && map[rx][ry]>0)	cnt++;
					}
					plus[i][j] += cnt;
				}else { // 비가 오지 않은 곳이라면
					if(map[i][j] >= 2) {
						cloud.add(new Cloud(i, j));
						plus[i][j] -= 2;
					}
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++)	
				map[i][j] += plus[i][j];
		}
	}
	
	private static boolean Valid(int x, int y, int N) {
		return (x>0 && x<=N && y>0 && y<=N);
	}
	
	private static void MoveCloud(int N, int d, int s, boolean[][] rain) {
		for(Cloud now : cloud) {
			int rx = now.x + s*dx[d];
			int ry = now.y + s*dy[d];
			
			if(rx > N)	rx %= N;
			if(rx < 1)	rx = N - (Math.abs(rx)%N);
			
			if(ry > N)	ry %= N;
			if(ry < 1)	ry = N - (Math.abs(ry)%N);
			
			map[rx][ry]++; // 비 1증가
			rain[rx][ry] = true;
		}
		
		cloud.clear();
	}
	
	private static void InitCloud(int N) {
		cloud.add(new Cloud(N-1, 1));
		cloud.add(new Cloud(N-1, 2));
		cloud.add(new Cloud(N, 1));
		cloud.add(new Cloud(N, 2));
	}
	
	private static class Cloud{
		int x;
		int y;
		
		Cloud(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}
}
