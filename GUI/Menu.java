import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener{
    private JFrame myFrame;
    //menubar
    private JMenuBar menuBar = new JMenuBar();

    //menu selection
    private JMenu fileMenu = new JMenu("File");
    private JMenu helpMenu = new JMenu("Help");
    private JMenu aboutMenu = new JMenu("About");
    //menu items
    private JMenuItem team = new JMenuItem("Members");
    private JMenuItem description = new JMenuItem("Description");
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem contact = new JMenuItem("Customer Service");

    private mainPage main;
    public Menu(mainPage main){
        myFrame = main.getFrame();
        this.main= main;
        //add menu bar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(aboutMenu);


        //add menu bar items
//        team = new JMenuItem("Members");
        team.addActionListener(this);
        aboutMenu.add(team);
//        description = new JMenuItem("Description");
        description.addActionListener(this);
        aboutMenu.add(description);
//        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        fileMenu.add(exit);
//        contact = new JMenuItem("Customer Service");
        contact.addActionListener(this);
        helpMenu.add(contact);

        myFrame.setJMenuBar(menuBar);

    }


    public Menu(JFrame frame){

        myFrame = frame;

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(aboutMenu);


        //add menu bar items
//        team = new JMenuItem("Members");
        team.addActionListener(this);
        aboutMenu.add(team);
//        description = new JMenuItem("Description");
        description.addActionListener(this);
        aboutMenu.add(description);
//        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        fileMenu.add(exit);
//        contact = new JMenuItem("Customer Service");
        contact.addActionListener(this);
        helpMenu.add(contact);

        myFrame.setJMenuBar(menuBar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = null, title = null;
        //check menu bar action
        if(e.getSource() == team){//show result of Team in About menu
            text = "<html>Xin Nie\t7640563<br/>Wanqi Li\t7908738<br/>Fengfan Bian\t7924160</html>";
            title = "Team Members";
            main.createMenuPage(title,text);
        }else if(e.getSource() == description){//show description in About menu
            title = "Description";
            text = "<html>This database contains the match data of 2018 and 2019 season NBA games. The dataset includes daily match results, team performance, player performance, daily team ranking, list of teams, team staff, and players.</html>";
            main.createMenuPage(title, text);
        }else if(e.getSource() == contact){//show contact in help menu
            title = "Customer Service";
            text = "<html>24/7 Hotline:<br/> Xin Nie: 204-290-1236<br/>Fengfan Bian: 431-990-1413<br/>Wanqi Li: 604-991-6688</html>";
            main.createMenuPage(title, text);
        }else if(e.getSource() == exit){//exit in file mneu
            System.exit(0);
        }
    }
}
