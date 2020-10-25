package Zaidimas;

import javax.swing.JFrame;

public class Main {

  public static void main(String[] args) {
    JFrame obj = new JFrame();
    Movement arkanoid = new Movement();
    obj.setBounds(600, 100, 700, 600);
    obj.setTitle("Arkanoid");
    obj.setVisible(true);
    obj.add(arkanoid);
  }
}
