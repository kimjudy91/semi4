--�쉶�썝�뀒�씠釉�

drop table members;

create table members(
	id varchar2(15) primary key,
	pwd varchar2(20) not null,
	name varchar2(10) not null,
	jumin varchar2(20) not null ,
	email varchar2(20) not null unique,
	address varchar2(20) not null,
	phone varchar2(20) not null unique,
	write_count number not null,
	reply_count number not null,
	grade number not null,
	warning number not null,
	genre_num number(5) REFERENCES genre(genre_num)
	
);



---------------------------------------------------------------------------------

--臾몄쓽�뀒�씠釉�

drop table nquire;

create table nquire(
	nquire_num number(5) primary key,
	id varchar2(15) REFERENCES members(id),
	title varchar2(20) not null,
	contents varchar2(150) not null,
	r_date date not null,
	comments varchar2(100)
	
);

create sequence nquire_seq;

-------------------------------------------------------------------------------------

--�쓬�븙寃뚯떆�뙋(而ㅻ�ㅻ땲�떚)

drop table music;

create table music(
	write_num number primary key,
	id varchar2(15) REFERENCES members(id),
    p_title varchar2(30) not null,
	contents varchar2(100),
	r_date date not null,
	views number(10),
	genre_num number(5) REFERENCES genre(genre_num)
	
);

create sequence write_num_seq;

-------------------------------------------------------------------------------------

-- �뙎湲�寃뚯떆�뙋(而ㅻ�ㅻ땲�떚)

drop table comments;

create table comments(
	comments_num number(5) primary key,
	write_num number REFERENCES music(write_num),
	id varchar2(15) REFERENCES members(id),
	comments_contents varchar2(100) not null,
	comments_date date not null

);

create sequence comments_num_seq;

-------------------------------------------------------------------------------------

-- �쓬�븙寃뚯떆�뙋(�뙆�씪 �떎�슫/�뾽濡쒕뱶)

drop table music_file;

create table music_file(
	write_num number primary key,
	id varchar2(15) REFERENCES members(id),
	f_num number(10) REFERENCES upload(f_num),
	p_title varchar2(30) not null,
	contents varchar2(100) not null,
	r_date date not null,
	views number(10),
	likes number,
	genre_num number(5) REFERENCES genre(genre_num)

);

create sequence write_num_seq;

-------------------------------------------------------------------------------------

-- �뙎湲�寃뚯떆�뙋(�뙆�씪 �떎�슫/�뾽濡쒕뱶)

drop table comment_file;

create table comment_file(
	write_num number REFERENCES music_file(write_num),
	id varchar2(15) REFERENCES members(id),
	comments_num number(5) primary key,
	comments_contents varchar2(100) not null,
	comments_date date not null

);

create sequence comments_num_seq;

-------------------------------------------------------------------------------------

-- �뾽濡쒕뱶�뀒�씠釉�

drop table upload;

create table upload(
	f_num number(10) primary key,
	orgfilename varchar2(150),
	savefilename varchar2(150),
	filesize number(10)
	
);

create sequence f_num_seq;

-------------------------------------------------------------------------------------

-- �떊怨좏뀒�씠釉�1 (�뙆�씪 �뾽濡쒕뱶寃뚯떆�뙋)

drop table report1;

create table report1(
	rnum number primary key,
	id varchar2(15) REFERENCES members(id),
	write_num number REFERENCES music_file(write_num),
	report_content varchar2(100) not null,
	comments varchar2(100)

);

create sequence rnum_seq;

-------------------------------------------------------------------------------------

-- �떊怨좏뀒�씠釉�2 (而ㅻ�ㅻ땲�떚寃뚯떆�뙋)

drop table report2;

create table report2(
	rnum number primary key,
	id varchar2(15) REFERENCES members(id),
	write_num number REFERENCES music(write_num),
	report_content varchar2(100) not null,
	comments varchar2(100)

);

create sequence rnum_seq;

-------------------------------------------------------------------------------------

-- 愿묎퀬�뀒�씠釉�

drop table advertise;

create table advertise(
	ad_num number(5) primary key,
	ad_money number not null,
	ad_com varchar2(50) not null,
	ad_image varchar2(50) not null,
	ad_start_date date not null,
	ad_end_date date not null
	
);

create sequence ad_num_seq;

-------------------------------------------------------------------------------------

-- �옣瑜댄뀒�씠釉�

drop table genre;

create table genre(
	genre_num number(5) primary key,
	genre_name varchar2(20) not null

);



