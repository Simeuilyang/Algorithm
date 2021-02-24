import java.io.*;

public class _1085 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int x = Integer.parseInt(s[0]);
		int y = Integer.parseInt(s[1]);
		int w = Integer.parseInt(s[2]);
		int h = Integer.parseInt(s[3]);
		
		System.out.print(Solution(x, y, w, h));
	}

	private static int Solution(int x, int y, int w, int h) {
		int N = h - y;
		int E = w - x;
		int S = y;
		int W = x;
		
		int min1 = Math.min(N, E);
		int min2 = Math.min(S, W);
		
		return Math.min(min1, min2);
	}
}
