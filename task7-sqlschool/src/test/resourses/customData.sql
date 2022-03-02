SET FOREIGN_KEY_CHECKS=0;

TRUNCATE TABLE school.groups RESTART IDENTITY;
TRUNCATE TABLE school.courses RESTART IDENTITY;
TRUNCATE TABLE school.students RESTART IDENTITY;
TRUNCATE TABLE school.students_courses RESTART IDENTITY;

SET FOREIGN_KEY_CHECKS=1;

INSERT INTO school.groups VALUES (1, 'aa-11');
INSERT INTO school.groups VALUES (2, 'bb-22');
INSERT INTO school.groups VALUES (3, 'cc-33');

INSERT INTO school.courses VALUES (DEFAULT, 'Course Of Jedi', 'Some art of Light force');
INSERT INTO school.courses VALUES (DEFAULT, 'Course Of Sitth', 'Some art of Dark force');

INSERT INTO school.students VALUES (DEFAULT, 1, 'Anjei', 'Sapkovsky');
INSERT INTO school.students VALUES (DEFAULT, 2, 'Boris', 'Kruger');
INSERT INTO school.students VALUES (DEFAULT, 2, 'Ilja', 'Pustoi');
INSERT INTO school.students VALUES (DEFAULT, 3, 'Denis', 'Kovalsky');
INSERT INTO school.students VALUES (DEFAULT, 3, 'Genadiy', 'Kozlov');
INSERT INTO school.students VALUES (DEFAULT, 3, 'Vasiliy', 'Mostovoi');

INSERT INTO school.students_courses VALUES (3, 1);
INSERT INTO school.students_courses VALUES (4, 1);
INSERT INTO school.students_courses VALUES (5, 2);
INSERT INTO school.students_courses VALUES (6, 2);