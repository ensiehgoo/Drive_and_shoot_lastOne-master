package our_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Help implements MouseListener{

    public static void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Font fnt = new Font("Comic Sans MS", Font.BOLD, 40);//font of the options

        g.setColor(Color.YELLOW);// background color of the buttons

        g.fill3DRect(910, 460, 100, 70, true);//fills color in 3d rectangles

        g.setFont(fnt);
        g.setColor(Color.GREEN);

        g.drawString("Back", 910 + 8, 460 + 45);
        g2d.draw3DRect(910, 460, 100, 70, true);


    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        if(mx>=10 && mx<=100) {

            if(my>=460 && my<=530) {

                Board.Action = Board.ACTION.MENU;///goes to game window
            }


        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


}
