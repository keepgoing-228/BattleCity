package Game;


import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;


/**
 * @author ASUS
 *
 */
public class TestRotation extends JFrame {
    public double px, py, qx, qy;
    public double tx, ty, ux, uy; // <point info>
    private int ox = 250, oy = 250; // origin point

    public TestRotation(double n, String title) {
        this.setSize(500, 500);
        this.setResizable(false);
        this.setTitle(title);
        px = Math.cos(n) * (-50) - Math.sin(n) * 100;
        py = Math.sin(n) * (-50) + Math.cos(n) * 100;
        qx = Math.cos(n) * 50 - Math.sin(n) * 100;
        qy = Math.sin(n) * 50 + Math.cos(n) * 100;

        tx = Math.cos(n) * (-80) - Math.sin(n) * 130;
        ty = Math.sin(n) * (-80) + Math.cos(n) * 130;
        ux = Math.cos(n) * 80 - Math.sin(n) * 130;
        uy = Math.sin(n) * 80 + Math.cos(n) * 130;
    }

    public void paint(Graphics g) {
        for (int i = 0; i < 500; i++) {
            g.setColor(Color.WHITE);
            g.drawLine(i, 0, i, 500);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospaced", Font.BOLD, 15));
        g.drawLine(250, 0, 250, 500);
        g.drawLine(0, 250, 500, 250);

        g.fillOval(245, 245, 10, 10);
        g.drawString("(0,0)", 265, 265);

        int ax, ay, bx, by, cx, cy, dx, dy;
        ax = (int) Math.round(px) + ox;
        ay = oy - (int) Math.round(py);
        bx = (int) Math.round(tx) + ox;
        by = oy - (int) Math.round(ty);
        g.fillOval(ax - 5, ay - 5, 30, 10);
        g.drawString(String.format("(%.2f,%.2f)", px, py), bx, by);
        cx = (int) Math.round(qx) + ox;
        cy = oy - (int) Math.round(qy);
        dx = (int) Math.round(ux) + ox;
        dy = oy - (int) Math.round(uy);
        g.fillOval(cx - 5, cy - 5, 10, 30);
        g.drawString(String.format("(%.2f,%.2f)", qx, qy), dx, dy);
        g.drawLine(ax, ay, ox, oy);
        g.drawLine(cx, cy, ox, oy);
        g.drawLine(ax, ay, cx, cy);
    }

    public static void main(String[] args) {
        Scanner fin = null;
        PrintStream fout = null;
        try {
            fin = new Scanner(new FileInputStream("input.txt"));
            fout = new PrintStream(new FileOutputStream("output.txt"));
        } catch (Exception e) {
        }

        double n = fin.nextDouble();
//        double n = 30.00;

        fout.println("Transform matrix:");
        fout.println("");
        fout.println("cos(" + n + ")  -sin(" + n + ")");
        fout.println("sin(" + n + ")  cos(" + n + ")");
        fout.println("");
        fout.println("The three coordinates:");
        fout.println("");
        fout.println("( 0.00 , 0.00 )");
       
        double px, py, qx, qy;
        n = n * Math.PI / 180;
        px = Math.cos(n) * (-50) - Math.sin(n) * 100;
        py = Math.sin(n) * (-50) + Math.cos(n) * 100;
        qx = Math.cos(n) * 50 - Math.sin(n) * 100;
        qy = Math.sin(n) * 50 + Math.cos(n) * 100;

        fout.printf("( %.2f, %.2f )", px, py);
        fout.println("");
        fout.printf("( %.2f, %.2f )", qx, qy);
        fout.println("");
        TestRotation guiT = new TestRotation(n, "Transform");
        TestRotation guiO = new TestRotation(0.0, "Origin");
        guiO.setVisible(true);
        guiT.setVisible(true);
        fin.close();
        fout.close();
    }
}