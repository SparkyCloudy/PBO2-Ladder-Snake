/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.pnb.SnakeUp.components;
import javax.swing.JOptionPane;

public class WinPopup {
    public static void showWinPopup(String winnerName) {
        String message = "Player " + winnerName + " wins!";
        JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
}
