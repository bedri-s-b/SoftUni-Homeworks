
/* insert */

INSERT INTO `products_stores` 
SELECT p.id , 1
FROM products as p
LEFT JOIN products_stores as ps 
on p.id = ps.product_id
WHERE  ps.product_id is null;

/* Update */

UPDATE employees as e
JOIN stores as s
on s.id = e.store_id
SET manager_id = 3
WHERE year(hire_date) >= 2003 AND `name` not in ('Cardguard','Veribet');

/* Delete */

DELETE FROM employees 
WHERE manager_id is not null and salary >= 6000;

/* Employees */

SELECT `first_name`,`middle_name`,`last_name`,`salary`,`hire_date` 
FROM employees
ORDER BY hire_date DESC;

/* products With old Price */

SELECT `name`, price, best_before, concat(substr(pr.`description`, 1,10) ,'...') as short_description, url
FROM pictures as pic
JOIN products as pr
on pic.id = pr.picture_id
WHERE char_length(`description`) > 100 and year(added_on) < 2019 and price > 20
ORDER BY price DESC;

/* Counts of products in stores and their average */

SELECT s.name,count(store_id) AS p_count, round(avg(p.price),2) as price
FROM stores as s 
LEFT JOIN products_stores as ps
on s.id = ps.store_id
LEFT JOIN products as p
on p.id = ps.product_id
GROUP BY s.name
ORDER BY p_count DESC, price DESC, s.id;

/* Specific employee */

SELECT concat(first_name,' ',last_name) as full_name, s.name as store_name,  a.name as address ,salary
FROM employees as e
JOIN addresses as a
on e.store_id = a.id
JOIN stores as s
on s.address_id = a.id
WHERE salary < 4000 and a.name LIKE '%5%' AND char_length(s.name) > 8 and last_name LIKE '%n' and
first_name != 'Rube';

/* Find all information of stores */

SELECT REVERSE(s.name) as  r_name, concat(upper(t.name),'-',a.name) as full_name ,COUNT(e.id)
FROM employees as e
JOIN stores as s
on e.store_id = s.id
JOIN addresses as a
on a.id = s.address_id
JOIN towns as t
on	a.town_id = t.id
GROUP BY s.name
ORDER BY full_name;

/* Find name of top paid employee by store name */


DELIMITER $$
USE `softuni_stores_system`$$
CREATE FUNCTION udf_top_paid_employee_by_store(store_name VARCHAR(50))
RETURNS VARCHAR(100)
DETERMINISTIC
BEGIN
	
    DECLARE result VARCHAR(100);
    
	 SET result := (SELECT
	concat(concat_ws(' ',first_name,concat(middle_name,'.'),last_name),' works in store for '
	,TIMESTAMPDIFF(year,hire_date, '2020-10-18' ),' years') as full_info
	FROM employees as e
	JOIN stores as s
	on e.store_id = s.id
	WHERE s.name = store_name
	ORDER BY salary DESC LIMIT 1
    );
    
RETURN result;
END$$

DELIMITER ;

/* Update product price by address */

DELIMITER $$
USE `softuni_stores_system`$$
CREATE PROCEDURE udp_update_product_price (address_name VARCHAR (50))
BEGIN

	DECLARE strart_with INT ;
   set strart_with := (
		if(address_name like '0%',100,200)
    );
    
	UPDATE addresses as a
	JOIN stores as s
	on a.id = s.address_id
	JOIN products_stores as ps
	on s.id = ps.store_id
	JOIN products as p
	on p.id = ps.product_id
	SET p.price = p.price + strart_with
	WHERE a.name = address_name;


END$$

DELIMITER ;


