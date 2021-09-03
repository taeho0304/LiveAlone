# 명세서 기반

# PJT#1

## 목표

Wordcount.java를 수정하여 각 단어의 첫 글자(알파멧)만을 카운트하는 프로그램을 Wordcount1char.java 파일 이름으로 작성

## 입력파일

- 일반 텍스트

## 필요한 함수

- substring(int startIdx, int endIdx)
  - String class의 member method
  - String에서 startIdx에서 시작해 (endIdx-1)까지 문자로 이루어진 String을 리턴합니다.

## 실행방법

1. Projet 디렉토리에서 ant 실행
2. hdfs dfs -rm -f wordcount_test_out
3. hadoop jar ssafy.jar inverted wordcount_test invertedindex_test_out
4. hdfs dfs -cat invertedindex_test_out/part-r-00000 | more
5. hdfs dfs -cat invertedindex_test_out/part-r-00001 | more

## 실행결과

<p align="center">
    <img src="C:\Users\multicampus\Desktop\발표\p1.PNG" alt="p1" style="zoom:60%;" />
    <img src="C:\Users\multicampus\Desktop\발표\p1-2.PNG" alt="p1-2" style="zoom:60%;" />
</p>





# PJT#2

## 목표

- Wordcount.java 를 변형하여 아래와 같은 일을 하도록 Wordcountsort.java 파일 이름으로 작성
  - reducer의 개수를 2개로 설정
  - Word의 첫 글자가 ASCII 코드 순서로 a 보다 앞에 오는 경우 reducer 0 으로 (즉, 결과가 part-r-0000에 찍히도록)
  - 나머지(특수문자 등)는 reducer 2로(part-t-00002)

## 필요한 함수

- toString()
  - String class의 member method
  - Hadoop의 Text 타입을 Java의 String 타입으로 변환

- chatAt(0)
  - String class의 member memthod
  - String에서 첫 번째 character를 리턴

- Partitioner 클래스

  - 셔플링 페이즈에서 출력된 (KEY, VALUE-LIST) 를 KEY를 보고 어느 reducer로 보낼 것 인지를 결정하는 class
  - IntWritable의 경우 reducer개수로 나눈 나머지에 의해서 Text의 경우 0에서 (reducer개수-1)까지의 값을 출력하는
    hash function이 기본으로 내장되어 있습니다.Main 함수에 다음을 추가 합니다.
  - job.setPartitionerClass(MyPartitioner.class);

  

## 실행방법

1. Projet 디렉토리에서 ant 실행
2. hadoop jar ssafy.jar inverted wordcount_test invertedindex_test_out
3. hdfs dfs -cat invertedindex_test_out/part-r-00000 | more
4. hdfs dfs -cat invertedindex_test_out/part-r-00001 | more

## 실행결과

![image-20210903105048812](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20210903105048812.png)

![image-20210903105234529](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20210903105234529.png)

# PJT # 3

## 목표

단어 빈도수 계산 알고리즘을 [Wordcount.java](http://wordcount.java) 를 수정하여 inverted index를 출력하는 MapReduce 알고리즘을 구현

## 입력 파일

- 일반 텍스트

## 출력 파일

- 단어 : <tab>wordcount-data.txt : position, wordcount-data.txt:posion

## 필요한 함수

- StringTokenizer : StringTokenizer(value.toString())
  - 단어 단위로 자르기
- Long : ((LongWritable)key).get()
  - 파일에서 현재 라인의 시작 위치의 byte offset을 가져옴
- filename+":"+p
  - 파일명과 현재 위치를 concatenate
- token.length()
  - String 타입의 variable인 token의 length를 얻으려면 필요함

## 실행 방법

1. Projet 디렉토리에서 ant 실행
2. hadoop jar ssafy.jar inverted wordcount_test invertedindex_test_out
3. hdfs dfs -cat invertedindex_test_out/part-r-00000 | more
4. hdfs dfs -cat invertedindex_test_out/part-r-00001 | more

## 실행 결과

![Untitled](C:\Users\multicampus\Documents\카카오톡 받은 파일\pjt#3\PJT # 3 b20ded1153794961b66822609389de56\Untitled.png)

![Untitled 1](C:\Users\multicampus\Documents\카카오톡 받은 파일\pjt#3\PJT # 3 b20ded1153794961b66822609389de56\Untitled 1.png)

# PJT#4

## 목표

MatrixAdd.template.java라는 Template를 이용하여 matrix addition을 하는 코드를 MatrixAdd.java 파일 이름으로 작성


## 필요한 함수

- String[] split(String delimeter)
  - String Class의 member method
  - String을 delimeter로 분리하여 array에 넣어 리턴
    - e.g. ) String[] arr = tmpStr.split ("\t");


## 입력 파일

A   0   0   3
A   0   1   -5
A   1   0   6
A   1   1   12
B   0   0   2
B   0   1   11
B   1   0   1
B   1   1   -7

## 출력 파일

0   0   5
1   1   5
0   1   6
1   0   7

## 실행 방법

1. Projet 디렉토리에서 ant 실행

2. hdfs dfs -mkdir matadd_test

3. hdfs dfs -put data/matadd-data.txt matadd_test

4. hadoop jar ssafy.jar inverted wordcount_test invertedindex_test_out

5. hdfs dfs -cat invertedindex_test_out/part-r-00000 | more

6. hdfs dfs -cat invertedindex_test_out/part-r-00001 | more

   

## 실행 결과

![p4-1](C:\Users\multicampus\Desktop\발표\p4-1.PNG)

![p4-2](C:\Users\multicampus\Desktop\발표\p4-2.PNG)

