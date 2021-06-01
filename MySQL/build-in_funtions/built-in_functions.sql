/* Find  Names of all Employees by First Name */

SELECT `first_name`, `last_name`
FROM employees
WHERE left(first_name,2) = 'Sa'
ORDER BY employee_id;

/* Find Names of all Employees by Last Name */

SELECT `first_name`, `last_name`
FROM employees
WHERE last_name LIKE '%ei%'
ORDER BY employee_id;

/* Find First Names of All Employess */

SELECT 
    first_name
FROM
    employees
WHERE
    department_id IN (3 , 10)
        AND YEAR(hire_date) BETWEEN 1995 AND 2005
ORDER BY employee_id;

/* Find all Empoloyees Except Engineers */

SELECT 
    first_name, last_name
FROM
    employees
WHERE
    job_title NOT LIKE '%engineer%'
ORDER BY employee_id;

/* Find Towns with Name Length */

SELECT 
    `name`
FROM
    towns
WHERE
    CHAR_LENGTH(`name`) IN (5 , 6)
ORDER BY `name`;

/* Find Towns Starting With */

SELECT 
    *
FROM
    towns
WHERE
    LEFT(`name`, 1) IN ('m' , 'k', 'b', 'e')
ORDER BY `name`;

/* Find towns Not Stariting With */

SELECT 
    *
FROM
    towns
WHERE
    LEFT(`name`, 1) NOT IN ('r' , 'b', 'd')
ORDER BY `name`;

/* Create Viaw Employees Hired After 2000 Years*/

CREATE VIEW `v_employees_hired_after_2000` AS
    SELECT 
        *
    FROM
        employees
    WHERE
        YEAR(`hire_date`) > 2000;

SELECT * FROM v_employees_hired_after_2000;

/* Length of Last Name */

SELECT 
    `first_name`, `last_name`
FROM
    employees
WHERE
    CHAR_LENGTH(`last_name`) = 5;
    
/* Countries Holdeing 'A' E or More Time */
 
SELECT 
    `country_name`, `iso_code`
FROM
    countries
WHERE
    `country_name` LIKE '%a%a%a%'
ORDER BY iso_code;

/* Mix of Peak and River Names */

SELECT 
    `peak_name`,
    `river_name`,
    LOWER(CONCAT(`peak_name`, SUBSTR(`river_name`, 2))) AS 'mix'
FROM
    `peaks`,
    `rivers`
WHERE
    RIGHT(peak_name, 1) = LEFT(river_name, 1)
ORDER BY mix;

/* Game from 2011 and 2012 Year */

SELECT `name`, date_format(`start`,'%Y-%m-%d') FROM games
WHERE year(`start`) in (2011,2012)
ORDER BY `start`,`name`
LIMIT 50;

/* User Email Providers */


SELECT 
    `user_name`,
    SUBSTR(`email`,
        LOCATE('@', `email`) + 1) AS 'Email Provider'
FROM
    users
ORDER BY `Email Provider` , user_name;

/* Get Users with IP Address like Pattern */

SELECT 
    `user_name`, ip_address
FROM
    users
WHERE
    ip_address LIKE '___.1%.%.___'
ORDER BY user_name;

/* Show all Games with Duration and part of the Day */

SELECT 
    `name` AS 'game',
    (CASE
        WHEN HOUR(`start`) BETWEEN 0 AND 11 THEN 'Morning'
        WHEN HOUR(`start`) BETWEEN 12 AND 17 THEN 'Afternoon'
        WHEN HOUR(`start`) BETWEEN 18 AND 24 THEN 'Evening'
    END) AS 'Part of the Day',
    (CASE
        WHEN `duration` < 4 THEN 'Extra Short'
        WHEN `duration` < 7 THEN 'Short'
        WHEN `duration` < 11 THEN 'Long'
        ELSE 'Extra Long'
    END) AS 'Duration'
FROM
    games;
    
/* Orders Table */

SELECT *
FROM orders;

SELECT 
    `product_name`,
    `order_date`,
    ADDDATE(`order_date`, INTERVAL 3 DAY) AS 'pay_due',
    ADDDATE(`ORDER_DATE`, INTERVAL 1 MONTH) AS 'deliver_due'
FROM
    orders;