import java.io.*;
import java.util.*;

public class _1620 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		
		Map<Integer, String> keyNum = new HashMap<>();
		Map<String, Integer> keyStr = new HashMap<>();
		
		for(int i=1; i<=N; i++) {
			String name = br.readLine();
			keyNum.put(i, name);
			keyStr.put(name, i);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			String q = br.readLine();
			if(i!=0)	sb.append("\n");
			
			if(Character.isDigit(q.charAt(0))) { // 숫자일 경우
				sb.append(keyNum.get(Integer.parseInt(q)));
			}else { // 문자일 경우
				sb.append(keyStr.get(q));
			}
		}
		
		System.out.print(sb.toString());
	}

}
