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

    public boolean MoveOK(int x, int y, UIPanel panel) {
        if ("将".equals(name)) {
            if (x > 5 || x < 3 || y > 2 || y < 0) {
                return false;
            }
            if ((Math.abs(gridx - x) == 1 && gridy == y) || (Math.abs(gridy - y) == 1 && gridx == x)) {
                panel.board[gridx][gridy] = null;
                return true;
            }
        } else if ("帥".equals(name)) {
            if (x > 5 || x < 3 || y > 9 || y < 7) {
                return false;
            }
            if ((Math.abs(gridx - x) == 1 && gridy == y) || (Math.abs(gridy - y) == 1 && gridx == x)) {
                panel.board[gridx][gridy] = null;
                return true;
            }
        } else if ("仕".equals(name)) {
            if (x > 5 || x < 3 || y > 2 || y < 0) {
                return false;
            }
            if (Math.abs(gridx - x) == 1 && Math.abs(gridy - y) == 1) {
                panel.board[gridx][gridy] = null;
                return true;
            }
        } else if ("士".equals(name)) {
            if (x > 5 || x < 3 || y > 9 || y < 7) {
                return false;
            }
            if (Math.abs(gridx - x) == 1 && Math.abs(gridy - y) == 1) {
                panel.board[gridx][gridy] = null;
                return true;
            }
        } else if ("象".equals(name)) {
            if (x < 0 || x > 8 || y < 0 || y > 4) {
                return false;
            }
            if (Math.abs(gridx - x) != 2 || Math.abs(gridy - y) != 2 ) {
                return false;
            }
            // 判断蹩脚
            int CenterX = (gridx + x) / 2;
            int CenterY = (gridy + y) / 2;
            if (panel.board[CenterX][CenterY] != null) {
                return false;
            }
            panel.board[gridx][gridy] = null;
            return true;
        } else if ("相".equals(name)) {
            if (x < 0 || x > 8 || y < 5 || y > 9) {
                return false;
            }
            if (Math.abs(gridx - x) != 2 || Math.abs(gridy - y) != 2 ) {
                return false;
            }
            // 判断蹩脚
            int CenterX = (gridx + x) / 2;
            int CenterY = (gridy + y) / 2;
            if (panel.board[CenterX][CenterY] != null) {
                return false;
            }
            panel.board[gridx][gridy] = null;
            return true;
        } else if ("馬".equals(name)) {
            if (x < 0 || x > 8 || y < 0 || y > 9) {
                return false;
            }
            // 判断是否日字格
            if (!(Math.abs(gridx - x) == 2 && Math.abs(gridy - y) == 1 ||
                    Math.abs(gridx - x) == 1 && Math.abs(gridy - y) == 2)) {
                return false;
            }
            // 判断是否蹩脚
            if (Math.abs(gridx - x) == 2) {
                int Center = (gridx + x) / 2;
                if (panel.board[Center][gridy] != null) {
                    return false;
                }
            }
            if (Math.abs(gridy - y) == 2) {
                int Center = (gridy + y) / 2;
                for (int i=0; i<32; i++) {
                    if (panel.array[i].gridy == Center && panel.array[i].gridx == gridx) {
                        return false;
                    }
                }
                if (panel.board[gridx][Center] != null) {
                    return false;
                }
            }
            panel.board[gridx][gridy] = null;
            return true;
        } else if ("車".equals(name)) {
            if (x < 0 || x > 8 || y < 0 || y > 9) {
                return false;
            }
            if (gridx != x && gridy != y) {
                return false;
            }
            int count = 0;
            if (gridx != x) {
                if (gridx > x) {
                    for (int i=x; i<gridx; i++) {
                        if (panel.board[i][gridy] != null) {
                            count += 1;
                            if (panel.board[i][gridy].color == panel.board[gridx][gridy].color) {
                                return false;
                            }
                        }
                    }
                } else {
                    for (int i=gridx+1; i<=x; i++) {
                        if (panel.board[i][gridy] != null) {
                            count += 1;
                            if (panel.board[i][gridy].color == panel.board[gridx][gridy].color) {
                                return false;
                            }
                        }
                    }
                }
                if (count > 1) {
                    return false;
                }
            } else {
                if (gridy > y) {
                    for (int i=y; i<gridy; i++) {
                        System.out.println(i);
                        if (panel.board[gridx][i] != null) {
                            count += 1;
                            if (panel.board[gridx][i].color == panel.board[gridx][gridy].color) {
                                return false;
                            }
                        }
                    }
                } else {
                    for (int i=gridy+1; i<=y; i++) {
                        if (panel.board[gridx][i] != null) {
                            count += 1;
                            if (panel.board[gridx][i].color == panel.board[gridx][gridy].color) {
                                return false;
                            }
                        }
                    }
                }
                System.out.println(count);
                if (count > 1) {
                    return false;
                }
            }
            panel.board[gridx][gridy] = null;
            return true;
        } else if ("炮".equals(name)) {
            if (x < 0 || x > 8 || y < 0 || y > 9) {
                return false;
            }
            if (gridx != x && gridy != y) {
                return false;
            }
            int count = 0;
            if (gridx != x) {
                if (gridx > x) {
                    for (int i=x; i<gridx; i++) {
                        if (panel.board[i][gridy] != null) {
                            count += 1;
                            if (panel.board[i][gridy].color == panel.board[gridx][gridy].color) {
                                return false;
                            }
                        }
                    }
                } else {
                    for (int i=gridx+1; i<=x; i++) {
                        if (panel.board[i][gridy] != null) {
                            count += 1;
                            if (panel.board[i][gridy].color == panel.board[gridx][gridy].color) {
                                return false;
                            }
                        }
                    }
                }
                if (count > 1) {
                    return false;
                }
            } else {
                if (gridy > y) {
                    for (int i=y; i<gridy; i++) {
                        System.out.println(i);
                        if (panel.board[gridx][i] != null) {
                            count += 1;
                            if (panel.board[gridx][i].color == panel.board[gridx][gridy].color) {
                                return false;
                            }
                        }
                    }
                } else {
                    for (int i=gridy+1; i<=y; i++) {
                        if (panel.board[gridx][i] != null) {
                            count += 1;
                            if (panel.board[gridx][i].color == panel.board[gridx][gridy].color) {
                                return false;
                            }
                        }
                    }
                }
                System.out.println(count);
                if (count > 1) {
                    return false;
                }
            }
            if (count == 1) {
                if (panel.board[x][y] == null) {
                    return false;
                } else if (panel.board[x][y].color == color) {
                    return false;
                }
            }
            panel.board[gridx][gridy] = null;
            return true;
        } else if ("卒".equals(name)) {
            if (x < 0 || x > 8 || y < 0 || y > 9) {
                return false;
            }
            if (gridy < 5) {
                if (gridx != x) {
                    return false;
                    // 卒不过河只能直走
                }
                if (y != gridy+1) {
                    return false;
                    // 卒只能走一格
                }
            } else {
                if (!((Math.abs(x-gridx)) == 1 && y == gridy || (y-gridy == 1 && x == gridx))) {
                    return false;
                }
            }
            panel.board[gridx][gridy] = null;
            return true;
        } else if ("兵".equals(name)) {
            if (x < 0 || x > 8 || y < 0 || y > 9) {
                return false;
            }
            if (gridy >= 5) {
                if (gridx != x) {
                    return false;
                    // 兵不过河只能直走
                }
                if (y != gridy-1) {
                    return false;
                    // 兵只能走一格
                }
            } else {
                if (!((Math.abs(x-gridx)) == 1 && y == gridy || (-y+gridy == 1 && x == gridx))) {
                    return false;
                }
            }
            panel.board[gridx][gridy] = null;
            return true;
        }

        return true;
    }


}