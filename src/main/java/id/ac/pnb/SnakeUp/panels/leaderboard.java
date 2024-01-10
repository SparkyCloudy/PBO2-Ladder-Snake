/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.pnb.SnakeUp.panels;

import javax.swing.*;
import id.ac.pnb.SnakeUp.components.GamePanel;
import id.ac.pnb.SnakeUp.database.DatabaseConnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author BAGASKARA
 */
public class leaderboard extends GamePanel{
    
    public leaderboard(){
       _initialize();
    }
    
    public void Data(){
        DatabaseConnection conn = new DatabaseConnection();
    }
    public void _initialize(){
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
        
        EmptyBorder padding = new EmptyBorder(50,50,50,50);
        JPanel content = new JPanel(new GridLayout(0, 1, 0, 10));
        content.setBorder(padding);
        content.setPreferredSize(new Dimension(1050,500));
        for (int i = 1; i <= 5; i++) {
            JPanel box = new JPanel();
            box.setLayout(new GridLayout(2,5));
            box.setPreferredSize(new Dimension(800,50));
            box.setBackground(Color.WHITE);
            
            String th[] = {"Ranking","Username","Match","Win","Win Rate"};
            for (int j = 0; j < th.length; j++) {
                JPanel isi = new JPanel(new FlowLayout(FlowLayout.CENTER));
                isi.add(new JLabel(""+th[j]));
                isi.setBackground(Color.white);
                box.add(isi);
            }
            
            String td[] = {"","Bagaskara","2000","1500","70%"};
            td[0] = ""+i;
            for (int j = 0; j < 5; j++) {
                JPanel isi = new JPanel(new FlowLayout(FlowLayout.CENTER));
                isi.add(new JLabel(""+td[j]));
                isi.setBackground(Color.white);
                box.add(isi);
            }
            
            content.add(box);
        }
        super.add(content);
        
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(1050,100));
        
        JButton back = new JButton("BACK");
        back.setPreferredSize(new Dimension(200,50));
        back.setBackground(Color.DARK_GRAY);
        back.setForeground(Color.WHITE);
        footer.add(back);
        super.add(footer);
        
    }

    @Override
    public void updateGame() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     
}
