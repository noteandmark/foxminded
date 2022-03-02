CREATE SCHEMA IF NOT EXISTS school;

DROP TABLE IF EXISTS school.courses CASCADE;

CREATE TABLE IF NOT EXISTS school.courses
(
    course_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 10 CACHE 1 ),
    course_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    course_description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT courses_pkey PRIMARY KEY (course_id)
)

TABLESPACE pg_default;

ALTER TABLE school.courses
    OWNER to "user";
    
--   
    
DROP TABLE IF EXISTS school.groups CASCADE;

CREATE TABLE IF NOT EXISTS school.groups
(
    group_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 10 CACHE 1 ),
    group_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY (group_id)
)

TABLESPACE pg_default;

ALTER TABLE school.groups
    OWNER to "user";
    
--
    
DROP TABLE IF EXISTS school.students CASCADE;

CREATE TABLE IF NOT EXISTS school.students
(
    student_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 200 CACHE 1 ),
    group_id integer NULL,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT students_pkey PRIMARY KEY (student_id),
    CONSTRAINT students_groupid_fkey FOREIGN KEY (group_id)
        REFERENCES school.groups (group_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE school.students
    OWNER to "user";
    
--
DROP TABLE IF EXISTS school.students_courses CASCADE;

CREATE TABLE IF NOT EXISTS school.students_courses
(
	student_id integer NOT NULL,
	course_id integer NOT NULL,
	CONSTRAINT students_and_courses_pkey PRIMARY KEY (student_id, course_id),
	CONSTRAINT student_courses_fkey FOREIGN KEY (student_id)
		REFERENCES school.students (student_id)
		ON DELETE CASCADE,
	CONSTRAINT student_courses_fkey2 FOREIGN KEY (course_id)
		REFERENCES school.courses (course_id)
		ON DELETE CASCADE	
)

TABLESPACE pg_default;

ALTER TABLE school.students_courses
    OWNER to "user";