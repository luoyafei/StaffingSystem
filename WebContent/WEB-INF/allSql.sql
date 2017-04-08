create database StaffingSystem;
user StaffingSystem;

create table t_admin (
	adminId varchar(32) primary key,
	adminNo varchar(32),
	adminNum varchar(32),
	adminPwd varchar(32),
	recentLogin datetime
) engine=innoDB default charset=utf8;

create table t_user (
	userId varchar(32) primary key,
	usersName varchar(11),
	userPhoto varchar(255),
	userIdcard varchar(32),
	userAddress varchar(255),
	age int,
	department varchar(32),
	post varchar(32),
	userTel varchar(11),
	userEmail varchar(32)
) engine=innoDB default charset=utf8;
--考勤表
create table t_check (
	checkId varchar(32) primary key,
	checkNo varchar(32),
	checkTitle varchar(255),
	checkContent varchar(255),
	checkDate datetime
) engine=innoDB default charset=utf8;
--工资
create table t_salary (
	salaryId varchar(32) primary key,
	salaryNo varchar(32),
	salaryTitle varchar(255),
	salaryContent varchar(255),
	salaryDate datetime
) engine=innoDB default charset=utf8;
--招聘
create table t_employ (
	employId varchar(32) primary key,
	employNo varchar(32),
	employTitle varchar(255),
	employContent varchar(255),
	employDate datetime
) engine=innoDB default charset=utf8;
--组织
create table t_organization (
	organizationId varchar(32) primary key,
	organizationNo varchar(32),
	organizationTitle varchar(255),
	organizationContent varchar(255),
	organizationDate datetime
) engine=innoDB default charset=utf8;
--公司事务
create table t_dashi (
	dashiId varchar(32) primary key,
	dashiNo varchar(32),
	dashiTitle varchar(255),
	dashiContent varchar(255),
	dashiDate datetime
) engine=innoDB default charset=utf8;