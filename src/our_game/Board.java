package our_game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Board extends JPanel implements ActionListener, Measurements{

	Dude p;//player
	Image img, menuImg, helpImg, overImg;//background image
	Timer time;//thread
	Enemy en1;//enemy 1
	Enemy en2;
	Enemy en3;

	Enemy[] enemy;//array of enemy

	Menu menu;

	public static enum ACTION{
		MENU,GAME,HELP,OVER };

	public static ACTION Action = ACTION.MENU;

	boolean isStart = false;//boolean for starting game
	public static int SCORE = 0;

	static Font font = new Font("Comic Sans MS", Font.BOLD, 25);

	public Board() {

        this.addMouseListener(new Menu());//MENU
        menu = new Menu();
        ImageIcon men = new ImageIcon("src/res/menu.png");
        menuImg = men.getImage();
		ImageIcon help = new ImageIcon("src/res/help.png");
		helpImg = help.getImage();
		overImg = men.getImage();//same as menu image

		addKeyListener(new AL());
		setFocusable(true);
		//enemy initialization
		en1 = new Enemy(1024, 430,p);
		en2 = new Enemy(1400, 370,p);
		en3 = new Enemy(1800,320,p);
		
		enemy = new Enemy[3];
		enemy[0] = en1;
		enemy[1] = en2;
		enemy[2] = en3;

		p = new Dude(enemy);//initializing player

		ImageIcon i = new ImageIcon("src/res/back.png");
		img = i.getImage();//background image
        
		time = new Timer(5, this);//thread initialization
		
		time.start();//thread start
	}

	public void actionPerformed(ActionEvent e) {

		ArrayList bullets = Dude.getBullets();

		for (int w = 0; w < bullets.size(); w++) {
			Bullet m = (Bullet) bullets.get(w);
			if (m.getVisible() == true && p.b == 1)
				m.rightMove();
			else if (m.getVisible() == true && p.b == -1)
				m.leftMove();
			else
				bullets.remove(w);
		}
		p.move();//player moving

		ArrayList bull = Enemy.getBull();

		for (int w = 0; w < bull.size(); w++) {
			Bullet m = (Bullet) bull.get(w);
			if (m.getVisible() == true && en1.isAlive) {
				for (int j=0; j<10; j++) {
					m.enemyMove();//enemy shooting

				/*if (!m.enemyMove()) {
					time.stop();

				}*/
				}
			}
			else
				bull.remove(w);
		}

		if(p.x % BACK_WIDTH >= 50 && !Dude.isForwarding && isStart){
			en1.move(2);
			en2.move(2);
			en3.move(2);
//			for(int i = 0; i < enemy.length; i++){
//				enemy[i].move(1);
			if(enemy[0].isAlive)
				enemy[0].enemyFire();
//			}
		}
		if(Dude.isForwarding && isStart)
		{
			en1.move(1);
			en2.move(1);
			en3.move(1);
			for(int i = 0; i < enemy.length; i++){
				enemy[i].move(1);
			}
			if (enemy[0].isAlive){
				enemy[0].enemyFire();
			}
		}

		repaint();
		
		System.out.println(Dude.isForwarding);
	}

	public void paint(Graphics g) {
		super.paint(g);
		//???
		Graphics2D g2d = (Graphics2D) g;


		if ((p.getX() - 20) % (2*BACK_WIDTH) == 0)//resetting value of nx(leftmost x coordinate)
			p.nx = 0;
		if ((p.getX() - 1044) % (2*BACK_WIDTH) == 0)//resetting value of nx2(rightmost x coordinate)
			p.nx2 = 0;

		//printing values of player.x , background(nx,nx2) , enemy(x,y)
		System.out.println("x = " + p.getX() + "   nx2 = " + p.nx2 + "   nx = " + p.nx + " py = " + p.y + ", ex= "
				+ en1.getX() + " ey= " + en1.getY() + " is " + en1.isAlive);


		if(Action == ACTION.GAME) {
			isStart = true;
			g2d.drawImage(img, 1024 - p.nx2, 0, null);//drawing image of background(here,initially,1024-p.nx2 = 0)
		}
		else if(Action == ACTION.MENU){
			((Graphics2D) g).drawImage(menuImg,0,0,null);
			Menu.render(g);
		}
		else if(Action == ACTION.HELP){
			((Graphics2D) g).drawImage(helpImg,0,0,null);
			Help.render(g);
		}
		else if(Action == ACTION.OVER){
			g2d.drawImage(img,p.getX(),0,null);
			Over.render(g);
		}

		/*g2d.drawString("Score: " + SCORE , 850, 24);
        g2d.setFont(font);
		g2d.setColor(Color.RED);*/

		if (p.getX() >= 20)//when player.x >= 20,
			g2d.drawImage(img, 1024 - p.nx, 0, null);
		if (p.getY() > 435)
			p.y = 435;
		if (p.getY() < 20)
			p.y = 20;

		g2d.drawImage(p.getImage(), 300, p.getY(), null);//drawing image of player
		if (p.h == 1)
			g2d.drawImage(p.getFlyImage(), 335, p.getY() + 33, null);//drawing image of fly

		ArrayList bullets = Dude.getBullets();

		for (int w = 0; w < bullets.size(); w++) {
			Bullet m = (Bullet) bullets.get(w);
			g2d.drawImage(m.getImage(), m.getX(), m.getY(), null);//drawing image of bullet
		}

		if (p.x % 1024 >= 50) {
			for(int i = 0; i < enemy.length; i++){
				if (enemy[i].getX() < 0) {
					enemy[i].x = 1024;
					enemy[i].isAlive = true;
					
					enemy[i].setImage();

				}
				if (enemy[i].Alive() == true) {
					g2d.drawImage(enemy[i].getImage(), enemy[i].getX(), enemy[i].getY(), null);//drawing image of enemy
				}
				//enemy shooting!
				if( enemy[0].isAlive){
					ArrayList bull = Enemy.getBull();

					for (int w = 0; w < bull.size(); w++) {
							Bullet m = (Bullet) bull.get(w);
							g2d.drawImage(m.getImageEnemy(), m.getX() - 5, m.getY() + 5, null);//drawing image of bullet

						/*if (m.getX() < 0){
							m.x = 1024;
						}
						*/
							System.out.println("Enemy shooting x : " + m.getX() + " Enemy shooting y : " + m.getY());
							if (m.getX() <= 394 && m.getY() <= 473 && m.getY() >= 435) {
								Over.render(g);
								time.stop();
							}
					}
				}
			}
		}

		//collision!
		ImageIcon img = new ImageIcon("src/res/boom.png");
		
		for(int i = 0; i < enemy.length; i++){
			if (enemy[i].isAlive && Math.abs(300 - enemy[i].getX()) <= 94 && Math.abs(p.y - enemy[i].getY()) <= 30) {
				System.out.println(p.x + " " + p.y + ", " + enemy[i].getX() + " " + enemy[i].getY());
				System.out.println("Collide");
				
					g2d.drawImage(img.getImage(), 300+65, p.getY(), null);//drawing image of collision
					enemy[i].isAlive = false;
					Over.render(g);
					time.stop();
			}
		}
		

//			 if(p.getnx() > 500)
//				 if(en2.Alive() == true)
//					 g2d.drawImage(en2.getImage(), en2.getX(), en2.getY(), null);

	}

	private class AL extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			p.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);
		}
	}
}
