import java.io.*;
import java.util.*;

public class _7662 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String[] s;
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			if(t != 0)	sb.append("\n");
			int N = Integer.parseInt(br.readLine());
			DeletableMaxHeap maxheap = new DeletableMaxHeap();
			DeletableMinHeap minheap = new DeletableMinHeap();
			for(int i=0; i<N; i++) {
				s = br.readLine().split(" ");
				int num = Integer.parseInt(s[1]);
				if(s[0].equals("I")) {
					maxheap.Add(num);
					minheap.Add(num);
				}else { // "D"
					int deleteNum;
					if(num == 1) { // �ִ� ����
						if(maxheap.data.Count() == maxheap.delete.Count())
							continue;
						else 
							deleteNum = maxheap.Top();
						
						maxheap.Remove(deleteNum);
						minheap.Remove(deleteNum);
						
					}else{ // num==-1 // �ּڰ� ����
						if(minheap.data.Count() == minheap.delete.Count())
							continue;
						else
							deleteNum = minheap.Top();
						
						maxheap.Remove(deleteNum);
						minheap.Remove(deleteNum);
					}
				}
			}
			
			if(maxheap.data.Count() != maxheap.delete.Count()) {
				int max = maxheap.Top();
				int min = minheap.Top();
				sb.append(max + " " + min);
			}else { //data�� delete�� ũ�Ⱑ ���ٴ� �� -> heap�� ���� ���Ұ� ���� �� => �����!!
				sb.append("EMPTY");
			}
			
		}
		System.out.print(sb.toString());
	}
	
	private static class DeletableMaxHeap{
		private MaxHeap data;
		private MaxHeap delete;
		
		DeletableMaxHeap(){
			data = new MaxHeap();
			delete = new MaxHeap();
		}
		
		public void Add(int x) {
			data.insert(x);
		}
		
		public void Remove(int x) {
			delete.insert(x);
		}
		
		public int Top() {
			Adjust();
			return data.Top();
		}
		
		void Adjust() {
			while(delete.Count()>0 && data.Top()==delete.Top()) {
				data.delete();
				delete.delete();
			}
		}
	}
	
	private static class DeletableMinHeap{
		private MinHeap data;
		private MinHeap delete;
		
		DeletableMinHeap() {
			data = new MinHeap();
			delete = new MinHeap();
		}
		
		public void Add(int x) {
			data.insert(x);
		}
		
		public void Remove(int x) {
			delete.insert(x);
		}
		
		public int Top() {
			Adjust();
			return data.Top();
		}
		
		void Adjust() {
			while(delete.Count()>0 && data.Top() == delete.Top()) {
				data.delete();
				delete.delete();
			}
		}
	}
	
	private static class MaxHeap{
		private ArrayList<Integer> heap;
		
		MaxHeap(){
			heap = new ArrayList<>();
			heap.add(0); // idx 1���� �����ϱ� ����
		}
		
		public int Top() {
			return heap.get(1);
		}
		
		public int Count() {
			return heap.size() - 1;
		}
		
		public void insert(int val) {
			heap.add(val); // �ǵڿ� �ְ�
			int p = heap.size() - 1; // ���� ���� �ڸ�
			
			while(p>1 && heap.get(p/2)<heap.get(p)) { // �ڽĳ�尡 �� ũ��
				int tmp = heap.get(p);
				heap.set(p, heap.get(p/2));
				heap.set(p/2, tmp);
				
				p = p/2;
			}
		}
		
		public int delete() {
			if(heap.size()-1 < 1)
				return -1;
			
			int deleteItem = heap.get(1);
			
			heap.set(1, heap.get(heap.size()-1));
			heap.remove(heap.size()-1);
			
			int pos = 1;
			while(pos*2 < heap.size()) { // �ڽĳ�尡 ���� ������
				int max = heap.get(pos*2);
				int maxpos = pos*2;
				
				if((pos*2+1)<heap.size() && max<heap.get(pos*2+1)) { // ������ �ڽĳ�尡 �� ũ�� ��ȯ
					max = heap.get(pos*2+1);
					maxpos = pos*2 + 1;
				}
				
				if(heap.get(pos) >= max) // �θ��尡 �� ũ�� �׸�
					break;
				
				// �׷��� ������ ��ȯ���ش�
				int tmp = heap.get(pos);
				heap.set(pos, heap.get(maxpos));
				heap.set(maxpos, tmp);
				
				pos = maxpos;
			}
			
			return deleteItem;
		}
	}
	private static class MinHeap{
		private ArrayList<Integer> heap;
		
		MinHeap(){
			heap = new ArrayList<>();
			heap.add(0); // idx 1���� �����ϱ� ����
		}
		
		public int Top() { // �ּҰ�
			return heap.get(1);
		}
		
		public int Count() {
			return heap.size()-1;
		}
		
		public void insert(int val) {
			heap.add(val); // �켱 �� �ڿ� �ְ�
			int p = heap.size() -1; // ���� ���� �ڸ�
			
			while(p>1 && heap.get(p/2)>heap.get(p)) { // root��� ������ & �θ� ��庸�� �ڽĳ�尡 �� ������
				int tmp = heap.get(p/2);
				heap.set(p/2, heap.get(p));
				heap.set(p, tmp);
				
				p = p/2; // �θ� ��� �ڸ���
			}
		}
		
		public int delete() {
			if(heap.size()-1 < 1) { // �ϳ��� ���� ��
				return 0;
			}
			
			int deleteItem = heap.get(1); // ������ ���� ��Ʈ ���
			
			heap.set(1, heap.get(heap.size()-1)); // root�� ���� ������ �� �ְ�
			heap.remove(heap.size()-1); // ������ �� ����
			
			int pos = 1; // �ű� �� ��ġ -> ��Ʈ��� 1
			while(pos*2 < heap.size()) {
				int min = heap.get(pos * 2); // ���� �ڽĳ�� ��
				int minpos = pos*2; // ���� �ڽĳ�� ��ġ
				
				if((pos*2+1)<heap.size() && min>heap.get(pos*2+1)) { // ������ �ڽĳ�尡 �����ϰ�, �� ���� ���� �ڽĳ�庸�� ������
					min = heap.get(pos*2+1);
					minpos = pos*2 + 1;	 //������ �ڽĳ��� ����
				}
				
				if(heap.get(pos) <= min) // ���� ��尡 �ڽĳ�尪���� ������ �׸�
					break;
				
				//�׷��� ������ ���� ���� �ڽĳ�� ��ȯ
				int tmp = heap.get(pos);
				heap.set(pos, heap.get(minpos));
				heap.set(minpos, tmp);
				
				pos = minpos; // �� �ڽĳ�带 �θ�븣��
			}
			
			return deleteItem;
		}
	}
}
