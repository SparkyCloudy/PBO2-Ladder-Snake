/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.pnb.SnakeUp.panels;

import id.ac.pnb.SnakeUp.Game;
import id.ac.pnb.SnakeUp.components.GamePanel;
import id.ac.pnb.SnakeUp.components.GameWindow;
import id.ac.pnb.SnakeUp.database.DatabaseConnection;
import java.awt.*;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author BAGASKARA
 */
public class LeaderBoard extends GamePanel{
    
    private Connection conn = null;
    private PreparedStatement p = null;
    private ResultSet r = null;
    private JButton back;
    private boolean hide= false;
    
    public LeaderBoard(){
        _initialize();
    }
    
    public void content(){
        
        try {
            conn = DatabaseConnection.getInstance().getConnection();
            p = conn.prepareStatement("SELECT winrate.match,winrate.gameWin,user.username FROM winrate INNER JOIN user ON winrate.id_user = user.id_user ORDER BY gameWin DESC LIMIT 5");
            r = p.executeQuery();

            EmptyBorder padding = new EmptyBorder(50,50,50,50);
            JPanel content = new JPanel(new GridLayout(5, 1, 0, 10));
            content.setBorder(padding);
            content.setPreferredSize(new Dimension(1050,500));
 
            int rank = 1;
            
            while (r.next()) {
                JPanel box = new JPanel();
                box.setLayout(new GridLayout(2,5));
                box.setPreferredSize(new Dimension(800,50));
                box.setBackground(Color.WHITE);

                String field[] = {"Ranking","Username","Match","Win","Persentase"};
                for (int j = 0; j < field.length; j++) {
                    box.add(isiContent(field[j]));
                }
                
                String ranking = rank+"";
                String username = r.getString("username");
                String match = r.getString("match");
                String gameWin = r.getString("gameWin");
                String persentase = persentase(r.getInt("gameWin"),r.getInt("match"))+"%";

                box.add(isiContent(ranking));
                box.add(isiContent(username));
                box.add(isiContent(match));
                box.add(isiContent(gameWin));
                box.add(isiContent(persentase));
                
                
                
                content.add(box);
                rank++;
            }
            super.add(content);
            
        } catch (SQLException e) {
            e.getErrorCode();
        }
    }
    
    private JPanel isiContent(String data){
        JPanel isi = new JPanel(new FlowLayout(FlowLayout.CENTER));
        isi.add(new JLabel(""+data));
        isi.setBackground(Color.white);
        return isi;
    }
    
    
    private int persentase(int win,int pertandingan){
        return (int) Math.round(((double)win/pertandingan)*100);
    }
    
    public void _initialize(){
        //header
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(1050,100));
        header.setLayout(new BoxLayout(header,BoxLayout.PAGE_AXIS));
        JLabel text = new JLabel("TOP 5");
        text.setFont(new Font("Arial",Font.PLAIN,50));
        text.setAlignmentX(CENTER_ALIGNMENT);
        header.add(Box.createVerticalGlue());
        header.add(text);
        header.add(Box.createVerticalGlue());
        header.add(text);
        super.add(header);
        
        //content  
        content();
        
        //footer
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(1050,100));
        
        back = new JButton("back");
        back.setPreferredSize(new Dimension(400,50));
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.WHITE);
        
        back.addActionListener((ActionEvent e) -> {
            hide = true ;
        });
        footer.add(back);
        super.add(footer);
        
    }

    @Override
    public void updateGame() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean backMenu() {
      return hide;
    }
     
}
