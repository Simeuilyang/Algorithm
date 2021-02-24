# [BOJ]/[17471] 게리맨더링

## *- Bruteforcing -*

* `int[] people` : 각 구역의 인구수 (`1` ~ `N`)
* `ArrayList<Integer>[] map` : 연결된 각 구역을 연결리스트로
* `int total` : 총 인구수
* `int Min` : 두 선거구의 인구수 차이. `Integer.MAX_VALUE`로 초기화

</br>

1. 조합을 이용해 모든 구역을 두 선거구로 나눈다.

   * 이때, 반복되는 조합을 만들지 않도록 `1`개부터 `N/2`개까지의 조합만 생성

   * `void Comb(int N, int r, int start, ArrayList<Integer> A)`

     * `int N` : 원소의 총 개수
     * `int r ` : 조합배열을 완성하기까지 담아야하는 남은 원소의 개수
     * `int start` : 현재의 위치. 담은 원소는 중복해서 담지 않기 위함
     * `ArrayList<Integer> A` : 조합배열

     ```java
     void Comb(int N, int r, int start, ArrayList<Integr> A){
         if(r == 0){
             // 조합을 다 만든 후 처리
         }else{
             for(int i=start; i<=N; i++){
                 A.add(i);
                 Comb(N, r-1, i+1, A); // ✔ r-1, i+1 ✔
                 A.remove(A.indexOf(i));
             }
         }
     }
     ```

2. 조합배열에 속하는 구역들을 선거구 A, 속하지 않는 구역들을 선거구 B로 설정한 후, 각 선거구에 속하는 구역들이 연결되어 있는지 확인한다.

   이때, 다음 처리를 위해 각 선거구의 인구수도 계산해준다.

   * `boolean Checklink(ArrayList<Integer> Arrlist, boolean[] visited)`

     : `Arrlist`에 속한 선거구들이 모두 연결되어있는지 확인하는 함수.

     * ***BFS*** 사용

       * `Arrlist`에 속한 선거구만 큐에 넣고, 하나씩 빼서 그 원소와 연결된 선거구를 확인하며 반복한다
       * 확인한 선거구는 `visited[i] = true`

     * 탐색 후, `visited`가 모두 `true`라면(= 선거구에 속하는 모든 구역이 연결되어 있다면) `return true`

       `false`가 하나라도 있다면(= 선거구에 속하는 구역 중 연결되지 않은 구역이 있다면) `return false`

3. 두 선거구 각각 모두 연결되어 있다면, 두 선거구의 인구수 차이와 `Min`값 중 작은 값을 `Min`값으로 설정한다.

</br>

## :speaking_head:

* 너무 오랜만에 알고리즘 문제를 풀어서 그런지 조합을 어떻게 구현했는지 생각이 안나서 이전에 풀었던 것을 참고하여서 풀었다. 이해하면서 암기하쟈!