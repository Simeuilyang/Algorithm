import java.io.*;
import java.util.*;
public class _18870 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		
		PriorityQueue<FirstLoc> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			pq.add(new FirstLoc(i, Integer.parseInt(s[i])));
		}
		
		int[] res = Solution(N, pq);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			if(i != 0)	sb.append(" " + res[i]);
			else	sb.append(res[i]);
		}
		System.out.print(sb.toString());
	}
	
	private static int[] Solution(int N, PriorityQueue<FirstLoc> firstloc) {
		int[] res = new int[N];
		PriorityQueue<FirstOrder> firstorder = new PriorityQueue<>();
		
		Map<Integer, Integer> map = new HashMap<>(); // <ÁÂÇ¥, °á°ú°ª>
		int smallCnt = 0;
		for(int i=0; i<N; i++) {
			FirstLoc now = firstloc.poll();
			if(map.containsKey(now.loc)) {
				firstorder.add(new FirstOrder(now.order, map.get(now.loc)));
			}else {
				firstorder.add(new FirstOrder(now.order, smallCnt));
				map.put(now.loc, smallCnt++);
			}
		}
		
		for(int i=0; i<N; i++) {
			res[i] = firstorder.poll().res;
		}
		
		return res;
	}
	
	private static class FirstOrder implements Comparable<FirstOrder>{
		private int order;
		private int res;
		
		FirstOrder(int order, int res) {
			this.order = order;
			this.res = res;
		}
		
		@Override
		public int compareTo(FirstOrder f) {
			return (this.order >= f.order)? 1 : -1;
		}
	}
	private static class FirstLoc implements Comparable<FirstLoc>{
		private int order;
		private int loc;
		
		FirstLoc(int order, int loc){
			this.order = order;
			this.loc = loc;
		}
		
		@Override
		public int compareTo(FirstLoc f) {
			return (this.loc >= f.loc)? 1 : -1;
		}
	}
}
