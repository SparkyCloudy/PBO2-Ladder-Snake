package id.ac.pnb.SnakeUp.panels;

import com.mysql.cj.log.Log;
import id.ac.pnb.SnakeUp.components.GamePanel;
import id.ac.pnb.SnakeUp.components.GameWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainMenu extends GamePanel {

    private JButton Exit_button;
    private JButton Leaderboard_button;
    private JButton Login_button;
    private JLabel MenuTittle;
    private JButton Play_button;
    private JLabel Player1Label;
    private JLabel Player1_name;
    private JLabel Player2Label;
    private JLabel Player2_name;
    private GamePanel panel;
    private GameWindow window;
    private boolean play = false;
    private boolean leaderboard = false;

    public MainMenu() {
        _initialize();

        Play_button.addActionListener((ActionEvent e) -> {
            play = true ;
        });

        Leaderboard_button.addActionListener((ActionEvent e) -> {
            leaderboard = true;

        });

        Exit_button.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

    private void _initialize(){

        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(1050,200));
        header.setLayout(new BoxLayout(header,BoxLayout.PAGE_AXIS));

        MenuTittle = new JLabel("SNAKE UP");
        MenuTittle.setFont(new Font("Arial",Font.PLAIN,50));
        MenuTittle.setAlignmentX(CENTER_ALIGNMENT);

        header.add(Box.createVerticalGlue());
        header.add(MenuTittle);
        super.add(header);

        EmptyBorder padding = new EmptyBorder(50,50,50,50);
        JPanel content = new JPanel(new GridLayout(0,2,0,10));
        content.setBorder(padding);
        header.setPreferredSize(new Dimension(1050,100));

        Play_button = new JButton("Play");
        Play_button.setFont(new Font("Arial",Font.PLAIN,50));
        Leaderboard_button = new JButton("Leaderboard");
        Leaderboard_button.setFont(new Font("Arial",Font.PLAIN,50));
        Login_button = new JButton("Login");
        Login_button.setFont(new Font("Arial",Font.PLAIN,50));
        Exit_button = new JButton("Exit");
        Exit_button.setFont(new Font("Arial",Font.PLAIN,50));

        content.add(Play_button);
        content.add(Leaderboard_button);
        content.add(Login_button);
        content.add(Exit_button);
        super.add(content);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.setPreferredSize(new Dimension(1050,100));
        JPanel player = new JPanel(new GridLayout(0,2,0,10));

        Player1Label = new JLabel("Player 1 : ");
        Player1Label.setFont(new Font("Arial",Font.PLAIN,30));
        Player1_name = new JLabel("");
        Player1_name.setFont(new Font("Arial",Font.PLAIN,30));
        Player2Label = new JLabel("Player 2 : ");
        Player2Label.setFont(new Font("Arial",Font.PLAIN,30));
        Player2_name = new JLabel("");
        Player2_name.setFont(new Font("Arial",Font.PLAIN,30));


        player.add(Player1Label);
        player.add(Player1_name);
        player.add(Player2Label);
        player.add(Player2_name);


        footer.add(player);
        super.add(footer);
    }
    private void _leaderboard() {
        this.panel = new LeaderBoard();
        this.window = new GameWindow(panel);
    }

    public boolean retrun() {
      return play ;
    }
    public boolean leader() {
      return leaderboard ;
    }



    @Override
    public void updateGame() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
