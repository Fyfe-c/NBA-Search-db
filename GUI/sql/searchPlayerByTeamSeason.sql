select Player.player_name as PlayerName,Player.season as Season,TeamProperty.team_name as Team,Coach.coach_name as Coach from Player
left join TeamProperty on TeamProperty.team_id = Player.team_id
left join Coach on Coach.coach_id = Player.coach_id
where TeamProperty.team_name like '%celtics%' and Player.season = 2018;
