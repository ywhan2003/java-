import java.awt.*;
import javax.swing.*;

public class Start {
    private JFrame f;
    private JButton b1;
    private JButton b2;

    public static void main(String args[]) {
        Start guiWindow = new Start();
        guiWindow.go();
    }
    public void go() {
        f = new JFrame("中国象棋");
        f.setSize(500,500);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        Font font = new Font("华文隶书", Font.BOLD, 50);
        JLabel name = new JLabel("中国象棋");
        name.setFont(font);
        JLabel Pic = new JLabel(new ImageIcon("E:/Desktop/Programme/Java/Chess/src/BG.png"));
        Pic.setSize(500,500);
        b1 = new JButton("开始游戏");
        b2 = new JButton("退出游戏");
        b1.setBounds(200,200,100,40);
        b1.setFocusable(false);
        b2.setBounds(200,300,100,40);
        b2.setFocusable(false);
        name.setBounds(150,100,400,40);
        f.add(Pic);
        f.add(b1);
        f.add(b2);
        f.add(name);
        f.setVisible(true);
        f.addWindowListener(new WinListener());
    }
}
