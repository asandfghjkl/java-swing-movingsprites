package id.ac.its.sandra.movingsprites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Board extends JPanel implements ActionListener {

    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int DELAY = 10;
    private Timer timer;
    private SpaceShip spaceShip;
    private List<Asteroid> asteroids;

    private final int[][] placement = {
            {2170, 30}, {1370, 76}, {660, 198},
            {770, 163}, {755, 65},  {490, 82},
            {610, 99},  {595, 37},  {930, 213},
            {825, 231}, {732, 184}, {396, 153},
    };
    
    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        spaceShip = new SpaceShip(ICRAFT_X, ICRAFT_Y);
        
        initAsteroids();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initAsteroids() {
        
        asteroids = new ArrayList<>();

        for (int[] p : placement) {
            asteroids.add(new Asteroid(p[0], p[1]));
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(spaceShip.getImage(), spaceShip.getX(),
                spaceShip.getY(), this);

        List<Missile> missiles = spaceShip.getMissiles();

        for (Missile missile : missiles) {
            
            g2d.drawImage(missile.getImage(), missile.getX(),
                    missile.getY(), this);
        }
        
        for (Asteroid asteroid : asteroids) {
                g2d.drawImage(asteroid.getImage(), asteroid.getX(), 
                		asteroid.getY(), this);
        }  
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        updateMissiles();
        updateSpaceShip();
        updateAsteroids();

        checkCollisions();
        
        repaint();
    }

    private void updateMissiles() {

        List<Missile> missiles = spaceShip.getMissiles();

        for (int i = 0; i < missiles.size(); i++) {

            Missile missile = missiles.get(i);

            if (missile.isVisible()) {

                missile.move();
            } else {

                missiles.remove(i);
            }
        }
    }

    private void updateSpaceShip() {

        spaceShip.move();
    }

    private void updateAsteroids() {
        for (int i = 0; i < asteroids.size(); i++) {

            Asteroid a = asteroids.get(i);
            
            if (a.isVisible()) {
                a.move();
            } else {
                asteroids.remove(i);
            }
        }
    }
    

    public void checkCollisions() {

        List<Missile> ms = spaceShip.getMissiles();

        for (Missile m : ms) {

            Rectangle r1 = m.getBounds();

            for (Asteroid asteroid : asteroids) {

                Rectangle r2 = asteroid.getBounds();

                if (r1.intersects(r2)) {
                    
                    m.setVisible(false);
                    asteroid.setVisible(false);
                }
            }
        }
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            spaceShip.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            spaceShip.keyPressed(e);
        }
    }
}