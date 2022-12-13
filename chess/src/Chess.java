import javax.swing.*;
import java.awt.*;

public class Chess {
    // 棋子大小
    private final int SIZE = 25;
    // 棋子间距
    private final int SPACE = 62;

    private String name;

    private int color;
    // 0->red, 1->black

    // 绘制棋子时的坐标
    private int x;
    private int y;

    // 棋子网格坐标
    private int gridx;
    private int gridy;


    public void setName(String name) {
        this.name = name;
    }


    public void setColor(int color) {
        this.color = color;
    }


    public void setX(int x) {
        this.gridx = x;
    }


    public void setY(int y) {
        this.gridy = y;
    }


    public void CalXY() {
        x = 40 + gridx * SPACE - SIZE / 2;
        y = 80 + gridy * SPACE - SIZE / 2;
    }

}
