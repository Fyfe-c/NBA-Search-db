import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class dataBase {
    private Connection connection;
    public dataBase(){
        Properties prop = new Properties();
        String fileName = "auth.cfg";
        try {
            FileInputStream configFile = new FileInputStream(fileName);
            prop.load(configFile);
            configFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find config file.");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Error reading config file.");
            System.exit(1);
        }
        String username = (prop.getProperty("username"));
        String password = (prop.getProperty("password"));

        if (username == null || password == null){
            System.out.println("Username or password not provided.");
            System.exit(1);
        }
        String connectionUrl =
                "jdbc:sqlserver://uranium.cs.umanitoba.ca:1433;"
                        + "database=cs3380;"
                        + "user=" + username + ";"
                        + "password="+ password +";"
                        + "encrypt=false;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        try {
            // create a connection to the database
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    //*************************************Search Player******************************* */
    //search player by player name
    public ResultSet plyerByName(String name){
        ResultSet result = null;
        try {

            if(!name.equals("")) {
                String sql = "select Player.player_name as Name, Player.season as Season, TeamProperty.team_name as Team, Coach.coach_name as Coach"+
                " from Player left join TeamProperty on Player.team_id = TeamProperty.team_id"+
                " left join Coach on Coach.coach_id = Player.coach_id"+
                " where Player.player_name like ?"
                +" order by Player.player_name, Player.season ASC";
                PreparedStatement statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                statement.setString(1, "%" + name+ "%");
                result = statement.executeQuery();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }



    //search player by team and season
    public ResultSet playerByTeamSeason(String name, Integer season){
        ResultSet result = null;
        try {

            if(!name.equals("") && season != null) {
                String sql = "select Player.player_name as PlayerName,Player.season as Season,TeamProperty.team_name as Team,Coach.coach_name as Coach from Player"+
                " left join TeamProperty on TeamProperty.team_id = Player.team_id"+
                " left join Coach on Coach.coach_id = Player.coach_id"+
                " where TeamProperty.team_name like ? and Player.season = ?"+
                " order by Player.player_name, Player.season ASC";
                PreparedStatement statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                statement.setString(1, "%" + name+ "%");
                statement.setInt(2, season);
                result = statement.executeQuery();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    //what game did palyer join in a season.
    public ResultSet playerGame(String name, Integer season){
        ResultSet result = null;
        try {

            if(!name.equals("") && season != null) {
                String sql = "select player_name as Name, match.season as Season,date as Date, away.team_name as AwayTeam,home.team_name as HomeTeam"+
                " from match"+
                " join player on match.away_team_id = player.team_id and match.season = Player.season"+
                " join TeamProperty away on away.team_id = match.away_team_id"+
                " join TeamProperty home on home.team_id = match.home_team_id"+
                " where Player.player_name like ? and Player.season = ?"+
                " union"+
                " select player_name as name, match.season as season,date,away.team_name,home.team_name"+
                " from match"+
                " join player on match.home_team_id = player.team_id and match.season = Player.season"+
                " join TeamProperty away on away.team_id = match.away_team_id"+
                " join TeamProperty home on home.team_id = match.home_team_id"+
                " where Player.player_name like ? and Player.season = ?";
                PreparedStatement statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                statement.setString(1, "%" + name+ "%");
                statement.setString(3, "%" + name+ "%");
                statement.setInt(2, season);
                statement.setInt(4, season);
                result = statement.executeQuery();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    //******************************Search Team****************************** */
    //search team by team name.
    public ResultSet teamByName(String name){
        ResultSet result = null;
        try {
            if(!name.equals("")){
            String sql = "select TeamProperty.team_name as TeamName, Owner.owner_name as Owner, Manager.manager_name as Manager, Coach.coach_name as Coach, TeamProperty.arena_name as Arena, Arena.capacity as ArenaCapacity, City.city_name as City"+
            " from TeamProperty left join Owner on Owner.owner_id = TeamProperty.owner_id"+
            " left join Manager on Manager.manager_id = Owner.manager_id"+
            " left join Coach on Coach.coach_id = Owner.coach_id"+
            " left join Arena on Arena.arena_name = TeamProperty.arena_name"+
            " left join City on City.city_id = TeamProperty.city_id"+
            " where TeamProperty.team_name like ?";
            PreparedStatement statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, "%" + name + "%");
            result = statement.executeQuery();
        }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public ResultSet listAllTeams(){
        ResultSet result = null;
        try {
            
            String sql = "select TeamProperty.team_name as TeamName, Owner.owner_name as Owner, Manager.manager_name as Manager,"+
            " Coach.coach_name as Coach, TeamProperty.arena_name as Arena, Arena.capacity as ArenaCapacity, City.city_name as City"+
            " from TeamProperty left join Owner on Owner.owner_id = TeamProperty.owner_id"+
            " left join Manager on Manager.manager_id = Owner.manager_id"+
            " left join Coach on Coach.coach_id = Owner.coach_id"+
            " left join Arena on Arena.arena_name = TeamProperty.arena_name"+
            " left join City on City.city_id = TeamProperty.city_id"+
            " order by TeamProperty.team_name DESC";
            PreparedStatement statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            result = statement.executeQuery();
        


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    //team winRate
    public ResultSet teamWinRate(Integer season){
        ResultSet result = null;
        try {
            if(season!=null){
            String sql = "select team_name as TeamName,CAST(w as decimal(4,2))/CAST(g as decimal(4,2)) as WinRate"+
            " from Teamrank"+
            " where season = ?"+
            " order by WinRate DESC";
            PreparedStatement statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, season);
            result = statement.executeQuery();
        }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    
    
//***************************Search Performance***************************
public ResultSet playerPerTop5(String team, String performance, Integer season){
    ResultSet result = null;
    try {
        
        if(!team.equals("") && season != null && !performance.equals("")){
            
        String sql = "SELECT Player.player_name as Name, "+performance +
        " FROM Player"+
        " JOIN TeamProperty ON Player.Team_id = TeamProperty.Team_id"+
        " JOIN PlayerRank ON Player.player_id = PlayerRank.player_id"+
        " WHERE TeamProperty.team_name = ? AND PlayerRank.season = ?"+
        " ORDER BY "+performance +" DESC"+
        " OFFSET 5 ROWS"+
        " FETCH NEXT 5 ROWS ONLY";
        PreparedStatement statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        statement.setString(1, team);
        statement.setInt(2, season);

        result = statement.executeQuery();
    }


    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}


//top 5 team by different performance rank
public ResultSet teamPerTop15(String performance, Integer season){
    ResultSet result = null;
    try {
      
        if(season != null && !performance.equals("")){
            
        String sql = "WITH ScoreForTeam AS("+
            " SELECT TeamProperty.Team_id, SUM("+ performance +") as Total"+
            " FROM Player"+
            " JOIN TeamProperty ON Player.Team_id = TeamProperty.Team_id"+
            " JOIN PlayerRank ON Player.player_id = PlayerRank.player_id"+
            " WHERE PlayerRank.season = ?"+
            " GROUP BY TeamProperty.Team_id"+
            " ORDER BY Total DESC"+
            " OFFSET  15 ROWS"+
            " FETCH NEXT 15 ROWS ONLY)"+
            " SELECT TeamProperty.team_name, Total"+
            " FROM TeamProperty"+
            " JOIN ScoreForTeam ON ScoreForTeam.Team_id = TeamProperty.Team_id";
            
        PreparedStatement statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1, season);
        // statement.setString(2, performance);

        
        result = statement.executeQuery();
    }


    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}


//top 15 team hitrate
public ResultSet teamPerTop15HitRate(String performance, Integer season){
    ResultSet result = null;
    try {
      
        if(season != null && !performance.equals("")){
            
        String sql = "WITH ScoreForTeam AS("+
            " SELECT TeamProperty.Team_id, AVG("+performance+") * 100 AS HitRate"+
            " FROM Player"+
            " JOIN TeamProperty ON Player.Team_id = TeamProperty.Team_id"+
            " JOIN PlayerRank ON Player.player_id = PlayerRank.player_id"+
            " WHERE PlayerRank.season = ?"+
            " GROUP BY TeamProperty.Team_id"+
            " ORDER BY HitRate DESC"+
            " OFFSET  15 ROWS"+
            " FETCH NEXT 15 ROWS ONLY)"+
            " SELECT TeamProperty.team_name, HitRate"+
            " FROM TeamProperty"+
            " JOIN ScoreForTeam ON ScoreForTeam.Team_id = TeamProperty.Team_id";
                        
        PreparedStatement statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1, season);
        
        

        
        result = statement.executeQuery();
    }


    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}

//top position that do best in certain field
public ResultSet bestPerformanceByPosition(String performance, Integer season){
    ResultSet result = null;
    try {
      
        if(season != null && !performance.equals("")){
            
        String sql = "select PlayerRank.position, avg("+performance+") as Avg from PlayerRank"+
                    " where (position = 'C' or position = 'PG' or position = 'SG' or position = 'PF' or position = 'SF')"+
                    " and season = ?"+ 
                    " group by position"+
                    " order by Avg DESC";
                        
        PreparedStatement statement = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        statement.setInt(1, season);
        
        result = statement.executeQuery();
    }


    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}



}
