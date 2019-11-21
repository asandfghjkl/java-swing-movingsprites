package id.ac.its.sandra.movingsprites;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame {

    public Game() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setSize(400, 300);
        setResizable(false);
        
        setTitle("Shooting Missiles Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
        	Game ex = new Game();
            ex.setVisible(true);
        });
    }
}