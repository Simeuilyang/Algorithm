# [BOJ]/[2615] 오목

## - Simulation , BFS -

* ```java
  static int[][] dx = {{0, 0}, {-1, 1}, {-1, 1}, {1, -1}};
  static int[][] dy = {{1, -1}, {0, 0}, {1, -1}, {1, -1}};
  ```

  가로(`0`), 세로(`1`), 대각선 /(`2`), 대각선 \ (`3`)각 방향대로 확인해주기 위해 `dx`, `dy`를 위처럼 설정한다.

* ***`in Main`*** 

  `map`을 확인하며 검은 돌(`1`)이나 흰 돌(`0`)이 있다면 `q`에 `add`하고 `Checkwin(map, q)`을 호출한다. 

* **`private static int Checkwin(int[][] map, Queue<int[]> q) `** 

  : `q`에 들어있는 돌 주위로 같은 돌이 5개있는지 각 방향별로 확인하는 함수이다.

  * 우선, 처음에 `q`에 담겨있는 돌의 위치(`int x,y`)와, 돌의 색깔(`int color`)을 따로 저장한다.

  * 마지막에 승부가 났다면 가장 왼쪽의 위쪽에 있는 돌의 좌표를 출력해야하므로 주변에 같은 색의 돌이 있다면 `checkloc`리스트에 `add`해준다.

  * :star:각 방향(`d`)을 확인해줄 때마다 `boolean[][] visited`를 초기화해줘야한다.

    (이전 방향에서의 방문 여부는 다른 방향에서 확인할 때 영향을 주지 않기 때문이다.)

  * `q`의 크기가 `0`이 되기 전까지 주변 같은 돌을 찾아 `checkloc`에 `add`해주고, `q.size()==0`이라면 `checkloc.size()`를 확인한다.

    -> `checkloc.sie()==5`라면 승부가 난 것!

    ​	이 때, `checkloc`를 가장왼쪽, 가장위쪽에 있는 좌표가 가장 작은 값이 되도록 sorting한다.

    > **:star: ArrayList<int[]> Sorting**
    >
    > ```java
    > import java.util.Collections;
    > import java.util.Comparator;
    > 
    > ...
    > Collections.sort(ArrayList, new sortingclass());
    > ...
    >     
    > static class sortingclass() implements Comparator<int[]>{
    >     @Override
    >     public int compare(int[] o1, int[] o2){
    >         //o1이 크면 return 1, o2가 크면 return -1, 같으면 return 0
    > 		//아래 조건문은 이 문제에 해당되는 조건이다.
    >         if(o1[1] > o2[1]) {
    > 			return 1;
    > 		}else if(o1[1] < o2[1]){
    > 			return -1;
    > 		}else { //행이 같을 때
    > 			if(o1[0] > o2[0])
    > 				return 1;
    > 			else
    > 				return -1;
    > 		}
    >     }
    > }
    > ```

  * sorting후, `checkloc.get(0)`의 좌표와 그에 해당하는 돌의 색깔을 출력해주고 프로그램을 종료한다.

