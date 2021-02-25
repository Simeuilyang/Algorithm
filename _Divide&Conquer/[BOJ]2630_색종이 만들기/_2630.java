import java.io.*;

public class _2630 {
	static int[][] map;
	static int White = 0;
	static int Blue = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		String[] s;
		for(int i=0; i<N; i++) {
			s = br.readLine().split(" ");
			for(int j=0; j<N; j++)
				map[i][j] = Integer.parseInt(s[j]);
		}
		Divide_Check(N, 0, N-1, 0, N-1);
		System.out.print(White+"\n"+Blue);
	}
	
	private static int ChkColor(int sx, int ex, int sy, int ey) {
		int color = map[sx][sy];
		for(int i=sx; i<=ex; i++) {
			for(int j=sy; j<=ey; j++) {
				if(map[i][j] != color)
					return -1;
			}
		}
		return color;
	}
	
	private static void Divide_Check(int N, int sx, int ex, int sy, int ey) {
		int color = ChkColor(sx, ex, sy, ey);
		if(color != -1) {
			if(color == 0)	White++;
			else	Blue++;
			return;
		}else {
			Divide_Check(N/2, sx, sx+(N/2)-1, sy, sy+(N/2)-1);
			Divide_Check(N/2, sx, sx+(N/2)-1, sy+(N/2), ey);
			Divide_Check(N/2, sx+(N/2), ex, sy, sy+(N/2)-1);
			Divide_Check(N/2, sx+(N/2), ex, sy+(N/2), ey);
			return;
		}
	}
}
