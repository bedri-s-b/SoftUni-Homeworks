/* Insert */

INSERT INTO coaches (first_name,last_name,salary,coach_level)
SELECT first_name, last_name, salary, char_length(first_name)
FROM players
WHERE age >= 45;

/* Update */

UPDATE coaches c
JOIN players_coaches pc
on c.id = pc.coach_id
SET coach_level = coach_level + 1
WHERE first_name LIKE 'A%';

/* DELETE */

DELETE FROM players
WHERE age >= 45;


/* Players */

SELECT `first_name`,age,salary 
FROM players
ORDER BY salary DESC
;

/* Young offense players without contract */

SELECT players.id, concat(`first_name`,' ',last_name) as full_name , age, position,hire_date
FROM players
JOIN skills_data as sd
on players.skills_data_id = sd.id
WHERE position = 'A' and hire_date is null and age < 23 and sd.strength > 50
ORDER BY salary, age;

/* Detail info for all teams */

SELECT t.name,established , fan_base,(SELECT COUNT(*) FROM players WHERE team_id = t.id) as count_of_players
FROM teams as t
ORDER BY count_of_players DESC, fan_base DESC;

/* Total salaries and players by country */

SELECT c.name, COUNT(p.id) as count_pl, SUM(p.salary)
FROM countries as c 
LEFT JOIN towns as t
on c.id = t.country_id
LEFT JOIN stadiums as s
on t.id = s.town_id
LEFT JOIN teams as tm
on s.id = tm.stadium_id
LEFT JOIN players as p
on tm.id = p.team_id
GROUP BY c.id
ORDER BY count_pl DESC , c.name;

/* Find all players that play on stadium */


DELIMITER $$
USE `fsd`$$
CREATE FUNCTION udf_stadium_players_count (stadium_name VARCHAR(30))
RETURNS INTEGER
DETERMINISTIC
BEGIN
RETURN (
	SELECT count(p.id)
	FROM stadiums as s
	LEFT JOIN teams as tm
	on s.id = tm.stadium_id
	LEFT JOIN players as p
	on tm.id = p.team_id
	WHERE s.name = stadium_name
	GROUP BY s.id
);
END$$

DELIMITER ;


/* Find good playmaker by teams */

DELIMITER $$
CREATE PROCEDURE `udp_find_playmaker` (min_dribble_points INT, team_name VARCHAR(45))
BEGIN

	SELECT concat_ws(' ',p.first_name,p.last_name) as full_name,
	age, salary, dribbling, speed, t.name
	FROM players as p
	JOIN skills_data as s
	on p.skills_data_id = s.id
	JOIN teams as t
	on p.team_id = t.id
	WHERE t.name = team_name and s.speed > (SELECT AVG(speed) FROM skills_data) and s.speed > min_dribble_points 
	ORDER BY s.speed DESC LIMIT 1;

END$$

DELIMITER ;
CALL udp_find_playmaker (20, 'Skyble');

