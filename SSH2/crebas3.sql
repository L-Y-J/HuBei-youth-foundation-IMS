/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2014/5/17 19:18:16                           */
/*==============================================================*/



-- Type package declaration
create or replace package PDTypes  
as
    TYPE ref_cursor IS REF CURSOR;
end;

-- Integrity package declaration
create or replace package IntegrityPackage AS
 procedure InitNestLevel;
 function GetNestLevel return number;
 procedure NextNestLevel;
 procedure PreviousNestLevel;
 end IntegrityPackage;
/

-- Integrity package definition
create or replace package body IntegrityPackage AS
 NestLevel number;

-- Procedure to initialize the trigger nest level
 procedure InitNestLevel is
 begin
 NestLevel := 0;
 end;


-- Function to return the trigger nest level
 function GetNestLevel return number is
 begin
 if NestLevel is null then
     NestLevel := 0;
 end if;
 return(NestLevel);
 end;

-- Procedure to increase the trigger nest level
 procedure NextNestLevel is
 begin
 if NestLevel is null then
     NestLevel := 0;
 end if;
 NestLevel := NestLevel + 1;
 end;

-- Procedure to decrease the trigger nest level
 procedure PreviousNestLevel is
 begin
 NestLevel := NestLevel - 1;
 end;

create or replace type Array_Power as table of CHAR
/

/
/*==============================================================*/
/* Table: Appilcation                                           */
/*==============================================================*/
create table Appilcation 
(
   SId                  INTEGER,
   State                CHAR(30)                default '0' not null,
   WriterId             INTEGER,
   ApproveFromDepartment VARCHAR(300)             not null,
   ProvinceOptions      VARCHAR(300)             not null,
   Writer               VARCHAR(300)             not null,
   AppilcationId        INTEGER              not null,
   IfMove               INTEGER              not null,
   WillHaveDanger       INTEGER,
   WillHaveInternet     INTEGER,
   GovernmentMoney      INTEGER,
   TotalMoney           INTEGER,
   newFacility          INTEGER              not null,
   ifMove2              INTEGER              not null,
   classroomFoold       INTEGER              not null,
   cassroomStructure    CHAR(30)             not null,
   classroomArea        INTEGER              not null,
   classroomCost        INTEGER              not null,
   studentDormitoryFoold INTEGER,
   studentDormitoryStructrue CHAR(30),
   studentDormitoryArea INTEGER,
   studentDormitoryCost INTEGER,
   teacherDormitoryFoold INTEGER,
   teacherDormitoryStructrue CHAR(30),
   teacherDormitoryArea INTEGER,
   teacherDormitoryCost INTEGER,
   toiletStructrue      CHAR(30),
   toiletArea           INTEGER,
   toletCost            INTEGER,
   canteenStructure     CHAR(30),
   canteenArea          INTEGER,
   canteenCost          INTEGER,
   other                VARCHAR(900),
   covers               INTEGER,
   buildingArea         INTEGER,
   havePlayground       INT,
   playgroundIncllude   VARCHAR(900),
   constraint PK_APPILCATION primary key (AppilcationId)
)
/

comment on column Appilcation.ApproveFromDepartment is
'部门确认'
/

comment on column Appilcation.WillHaveDanger is
'项目完成后是否还存在危房'
/

comment on column Appilcation.WillHaveInternet is
'是否可以上网'
/

comment on column Appilcation.ifMove2 is
'是否移址'
/

/*==============================================================*/
/* Table: Department                                            */
/*==============================================================*/
create table Department 
(
   DepartId             INTEGER              not null,
   DepartmentName       VARCHAR(300)             not null,
   'level'              VARCHAR(30)          not null,
   constraint PK_DEPARTMENT primary key (DepartId)
)
/

/*==============================================================*/
/* Table: Job                                                   */
/*==============================================================*/
create table Job 
(
   JobId                CHAR(30)                not null,
   DepartmentId         INTEGER,
   JobName              VARCHAR(300)             not null,
   DisableFlat          INTEGER              default 0 not null,
   constraint PK_JOB primary key (JobId)
)
/

/*==============================================================*/
/* Table: MonthlyProgress                                       */
/*==============================================================*/
create table MonthlyProgress 
(
   WriterId             INTEGER,
   SId                  INTEGER,
   WriteDate            DATE                 not null,
   MonthlyProgressId    INTEGER              not null,
   State                INTEGER              not null
      constraint CKC_STATE_MONTHLYP check (State between 0 and 2),
   CanDoneOnTime        INTEGER              not null,
   Description          CLOB,
   Signature            CHAR(30)             not null,
   PhoneAera            INTEGER              not null,
   Phone                INTEGER              not null,
   constraint PK_MONTHLYPROGRESS primary key (MonthlyProgressId)
)
/

comment on column MonthlyProgress.State is
'0为准备阶段
1为施工阶段
2为验收阶段'
/

/*==============================================================*/
/* Table: Photo                                                 */
/*==============================================================*/
create table Photo 
(
   PhotoId              INTEGER              not null,
   AppilcationId        INTEGER,
   MonthlyProgressId    INTEGER,
   PhotoName            VARCHAR(300)             not null,
   Information          CLOB,
   PhotoType            CHAR(30),
   'Number'             INTEGER              not null,
   constraint PK_PHOTO primary key (PhotoId)
)
/

/*==============================================================*/
/* Table: Students                                              */
/*==============================================================*/
create table Students 
(
   StudentId            INTEGER              not null,
   StudentName          VARCHAR2(30)         not null,
   School               VARCHAR(300),
   StudentState         CHAR(30)                default 'UnSubsidize' not null
      constraint CKC_STUDENTSTATE_STUDENTS check (StudentState in ('Unsubsidize','Subsidized')),
   RecordDate           DATE                 not null,
   constraint PK_STUDENTS primary key (StudentId)
)
/

/*==============================================================*/
/* Table: Subsidize                                             */
/*==============================================================*/
create table Subsidize 
(
   SubsidizeId          INTEGER              not null,
   Subsidizer           VARCHAR(300)          not null,
   SubsidizeProvince    VARCHAR2(30),
   SubsidizeDate        DATE                 not null,
   SubsidizeMoney       INTEGER,
   SubsidizeItem        VARCHAR(300),
   SubsidizeDapartment  VARCHAR(300),
   IsForeign            INTEGER              default 0 not null,
   IsNation             INTEGER              default 0 not null,
   IsPersonal           INTEGER              default 0 not null,
   Remark               VARCHAR(300),
   State                CHAR(30)             default 'Planning' not null,
   SubsidizerPlace      VARCHAR(300)          not null,
   constraint PK_SUBSIDIZE primary key (SubsidizeId)
)
/

/*==============================================================*/
/* Table: SubsidizeSchool                                       */
/*==============================================================*/
create table SubsidizeSchool 
(
   SId                  INTEGER              not null,
   SchoolName           VARCHAR2(60)         not null,
   RecordDate           DATE                 not null,
   CompleteDate         DATE,
   Province             VARCHAR2(30)         not null,
   Qu                   VARCHAR2(30),
   Xian                 VARCHAR2(30),
   Xiang                varcahr(30),
   Cun                  VARCHAR2(30),
   LocalXiang           VARCHAR2(30)         not null,
   LocalCun             VARCHAR2(30)         not null,
   IsMerge              INTEGER              default 0 not null,
   MergeSchool          VARCHAR2(60),
   MergeVillages        VARCHAR2(30),
   MergeHamlet          VARCHAR2(30),
   MergeLocalXiang      VARCHAR2(30),
   MergeLocalCun        VARCHAR2(30),
   PresidentName1       VARCHAR2(30),
   HomePhone            INTEGER,
   OfficePhone          INTEGER,
   PresidnetName2       VARCHAR2(30),
   Email1               VARCHAR2(30),
   Email2               VARCHAR2(30),
   CellPhone1           INTEGER,
   CellPhone2           INTEGER,
   HomePhoneArea        INTEGER,
   OfficePhoneArea      INTEGER,
   BackgroundInformation CLOB,
   NumOfClass           INTEGER,
   NumOfStudent         INTEGER,
   VillageStudentFrom   INTEGER,
   AreaStudentFrom      INTEGER,
   NumOfSubsidizeStudent INTEGER,
   NumOfTeacher         INTEGER,
   NumOfOfficeTeacher   INTEGER,
   NumOfSubstituteTeacher INTEGER,
   NumOfBoardingTeacher INTEGER,
   NUmOfBoardingStudent INTEGER,
   Covers               INTEGER,
   BuildingArea         INTEGER,
   ClassroomArea        INTEGER,
   ClassroomCompletedDate DATE,
   ClassroomType        CHAR(30),
   ClassroomDanger      CHAR(30),
   StudentDormitoryArea INTEGER,
   StudentDormitoryCompleteDate DATE,
   StudentDormitoryType CHAR(30),
   StudentDormitoryDanger CHAR(30),
   TeacherDormitoryArea INTEGER,
   TeacherDormitoryCompleteDate DATE,
   TeacherDormitoryType CHAR(30),
   TeacherDormitoryDanger CHAR(30),
   CanteenArea          INTEGER,
   CanteenCompleteDate  DATE,
   CanteenType          CHAR(30),
   CanteenDanger        CHAR(30),
   ToiletArea           INTEGER,
   ToiletCompleteDate   DATE,
   ToiletDanger         CHAR(30),
   ToiletSituation      INTEGER,
   Other                VARCHAR(300),
   HavePlayground       INTEGER,
   FacilityId           INTEGER              not null,
   TeacherEducationPassRate INTEGER             
      constraint CKC_TEACHEREDUCATIONP_SUBSIDIZ check (TeacherEducationPassRate is null or (TeacherEducationPassRate between 0 and 100)),
   TeacherJobPassRate   INTEGER             
      constraint CKC_TEACHERJOBPASSRAT_SUBSIDIZ check (TeacherJobPassRate is null or (TeacherJobPassRate between 0 and 100)),
   EnrollmentOfStudent  INTEGER             
      constraint CKC_ENROLLMENTOFSTUDE_SUBSIDIZ check (EnrollmentOfStudent is null or (EnrollmentOfStudent between 0 and 100)),
   StudentRetentionRate INTEGER             
      constraint CKC_STUDENTRETENTIONR_SUBSIDIZ check (StudentRetentionRate is null or (StudentRetentionRate between 0 and 100)),
   StudentDropoutRate   INTEGER             
      constraint CKC_STUDENTDROPOUTRAT_SUBSIDIZ check (StudentDropoutRate is null or (StudentDropoutRate between 0 and 100)),
   EnrollmentRateOfStudent INTEGER             
      constraint CKC_ENROLLMENTRATEOFS_SUBSIDIZ check (EnrollmentRateOfStudent is null or (EnrollmentRateOfStudent between 0 and 100)),
   DeskRate             INTEGER             
      constraint CKC_DESKRATE_SUBSIDIZ check (DeskRate is null or (DeskRate between 0 and 100)),
   InstrumentRate       INTEGER             
      constraint CKC_INSTRUMENTRATE_SUBSIDIZ check (InstrumentRate is null or (InstrumentRate between 0 and 100)),
   NumOfBooks           INTEGER,
   NumOfComputer        INTEGER,
   TypeOfComputer       CHAR(30),
   SportsEquipmentSituation VARCHAR(300),
   HaveInternet         INTEGER,
   Honor                CLOB,
   FutureNumOfStudent   INTEGER,
   FutureNumOfBoardingStudent INTEGER,
   FutureNumOfTeacher   INTEGER,
   FutureNumOfBoardingTeacher INTEGER,
   ChangeReason         VARCHAR(300),
   HaveEngineering      INTEGER,
   XianAverageIncome    INTEGER,
   XiangAverageIncome   INTEGER,
   CunAverageIncome     INTEGER,
   constraint PK_SUBSIDIZESCHOOL primary key (SId)
)
/

comment on column SubsidizeSchool.VillageStudentFrom is
'学生来自几个村'
/

comment on column SubsidizeSchool.AreaStudentFrom is
'学生来源自多少平方米内'
/

comment on column SubsidizeSchool.NumOfOfficeTeacher is
'办公老师的数目'
/

comment on column SubsidizeSchool.NumOfBoardingTeacher is
'住校老师数目'
/

comment on column SubsidizeSchool.NUmOfBoardingStudent is
'住校学生数目'
/

comment on column SubsidizeSchool.Covers is
'占地面积'
/

comment on column SubsidizeSchool.ToiletSituation is
'平均几个人一个坑'
/

comment on column SubsidizeSchool.HavePlayground is
'有无体育场'
/

comment on column SubsidizeSchool.TeacherEducationPassRate is
'教师学历合格率'
/

comment on column SubsidizeSchool.TeacherJobPassRate is
'教师岗位合格率'
/

comment on column SubsidizeSchool.StudentRetentionRate is
'学生巩固率'
/

comment on column SubsidizeSchool.DeskRate is
'课桌配齐率'
/

comment on column SubsidizeSchool.InstrumentRate is
'仪器配齐率'
/

comment on column SubsidizeSchool.NumOfBooks is
'图书拥有量'
/

comment on column SubsidizeSchool.SportsEquipmentSituation is
'拥有体育器材情况'
/

comment on column SubsidizeSchool.HaveInternet is
'是否拥有互联网'
/

comment on column SubsidizeSchool.Honor is
'近两年奖励情况'
/

comment on column SubsidizeSchool.HaveEngineering is
'是否有三年内落实但沿未动工的工程'
/

/*==============================================================*/
/* Table: SubsidizeToSchool                                     */
/*==============================================================*/
create table SubsidizeToSchool 
(
   SubsidizeId          INTEGER,
   SId                  INTEGER
)
/

/*==============================================================*/
/* Table: SubsidizeToStudent                                    */
/*==============================================================*/
create table SubsidizeToStudent 
(
   SubsidizeId          INTEGER,
   StudentId            INTEGER
)
/

/*==============================================================*/
/* Table: Writer                                                */
/*==============================================================*/
create table Writer 
(
   JobId                CHAR(30),
   Writer               VARCHAR(300)             not null,
   PhoneArea            INTEGER              not null,
   Phone                INTEGER              not null,
   FaxArea              INTEGER,
   Fax                  INTEGER,
   Email                VARCHAR(30)          not null,
   CellPhone            INTEGER,
   WriterId             INTEGER              not null,
   userId               INTEGER,
   constraint PK_WRITER primary key (WriterId)
)
/

comment on table Writer is
'含有一个外键与myuser表关连'
/

alter table Appilcation
   add constraint FK_APPILCAT_REFERENCE_SUBSIDIZ foreign key (SId)
      references SubsidizeSchool (SId)
/

alter table Appilcation
   add constraint FK_APPILCAT_REFERENCE_WRITER foreign key (WriterId)
      references Writer (WriterId)
/

alter table Job
   add constraint FK_JOB_REFERENCE_DEPARTME foreign key (DepartmentId)
      references Department (DepartId)
/

alter table MonthlyProgress
   add constraint FK_MONTHLYP_REFERENCE_WRITER foreign key (WriterId)
      references Writer (WriterId)
/

alter table MonthlyProgress
   add constraint FK_MONTHLYP_REFERENCE_SUBSIDIZ foreign key (SId)
      references SubsidizeSchool (SId)
/

alter table Photo
   add constraint FK_PHOTO_REFERENCE_MONTHLYP foreign key (MonthlyProgressId)
      references MonthlyProgress (MonthlyProgressId)
/

alter table Photo
   add constraint FK_PHOTO_REFERENCE_APPILCAT foreign key (AppilcationId)
      references Appilcation (AppilcationId)
/

alter table SubsidizeToSchool
   add constraint FK_SUBSIDIZ_REFERENCE_SUBSIDIZ foreign key (SId)
      references SubsidizeSchool (SId)
/

alter table SubsidizeToSchool
   add constraint FK_SUBSIDIZ_SUBSITOSC_SUBSIDIZ foreign key (SubsidizeId)
      references Subsidize (SubsidizeId)
/

alter table SubsidizeToStudent
   add constraint FK_SUBSIDIZ_REFERENCE_STUDENTS foreign key (StudentId)
      references Students (StudentId)
/

alter table SubsidizeToStudent
   add constraint FK_SUBSIDIZ_SUBSITOST_SUBSIDIZ foreign key (SubsidizeId)
      references Subsidize (SubsidizeId)
/

alter table Writer
   add constraint FK_WRITER_REFERENCE_JOB foreign key (JobId)
      references Job (JobId)
/


