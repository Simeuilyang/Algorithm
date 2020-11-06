//문자열
//LCS: 공통 부분 수열 -> 연속하지 않아도 된다. 순서는 동일해야함
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class _9252 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s1 = br.readLine().split("");
		String[] s2 = br.readLine().split("");
		int[][] LCS = new int[s2.length+1][s1.length+1];
		int result = 0;
		ArrayList<String> t = new ArrayList<>();
		
		for(int i=0; i<s2.length+1; i++) {
			for(int j=0; j<s1.length+1; j++) {
				if(i==0 || j==0)
					LCS[i][j] = 0;
				else if(s1[j-1].equals(s2[i-1])) {
					LCS[i][j] = LCS[i-1][j-1] + 1;
					
				}else {
					LCS[i][j] = Math.max(LCS[i][j-1], LCS[i-1][j]);
				}
				result = Math.max(result, LCS[i][j]);
			}	
		}
		
		System.out.println(result);
		
		int[] find = new int[] {s2.length, s1.length};
		while(find[0]!=0 && find[1]!=0) {
			if(LCS[find[0]][find[1]-1] == LCS[find[0]][find[1]]) {
				find[1] -= 1;
			}else if(LCS[find[0]-1][find[1]] == LCS[find[0]][find[1]]) {
				find[0] -= 1;
			}else if(LCS[find[0]][find[1]-1]!=LCS[find[0]][find[1]] && LCS[find[0]-1][find[1]] != LCS[find[0]][find[1]]) {
				t.add(s2[find[0]-1]);
				find[0] -= 1;
				find[1] -= 1;				
			}
		}
		
		for(int i=t.size()-1; i>=0; i--) {
			System.out.print(t.get(i));
		}

	}

}
