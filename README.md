Our program can run in Linux lab with MakeFile. You can use “make run” to run, “make clean” to clear .class file.

Before run the program：
    Update your username and password in auth.cfg
    Run NBA.sql in this folder on SQL Server to create database.
    Then use "make run" to run the GUI.


What is included in this folder：
    MakeFile
    NBA.sql
    auth.cfg
    dataBase.java
    LaunchPage.java
    logo.png
    Main.java
    mainPage.java
    Menu.java
    mssql-jdbc-11.2.0.jre11.jar
    ReadMe.md
    sql folder contains the query we wrote for the porgram.


Explaination Of Query:
    In Search Player: 
        1, Search player by a given player name.
        2, List of players in one team in a selected season
        3, Matchs a player played in a selected season, seach by enter player name, searching result includes data of the match,
            home team of this match and away team of this match. 
    In Search Team:
        1, List all teams in the league
        2, Search team by given a team name
        3, List all Teams WinRate in a given season.

    In Search Performance:
        1, Top 5 players in a selected team by selected field(such as 3 point shoots, how many blocks the play get,etc )
        2, Top 15 Team by selected field(such as 3 point shoots, how many blocks the play get,etc )
        3, Top 15 Team by hit rate(2-point or 3 point).
        4, The advantage of different position on the court in selected field and selected season(such as which position on the courst is good at
            shooting 3 points).
