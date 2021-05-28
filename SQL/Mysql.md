# [Mysql]

1. **정렬**

   * 오름차순

     ```mysql
     ORDER BY 컬럼명 (ASC);
     ```

   * 내림차순 (역순)

     ```mysql
     ORDER BY 컬럼명 DESC;
     ```

   * 여러 컬럼으로 정렬

     ```mysql
     ORDER BY 컬럼1 [, 컬럼2, 컬럼3, ...];
     ```

   * 여러 기준으로 정렬

     ```mysql
     ORDER BY 컬럼1 ASC|DESC, 컬럼 2 ASC|DESC, ...;
     ```

     컬럼1로 해당 조건에 맞게 정렬되고, 컬럼1 조건이 같다면, 컬럼 2조건으로 정렬, ...

     

2. **`WHERE`절 `VARCHAR`타입 비교**

   ```mysql
   WHERE 컬럼명 = "TEST"; -- 부등호 하나 '=', 비교대상은 큰 따옴표
   ```

   ```mysql
   WHERE 컬럼명 != "TEST"; -- 다를 경우에는 '!=, 비교대상은 큰 따옴표
   ```

3. **상위 N개 레코드 출력**

   ```mysql
   ORDER BY ~~ -- ORDER BY 뒤에 사용
   LIMIT N; -- 쿼리 결과의 N개까지만 출력한다.
   ```

   ```mysql
   LIMIT A, B;
   ```

   두 개의 정수가 들어간다면, `A`는 시작할 레코드의 번호, `B`는 반환할 결과의 수가 된다.

   예) 20위에서 30위까지 보고싶다면, `LIMIT 19, 10` (인덱스 `0`부터니까 `19`)

   

4. **데이터 개수 구하기 - `COUNT`**

   `COUNT`는 테이블의 컬럼의 데이터 갯수를 가져온다. **이 때, `NULL`인 데이터는 제외하고 계산한다.**

   ```mysql
   SELECT COUNT(*) -- 전체 행 개수
   ```

   ```mysql
   SELECT COUNT(컬럼명) -- 컬럼 데이터 개수
   ```

   :heavy_plus_sign: 중복된 값을 겹치지 않게 개수 구하기 - `DISTINCT` 와 `GROUP BY`

   `COUNT`시에 `GROUP BY`는 각각의 개수를, `DISTINCT`는 겹치지 않는 값들 전체의 개수를 출력하다.

   * 동일 컬럼명 당 개수를 출력

   ```mysql
   SELECT 컬럼명, COUNT(컬럼명)
   FROM 테이블명
   GROUP BY 컬럼명;
   ```

   * 중복 제거된 전체의 개수를 출력

   ```mysql
   SELECT COUNT(DISTINCT 컬럼명)
   FROM 테이블명;
   ```

    

5. **SQL 문법 순서와 실행 순서**

   **문법 순서**

   ```mysql
   SELECT ~
   FROM ~
   WHERE ~
   GROUP BY ~
   HAVING ~
   ORDER BY ~
   ```

   * `GROUP BY` 다음 `ORDER BY` !!

   **실행 순서**

   1. 조회 테이블 확인 (`FROM`)
   2. 데이터 추출 조건 확인 (`WHERE`)
   3. 컬럼 그룹화 (`GROUP BY`)
   4. 그룹화 조건 (`HAVING`)
   5. 데이터 추출 (`SELECT`)
   6. 데이터 순서 정렬 (`ORDER BY`)



6. **:star: Mysql 주의**

   서브쿼리를 명시적으로 임의의 이름으로 지정해주어야 한다.

   예)

   ```mysql
   SELECT NAME, CNT
   FROM (SELECT NAME, COUNT(NAME) CNT
       FROM ANIMAL_INS
       GROUP BY NAME) A -- 이와 같이 
   WHERE CNT >= 2
   ORDER BY NAME;
   ```



7. **날짜 데이터 `DATETIME`에서 일부만 추출하기**

   `YEAR` : 연도 추출

   `MONTH` : 월 추출

   `DAY` : 일 추출

   `HOUR` : 시 추출

   `MINUTE` : 분 추출

   `SECOND` : 초 추출

   ```mysql
   -- 예시
   SELECT HOUR(기준 날짜);
   ```

   

8. **별칭 Alias**

   `AS` 명령어로 별칭을 붙일 수 있다. (생략 가능)

   * 별칭이 ASCII가 아닐 경우 쌍 따옴표를 붙여야 한다.

   * **`WHERE`에서 별칭을 사용하면 에러가 발생한다.**

     => `SELECT` 쿼리의 명령어 순서는 `WHERE` => `SELECT`이므로 에러가 발생하게 된다.

   * `ORDER BY`는 마지막에 처리되므로 별칭 사용이 가능하다.



9. **NULL 체크**

   ```mysql
   WHERE 컬럼명 IS NULL;
   ```

   ```mysql
   WHERE 컬럼명 IS NOT NULL;
   ```

   :heavy_plus_sign: `IFNULL()`

   ```mysql
   SELECT IFNULL(A, B"TEST")
   ...
   ```

   * 컬럼 `A`가 `NULL`이면, `B`를 출력, `NULL`이 아니면 `A`값을 출력한다.



10. **`IN`, `NOT IN` / `EXISTS`, `NOT EXISTS`**

    1) `IN`

    ```mysql
    SELECT * FROM A
    WHERE A.number IN (SELECT B.number FROM B);
    ```

    * 작동 원리

      `IN`뒤에 있는 괄호의 서브쿼리를 먼저 실행 (`B` 테이블에 제일 먼저 접근해 `B.number`를 리스트로 `IN`이하에 뿌려준다.)

      -> 이후 `A` 테이블에서 하나의 레코드를 가져와 `A`의 `number`값이 앞서 가져온 `IN`이하의 요소들에 포함되어 있는지 확인

      -> 포함되어 있다면 해당 레코드를 출력

    2) `EXISTS`

    ```mysql
    SELECT * FROM A
    WHERE EXISTS (SELECT B.number 
                  FROM B
                  WHERE A.number = B.number);
    ```

    * 작동 원리

      먼저 `A` 테이블에 접근해 하나의 레코드를 가져오고, 해당 레코드에 대해서 `EXISTS` 이하의 서브쿼리를 실행해 ***서브쿼리에 대한 결과가 존재하는지*** 확인

      -> 존재하면 출력

      -> 존재하지 않으면 출력하지 않는다.

    3) `NOT IN`

    ```mysql
    SELECT *
    FROM A
    WHERE A.number NOT IN (SELECT B.number
                          FROM B
                          WHERE B.number IS NOT NULL);
    ```

    * 작동 원리

      우선 `NOT IN`이하의 서브 쿼리를 수행 (`B`로부터 그 결과가 리스트로 반환됨)

      -> `A`에서 하나의 레코드씩 가져와 소괄호의 요소들에 포함되지 않아야 결과로 반환

      -> 포함되면 반환되지 않는다.

    * :star: 이때, 만약 서브 쿼리의 결과에 `NULL`이 포함되어있으면, 원하는 결과값이 나오지 않을 수도 있다.

      => `NOT IN`은 레코드를 하나씩 가져와서 서브쿼리 결과와 하나씩 비교하는데, 이때 서브쿼리 결과의 `NULL`과 비교하게 되면 `UNKNOWN`값을 반환하게 된다. 따라서 조건이 `True`가 아니므로 결과가 반환되지 않게 된다. (`NULL`과의 비교연산은 항상 `UNKNOWN`값을 반환한다. => 그래서 `IS NULL`이나 `IS NOT NULL`을 사용해야 함)

      => 원하는 값을 반환하기 위해서는 서브쿼리 결과값에 `NULL`이 포함되지 않도록 처리해주면 된다.

    4) `NOT EXISTS`

    ```mysql
    SELECT *
    FROM A
    WHERE NOT EXISTS (SELECT B.number
                      FROM B
                      WHERE A.number = B.number);
    ```

    * `EXISTS`는 `A`의 레코드를 하나씩 가져와 그것을 가지고 서브쿼리를 수행한 후 결과값이 존재하면 반환하는 방식이었으므로,

      `NOT EXISTS`는 `A`의 레코드를 하나씩 가져와 그것을 가지고 서브쿼리를 수행한 결과값이 존재하지 않으면 결과를 반환하게 된다.

    * 여기서 또한 `NULL`값에 대한 비교연산은 `UNKNOWN`이 반환되므로,

      `A` 테이블의 `number`가 `NULL`이라면, 출력되게 된다.

       

11. **특정 문자 포함 검색 - `LIKE`**

    ```mysql
    SELECT 컬럼명 FROM 테이블명 WHERE 컬럼명 LIKE '특정문자열%';
    SELECT 컬럼명 FROM 테이블명 WHERE 컬럼명 LIKE '%특정문자열';
    SELECT 컬럼명 FROM 테이블명 WHERE 컬럼명 LIKE '%특정문자열%';
    ```

    * 여러 개의 특정 문자를 포함하는 데이터를 검색할 때에는 `OR`연산자를 사용한다.

    **:heavy_plus_sign: 대소문자 구문없이 특정 문자 포함 검색 - `UPPER`, `LOWER`**

    ```mysql
    WHERE UPPER(컬럼명) LIKE '%특정문자열%';
    ```

    ```mysql
    WHERE LOWER(컬럼명) LIKE '%특정문자열%';
    ```

     

12. **`CASE`문 - 데이터값에 따라 다른 결과를 반환해야할 때 사용**

    ```mysql
    SELECT CASE WHEN 컬럼명 조건 [OR 조건 | AND 조건 | ...]
    		THEN 조건 만족 시 반환값
    		ELSE 조건 불만족 시 반환값
    		END AS 별칭
    FROM 테이블명
    ...;
    ```

    `CASE WHEN` .. `THEN` .. `ELSE` .. `END`문을 사용한다.

     

13. **두 날짜간의 차이를 가져올 때**

* `DATEDIFF` : 단순히 일 차이

* `TIMESTAMPDIFF` : 연, 분기, 월, 주, 일, 시, 분, 초를 지정하여 가져올 때

  ```mysql
  DATEDIFF(날짜1, 날짜2);
  ```

  간단히 `날짜1 - 날짜2`이다.

  ```mysql
  TIMESTAMPDIFF(단위, 날짜1, 날짜2);
  ```

  단위: `SECOND`, `MINUTE`, `HOUR`, `DAY`, `WEEK`, `MONTH`, `QUARTER`, `YEAR`



14. **날짜를 지정한 형식으로 출력**

    ```mysql
    DATE_FORMAT(날짜, 형식)
    ```

    `%Y`: 4자리 년도 / `%y` : 2자리 년도

    `%M`: 긴 월(영문) / `%m` : 숫자 월 (두 자리) / `%c` : 숫자 월(한자리는 한 자리)

    `%d` : 일자 (두자리) / `%e` : 일자(한자리는 한 자리)

    `%W` : 긴 요일 / `%a` : 짧은 요일

    `%I` : 시간 (12시간 기준) / `%H` : 시간 ( 24시간 기준)

    `%i` : 분

    `%S` : 초