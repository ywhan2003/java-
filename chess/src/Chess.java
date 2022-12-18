import javax.swing.*;
import java.awt.*;

public class Chess {
    // 棋子大小
    private static final int SIZE = 40;
    // 棋子间距
    private static final int SPACE = 62;

    private String name;

    private int color;
    // 0->red, 1->black

    // 绘制棋子时的坐标
    private int x;
    private int y;

    // 棋子网格坐标
    private int gridx;
    private int gridy;

    // 绘制棋子边框
    public void drawRect(Graphics g) {
        g.setColor(Color.black);
        CalXY();
        g.drawRect(this.x, this.y, SIZE, SIZE);
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setX(int x) {
        this.gridx = x;
    }


    public void setY(int y) {
        this.gridy = y;
    }

    public int getX(){
        return gridx;
    }

    public int getY(){
        return gridy;
    }

    public void CalXY() {
        this.x = 40 + gridx * SPACE - SIZE / 2;
        this.y = 80 + gridy * SPACE - SIZE / 2;
    }

    public static int getXFromXY(int x){
        int gridx = (x + SIZE / 2 - 40) / SPACE;
        if (gridx < 0 || gridx > 8)
            return -1;
        return gridx;
    }

    public void draw(Graphics g, JPanel panel) {
        g.setColor(Color.ORANGE);
        CalXY();
        g.fillOval(this.x, this.y, SIZE, SIZE);
        if (color == 0) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }
        g.drawString(name, this.x + 5, this.y + 30);

    }

    public static int getYFromXY(int y){
        int gridy = (y + SIZE / 2 - 80) / SPACE;
        if (gridy < 0 || gridy > 9)
            return -1;
        return gridy;
    }

    public boolean MoveOK(int x, int y) {
        return true;
    }


}