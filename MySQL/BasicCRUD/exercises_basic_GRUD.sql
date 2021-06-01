/* 1 find all infortions about departments */

SELECT * FROM departments
ORDER BY `department_id`;

/* 2 find all departments names */

SELECT `name` 
FROM departments
ORDER BY `department_id`;

/* find salary of each employee */

SELECT `first_name`,`last_name`,`salary`
FROM `employees`
ORDER BY employee_id;

/* find full name of each employee */

SELECT `first_name`,`middle_name`,`last_name` 
FROM employees
ORDER BY `employee_id`;

/* find all email address of each emloyee */

SELECT concat(`first_name`,'.' ,`last_name`,'@softuni.bg') as 'full_email_address'
FROM employees;

/* find all different employee's salaries */

SELECT DISTINCT `salary`
FROM employees
ORDER BY  `employee_id`;

/* find all information about employees */

SELECT * FROM employees
WHERE `job_title` = 'Sales Representative'
ORDER BY `employee_id`;

/* find names of all employees by salary in range */

SELECT `first_name`,`last_name`, `job_title`
FROM employees
WHERE `salary` >= 20000 AND `salary` <= 30000
ORDER BY `employee_id`;

/* find all names of employees */

SELECT concat_ws(' ',`first_name`,`middle_name`,`last_name`) as 'Full Name' 
FROM employees
WHERE salary IN (25000, 14000, 12500 ,23600);

/* find all employees without manager */

SELECT `first_name`, `last_name`
FROM employees
WHERE manager_id is NULL;

/* find all employees with salary more than 50000 */

SELECT `first_name`,`last_name`,`salary`
FROM employees
WHERE salary > 50000
ORDER BY salary DESC;

/* find 5 best paid employees */

SELECT `first_name`,`last_name`
FROM employees
ORDER BY salary DESC 
LIMIT 5;

/* find all employees except marketung */

SELECT `first_name`, `last_name`
FROM employees
WHERE department_id != 4;

/* sort employees table */

SELECT * FROM employees
ORDER BY salary DESC, first_name ASC, last_name DESC, middle_name ASC;

/* create view employees with salaries */

CREATE VIEW v_employees_salaries as
	SELECT `first_name`, `last_name` ,`salary`
	FROM employees;
    
    SELECT * FROM v_employees_salaries;
    
/* create view employees with job titles */

CREATE VIEW v_employees_job_title as
	SELECT concat_ws(' ',`first_name`, `middle_name`,`last_name`) as 'full_name',`job_title`
	FROM employees;

/* distinct job title */

SELECT DISTINCT `job_title`
FROM employees
ORDER BY job_title;


/* find first 10 started projects */

SELECT *
FROM projects
WHERE end_date IS NOT NULL
ORDER BY `start_date`, `name`
LIMIT 10;

/* last 7 hired employees */

SELECT `first_name`,`last_name`,`hire_date`
FROM employees
ORDER BY hire_date DESC
LIMIT 7;

/* increase salaries */

SELECT * FROM employees
WHERE department_id in (1,2,4,11)
ORDER BY salary; 

SELECT * FROM departments;

UPDATE `employees`
SET salary = salary * 1.12
WHERE department_id in (1,2,4,11);

SELECT salary FROM employees;

/* all mountain peaks */

SELECT `peak_name` 
FROM peaks
ORDER BY peak_name;

/* biggest countries by population */

SELECT `country_name`, `population`
FROM countries
WHERE `continent_code` = 'EU'
ORDER BY population DESC
LIMIT 30;

/* countries and currency (Euro / not Euro) */

SELECT `country_name`, `country_code`,if(currency_code = 'EUR','Euro','Not Euro') as 'currency'
FROM countries
ORDER BY country_name;


/* all diablo charasters */

SELECT `name` 
FROM characters
ORDER BY `name`;


        