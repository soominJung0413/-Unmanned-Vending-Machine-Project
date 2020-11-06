    CREATE TABLE PRODUCT (
    PRODID NUMBER(5),           --제품번호
    NAME VARCHAR2(100),         --상품명
    KIND NUMBER(2),             --상품분류 샌드위치/샐러드/음료
    PRICE NUMBER(10),           --가격
    OLDPRICE NUMBER(10),        --이전가격
    COST NUMBER(10),            --원가
    CONTENT VARCHAR2(100),      --추가내용
    STOCK NUMBER(3)             --재고수량 
);

INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (1,'햄에그샌드위치',10,3000,2900,2000,'햄에그햄에그',100);
INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (2,'아보카도샌드위치',10,3500,3000,2500,'자연의버터',100);

INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (3,'에그샐러드',20,2900,2500,2000,'에그에그',100);
INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (4,'치킨샐러드',20,3200,2700,2200,'건강다이어트',100);

INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (5,'아메리카노',30,1500,1100,500,'하루의 시작',100);
INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (6,'라떼',30,1500,1100,500,'부드러운 목넘김',100);

INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (7,'콜라',40,1000,900,500,'캬~',100);
INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (8,'쥬스',40,1000,900,500,'오렌지',100);

SELECT * FROM PRODUCT;

CREATE TABLE CATEGORY (
    KIND NUMBER(2),             --상품분류 샌드위치/샐러드/음료
    NAME VARCHAR2(100),         --분류명
    DETAIL VARCHAR2(1000)       --추가설명
);

INSERT INTO CATEGORY (KIND,NAME,DETAIL)
VALUES (10,'샌드위치', '햄/에그/아보카도/치즈');
INSERT INTO CATEGORY (KIND,NAME,DETAIL)
VALUES (20,'샐러드', '치킨/계란');
INSERT INTO CATEGORY (KIND,NAME,DETAIL)
VALUES (30,'커피', '아메리카노/아이스아메리카노/라떼');
INSERT INTO CATEGORY (KIND,NAME,DETAIL)
VALUES (40,'음료', '콜라/사이다/환타/쥬스');

CREATE TABLE ORDERED (
    ORDERID NUMBER(5),              --주문번호
    PRODID NUMBER(5),               --제품번호
    CNT NUMBER(5),                  --판매개수 
    ORDERDATE DATE DEFAULT SYSDATE, --판매일시
    PAY VARCHAR2(4)                 --CASH, CARD		////변경사항!!!!

);

CREATE TABLE EMPS (
    EMPLOYEE_ID NUMBER(6),      --사원번호
    NAME VARCHAR2(100),         --사원이름
    JOB_ID VARCHAR2(20),        --직책 사장,직원
    LOGINID VARCHAR2(20),       --관리자 로긴 ID		////변경사항!!!!
    PASSWORD VARCHAR2(20)       --관리자 로긴 PASSWORD	    ////변경사항!!!!
);

INSERT INTO EMPS (EMPLOYEE_ID,NAME,JOB_ID,LOGINID,PASSWORD)
VALUES (10,'사장','CEO','ceo','1234');
INSERT INTO EMPS (EMPLOYEE_ID,NAME,JOB_ID,LOGINID,PASSWORD)
VALUES (11,'직원1', 'MANAGER','manager','1234');



-- ----------------------------------------
SELECT * FROM PRODUCT;
update product set stock=100 where prodid=1;
update product set stock=100 where prodid=2;
update product set stock=100 where prodid=3;
update product set stock=100 where prodid=4;
update product set stock=100 where prodid=5;
update product set stock=100 where prodid=6;
update product set stock=100 where prodid=7;
update product set stock=100 where prodid=8;

SELECT * FROM emps;
SELECT * FROM category;

SELECT orderid, prodid, cnt, to_char(ORDERDATE,'yyyy/mm/dd hh24:mi') as order_date, pay FROM ORDERED;
select * from ordered;

delete from ordered;

----------------------------------------------------------------------------------------
--이하는 VendingMachine.java에서 DB접속을 통해 수행하는 내용임

--1) VendingMachine.java 실행시
-- 가. kind값을 매개변수로 하여 for문으로 1~4까지 돌면서 category table 전체 내용을 CategoryVo 객체로 가져옴
--   sql) SELECT * FROM CATEGORY WHERE KIND=?;

-- 나. prodid값을 매개변수로 하여 for문으로 1~8까지 돌면서 product table 전체 내용을 ProductVo객체로 가져옴
--   sql) SELECT * FROM PRODUCT WHERE PRODID=?;

--3) VendingMachine.java에서 최종 "결제확인" 버튼 클릭시 ordered table에 주문내용 insert됨
--   sql) INSERT INTO ORDERED (ORDERID, PRODID, CNT, PAY) 
--        VALUES (orderid, prodid, cnt, pay);

--4) 동시에 주문내용에 있는 prodid를 매개변수로 하여 product table의 재고(stock 컬럼)에서
--   판매수량 만큼 감해서 update 수행
--   sql)UPDATE PRODUCT SET STOCK=? WHERE PRODID=?;

--5) VendingMachine.java의 LOGIN Page에서 id,password를 입력하고 로그인 버튼 클릭시
--   해당 id,password를 매개변수로 하여 DB Query가 날아가고 return 값이 1이면 
--   JOptionPane으로 "로그인 성공", 그외는 "로그인 실패" 띄워줌
--   sql) SELECT COUNT(EMPLOYEE_ID) AS CNT FROM EMPS
--        WHERE LOGINID=? and PASSWORD = ?;

--6) 로그인 성공시 product table과 ordered table을 prodid로 JOIN하고, prodid로 GROUP BY 및
--   Descending Sort를 하여 prodid별 판매량, 매출, 마진, 재고량을 보여 줌
--   sql) SELECT P.PRODID AS 품번, NAME AS 품명, NVL(SUM(CNT),0) AS 판매량,
--        NVL(SUM(PRICE*CNT),0) AS 매출, NVL(SUM((PRICE-COST)*CNT),0) AS 마진, STOCK AS 재고  
--        FROM PRODUCT P, ORDERED O WHERE P.PRODID = O.PRODID(+)
--        GROUP BY P.PRODID, NAME, STOCK
--        ORDER BY P.PRODID;
