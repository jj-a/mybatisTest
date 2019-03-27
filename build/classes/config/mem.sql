-- mem.sql

-- 테이블 생성
CREATE TABLE mem(
	num number, 
	name varchar2(20), 
	age number 
)
;

-- 시퀀스 생성
CREATE SEQUENCE mem_seq
;


-- 추가
INSERT INTO mem(num, name, age) 
VALUES(mem_seq.nextval, #{name}, #{age}) 
;

-- 수정
UPDATE mem 
SET name=#{name}, age=#{age} 
WHERE num=#{num} 
;

-- 삭제
DELETE FROM mem 
WHERE num=#{num} 
;


-- 조회
SELECT num, name, age FROM mem 
ORDER BY num ASC 
;


INSERT INTO mem(num, name, age) 
VALUES(mem_seq.nextval, '샌드위치', 27) 
;


