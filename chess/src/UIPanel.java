import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIPanel extends JPanel {
    private int[] color = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] gridx = {0, 1, 2, 3, 4, 5, 6, 7, 8, 1, 7, 0, 2, 4, 6, 8, 0, 1, 2, 3, 4, 5, 6, 7, 8, 1, 7, 0, 2, 4, 6, 8};
    private int[] gridy = {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 3, 3, 3, 3, 3, 9, 9, 9, 9, 9, 9, 9, 9, 9, 7, 7, 6, 6, 6, 6, 6};
    // 保存所有棋子
    private Chess[] array = new Chess[32];

    // 当前选中的棋子
    private Chess selectedChess;

    public UIPanel() {
        CreateChess();
        // 添加点击事件
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("点击棋盘的坐标为：x="+e.getX()+",y="+e.getY());
                int gridx = Chess.getXFromXY(e.getX());
                int gridy = Chess.getYFromXY(e.getY());
                System.out.println("点击棋盘的网格坐标为：x="+gridx+",y="+gridy);
                if (selectedChess == null){
                    // 第一次选择一个棋子
                    selectedChess = getChess(gridx, gridy);
                }
                else {
                    Chess c = getChess(gridx, gridy);
                    if (c != null) {
                        // 第n次点击的时候有棋子
                        if (c.getColor() == selectedChess.getColor()){
                            // 重新选择
                            selectedChess = c;
                            System.out.println("重新选择");
                        }
                        else {
                            // 吃子
                            System.out.println("吃子");
                            if (selectedChess.MoveOK(gridx, gridy)) {

                            }
                        }

                    }
                    else {
                        // 第n次点击的地方没有棋子
                        // 移动
                        System.out.println("移动");
                        if (selectedChess.MoveOK(gridx, gridy)) {
                            selectedChess.setX(gridx);
                            selectedChess.setY(gridy);
                        }
                    }
                }
                System.out.println("点击的棋子对象为："+selectedChess);
                // 刷新棋盘
                repaint();
            }
        });
    }

    private Chess getChess(int x, int y){
        for (int i=0; i<32; i++) {
            if (array[i].getX() == x && array[i].getY() == y){
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


        String[] name = {"車", "馬", "象", "士", "将", "士", "象", "馬", "車", "炮", "炮", "卒", "卒", "卒", "卒", "卒",
                "車", "馬", "相", "仕", "帥", "仕", "相", "馬", "車", "炮", "炮", "兵", "兵", "兵", "兵", "兵"};

        for (int i=0; i<32; i++) {
            Chess c = array[i];
            c.draw(g, this);

        }

        if (selectedChess != null) {
            selectedChess.drawRect(g);
        }
    }



    private void CreateChess() {
        // 保存棋子
        // 保存黑子
        String[] BName = {"車", "馬", "象", "士", "将", "士", "象", "馬", "車", "炮", "炮", "卒", "卒", "卒", "卒", "卒"};
        int[] Bx = {0, 1, 2, 3, 4, 5, 6, 7, 8, 1, 7, 0, 2, 4, 6, 8};
        int[] By = {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 3, 3, 3, 3, 3};
        int size = BName.length;
        for (int i=0; i<size; i++) {
            Chess c = new Chess();
            c.setName(BName[i]);
            c.setColor(1);
            c.setX(Bx[i]);
            c.setY(By[i]);
            c.CalXY();
            // 将棋子保存在数组里
            array[i] = c;
        }

        // 保存红子
        String[] RName = {"車", "馬", "相", "仕", "帥", "仕", "相", "馬", "車", "炮", "炮", "兵", "兵", "兵", "兵", "兵"};
        int[] Rx = {0, 1, 2, 3, 4, 5, 6, 7, 8, 1, 7, 0, 2, 4, 6, 8};
        int[] Ry = {9, 9, 9, 9, 9, 9, 9, 9, 9, 7, 7, 6, 6, 6, 6, 6};
        size = RName.length;
        for (int i=0; i<size; i++) {
            Chess c = new Chess();
            c.setName(RName[i]);
            c.setColor(0);
            c.setX(Rx[i]);
            c.setY(Ry[i]);
            c.CalXY();
            // 将棋子保存在数组里
            array[i+size] = c;
        }
    }
}