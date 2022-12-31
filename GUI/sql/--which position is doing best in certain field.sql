

--which position is doing best in certain field.


select PlayerRank.position, avg(blocks) as Avg from PlayerRank
where (position = 'C' or position = 'PG' or position = 'SG' or position = 'PF' or position = 'SF') 
and season = 2019

group by position

order by  Avg DESC;


