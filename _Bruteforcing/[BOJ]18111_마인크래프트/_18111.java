import java.io.*;

public class _18111 {
	static int MinTime = Integer.MAX_VALUE;
	static int MaxHeight = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		int B = Integer.parseInt(s[2]); // 인벤토리 내 블럭 개수
		
		int max = -1;
		int min = Integer.MAX_VALUE;
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				max = Math.max(max, map[i][j]);
				min = Math.min(min, map[i][j]);
			}
		}
		
		Solution(N, M, B, map, min, max);
		System.out.print(MinTime + " " + MaxHeight);
	}
	
	private static void Solution(int N, int M, int B, int[][] map, int min, int max) {
		for(int height=min; height<=max; height++) {
			int time = 0;
			int nowB = B;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					int mapH = map[i][j];
					if(mapH > height) {
						int diff = mapH - height;
						nowB += diff;
						time += 2*diff;
					}else if(mapH < height) {
						int diff = height - mapH;
						nowB -= diff;
						time += diff;
					}
				}
			}
			
			if(nowB >= 0) {
				if(MinTime > time) { // 시간이 더 적게 걸리면 갱신
					MinTime = time;
					MaxHeight = height;
				}else if(MinTime == time) { // 최소 시간이 같다면 높은 땅으로
					MaxHeight = Math.max(MaxHeight, height);
				}
			}
		}
	}
}
