import javax.swing.*;

public class ChineseChess {
    public static void main(String[] args) {
        JFrame fr = new JFrame("中国象棋");
        // 设置界面大小
        fr.setSize(900,800);
        // 设置界面居中
        fr.setLocationRelativeTo(null);
        // 增加窗口关闭功能
        fr.addWindowListener(new WinListener());
        // 将游戏面板加入界面
        fr.setContentPane(new UIPanel());
        fr.setVisible(true);
    }
}
