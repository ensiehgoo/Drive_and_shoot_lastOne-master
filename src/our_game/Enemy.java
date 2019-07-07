package our_game;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy {
	Image img;
	int x, y;
	boolean isAlive = true;
	static ArrayList bull;
	Dude p;

	ImageIcon red_car = new ImageIcon("src/res/redEnemy.png");
	ImageIcon blue_car = new ImageIcon("src/res/blueEnemy.png");
	ImageIcon yellow_car = new ImageIcon("src/res/yellowEnemy.png");
	ImageIcon another_car = new ImageIcon("src/res/anotherEnemy.png");

	public Enemy(int startX, int startY) {
		x = startX;
		y = startY;
		img = setImage();
	}
	public Enemy(int startX, int startY, Dude p) {
		x = startX;
		y = startY;
		img = setImage();
		bull = new ArrayList();
		this.p = p;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean Alive() {
		return isAlive;
	}

	public  void enemyFire(){
		Bullet z = new Bullet(getX(),getY()+20,p,3);
		bull.add(z);
	}

	public static ArrayList getBull(){
		return bull;
	}

	public Image setImage() {
		//randomly setting image
		Random rand = new Random();
		int k = rand.nextInt(20);
		System.out.println(k);
		if (k <= 5)
			img = red_car.getImage();
		else if (k>5 && k <=10)
			img = blue_car.getImage();
		else if(k > 10 && k <=15)
			img = yellow_car.getImage();
		else
			img = another_car.getImage();
//		img = red_car.getImage();
		return img;
	}

	public Image getImage() {
		// Random rand = new Random();
		// int k=rand.nextInt(3);
		// System.out.println(k);
		// if(k == 1) img = red_car.getImage();
		// else if(k == 2) img = blue_car.getImage();
		// else img = yellow_car.getImage();
		// img = red_car.getImage();
		return img;
	}
	public int getHeight(){
		if(img == another_car.getImage()) return 56;
		return 39;
	}

	public void move(int dx) {
		x = x - dx - dx;
	}

	/*public void move1(int dx) {
		x = x - dx - dx;
	}*/

}
