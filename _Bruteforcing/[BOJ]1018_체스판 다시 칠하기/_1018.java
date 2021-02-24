import java.io.*;

public class _1018 {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	static String[][] map;
	static int N, M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		
		map = new String[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().split("");
		}
		
		System.out.print(ComputeMin());
	}
	
	private static int MapScan(int sx, int ex, int sy, int ey) {
		int cnt1 = 0;
		int cnt2 = 0;	
		
		String now = "B"; // 시작이 B일 때
		for(int i=sx; i<=ex; i++) {
			for(int j=sy; j<=ey; j++) {
				if(!map[i][j].equals(now)) {
					cnt1++;
				}
				
				if(j != ey) // 마지막 행이면 그대로
					now = now.equals("W")? "B" : "W";
			}
		}
		
		now = "W"; // 시작이 B일 때
		for(int i=sx; i<=ex; i++) {
			for(int j=sy; j<=ey; j++) {
				if(!map[i][j].equals(now)) {
					cnt2++;
				}
				
				if(j != ey) // 마지막 행이면 그대로
					now = now.equals("W")? "B" : "W";
			}
		}
		
		return Math.min(cnt1, cnt2);
	}
	
	private static int ComputeMin() {
		int res = Integer.MAX_VALUE;
		
		for(int i=0; i<=N-8; i++) {
			for(int j=0; j<=M-8; j++) {
				res = Math.min(res, MapScan(i, i+7, j, j+7));
			}
		}
		return res;
	}
	
}
