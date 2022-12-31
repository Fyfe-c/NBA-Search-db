--list all teams
select TeamProperty.team_name as TeamName, Owner.owner_name as Owner, Manager.manager_name as Manager, 
Coach.coach_name as Coach, TeamProperty.arena_name as Arena, Arena.capacity as ArenaCapacity, City.city_name as City
from TeamProperty left join Owner on Owner.owner_id = TeamProperty.owner_id
left join Manager on Manager.manager_id = Owner.manager_id
left join Coach on Coach.coach_id = Owner.coach_id
left join Arena on Arena.arena_name = TeamProperty.arena_name
left join City on City.city_id = TeamProperty.city_id;