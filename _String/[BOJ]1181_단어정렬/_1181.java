import java.io.*;
import java.util.*;

public class _1181 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<String> chk = new ArrayList<>();
		PriorityQueue<Str> pq = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			if(!chk.contains(s)) { // ���ٸ�
				chk.add(s);
				pq.add(new Str(s, s.length()));
			}
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(pq.poll().str);
		while(!pq.isEmpty()) {
			sb.append("\n"+pq.poll().str);
		}
		
		System.out.print(sb.toString());
	}

	private static class Str implements Comparable<Str>{
		String str;
		int len;
		
		Str(String str, int len){
			this.str = str;
			this.len = len;
		}
		
		@Override
		public int compareTo(Str s) {
			if(this.len > s.len)
				return 1;
			else if(this.len < s.len)
				return -1;
			else { // ���̰� ������
				return this.str.compareTo(s.str);
				/*
				 *  �� ���ڿ��� ���� ������ ���ٸ� 0�� ��ȯ
				 * ��� ���ڿ��� �Ű������� ���� ���ڿ����� ���� ������ �ռ� ��� "����"�� ��ȯ
				 * ��� ���ڿ��� �Ű������� ���� ���ڿ����� ���� ������ ���� ��� "���"�� ��ȯ 
				 *
				 */
			}
		}
	}
}
