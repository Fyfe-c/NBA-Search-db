import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class mainPage implements ActionListener {
    private dataBase database;

    private ImageIcon logo = new ImageIcon("logo.png");
    private JButton button = new JButton();
    private JFrame main = new JFrame();// main frame of main page
    private JFrame menuFrame;
    private JPanel mainPanel = new JPanel();
    private JFrame spFrame;// search player frame
    private JFrame stFrame;// search team frame
    private JFrame sperFrame;// search performance frame.
    private JButton buttonSP = new JButton("Search Players");
    private JButton buttonST = new JButton("Search Teams");
    private JButton buttonSPER = new JButton("Search Performance");
    // menubar
    private JMenuBar menuBar = new JMenuBar();

    // menu selection
    private JMenu fileMenu = new JMenu("File");
    private JMenu helpMenu = new JMenu("Help");
    private JMenu aboutMenu = new JMenu("About");
    // menu items
    private JMenuItem team = new JMenuItem("Members");
    private JMenuItem description = new JMenuItem("Description");
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem contact = new JMenuItem("Customer Service");

    // main page components
    // search player
    private JPanel pSearchPlayer;
    private JTextField playerNameBox;
    private JButton bSearchPlayerName;
    private JButton bSearchPlayerByTeam;
    private JComboBox pBoxT;
    private JComboBox pBoxS;
    private String pBoxTeam = "";
    private Integer pBoxSeason = null;
      //list game a player played
    private JComboBox pBoxS3;
    private Integer pBoxSeason3 = null;
    private JTextField playerNameBox3;
    private JButton bSearchP3;
    // searchTeam
    private JPanel pSearchTeam;
    private JTextField teamNameBox;
    private JButton bSearchTeamName;
    private JButton bsearchTeam2;

    private JButton bsearchTeam3;
    private JComboBox tBoxS3;
    private Integer tBoxS3I = null;

    //search Performance
    JComboBox perBoxTeam;
    JComboBox perBoxSeason;
    JComboBox perBoxPerformance;
    String perBoxTeamS ="", perBoxPerfomanceS="";
    Integer perBoxSeasonI = null;
    JButton bSearchPer1;

    JComboBox perBoxSeason2;
    JComboBox perBoxPerformance2;
    String perBoxPerformanceS2="";
    Integer perBoxSeaonI2 = null;
    JButton bSearchPer2;

    JComboBox perBoxSeason3;
    JComboBox perBoxPerformance3;
    String perBoxPerformanceS3="";
    Integer perBoxSeasonI3 = null;
    JButton bSearchPer3;

    JComboBox perBoxSeason4;
    JComboBox perBoxPerformance4;
    String perBoxPerformanceS4="";
    Integer perBoxSeasonI4 = null;
    JButton bSearchPer4;
    

    public mainPage(dataBase d) {
        this.database = d;
        JPanel SP = new JPanel();
        JPanel ST = new JPanel();
        JPanel SPER = new JPanel();
        buttonSP.setPreferredSize(new Dimension(600, 200));
        buttonST.setPreferredSize(new Dimension(600, 200));
        buttonSPER.setPreferredSize(new Dimension(600, 200));
        buttonSP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonST.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonSPER.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonSP.setFont(new Font("Serif", Font.PLAIN, 22));
        buttonST.setFont(new Font("Serif", Font.PLAIN, 22));
        buttonSPER.setFont(new Font("Serif", Font.PLAIN, 22));
        buttonSP.addActionListener(this);
        buttonST.addActionListener(this);
        buttonSPER.addActionListener(this);

        SP.add(buttonSP);
        ST.add(buttonST);
        SPER.add(buttonSPER);
        main.setTitle("NBA");
        main.setPreferredSize(new Dimension(800, 800));
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit window
        main.setResizable(false);
        main.setIconImage(logo.getImage());// set frame icon
        main.setLayout(new GridLayout(3, 1));
        main.getContentPane().setBackground(new Color(255, 255, 255));

        new Menu(this);
        setMenu();
        main.add(SP);
        main.add(ST);
        main.add(SPER);

        main.pack();
        main.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String text = null, title = null;
            // check menu bar action
            if (e.getSource() == team) {// show result of Team in About menu
                text = "<html>Xin Nie\t7640563<br/>Wanqi Lin\t7908738<br/>Fengfan Bian\t7924160</html>";
                title = "Team Members";
                createMenuPage(title, text);
            } else if (e.getSource() == description) {// show description in About menu
                title = "Description";
                text = "<html>This database contains the match data of 2018 and 2019 season NBA games. The dataset includes daily match results, team performance, player performance, daily team ranking, list of teams, team staff, and players.</html>";
                createMenuPage(title, text);
            } else if (e.getSource() == contact) {// show contact in help menu
                title = "Customer Service";
                text = "<html>24/7 Hotline:<br/> Xin Nie 204-290-1236</html>";
                createMenuPage(title, text);
            } else if (e.getSource() == exit) {// exit in file mneu
                // dispose();
                System.exit(0);
            }

            // main page click**************************
            else if (e.getSource() == buttonSP) {

                createSearchWindowP("Search Players", spFrame);
            } else if (e.getSource() == buttonST) {
                createSearchWindowT("Search Teams", stFrame);
            } else if (e.getSource() == buttonSPER) {
                createSearchWindowPER("Search Performance", sperFrame);
            }
            // ****************************************
            // search page click
            else if (e.getSource() == bSearchPlayerName) {
                // search player by name
                String name = playerNameBox.getText();
                result("Search Player By Name", database.plyerByName(name));

            } else if (e.getSource() == pBoxT) {//search Player by team
                pBoxTeam = (String) pBoxT.getSelectedItem();//
            } else if (e.getSource() == pBoxS) {
                pBoxSeason = (Integer) pBoxS.getSelectedItem();
            } else if (e.getSource() == bSearchPlayerByTeam) {
                result("Search Player By Team and Season",database.playerByTeamSeason(pBoxTeam,pBoxSeason));
            }
            //list of game a player attend
            else if(e.getSource()==pBoxS3){
                pBoxSeason3 = (Integer)pBoxS3.getSelectedItem();
            }
            else if(e.getSource()==bSearchP3){
                String name = playerNameBox3.getText();
                result("Game List " + pBoxSeason3, database.playerGame(name, pBoxSeason3));
            }



            // search team
            
            else if (e.getSource() == bSearchTeamName) {
                

                String teamName = teamNameBox.getText();
                result("Search Team By Name", database.teamByName(teamName));
            } else if(e.getSource()==export){

            }//list all teams
            else if(e.getSource()==bsearchTeam2){
                result("Team List", database.listAllTeams());
            }
            //winrate
            else if (e.getSource() == tBoxS3){
                tBoxS3I = (Integer)tBoxS3.getSelectedItem();
            }else if(e.getSource() == bsearchTeam3){
                result("WinRate - " + tBoxS3I, database.teamWinRate(tBoxS3I));
            }

            //search performance
            //1 search plaer each team by performance
            else if(e.getSource()==perBoxTeam){
                perBoxTeamS = (String)perBoxTeam.getSelectedItem();
            }else if(e.getSource()==perBoxSeason){
                perBoxSeasonI = (Integer)perBoxSeason.getSelectedItem();
            }else if(e.getSource()==perBoxPerformance){
                perBoxPerfomanceS = (String)perBoxPerformance.getSelectedItem();
            }else if(e.getSource()==bSearchPer1){
                result("Top 5 Payers Each Team By " + perBoxPerfomanceS,database.playerPerTop5(perBoxTeamS, perBoxPerfomanceS, perBoxSeasonI));
            }//seach top 15 team
            else if(e.getSource()==perBoxSeason2){
                perBoxSeaonI2 = (Integer)perBoxSeason2.getSelectedItem();
            }else if(e.getSource()==perBoxPerformance2){
                perBoxPerformanceS2 = (String)perBoxPerformance2.getSelectedItem();
            }else if(e.getSource()==bSearchPer2){
                result("Top 15 Team By " + perBoxPerformanceS2, database.teamPerTop15(perBoxPerformanceS2, perBoxSeaonI2));
            }//search top 15 hitRate
            else if(e.getSource()==perBoxSeason3){
                perBoxSeasonI3 = (Integer)perBoxSeason3.getSelectedItem();
            }else if(e.getSource() == perBoxPerformance3){
                perBoxPerformanceS3 = (String)perBoxPerformance3.getSelectedItem();
            }else if(e.getSource() == bSearchPer3){
                result("Top 15 Team By "+ perBoxPerformanceS3 , database.teamPerTop15HitRate(perBoxPerformanceS3, perBoxSeasonI3));

            }//which posistion good at what
            else if(e.getSource()==perBoxPerformance4){
                perBoxPerformanceS4 = (String)perBoxPerformance4.getSelectedItem();
            }else if(e.getSource()==perBoxSeason4){
                perBoxSeasonI4 = (Integer)perBoxSeason4.getSelectedItem();
            }else if(e.getSource()==bSearchPer4){
                result("Position Advantage - " + perBoxPerformanceS4, database.bestPerformanceByPosition(perBoxPerformanceS4, perBoxSeasonI4));
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }// end of actionperformance

    /**
     * create window which contains the searching result
     * 
     * @param title
     * @param result
     */
    private JButton export = null;
    public void result(String title, ResultSet result) throws Exception {

        JFrame resultFrame = new JFrame();
        resultFrame.setLayout(new GridLayout(2,1));
        JTable table = null;
        JScrollPane scroll = null;
        // export = new JButton("Export");
        resultFrame.setIconImage(logo.getImage());// set frame icon
            resultFrame.setTitle(title);
            resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            // resultFrame.getContentPane().setBackground(new Color(255, 255, 255));
            resultFrame.setPreferredSize(new Dimension(500, 500));
            resultFrame.setLayout(new GridLayout());

            // System.out.println(result == null);
            if (result != null && result.isBeforeFirst()) {

                // create resultTbale for dispaly
                table = resultTable(result);

                scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// add to scroll bar
                resultFrame.add(scroll);
                // resultFrame.add(export);
                resultFrame.pack();
                resultFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(resultFrame, "No Result");
            }

            // resultFrame.add(new JButton("laal"));

    }//end of result

    /**
     * create seach player window
     * 
     * @param title window title
     * @param frame searchPplyer frame
     */
    private void createSearchWindowP(String title, JFrame frame) throws Exception {

        frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setResizable(false);
        frame.setTitle(title);
        frame.setLayout(new GridLayout(3, 1));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIconImage(logo.getImage());// set frame icon
        // search player by name
        JLabel lable1 = new JLabel("<html>Search Player By Name<br/>(FirstName LastName)</html>");
        pSearchPlayer = new JPanel();
        pSearchPlayer.setLayout(new GridBagLayout());
        pSearchPlayer.setBounds(0, 0, 800, 200);
        playerNameBox = new JTextField();
        playerNameBox.setPreferredSize(new Dimension(250, 40));
        bSearchPlayerName = new JButton("Submit");
        bSearchPlayerName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bSearchPlayerName.addActionListener(this);// add action to button
        pSearchPlayer.add(lable1);
        pSearchPlayer.add(Box.createHorizontalStrut(10));
        pSearchPlayer.add(playerNameBox);
        pSearchPlayer.add(Box.createHorizontalStrut(10));
        pSearchPlayer.add(bSearchPlayerName);
        pSearchPlayer.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        // list player by team
        JLabel label2 = new JLabel("<html>Search Player By Team<br/>(Team,Season)");
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        bSearchPlayerByTeam = new JButton("Submit");
        bSearchPlayerByTeam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bSearchPlayerByTeam.addActionListener(this);
        String[] team = new String[] { "Hawks", "Celtics", "Pelicans", "Bulls",
                "Mavericks", "Nuggets", "Rockets",
                "Clippers", "Lakers", "Heat",
                "Bucks", "Timberwolves", "Nets",
                "Knicks", "Magic", "Pacers",
                "76ers", "Suns", "Trail Blazers",
                "Kings", "Spurs", "Thunder",
                "Raptors", "Jazz", "Grizzlies", "Wizards",
                "Pistons", "Hornets", "Cavaliers", "Warriors" };
        Integer[] season = new Integer[] { 2018, 2019 };
        pBoxT = new JComboBox<String>(team);
        pBoxS = new JComboBox<Integer>(season);
        pBoxT.addActionListener(this);
        pBoxS.addActionListener(this);
        pBoxSeason = (Integer) pBoxS.getSelectedItem();
        pBoxTeam = (String) pBoxT.getSelectedItem();
        panel2.add(label2);
        panel2.add(Box.createHorizontalStrut(10));
        panel2.add(pBoxT);
        panel2.add(Box.createHorizontalStrut(10));
        panel2.add(pBoxS);
        panel2.add(Box.createHorizontalStrut(10));
        panel2.add(bSearchPlayerByTeam);

        //list all game one player played
        JLabel label3 = new JLabel("<html>Game A Player Attend<br/>(Player Name)");
        JPanel panel3 = new JPanel();
        pBoxS3 = new JComboBox<Integer>(season);
        pBoxS3.addActionListener(this);
        pBoxSeason3 =(Integer) pBoxS3.getSelectedItem();
        panel3.setLayout(new GridBagLayout());
        playerNameBox3 = new JTextField();
        playerNameBox3.setPreferredSize(new Dimension(250, 40));
        bSearchP3 = new JButton("Submit");
        bSearchP3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bSearchP3.addActionListener(this);// add action to button

        panel3.add(label3);
        panel3.add(Box.createHorizontalStrut(10));
        panel3.add(playerNameBox3);
        panel3.add(Box.createHorizontalStrut(10));
        panel3.add(pBoxS3);
        panel3.add(Box.createHorizontalStrut(10));
        panel3.add(bSearchP3);
        panel3.setBorder(BorderFactory.createLineBorder(Color.lightGray));


        frame.add(pSearchPlayer);
        frame.add(panel2);
        frame.add(panel3);
        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * create the window for search for Team
     * 
     * @param title window title
     * @param frame window frame.
     */
    private void createSearchWindowT(String title, JFrame frame) throws Exception {

        frame = new JFrame();
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setResizable(false);
        frame.setTitle(title);
        frame.setLayout(new GridLayout(3, 1));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIconImage(logo.getImage());// set frame icon

        JLabel label2 = new JLabel("Search Team By Name");
        pSearchTeam = new JPanel();
        pSearchTeam.setLayout(new GridBagLayout());
        pSearchTeam.setBounds(0, 200, 800, 200);
        teamNameBox = new JTextField();
        teamNameBox.setPreferredSize(new Dimension(250, 40));

        bSearchTeamName = new JButton("Submit");
        bSearchTeamName.addActionListener(this);
        pSearchTeam.add(label2);
        pSearchTeam.add(Box.createHorizontalStrut(10));
        pSearchTeam.add(teamNameBox);        
        pSearchTeam.add(Box.createHorizontalStrut(10));
        pSearchTeam.add(bSearchTeamName);
        pSearchTeam.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        //list all teams
        bsearchTeam2 = new JButton("Show All");
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        JLabel lable2 = new JLabel("List All Teams");
        panel2.setLayout(new GridBagLayout());
        panel2.setBounds(0, 200, 800, 200);
        bsearchTeam2.addActionListener(this);
        panel2.add(lable2);
        panel2.add(Box.createHorizontalStrut(10));
        panel2.add(bsearchTeam2);

        //list of win rate
        bsearchTeam3 = new JButton("Submit");
        JPanel panel3 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        JLabel lable3 = new JLabel("Team Win Rate");
        panel3.setLayout(new GridBagLayout());
        panel3.setBounds(0, 200, 800, 200);
        bsearchTeam3.addActionListener(this);
        Integer[] season = new Integer[] { 2018, 2019 };
        tBoxS3 = new JComboBox<Integer>(season);
        tBoxS3.addActionListener(this);
        tBoxS3I = (Integer) tBoxS3.getSelectedItem();
        panel3.add(lable3);
        panel3.add(Box.createHorizontalStrut(10));
        panel3.add(tBoxS3);
        panel3.add(Box.createHorizontalStrut(10));
        panel3.add(bsearchTeam3);

        frame.setJMenuBar(menuBar);
        frame.add(panel2);
        frame.add(pSearchTeam);
        frame.add(panel3);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * create the window for search for performance
     * 
     * @param title window title
     * @param frame window frame.
     */
    
    private void createSearchWindowPER(String title, JFrame frame) {

        frame = new JFrame();
        frame.setLayout(new GridLayout(4,1));
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setResizable(false);
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIconImage(logo.getImage());// set frame icon

        //display players whose performan is in top 5 in a team or in the league
        JLabel label1 = new JLabel("<html>Top 5 Player<br/>Each Team</html>");
        JPanel panel1 = new JPanel();
        bSearchPer1 = new JButton("Submit");

        panel1.setLayout(new GridBagLayout());
        panel1.setBounds(0, 200, 800, 200);
        panel1.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        bSearchPer1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bSearchPer1.addActionListener(this);
        String[] team = new String[] { "Hawks", "Celtics", "Pelicans", "Bulls",
                "Mavericks", "Nuggets", "Rockets",
                "Clippers", "Lakers", "Heat",
                "Bucks", "Timberwolves", "Nets",
                "Knicks", "Magic", "Pacers",
                "76ers", "Suns", "Trail Blazers",
                "Kings", "Spurs", "Thunder",
                "Raptors", "Jazz", "Grizzlies", "Wizards",
                "Pistons", "Hornets", "Cavaliers", "Warriors" };
        Integer[] season = new Integer[] { 2018, 2019 };
        String[] performance = new String[]{"field_goal","field_goal_attempts",
                "personal_fouls","turnovers","three_point","three_point_attempts",
                "blocks","steals","free_throws_attempts","assists","total_rebounds",
                "two_point_attempts","defensive_rebounds","free_throws",
                "offensive_Rebounds","three_point_percent","two_point_percent",
                "player_score"};

        //combo box
        perBoxTeam = new JComboBox<String>(team);
        perBoxSeason = new JComboBox<Integer>(season);
        perBoxPerformance = new JComboBox<String>(performance);
        perBoxTeam.addActionListener(this);
        perBoxSeason.addActionListener(this);
        perBoxPerformance.addActionListener(this);
        perBoxTeamS = (String)perBoxTeam.getSelectedItem();
        perBoxSeasonI = (Integer)perBoxSeason.getSelectedItem();
        perBoxPerfomanceS = (String)perBoxPerformance.getSelectedItem();
        panel1.add(label1);
        panel1.add(Box.createHorizontalStrut(10));
        panel1.add(perBoxTeam);
        panel1.add(Box.createHorizontalStrut(10));
        panel1.add(perBoxSeason);
        panel1.add(Box.createHorizontalStrut(10));
        panel1.add(perBoxPerformance);
        panel1.add(Box.createHorizontalStrut(10));
        panel1.add(bSearchPer1);
        panel1.add(Box.createHorizontalStrut(10));

        //top 15 team by performance
        JLabel label2 = new JLabel("<html>Top 15 Team<br/>By Performance</html>");
        JPanel panel2 = new JPanel();
        bSearchPer2 = new JButton("Submit");

        panel2.setLayout(new GridBagLayout());
        panel2.setBounds(0, 200, 800, 200);
        panel2.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        String[] performance2 = new String[]{"field_goal","field_goal_attempts",
        "personal_fouls","turnovers","three_point","three_point_attempts",
        "blocks","steals","free_throws_attempts","assists","total_rebounds",
        "two_point_attempts","defensive_rebounds","free_throws",
        "offensive_Rebounds","player_score"};

        bSearchPer2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bSearchPer2.addActionListener(this);
        perBoxSeason2 = new JComboBox<Integer>(season);
        perBoxPerformance2 = new JComboBox<String>(performance2);
        perBoxSeason2.addActionListener(this);
        perBoxPerformance2.addActionListener(this);
        perBoxSeaonI2 = (Integer)perBoxSeason2.getSelectedItem();
        perBoxPerformanceS2 = (String)perBoxPerformance2.getSelectedItem();
        panel2.add(label2);
        panel2.add(Box.createHorizontalStrut(10));
        panel2.add(perBoxSeason2);
        panel2.add(Box.createHorizontalStrut(10));
        panel2.add(perBoxPerformance2);
        panel2.add(Box.createHorizontalStrut(10));
        panel2.add(bSearchPer2);

        //top 15 team of hit rate
        JLabel label3 = new JLabel("<html>Top 15 Team<br/>By Hit Rate");
        JPanel panel3 = new JPanel();
        bSearchPer3 = new JButton("Submit");
        panel3.setLayout(new GridBagLayout());
        panel3.setBounds(0, 200, 800, 200);
        panel3.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        String[] performance3 = new String[]{"three_point_percent","two_point_percent"};
        bSearchPer3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bSearchPer3.addActionListener(this);
        perBoxSeason3 = new JComboBox<Integer>(season);
        perBoxPerformance3 = new JComboBox<String>(performance3);
        perBoxSeason3.addActionListener(this);
        perBoxPerformance3.addActionListener(this);
        perBoxSeasonI3 = (Integer)perBoxSeason3.getSelectedItem();
        perBoxPerformanceS3 = (String)perBoxPerformance3.getSelectedItem();
        panel3.add(label3);
        panel3.add(Box.createHorizontalStrut(10));
        panel3.add(perBoxSeason3);
        panel3.add(Box.createHorizontalStrut(10));
        panel3.add(perBoxPerformance3);
        panel3.add(Box.createHorizontalStrut(10));
        panel3.add(bSearchPer3);

        //top performance each position
        JLabel label4 = new JLabel("<html>Fields that Different<br/>Position Good At");
        JPanel panel4 = new JPanel();
        bSearchPer4 = new JButton("Submit");
        panel4.setLayout(new GridBagLayout());
        panel4.setBounds(0, 200, 800, 200);
        panel4.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        bSearchPer4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bSearchPer4.addActionListener(this);

        perBoxSeason4 = new JComboBox<Integer>(season);
        perBoxPerformance4 = new JComboBox<String>(performance);
        perBoxSeason4.addActionListener(this);
        perBoxPerformance4.addActionListener(this);
        perBoxSeasonI4 = (Integer)perBoxSeason4.getSelectedItem();
        perBoxPerformanceS4 = (String)perBoxPerformance4.getSelectedItem();
        panel4.add(label4);
        panel4.add(Box.createHorizontalStrut(10));

        panel4.add(perBoxSeason4);
        panel4.add(Box.createHorizontalStrut(10));

        panel4.add(perBoxPerformance4);
        panel4.add(Box.createHorizontalStrut(10));

        panel4.add(bSearchPer4);

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * This method create the window for menu that need a new window to display.
     * 
     * @param title window title
     * @param text  text in the window
     */
    public void createMenuPage(String title, String text) {
        JLabel content = null;
        menuFrame = new JFrame();
        menuFrame.setTitle(title);
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuFrame.getContentPane().setBackground(new Color(255, 255, 255));
        menuFrame.setSize(500, 500);
        menuFrame.setIconImage(logo.getImage());// set frame icon
        if (text != null) {
            content = new JLabel();
            content.setIcon(logo);
            content.setText(text);
            content.setFont(new Font("Serif", Font.PLAIN, 22));
            content.setVerticalTextPosition(JLabel.CENTER);
            // content.setHorizontalTextPosition(JLabel.CENTER);
            menuFrame.add(content);
            menuFrame.setVisible(true);
        }
    }

    /**
     * resultTable create an JTable output to window
     * 
     * @param result resultset
     * @return Jtable to output
     * @throws SQLException
     */
    private JTable resultTable(ResultSet result) throws SQLException {
        String[] colName;
        String[][] data;
        JTable table;
        ResultSetMetaData metaData = result.getMetaData();
        int col = metaData.getColumnCount();

        result.last();// move to last line of result set
        int row = result.getRow();// get row number
        result.beforeFirst();// move cursor back before 1st line.

        colName = new String[col];
        data = new String[row][col];
        for (int i = 1; i <= col; i++) {
            colName[i - 1] = metaData.getColumnName(i);
        }

        int i = 0;
        while (result.next()) {
            for (int j = 1; j <= col; j++) {
                data[i][j - 1] = result.getString(metaData.getColumnName(j));
            }
            i++;
        }

        table = new JTable(data, colName);
        //instance table model and disbale editable table
        DefaultTableModel tableModel = new DefaultTableModel(data, colName) {
        @Override
        public boolean isCellEditable(int row, int column) {
           //all cells false
           return false;
        }
    };
        table.setModel(tableModel);
        return table;
    }

    // set menu for the window of menubar and search window.
    private void setMenu() {
        // add menu bar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(aboutMenu);

        // add menu bar items
        team.addActionListener(this);
        aboutMenu.add(team);
        description.addActionListener(this);
        aboutMenu.add(description);
        exit.addActionListener(this);
        fileMenu.add(exit);
        contact.addActionListener(this);
        helpMenu.add(contact);
    }

    public static boolean exportToCSV(JTable tableToExport, String pathToExportTo) {

    try {

        TableModel model = tableToExport.getModel();
        FileWriter csv = new FileWriter(new File(pathToExportTo));

        for (int i = 0; i < model.getColumnCount(); i++) {
            csv.write(model.getColumnName(i) + ",");
        }

        csv.write("\n");

        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                csv.write(model.getValueAt(i, j).toString() + ",");
            }
            csv.write("\n");
        }

        csv.close();
        return true;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}

    public JFrame getFrame() {
        return main;
    }
}
