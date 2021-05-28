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

    

5. SQL 문법 순서와 실행 순서

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



6. :star: Mysql 주의

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



7. 날짜 데이터 `DATETIME`에서 일부만 추출하기

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

   

8. 별칭 Alias

   `AS` 명령어로 별칭을 붙일 수 있다. (생략 가능)

   * 별칭이 ASCII가 아닐 경우 쌍 따옴표를 붙여야 한다.

   * **`WHERE`에서 별칭을 사용하면 에러가 발생한다.**

     => `SELECT` 쿼리의 명령어 순서는 `WHERE` => `SELECT`이므로 에러가 발생하게 된다.

   * `ORDER BY`는 마지막에 처리되므로 별칭 사용이 가능하다.