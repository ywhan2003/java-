import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class UIPanel extends JPanel {
    // 保存所有棋子
    public Chess[] array = new Chess[32];
    public Chess[][] board = new Chess[10][10];
    UIPanel panel = this;

    // 当前选中的棋子
    private Chess selectedChess;

    // 当前玩家
    private int current = 0;
    // 默认红方先走

    // 标记游戏是否结束
    private int over = 0;

    private void finish() {
        current = 1 - current;
        selectedChess = null;
    }

    public UIPanel() {
        CreateChess();
        // 添加点击事件
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int gridx = Chess.getXFromXY(e.getX());
                int gridy = Chess.getYFromXY(e.getY());
                if (selectedChess == null){
                    // 第一次选择一个棋子
                    selectedChess = getChess(gridx, gridy);
                    if (selectedChess != null && selectedChess.getColor() != current || over != 0) {
                        selectedChess = null;
                    }
                }
                else {
                    Chess c = getChess(gridx, gridy);
                    if (c != null) {
                        // 第n次点击的时候有棋子
                        if (c.getColor() == selectedChess.getColor()){
                            // 重新选择
                            selectedChess = c;
                        }
                        else {
                            // 吃子
                            if (selectedChess.MoveOK(gridx, gridy, panel)) {
                                int index = c.getIndex();
                                if (array[index].getName().equals("将")) {
                                    over = 1;
                                }
                                else if (array[index].getName().equals("帥")) {
                                    over = 2;
                                }
                                array[index] = null;
                                selectedChess.setX(gridx);
                                selectedChess.setY(gridy);
                                board[gridx][gridy] = selectedChess;
                                finish();
                            }
                        }

                    }
                    else {
                        // 第n次点击的地方没有棋子
                        // 移动
                        if (selectedChess.MoveOK(gridx, gridy, panel)) {
                            selectedChess.setX(gridx);
                            selectedChess.setY(gridy);
                            board[gridx][gridy] = selectedChess;
                            finish();
                        }
                    }
                }
                // 刷新棋盘
                repaint();
            }
        });
    }

    private Chess getChess(int x, int y){
        for (int i=0; i<32; i++) {
            if (array[i] != null &&  array[i].getX() == x && array[i].getY() == y){
                return array[i];
            }
        }
        return null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // 绘制棋盘外圈大长方形
        int left = 40;
        int bottom = 80;
        int width = 500;
        int height = width/8*9;
        g.drawRect(left, bottom, width, height);


        // 绘制棋盘横线
        int Space = height / 9;
        for (int i=1; i<=9; i++) {
            g.drawLine(left ,bottom+i*Space, left+width, bottom+i*Space);
        }



        // 绘制棋盘竖线
        for (int i=1; i<=7; i++) {
            g.drawLine(left+i*Space, bottom+height, left+i*Space, bottom+5*Space);
        }
        for (int i=1; i<=7; i++) {
            g.drawLine(left+i*Space, bottom, left+i*Space, bottom+4*Space);
        }


        // 绘制斜线
        g.drawLine(left+3*Space, bottom+7*Space, left+5*Space, bottom+height);
        g.drawLine(left+3*Space, bottom+height, left+5*Space, bottom+7*Space);
        g.drawLine(left+3*Space, bottom, left+5*Space, bottom+2*Space);
        g.drawLine(left+3*Space, bottom+2*Space, left+5*Space, bottom);


        // 绘制棋盘中间汉字
        Font f = new Font("华文隶书", Font.BOLD,40);
        g.setFont(f);
        g.drawString("楚河", left+2*Space-10, bottom+4*Space+40);
        g.drawString("汉界", left+5*Space, bottom+4*Space+40);


        // 绘制棋子
        int diameter = Space - 1;
        Font f1 = new Font("隶书", Font.BOLD, diameter/2);
        g.setFont(f1);


        for (int i=0; i<32; i++) {
            Chess c = array[i];
            if (array[i] != null) {
                c.draw(g, this);
            }
        }

        if (selectedChess != null) {
            selectedChess.drawRect(g);
        }

        Font f2 = new Font("华文中宋", Font.BOLD,30);
        g.setFont(f2);
        if (current == 0 && over == 0) {
            g.setColor(Color.red);
            g.drawString("红方行动",left+2*Space-10+500, bottom+4*Space+40);
        }
        if (current == 1 && over == 0) {
            g.setColor(Color.black);
            g.drawString("黑方行动",left+2*Space-10+500, bottom+4*Space+40);
        }
        if (over == 1) {
            g.setColor(Color.red);
            g.drawString("红方胜利！",left+2*Space-10+500, bottom+4*Space+40);
        }
        if (over == 2) {
            g.setColor(Color.black);
            g.drawString("黑方胜利！",left+2*Space-10+500, bottom+4*Space+40);
        }
    }



    private void CreateChess() {
        // 保存棋子
        // 保存黑子
        String[] BName = {"車", "馬", "象", "仕", "将", "仕", "象", "馬", "車", "炮", "炮", "卒", "卒", "卒", "卒", "卒"};
        int[] Bx = {0, 1, 2, 3, 4, 5, 6, 7, 8, 1, 7, 0, 2, 4, 6, 8};
        int[] By = {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 3, 3, 3, 3, 3};
        int size = BName.length;
        for (int i=0; i<size; i++) {
            Chess c = new Chess();
            c.setName(BName[i]);
            c.setColor(1);
            c.setX(Bx[i]);
            c.setY(By[i]);
            c.setIndex(i);
            c.CalXY();
            // 将棋子保存在数组里
            array[i] = c;
            board[Bx[i]][By[i]] = c;
        }

        // 保存红子
        String[] RName = {"車", "馬", "相", "士", "帥", "士", "相", "馬", "車", "炮", "炮", "兵", "兵", "兵", "兵", "兵"};
        int[] Rx = {0, 1, 2, 3, 4, 5, 6, 7, 8, 1, 7, 0, 2, 4, 6, 8};
        int[] Ry = {9, 9, 9, 9, 9, 9, 9, 9, 9, 7, 7, 6, 6, 6, 6, 6};
        size = RName.length;
        for (int i=0; i<size; i++) {
            Chess c = new Chess();
            c.setName(RName[i]);
            c.setColor(0);
            c.setX(Rx[i]);
            c.setY(Ry[i]);
            c.setIndex(i+16);
            c.CalXY();
            // 将棋子保存在数组里
            array[i+size] = c;
            board[Rx[i]][Ry[i]] = c;
        }
    }
}
