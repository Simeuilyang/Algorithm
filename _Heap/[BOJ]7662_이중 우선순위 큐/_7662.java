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
					if(num == 1) { // 최댓값 삭제
						if(maxheap.data.Count() == maxheap.delete.Count())
							continue;
						else 
							deleteNum = maxheap.Top();
						
						maxheap.Remove(deleteNum);
						minheap.Remove(deleteNum);
						
					}else{ // num==-1 // 최솟값 삭제
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
			}else { //data와 delete의 크기가 같다는 것 -> heap에 남은 원소가 없는 것 => 비었다!!
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
			heap.add(0); // idx 1부터 시작하기 위함
		}
		
		public int Top() {
			return heap.get(1);
		}
		
		public int Count() {
			return heap.size() - 1;
		}
		
		public void insert(int val) {
			heap.add(val); // 맨뒤에 넣고
			int p = heap.size() - 1; // 넣은 원소 자리
			
			while(p>1 && heap.get(p/2)<heap.get(p)) { // 자식노드가 더 크면
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
			while(pos*2 < heap.size()) { // 자식노드가 있을 때까지
				int max = heap.get(pos*2);
				int maxpos = pos*2;
				
				if((pos*2+1)<heap.size() && max<heap.get(pos*2+1)) { // 오른쪽 자식노드가 더 크면 교환
					max = heap.get(pos*2+1);
					maxpos = pos*2 + 1;
				}
				
				if(heap.get(pos) >= max) // 부모노드가 더 크면 그만
					break;
				
				// 그렇지 않으면 교환해준다
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
			heap.add(0); // idx 1부터 시작하기 위해
		}
		
		public int Top() { // 최소값
			return heap.get(1);
		}
		
		public int Count() {
			return heap.size()-1;
		}
		
		public void insert(int val) {
			heap.add(val); // 우선 맨 뒤에 넣고
			int p = heap.size() -1; // 넣은 원소 자리
			
			while(p>1 && heap.get(p/2)>heap.get(p)) { // root노드 전까지 & 부모 노드보다 자식노드가 더 작으면
				int tmp = heap.get(p/2);
				heap.set(p/2, heap.get(p));
				heap.set(p, tmp);
				
				p = p/2; // 부모 노드 자리로
			}
		}
		
		public int delete() {
			if(heap.size()-1 < 1) { // 하나도 없는 것
				return 0;
			}
			
			int deleteItem = heap.get(1); // 삭제할 노드는 루트 노드
			
			heap.set(1, heap.get(heap.size()-1)); // root에 가장 마지막 값 넣고
			heap.remove(heap.size()-1); // 마지막 값 삭제
			
			int pos = 1; // 옮긴 값 위치 -> 루트노드 1
			while(pos*2 < heap.size()) {
				int min = heap.get(pos * 2); // 왼쪽 자식노드 값
				int minpos = pos*2; // 왼쪽 자식노드 위치
				
				if((pos*2+1)<heap.size() && min>heap.get(pos*2+1)) { // 오른쪽 자식노드가 존재하고, 그 값이 왼쪽 자식노드보다 작으면
					min = heap.get(pos*2+1);
					minpos = pos*2 + 1;	 //오른쪽 자식노드로 변경
				}
				
				if(heap.get(pos) <= min) // 현재 노드가 자식노드값보다 작으면 그만
					break;
				
				//그렇지 않으면 현재 노드와 자식노드 교환
				int tmp = heap.get(pos);
				heap.set(pos, heap.get(minpos));
				heap.set(minpos, tmp);
				
				pos = minpos; // 그 자식노드를 부모노르로
			}
			
			return deleteItem;
		}
	}
}
