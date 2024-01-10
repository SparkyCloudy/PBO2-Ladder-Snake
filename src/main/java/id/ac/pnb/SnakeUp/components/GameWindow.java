package id.ac.pnb.SnakeUp.components;

import javax.swing.*;

public class GameWindow {

    private GamePanel panel;
    private JFrame frame;
//  private JLayeredPane bg;  

    public GameWindow(GamePanel panel) {
        this.panel = panel;
        frame = new JFrame();
        _initialize();
        
        
    }
    public void setPanel(GamePanel newPanel) {
        frame.getContentPane().removeAll();
        frame.add(newPanel);
//        frame.revalidate();
//        frame.repaint();
        _initialize();
        
    }

    private void _initialize() {
//         bg = new javax.swing.JLayeredPane();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(this.panel);
//       bg.setBackground(new java.awt.Color(255, 255, 255));
//        bg.setOpaque(true);
//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
//        frame.getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(bg)
//        );
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
//     public JLayeredPane bg() {
//         return bg;
//     }
//    private javax.swing.JLayeredPane bg;
}
