package com.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

public class Bullet {
    private int x = 0;
    private int y = 0;
    private int speed = 10;
    private Timer bt;
    private String direction = "Right";

    public Bullet(int CELL_SIZE, String direction, Node head) {
        setPosition(head.x, head.y);
        this.direction = direction;
        update(CELL_SIZE);
    }

    public void drawBullet(Graphics g, int CELL_SIZE, boolean isSnakeA) {
        if (isSnakeA)
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.YELLOW);
        g.fillOval(x, y, CELL_SIZE, CELL_SIZE); 
    }

    public void update(int CELL_SIZE) {
        if (direction.equals("Left")) {
            x -= CELL_SIZE * 2; // left, x -= CELL_SIZE
        }
        else if (direction.equals("Right")) {
            x += CELL_SIZE * 2; // right, x += CELL_SIZE
        }
        else if (direction.equals("Up")) {
            y -= CELL_SIZE * 2; // up, y -= CELL_SIZE
        }
        else if (direction.equals("Down")) {
            y += CELL_SIZE * 2; // down, y += CELL_SIZE
        }
    }

    private void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setBulletTimer() {
        bt = new Timer();
        bt.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

            }
        }, 0, speed);
    }

    public boolean checkBorder(int leftBorder, int rightBorder, int topBorder, int bottomBorder) {
        if (x > rightBorder)
            return true;
        if (x < leftBorder)
            return true;
        if (y < topBorder)
            return true;
        if (y > bottomBorder) 
            return true;
        return false;
    }

    public boolean touchSnakeHead(Snake snake) {
        Node head = snake.getSnakeBody().get(0);

        if (Math.abs(head.x - this.x) <= (ContainerPanel.CELL_SIZE / 2) && 
            Math.abs(head.y - this.y) <= (ContainerPanel.CELL_SIZE / 2)) {
                return true;
            }
        return false;
    }

    public boolean touchSnakeBody(Snake snake) {
        for (int i = 1; i < snake.getSnakeBody().size(); ++i) {
            Node node = snake.getSnakeBody().get(i);

            if (Math.abs(node.x - this.x) <= (ContainerPanel.CELL_SIZE / 2) && 
                Math.abs(node.y - this.y) <= (ContainerPanel.CELL_SIZE / 2)) {
                    return true;
                }
        }
        return false;
    }
}
