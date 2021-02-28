# [BOJ]/[7662] 이중 우선순위 큐

## *- Heap -*

* 처음에는 Java에 있는 `PriorityQueue` 두 개를 사용하였다. 
  * 하나는 작은 숫자를 우선순위로, 하나는 큰 숫자를 우선순위로 하는 `pq`를 생성하였다.
  * `I`일 때는 두 `pq`에 모두 `add()`해주었다.
  * `D`일 때는 만약 최대값을 삭제하는 연산일 때에는 큰 숫자를 우선순위로 하는 `pq`에서 `poll()`한 값을 작은 숫자를 우선순위로 하는 `pq`에서 찾아서 `remove()`해주었고, 최소값을 삭제하는 연산일 때에도 마찬가지로 처리해주었다.
    * 하지만, 이렇게 구현하면 해당하는 숫자를 찾는데 시간이 `O(N)`만큼 소요되고, 또 연산의 개수 `k`의 범위가 `k <= 1,000,000`으로 상당히 크기 때문에 *시간초과* 가 나게 된다.

</br>

* 그래서 힙 두개를 가지는 새로운 `class`객체를 생성해야한다! 

* `MaxHeap`을 예로 들면,

  * 우선, `MaxHeap`을 두 개 가진 객체를 생성한다. (`class DeletableMaxHeap`)

    * 하나는 삽입되는 데이터를, 하나는 삭제되는 데이터를 저장하는 maxheap이다.

      ```java
      private MaxHeap data; // 삽입 데이터 저장
      private MaxHeap delete; // 삭제 데이터 저장
      // MaxHeap: 직접 생성한 class 객체. 내재되어있는 PriorityQueue를 사용해도 될 것 같다. 
      ```

  * 삽입되는 데이터를 저장하기 위한 메소드를 생성한다.

    ```java
    public void Add(int x){
    	data.insert(x); // 삽입되는 데이터는 data에 insert()
    }
    ```

  * 삭제되는 데이터를 저장하기 위한 메소드를 생성한다.

    ```java
    public void Remove(int x){
        delete.insert(x); // 삭제되는 데이터는 delete에 insert()
    }
    ```

  * Root 노드의 값을 가져오기 위한 메소드를 생성한다. 

    ```java
    public int Top(){
        Adjust(); 
        return data.Top();
    }
    ```

    * `Adjust()`는 삽입된 데이터 `data`와 삭제된 데이터 `delete`를 조정하여 `data`에 현재 최대값이 root에 오도록 한다.

      `delete`에 데이터가 있고, `data`의 root와 `delete`의 root가 같으면 그 두 데이터를 지운다. 삭제할 데이터가 없거나 (`delete.Count()==0`), 둘의 root가 다를 때 까지 반복한다. 

      ```java
      void Adjust(){
          while(delete.Count()>0 && data.Top()==delete.Top()){
              data.delete();
              delete.delete();
          }
      }
      ```

</br>

* 추가로, `D 1` 이나 `D -1`을 입력받았을 때 지울 데이터, 즉 `maxheap.Top()`이나 `minheap.Top()`으로 얻어온 데이터를 각 heap의 `delete` heap에 추가해주는데,
  * 만약 `data`의 개수와 `delete`의 개수가 같다면 사실 현재 상태는 pq에 데이터가 아무것도 없는 상태이므로 이 연산을 무시한다.
* 입력받은 연산을 모두 마치면, 최대값은 `maxheap`의 `Top()`으로, 최소값은 `minheap`의 `Top()`으로 값을 가져온다.
  * 이때도 또한, `data`의 개수와 `delete`의 개수가 같다면 pq가 비어있는 것으로 `"EMPTY"`를 출력하도록 한다.

## :speaking_head:

* 처음에 시도한 방법으로 하면 삽입은 `O(log n)`으로 가능하지만, 검색 후 삭제하는 데에 `O(N)`이 걸리기 때문에 시간이 오래 걸리게 된다.
* 두 번째 방법으로 하면 삽입도 `O(log n)`으로, 삭제도 heap에 추가해준 후 양쪽 힙에서 `Top`을 삭제해주므로 `O(log n)`으로 가능하게 된다. 