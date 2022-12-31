import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage extends JFrame implements ActionListener{
    private ImageIcon logo = new ImageIcon("logo.png");
    private JButton button = new JButton();
    private dataBase database;
    public LaunchPage(){
        this.setTitle("NBA");
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit window
        this.setResizable(false);
        this.setIconImage(logo.getImage());//set frame icon
        this.getContentPane().setBackground(new Color(255,255,255));
        database = new dataBase();
    }

    public void welcomePage(){
        JLabel label = new JLabel();

        button.setText("Next");
        button.setFont(new Font("Serif", Font.PLAIN,14));
        button.setBounds(600,600,100,50);
        button.addActionListener(this);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setVerticalAlignment(JButton.CENTER);
        button.setFocusable(false);
        button.setBackground(Color.lightGray);


        label.setIcon(logo);
        label.setText("<html>Welcome to Comp 3380 - G66 Project <br/>Please Click Next To Continue.</html>");
        label.setFont(new Font("Serif",Font.PLAIN,22));
        //set position of lable.
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setIconTextGap(0);


        this.add(button);

        this.add(label);


        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            this.dispose();
           new mainPage(database);

        }
    }

}
