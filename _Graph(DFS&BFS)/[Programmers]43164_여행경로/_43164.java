import java.util.*;
public class _43164 {

	public static void main(String[] args) {
//		String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
//		String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
		String[][] tickets = {{"ICN","A"},{"A","B"},{"A","C"},{"C","A"},{"B","D"}};
		String[] ans = solution(tickets);
		for(String a : ans)	System.out.print(a + " ");
	}
	
	private static String[] solution(String[][] tickets) {      
		int N = tickets.length; // 티켓 총 개수
		Map<String, ArrayList<Ticket>> adj = new HashMap<>();
		for(int i=0; i<N; i++) {
			String start = tickets[i][0];
			ArrayList<Ticket> tmp;
			if(adj.containsKey(start))	tmp = adj.get(start);	
			else	tmp = new ArrayList<>();
			
			tmp.add(new Ticket(i, tickets[i][1]));
			adj.put(start, tmp);
		}
		
		for(String start : adj.keySet()) {
			Collections.sort(adj.get(start));
		}
		
		boolean[] used = new boolean[N]; // 티켓 사용 여부
		ArrayList<String> res = new ArrayList<>();
		res.add("ICN");
		dfs("ICN", res, used, adj);
		
		String[] answer = new String[res.size()];
		for(int i=0; i<answer.length; i++)
			answer[i] = res.get(i);
		return answer;
    }
	
	private static boolean dfs(String now, ArrayList<String> res, boolean[] used, Map<String, ArrayList<Ticket>> adj) {
		ArrayList<Ticket> tmp = adj.get(now);
		if(tmp == null) {
			if(ChkAllUsed(used)) { // 모두 사용했으면
				return true;
			}
			return false;
		}
		
		for(Ticket next : tmp) {
			if(!used[next.idx]) {
				res.add(next.dest);
				used[next.idx] = true;
				boolean flag = dfs(next.dest, res, used, adj);
				if(flag)	return true;
				res.remove(res.size()-1);
				used[next.idx] = false;
			}
		}
		
		if(ChkAllUsed(used))	return true;
		else	return false;
	}
	
	private static boolean ChkAllUsed(boolean[] used) {
		for(int i=0; i<used.length; i++)
			if(!used[i])	return false;
		return true;
	}
	
	private static class Ticket implements Comparable<Ticket>{
		int idx;
		String dest;
		
		Ticket(int idx, String dest) {
			this.idx = idx;
			this.dest = dest;
		}
		
		@Override
		public int compareTo(Ticket t) {
			return this.dest.compareTo(t.dest); // 오름차순
		}
		
		public String toString() {
			return dest;
		}
	}
}
