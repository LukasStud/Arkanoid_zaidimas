package Zaidimas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement extends JPanel implements KeyListener, ActionListener {

  private Timer time;

  private boolean running = false;

  private Map map;

  public Movement() {
    map = new Map();
    addKeyListener(this);
    setFocusable(true);
    time = new Timer(4, this);
    time.start();
  }

  @Override
  public void paint(Graphics g) {
    map.drawBackground(g);
    map.drawBall(g);
    map.drawBlocks((Graphics2D) g);
    map.drawPlayer(g);
    map.drawBorder(g);
    if (map.getBallY() > 550) {
      running = false;
      map.drawLose(g);
    }
    if (map.getTotalBlocks() == 0) {
      running = false;
      map.drawWin(g);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (running) {
      if (new Rectangle(map.getBallX(), map.getBallY(), 20, 20)
          .intersects(new Rectangle(map.getPlayerX(), 550, 100, 8))) {
        map.setBallMovementY(-map.getBallMovementY());
      }

      for (int i = 0; i < map.getBlock().length; i++) {
        for (int j = 0; j < map.getBlock()[0].length; j++) {
          if (map.getBlock()[i][j] != 0) {
            int blockX = j * map.getBlockWidth();
            int blockY = i * map.getBlockHeight();
            int blockWidth = map.getBlockWidth();
            int blockHeight = map.getBlockHeight();

            Rectangle ballRect = new Rectangle(map.getBallX(), map.getBallY(), 20, 20);
            Rectangle blockRect = new Rectangle(blockX, blockY, blockWidth, blockHeight);

            if (ballRect.intersects(blockRect)) {
              map.setBlockValue(0, i, j);
              map.setTotalBlocks(map.getTotalBlocks() - 1);

              if (map.getBallX() + 20 <= blockRect.x
                  || map.getBallX() + 20 >= blockRect.x + blockRect.width) {
                map.setBallMovementX(-map.getBallMovementX());
              } else {
                map.setBallMovementY(-map.getBallMovementY());
              }
            }
          }
        }
      }

      map.setBallX(map.getBallX() + map.getBallMovementX());
      map.setBallY(map.getBallY() + map.getBallMovementY());
      if (map.getBallX() < 0) {
        map.setBallMovementX(-map.getBallMovementX());
      }
      if (map.getBallY() < 0) {
        map.setBallMovementY(-map.getBallMovementY());
      }
      if (map.getBallX() > 660) {
        map.setBallMovementX(-map.getBallMovementX());
      }
    }
    repaint();
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_RIGHT:
        if (map.getPlayerX() >= 600) {
          map.setPlayerX(600);
        } else {
          moveRight();
        }
        break;
      case KeyEvent.VK_LEFT:
        if (map.getPlayerX() <= 0) {
          map.setPlayerX(0);
        } else {
          moveLeft();
        }
        break;
      case KeyEvent.VK_A:
        if (map.getPlayerX() <= 0) {
          map.setPlayerX(0);
        } else {
          moveLeft();
        }
        break;
      case KeyEvent.VK_D:
        if (map.getPlayerX() >= 600) {
          map.setPlayerX(600);
        } else {
          moveRight();
        }
        break;
      case KeyEvent.VK_ESCAPE:
        if (!running) {
          System.exit(0);
        }
        break;
    }
  }

  public void moveRight() {
    running = true;
    map.setPlayerX(map.getPlayerX() + map.getPlayerSpeed());
  }

  public void moveLeft() {
    running = true;
    map.setPlayerX(map.getPlayerX() - map.getPlayerSpeed());
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e) {}
}
