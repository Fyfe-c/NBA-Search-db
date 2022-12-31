--which game the player played.
select player_name as Name, match.season as Season,date as Date, away.team_name as AwayTeam,home.team_name as HomeTeam
from match
join player on match.away_team_id = player.team_id and match.season = Player.season
join TeamProperty away on away.team_id = match.away_team_id
join TeamProperty home on home.team_id = match.home_team_id
where Player.player_name like '%lebron%' and Player.season = 2018
union
select player_name as name, match.season as season,date,away.team_name,home.team_name
from match
join player on match.home_team_id = player.team_id and match.season = Player.season
join TeamProperty away on away.team_id = match.away_team_id
join TeamProperty home on home.team_id = match.home_team_id
where Player.player_name like '%lebron%' and Player.season = 2018;
