
build: Main.class dataBase.class LaunchPage.class Menu.class mainPage.class

Main.class: Main.java
	javac Main.java
dataBase.class: dataBase.java
	javac dataBase.java
LaunchPage.class: LaunchPage.java
	javac LaunchPage.java
Menu.class: Menu.java
	javac Menu.java
mainPage.class: mainPage.java
	javac mainPage.java

run: Main.class
	java -cp .:mssql-jdbc-11.2.0.jre11.jar Main

clean:
	rm Main.class dataBase.class LaunchPage.class Menu.class mainPage.class
