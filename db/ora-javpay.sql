    CREATE TABLE PRODUCT (
    PRODID NUMBER(5),           --��ǰ��ȣ
    NAME VARCHAR2(100),         --��ǰ��
    KIND NUMBER(2),             --��ǰ�з� ������ġ/������/����
    PRICE NUMBER(10),           --����
    OLDPRICE NUMBER(10),        --��������
    COST NUMBER(10),            --����
    CONTENT VARCHAR2(100),      --�߰�����
    STOCK NUMBER(3)             --������ 
);

INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (1,'�ܿ��׻�����ġ',10,3000,2900,2000,'�ܿ����ܿ���',100);
INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (2,'�ƺ�ī��������ġ',10,3500,3000,2500,'�ڿ��ǹ���',100);

INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (3,'���׻�����',20,2900,2500,2000,'���׿���',100);
INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (4,'ġŲ������',20,3200,2700,2200,'�ǰ����̾�Ʈ',100);

INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (5,'�Ƹ޸�ī��',30,1500,1100,500,'�Ϸ��� ����',100);
INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (6,'��',30,1500,1100,500,'�ε巯�� ��ѱ�',100);

INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (7,'�ݶ�',40,1000,900,500,'ļ~',100);
INSERT INTO PRODUCT (PRODID,NAME,KIND,PRICE,OLDPRICE,COST,CONTENT,STOCK)
VALUES (8,'�꽺',40,1000,900,500,'������',100);

SELECT * FROM PRODUCT;

CREATE TABLE CATEGORY (
    KIND NUMBER(2),             --��ǰ�з� ������ġ/������/����
    NAME VARCHAR2(100),         --�з���
    DETAIL VARCHAR2(1000)       --�߰�����
);

INSERT INTO CATEGORY (KIND,NAME,DETAIL)
VALUES (10,'������ġ', '��/����/�ƺ�ī��/ġ��');
INSERT INTO CATEGORY (KIND,NAME,DETAIL)
VALUES (20,'������', 'ġŲ/���');
INSERT INTO CATEGORY (KIND,NAME,DETAIL)
VALUES (30,'Ŀ��', '�Ƹ޸�ī��/���̽��Ƹ޸�ī��/��');
INSERT INTO CATEGORY (KIND,NAME,DETAIL)
VALUES (40,'����', '�ݶ�/���̴�/ȯŸ/�꽺');

CREATE TABLE ORDERED (
    ORDERID NUMBER(5),              --�ֹ���ȣ
    PRODID NUMBER(5),               --��ǰ��ȣ
    CNT NUMBER(5),                  --�ǸŰ��� 
    ORDERDATE DATE DEFAULT SYSDATE, --�Ǹ��Ͻ�
    PAY VARCHAR2(4)                 --CASH, CARD		////�������!!!!

);

CREATE TABLE EMPS (
    EMPLOYEE_ID NUMBER(6),      --�����ȣ
    NAME VARCHAR2(100),         --����̸�
    JOB_ID VARCHAR2(20),        --��å ����,����
    LOGINID VARCHAR2(20),       --������ �α� ID		////�������!!!!
    PASSWORD VARCHAR2(20)       --������ �α� PASSWORD	    ////�������!!!!
);

INSERT INTO EMPS (EMPLOYEE_ID,NAME,JOB_ID,LOGINID,PASSWORD)
VALUES (10,'����','CEO','ceo','1234');
INSERT INTO EMPS (EMPLOYEE_ID,NAME,JOB_ID,LOGINID,PASSWORD)
VALUES (11,'����1', 'MANAGER','manager','1234');



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
--���ϴ� VendingMachine.java���� DB������ ���� �����ϴ� ������

--1) VendingMachine.java �����
-- ��. kind���� �Ű������� �Ͽ� for������ 1~4���� ���鼭 category table ��ü ������ CategoryVo ��ü�� ������
--   sql) SELECT * FROM CATEGORY WHERE KIND=?;

-- ��. prodid���� �Ű������� �Ͽ� for������ 1~8���� ���鼭 product table ��ü ������ ProductVo��ü�� ������
--   sql) SELECT * FROM PRODUCT WHERE PRODID=?;

--3) VendingMachine.java���� ���� "����Ȯ��" ��ư Ŭ���� ordered table�� �ֹ����� insert��
--   sql) INSERT INTO ORDERED (ORDERID, PRODID, CNT, PAY) 
--        VALUES (orderid, prodid, cnt, pay);

--4) ���ÿ� �ֹ����뿡 �ִ� prodid�� �Ű������� �Ͽ� product table�� ���(stock �÷�)����
--   �Ǹż��� ��ŭ ���ؼ� update ����
--   sql)UPDATE PRODUCT SET STOCK=? WHERE PRODID=?;

--5) VendingMachine.java�� LOGIN Page���� id,password�� �Է��ϰ� �α��� ��ư Ŭ����
--   �ش� id,password�� �Ű������� �Ͽ� DB Query�� ���ư��� return ���� 1�̸� 
--   JOptionPane���� "�α��� ����", �׿ܴ� "�α��� ����" �����
--   sql) SELECT COUNT(EMPLOYEE_ID) AS CNT FROM EMPS
--        WHERE LOGINID=? and PASSWORD = ?;

--6) �α��� ������ product table�� ordered table�� prodid�� JOIN�ϰ�, prodid�� GROUP BY ��
--   Descending Sort�� �Ͽ� prodid�� �Ǹŷ�, ����, ����, ����� ���� ��
--   sql) SELECT P.PRODID AS ǰ��, NAME AS ǰ��, NVL(SUM(CNT),0) AS �Ǹŷ�,
--        NVL(SUM(PRICE*CNT),0) AS ����, NVL(SUM((PRICE-COST)*CNT),0) AS ����, STOCK AS ���  
--        FROM PRODUCT P, ORDERED O WHERE P.PRODID = O.PRODID(+)
--        GROUP BY P.PRODID, NAME, STOCK
--        ORDER BY P.PRODID;
