-- Search for player by a given String
SELECT * FROM Player
WHERE player_name LIKE '%CURRY%'

-- Search for playerRank by a given id
SELECT * FROM PlayerRank
WHERE player_id = 644;

-- Search for the three point rank for a given team id and season
SELECT Player.player_name, three_point
FROM Player
JOIN TeamProperty ON Player.Team_id = TeamProperty.Team_id
JOIN PlayerRank ON Player.player_id = PlayerRank.player_id
WHERE TeamProperty.team_name LIKE '%celtics%' AND PlayerRank.season = 2018
ORDER BY three_point DESC
OFFSET  5 ROWS 
FETCH NEXT 5 ROWS ONLY;


--find the top 5 team(all players in the team) has the top score of Three_points
--in 2018-2019
WITH ScoreForTeam AS(
SELECT TeamProperty.Team_id, SUM(three_point) AS totalScore
FROM Player
JOIN TeamProperty ON Player.Team_id = TeamProperty.Team_id
JOIN PlayerRank ON Player.player_id = PlayerRank.player_id
WHERE PlayerRank.season = 2018
GROUP BY TeamProperty.Team_id
ORDER BY totalScore DESC
OFFSET  15 ROWS 
FETCH NEXT 15 ROWS ONLY
)
SELECT TeamProperty.team_name, totalScore
FROM TeamProperty
JOIN ScoreForTeam ON ScoreForTeam.Team_id = TeamProperty.Team_id
;

--top 15 hit
WITH ScoreForTeam AS(
SELECT TeamProperty.Team_id, AVG(three_point_percent) AS HitRate
FROM Player
JOIN TeamProperty ON Player.Team_id = TeamProperty.Team_id
JOIN PlayerRank ON Player.player_id = PlayerRank.player_id
WHERE PlayerRank.season = 2018
GROUP BY TeamProperty.Team_id
ORDER BY HitRate DESC
OFFSET  15 ROWS
FETCH NEXT 15 ROWS ONLY
)
SELECT TeamProperty.team_name, HitRate
FROM TeamProperty
JOIN ScoreForTeam ON ScoreForTeam.Team_id = TeamProperty.Team_id
;

