import java.awt.*;
import java.awt.event.*;
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


        ImageIcon imageIcon = new ImageIcon("BG.png");
        // f.setIconImage(imageIcon.getImage());
        JLabel BackGround = new JLabel(imageIcon);
        // BackGround.setIcon(imageIcon);
        BackGround.setBounds(0, 0, 500, 500);
        // f.getContentPane().add(BackGround);


        JPanel p = (JPanel)f.getContentPane();
        p.setOpaque(false);
        Font font = new Font("华文隶书", Font.BOLD, 50);
        JLabel name = new JLabel("中国象棋");
        name.setFont(font);
        p.add(name);
        b1 = new JButton("开始游戏");
        b2 = new JButton("退出游戏");
        b1.setBounds(200,200,100,40);
        b1.setFocusable(false);
        b2.setBounds(200,300,100,40);
        b2.setFocusable(false);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChineseChess playing = new ChineseChess();
                playing.main(null);
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        name.setBounds(150,100,400,40);


        p.add(b1);
        p.add(b2);

        f.getLayeredPane().add(BackGround, new Integer(Integer.MIN_VALUE));
        f.setVisible(true);
        f.addWindowListener(new WinListener());
    }
}
