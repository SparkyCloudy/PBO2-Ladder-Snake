/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.pnb.SnakeUp;

import id.ac.pnb.SnakeUp.components.GamePanel;
import id.ac.pnb.SnakeUp.components.GameWindow;
import id.ac.pnb.SnakeUp.components.MainLoginPanel;
import id.ac.pnb.SnakeUp.utils.GlobalVars;

/**
 *
 * @author Firdaus
 */

public class Login {
     private GamePanel panel;
     private GameWindow window;
     
     public Login() {
   _initialize();
    
}
  private void _initialize() {
  
   
    this.window = new GameWindow(this.panel);
   
    
  }
    
}
