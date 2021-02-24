import java.io.*;
public class _2751 {
	static int[] sorted;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] list = new int[N];
		sorted = new int[N];
		for(int i=0; i<N; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		merge_sort(list, 0, N-1);
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<N; i++) {
			if(i!=(N-1))
				sb.append(list[i]+"\n");
			else
				sb.append(list[i]);
		}
		System.out.print(sb.toString());
	}
	
	private static void merge(int[] list, int left, int mid, int right) {
		int l_idx = left;
		int r_idx = mid+1;
		int s_idx = left; // sorted[]�� �ε���
		
		while(l_idx<=mid && r_idx<=right) {
			if(list[l_idx] <= list[r_idx])
				sorted[s_idx++] = list[l_idx++];
			else
				sorted[s_idx++] = list[r_idx++];
		}
		
		if(l_idx>mid) { // ���� ����Ʈ�� �� �Ű�ٸ�
			for(int i=r_idx; i<=right; i++)
				sorted[s_idx++] = list[i];
		}else { // ������ ����Ʈ�� �� �Ű�ٸ�
			for(int i=l_idx; i<=mid; i++) 
				sorted[s_idx++] = list[i];
		}
		
		for(int i=left; i<=right; i++)
			list[i] = sorted[i];
	}
	
	private static void merge_sort(int[] list, int left, int right) {
		if(left < right) {
			int mid = (left+right)/2; // �߰���
			merge_sort(list, left, mid);
			merge_sort(list, mid+1, right);
			merge(list, left, mid, right);
		}
	}
}
