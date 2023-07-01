SET SCHEMA SpaceTravel;

-- Generate Client

WITH n as (SELECT * FROM TABLE(name VARCHAR = ('Bill', 'Elon', 'Bob', 'Jon', 'Mark', 'Jeremy', 'Harry', 'Thomas', 'Oscar', 'George', 'Jacob', 'Jim', 'Evan', 'Tom', 'Richard')) name)

INSERT INTO Client (name)

SELECT n.name name FROM n;

-- Generate Planet

WITH n as (SELECT * FROM TABLE(name VARCHAR = ('Mercury', 'Venus', 'Earth', 'Mars', 'Jupiter', 'Saturn', 'Uranus', 'Neptune')) name)

INSERT INTO Planet (id, name)

SELECT UPPER(n.name), n.name name FROM n;

-- Generate Ticket

WITH d as (
SELECT o.r                                      r,
       CONVERT(ROUND(RAND()*14+1),BIGINT)       client,
       CONVERT(ROUND(RAND()*7+1),BIGINT)        from_planet,
       CONVERT(ROUND(RAND()*7+1),BIGINT)        to_planet

  FROM (SELECT * FROM TABLE(r INT = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10))) o),
     p as (SELECT ROW_NUMBER() OVER (ORDER BY CONVERT(ROUND(RAND()*99999),int)) l, id  FROM Planet),
     c as (SELECT ROW_NUMBER() OVER (ORDER BY CONVERT(ROUND(RAND()*99999),int)) l, id  FROM Client)

INSERT INTO Ticket  (created_at,  client_id, from_planet_id, to_planet_id)

SELECT TIMESTAMP '2023-01-01' + RAND()*365 + RAND()  created_at,
      (SELECT c.id FROM c WHERE c.l = d.client)      client_id,
      (SELECT p.id FROM p WHERE p.l = d.from_planet) from_planet_id,
      (SELECT p.id FROM p WHERE p.l = d.to_planet)   to_planet_id

  FROM d;
