import javax.swing.*;
import java.awt.*;

public class UIPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        // super.paint(g);
        // 绘制棋盘外圈大长方形
        int left = 40;
        int bottom = 80;
        int width = 500;
        int height = width/8*10;
        g.drawRect(left, bottom, width, height);


        // 绘制棋盘横线
        int Space = height / 10;
        for (int i=1; i<=4; i++) {
            g.drawLine(left ,bottom+i*Space, left+width, bottom+i*Space);
        }
        for (int i=6; i<=9; i++) {
            g.drawLine(left ,bottom+i*Space, left+width, bottom+i*Space);
        }


        // 绘制棋盘竖线
        for (int i=1; i<=7; i++) {
            g.drawLine(left+i*Space, bottom+height, left+i*Space, bottom+6*Space);
        }
        for (int i=1; i<=7; i++) {
            g.drawLine(left+i*Space, bottom, left+i*Space, bottom+4*Space);
        }


        // 绘制斜线
        g.drawLine(left+3*Space, bottom+8*Space, left+5*Space, bottom+height);
        g.drawLine(left+3*Space, bottom+height, left+5*Space, bottom+8*Space);
        g.drawLine(left+3*Space, bottom, left+5*Space, bottom+2*Space);
        g.drawLine(left+3*Space, bottom+2*Space, left+5*Space, bottom);


        // 绘制棋盘中间汉字
        Font f = new Font("华文隶书", Font.BOLD,50);
        g.setFont(f);
        g.drawString("楚河", left+2*Space-10, bottom+5*Space+10);
        g.drawString("汉界", left+5*Space, bottom+5*Space+10);


        // 绘制棋子
        int diameter = Space - 1;
        Font f1 = new Font("隶书", Font.BOLD, diameter/2);
        g.setFont(f1);


        // 绘制黑子
        // 绘制底线棋子
        String[] name1 = {"車", "馬", "象", "士", "将", "士", "象", "馬", "車"};
        int size2 = name1.length;
        for (int i=0; i<size2; i++) {
            g.setColor(Color.ORANGE);
            g.fillOval(left+i*Space-Space/2, bottom-Space/2, diameter, diameter);
            g.setColor(Color.BLACK);
            g.drawString(name1[i], left+i*Space-Space/4, bottom+Space/5);
        }


        // 绘制双炮
        g.setColor(Color.ORANGE);
        g.fillOval(left+Space-Space/2, bottom+2*Space-Space/2, diameter, diameter);
        g.setColor(Color.BLACK);
        g.drawString("炮", left+Space-Space/4, bottom+2*Space+Space/5);

        g.setColor(Color.ORANGE);
        g.fillOval(left+7*Space-Space/2, bottom+2*Space-Space/2, diameter, diameter);
        g.setColor(Color.BLACK);
        g.drawString("炮", left+7*Space-Space/4, bottom+2*Space+Space/5);


        // 绘制卒
        for (int i=0; i<5; i++) {
            g.setColor(Color.ORANGE);
            g.fillOval(left+2*i*Space-Space/2, bottom+3*Space-Space/2, diameter, diameter);
            g.setColor(Color.BLACK);
            g.drawString("卒", left+2*i*Space-Space/4, bottom+3*Space+Space/5);
        }


        // 绘制红子
        // 绘制底线
        String[] name2 = {"車", "馬", "相", "仕", "帥", "仕", "相", "馬", "車"};
        size2 = name2.length;
        for (int i=0; i<size2; i++) {
            g.setColor(Color.ORANGE);
            g.fillOval(left+i*Space-Space/2, bottom+10*Space-Space/2, diameter, diameter);
            g.setColor(Color.RED);
            g.drawString(name2[i], left+i*Space-Space/4, bottom+10*Space+Space/5);
        }


        // 绘制双炮
        g.setColor(Color.ORANGE);
        g.fillOval(left+Space-Space/2, bottom+8*Space-Space/2, diameter, diameter);
        g.setColor(Color.RED);
        g.drawString("炮", left+Space-Space/4, bottom+8*Space+Space/5);

        g.setColor(Color.ORANGE);
        g.fillOval(left+7*Space-Space/2, bottom+8*Space-Space/2, diameter, diameter);
        g.setColor(Color.RED);
        g.drawString("炮", left+7*Space-Space/4, bottom+8*Space+Space/5);


        // 绘制兵
        for (int i=0; i<5; i++) {
            g.setColor(Color.ORANGE);
            g.fillOval(left+2*i*Space-Space/2, bottom+7*Space-Space/2, diameter, diameter);
            g.setColor(Color.RED);
            g.drawString("卒", left+2*i*Space-Space/4, bottom+7*Space+Space/5);
        }
    }
}
