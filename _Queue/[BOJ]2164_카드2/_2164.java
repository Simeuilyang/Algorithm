import java.util.*;

public class _2164 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		System.out.print(Solution(N));
		sc.close();
	}
	
	private static int Solution(int N) {
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=N; i++)
			q.add(i);
		
		while(q.size() != 1) {
			q.remove(); // �� �� ī�� ����
			if(q.size()==1)		break;
			
			int head = q.poll(); // ���� ī�� 
			q.add(head);	// �� �Ʒ���
		}
		
		return q.poll();
	}
}
