import javax.swing.*;
import java.awt.*;

public class Chess {
    // 棋子大小
    private static final int SIZE = 25;
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
        g.drawRect(x,y,61,61);
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
        x = 20 + gridx * SPACE - SIZE / 2;
        y = 60 + gridy * SPACE - SIZE / 2;
    }

    public static int getXFromXY(int x){
        int gridx = (x + SIZE / 2 - 20) / SPACE;
        if (gridx < 0 || gridx > 8)
            return -1;
        return gridx;
    }


    public static int getYFromXY(int y){
        int gridy = (y + SIZE / 2 - 60) / SPACE;
        if (gridy < 0 || gridy > 9)
            return -1;
        return gridy;
    }



}