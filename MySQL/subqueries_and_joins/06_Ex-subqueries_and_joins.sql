/* Employee Address */

SELECT 
    e.employee_id, e.job_title, a.address_id, a.address_text
FROM
    employees AS e
        JOIN
    addresses AS a ON a.address_id = e.address_id
ORDER BY a.address_id
LIMIT 5;

/* Addresses with Towns */

SELECT 
    e.first_name, e.last_name, t.`name`, a.address_text
FROM
    employees AS e
        JOIN
    addresses AS a USING (`address_id`)
        JOIN
    towns AS t USING (`town_id`)
ORDER BY e.first_name , e.last_name
LIMIT 5;

/* Sales Employee */

SELECT 
    e.employee_id, e.first_name, e.last_name, d.`name`
FROM
    employees AS e
        JOIN
    departments AS d ON d.department_id = e.department_id
WHERE
    d.`name` = 'Sales'
ORDER BY e.employee_id DESC;

/* Employee Departments */

SELECT 
    e.employee_id, e.first_name, e.salary, d.`name`
FROM
    employees AS e
        JOIN
    departments AS d ON d.department_id = e.department_id
WHERE
    salary > 15000
ORDER BY d.department_id DESC
LIMIT 5;

/* Employees Without Project */

SELECT 
    e.employee_id, e.first_name
FROM
    employees AS e
        LEFT JOIN
    employees_projects AS ep ON e.employee_id = ep.employee_id
WHERE
    ep.project_id IS NULL
ORDER BY e.employee_id DESC
LIMIT 3;

/* Employees Hired After */

SELECT 
    e.first_name, e.last_name, e.hire_date, d.name
FROM
    employees AS e
        JOIN
    departments AS d ON d.department_id = e.department_id
WHERE
    DATE(19990101) < e.hire_date
        AND d.name = 'Finance'
        OR d.name = 'Sales'
ORDER BY e.hire_date;

/* Employees wich Project */

SELECT 
    e.employee_id, e.first_name, p.name
FROM
    employees AS e
        JOIN
    employees_projects AS ep ON ep.employee_id = e.employee_id
        JOIN
    projects AS p USING (`project_id`)
WHERE
    DATE(p.start_date) > '2002-08-13'
        AND end_date IS NULL
ORDER BY first_name , p.name
LIMIT 5;

/* Employee 24 */

SELECT 
    e.employee_id,
    e.first_name,
    IF(YEAR(p.start_date) >= 2005,
        NULL,
        p.name) AS p_name
FROM
    employees AS e
        JOIN
    employees_projects AS ep ON ep.employee_id = e.employee_id
        JOIN
    projects AS p USING (`project_id`)
WHERE
   e.employee_id = 24
ORDER BY p.name;

/* Employee Manager */

SELECT 
    e.employee_id,
    e.first_name,
    e.manager_id,
    e2.first_name AS manager_name
FROM
    employees AS e
        JOIN
    employees AS e2 ON e.manager_id = e2.employee_id
WHERE
    e.manager_id IN (7 , 3)
ORDER BY e.first_name;

/* Employee Summary */

SELECT 
    e.employee_id,
    CONCAT_WS(' ', e.first_name, e.last_name) AS e_name,
    CONCAT_WS(' ', e2.first_name, e2.last_name) AS m_name,
    d.name
FROM
    employees AS e
        JOIN
    employees AS e2 ON e.manager_id = e2.employee_id
        JOIN
    departments AS d ON e.department_id = d.department_id
ORDER BY e.employee_id
LIMIT 5;

/* Min Average SAlary  */

SELECT AVG(salary) as min_salary
FROM employees
GROUP BY department_id
ORDER BY min_salary
LIMIT 1;


/* Highest Peaks in Bulgaria */

SELECT 
    mc.country_code, m.mountain_range, p.peak_name, p.elevation
FROM
    countries AS c
        JOIN
    mountains_countries AS mc ON c.country_code = mc.country_code
        JOIN
    mountains AS m ON mc.mountain_id = m.id
        JOIN
    peaks AS p ON m.id = p.mountain_id
WHERE
    mc.country_code = 'BG'
        AND p.elevation > 2835
ORDER BY p.elevation DESC;

/* Count Mountain Range */

SELECT 
    country_code, COUNT(mountain_range) AS m_r
FROM
    mountains_countries AS mc
        JOIN
    mountains AS m ON mc.mountain_id = m.id
WHERE
    country_code IN ('BG' , 'RU', 'US')
GROUP BY country_code
ORDER BY m_r DESC;

/* Countries witch Rivers */

SELECT 
    c.country_name, r.river_name
FROM
    countries AS c
        LEFT JOIN
    countries_rivers AS cr ON cr.country_code = c.country_code
        LEFT JOIN
    rivers AS r ON cr.river_id = r.id
WHERE
    continent_code = 'AF'
ORDER BY c.country_name
LIMIT 5;


/* *Continents and Currencies */

SELECT 
    continent_code,
    currency_code,
    COUNT(country_name) AS currency_usage
FROM
    countries AS c
GROUP BY continent_code , currency_code
HAVING currency_usage = (SELECT 
        COUNT(country_name) AS coun
    FROM
        countries AS c1
    WHERE
        c1.continent_code = c.continent_code
    GROUP BY currency_code
    ORDER BY coun DESC
    LIMIT 1)
    AND currency_usage > 1
ORDER BY continent_code , currency_code;

/*  Countries Without Any Mountains */

SELECT 
    COUNT(*) AS country_count
FROM
    countries AS c
        LEFT JOIN
    mountains_countries AS mc USING (country_code)
WHERE
    mc.mountain_id IS NULL;
    
/* Highest Peak and Longest River by Country */

SELECT 
    c.country_name,
    MAX(p.elevation) AS highest_peak_elevation,
    MAX(r.length) AS longest_river_length
FROM
    countries AS c
        JOIN
    mountains_countries AS mc USING (country_code)
        JOIN
    mountains AS m ON mc.mountain_id = m.id
        JOIN
    peaks AS p ON m.id = p.mountain_id
        JOIN
    countries_rivers AS cr USING (country_code)
        JOIN
    rivers AS r ON cr.river_id = r.id
GROUP BY c.country_name
ORDER BY highest_peak_elevation DESC , longest_river_length DESC
LIMIT 5;