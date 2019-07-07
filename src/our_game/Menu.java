package our_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Menu implements MouseListener{

    public static void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Font fnt = new Font("Comic Sans MS", Font.BOLD, 40);//font of the options

        g.setColor(Color.DARK_GRAY);// background color of the buttons

        g.fill3DRect(440, 210, 100, 70, true);//fills color in 3d rectangles
        g.fill3DRect(440, 297, 100, 70, true);
        g.fill3DRect(440, 384, 100, 70, true);

        g.setColor(Color.GREEN);//font color
        g.setFont(fnt);

        //draws buttons

        g.drawString("Play", 440 + 10, 210 + 45);
        g2d.draw3DRect(440, 210, 100, 70,true);

        g.drawString("Help", 440 + 8, 297 + 45);
        g2d.draw3DRect(440, 297, 100, 70, true);

        g.drawString("Quit", 440 + 8, 384 + 45);
        g2d.draw3DRect(440, 384, 100, 70, true);


    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        if(mx>=440 && mx<=540) {

            if(my>=210 && my<=280) {

                Board.Action = Board.Action.GAME;///goes to game window
            }

            if(my>=297 && my<=367) {

                Board.Action = Board.ACTION.HELP;
            }

            if(my>=384 && my<=454) {

                System.exit(1);
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