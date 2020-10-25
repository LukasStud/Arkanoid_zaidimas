package Zaidimas;

import java.awt.*;

public class Map {

  private int rows = 3;
  private int columns = 7;
  private int totalBlocks = rows * columns;

  private int[][] block = new int[rows][columns];
  private int blockWidth = 690 /columns;
  private int blockHeight = 120 / rows;

  private int ballMovementX = -1;
  private int ballMovementY = 2;

  private int playerX = 300;
  private int playerSpeed = 50;

  private int ballX = 400;
  private int ballY = 350;

  public Map() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        block[i][j] = 1;
      }
    }
  }

  public int getBlockWidth() {
    return blockWidth;
  }

  public int getBlockHeight() {
    return blockHeight;
  }

  public int[][] getBlock() {
    return block;
  }

  public int getTotalBlocks() {
    return totalBlocks;
  }

  public void setTotalBlocks(int totalBlocks) {
    this.totalBlocks = totalBlocks;
  }

  public int getBallMovementX() {
    return ballMovementX;
  }

  public void setBallMovementX(int ballMovementX) {
    this.ballMovementX = ballMovementX;
  }

  public int getBallMovementY() {
    return ballMovementY;
  }

  public void setBallMovementY(int ballMovementY) {
    this.ballMovementY = ballMovementY;
  }

  public int getPlayerX() {
    return playerX;
  }

  public void setPlayerX(int playerX) {
    this.playerX = playerX;
  }

  public int getBallX() {
    return ballX;
  }

  public void setBallX(int ballX) {
    this.ballX = ballX;
  }

  public int getBallY() {
    return ballY;
  }

  public void setBallY(int ballY) {
    this.ballY = ballY;
  }

  public int getPlayerSpeed() {
    return playerSpeed;
  }


  public void setBlockValue(int value, int row, int col) {
    block[row][col] = value;
  }

  public void drawBackground(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(1, 1, 692, 592);
  }

  public void drawBorder(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, 3, 592);
    g.fillRect(0, 0, 692, 3);
    g.fillRect(681, 0, 3, 592);
  }

  public void drawPlayer(Graphics g) {
    g.setColor(Color.blue);
    g.fillRect(playerX, 550, 100, 8);
  }

  public void drawBall(Graphics g) {
    g.setColor(Color.green);
    g.fillOval(ballX, ballY, 20, 20);
  }

  public void drawBlocks(Graphics2D g) {
    for (int i = 0; i < block.length; i++) {
      for (int j = 0; j < block[0].length; j++) {
        if (block[i][j] > 0) {
          g.setColor(Color.red);
          g.fillRect(j * blockWidth, i * blockHeight, blockWidth, blockHeight);
          g.setStroke(new BasicStroke(3));
          g.setColor(Color.black);
          g.drawRect(j * blockWidth, i * blockHeight, blockWidth, blockHeight);
        }
      }
    }
  }

  public void drawLose(Graphics g) {
    ballMovementX = 0;
    ballMovementY = 0;
    playerSpeed = 0;
    g.setColor(Color.green);
    g.setFont(new Font("Serif", Font.BOLD, 25));
    g.drawString("You lose", 300, 300);
    g.drawString("Press escape to exit", 250, 320);
  }

  public void drawWin(Graphics g) {
    ballMovementX = 0;
    ballMovementY = 0;
    playerSpeed = 0;
    g.setColor(Color.green);
    g.setFont(new Font("Serif", Font.BOLD, 25));
    g.drawString("You win", 300, 300);
    g.drawString("Press escape to exit", 250, 320);
  }
}
