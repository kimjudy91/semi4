--회원테이블

drop table members;

create table members(
	id varchar2(15) primary key,
	pwd varchar2(20) not null,
	name varchar2(10) not null,
	jumin varchar2(20) not null unique,
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

--문의테이블

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

--음악게시판(커뮤니티)

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

-- 댓글게시판(커뮤니티)

drop table comment;

create table comment(
	comments_num number(5) primary key,
	write_num number REFERENCES music(write_num),
	id varchar2(15) REFERENCES members(id),
	comments_contents varchar2(100) not null,
	comments_date date not null

);

create sequence comments_num_seq;

-------------------------------------------------------------------------------------

-- 음악게시판(파일 다운/업로드)

drop table music_file;

create table music_file(
	write_num number primary key,
	id varchar2(15) REFERENCES members(id),
	f_num number(10) REFERENCES upload(f_num),
	p_title varchar2(30) not null,
	contents varchar2(100) not null,
	r_date date not null,
	views number(10),
	like number,
	genre_num number(5) REFERENCES genre(genre_num)

);

create sequence write_num_seq;

-------------------------------------------------------------------------------------

-- 댓글게시판(파일 다운/업로드)

drop table comment_file;

create table comment_file(
	write_num number REFERENCES music_file(write_num),
	id varchar2(15) REFERENCES members(id),
	comments_num number(5) primary key,
	comments_contents varchar2(100) not null,
	comments_date date not null,

);

create sequence comments_num_seq;

-------------------------------------------------------------------------------------

-- 업로드테이블

drop table upload;

create table upload(
	f_num number(10) primary key,
	orgfilename varchar2(150),
	savefilename varchar2(150),
	filesize number(10)
	
);

create sequence f_num_seq;

-------------------------------------------------------------------------------------

-- 신고테이블1 (파일 업로드게시판)

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

-- 신고테이블2 (커뮤니티게시판)

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

-- 광고테이블

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

-- 장르테이블

drop table genre;

create table genre(
	genre_num number(5) primary key,
	genre_name varchar2(20) not null

);



