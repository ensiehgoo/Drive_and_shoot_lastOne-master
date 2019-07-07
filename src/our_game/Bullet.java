package our_game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet {
	int x, y;
	Image img;
	Image imgEn;
	boolean visible = true;
	Enemy enemy[];
	Dude p;

	int speed;

	
	public Bullet(int startX, int startY, Enemy[] enemy){
		x = startX;
		y = startY;
		ImageIcon newBullet = new ImageIcon("src/res/bullet_img.gif");
		img = newBullet.getImage();
		this.enemy = enemy;
	}
	public Bullet(int startX, int startY, Dude p, int speed){
		x = startX;
		y = startY;
		ImageIcon newBullet = new ImageIcon("src/res/bullet_img(en)__.png");
		imgEn = newBullet.getImage();
		this.p = p;
		this.speed = speed;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public boolean getVisible(){
		return visible;
	}
	public Image getImage(){
		return img;
	}
	public Image getImageEnemy(){
		return imgEn;
	}
	public void rightMove (){
		x = x + 5;
		for(int i = 0; i < enemy.length; i++){
			if(enemy[i].isAlive && x>enemy[i].x && Math.abs(y-enemy[i].y)<=enemy[i].getHeight() && y>enemy[i].y){
				enemy[i].isAlive = false;
				visible = false;
				Board.SCORE++;
			}
		}

		if(x>1024) visible = false;
	}
	
	public void leftMove (){
		x = x - 5;
		for(int i = 0; i < enemy.length; i++){
			if(enemy[i].isAlive && x>enemy[i].x && Math.abs(y-enemy[i].y)<=enemy[i].getHeight() && y>enemy[i].y){
				enemy[i].isAlive = false;
				visible = false;
			}
		}
		if(x < 0) visible = false;
	}

	public boolean enemyMove() {
/*
		System.out.println("Bullet x : " + getX() + " Bullet y: " + getY() + " Player x : " + p.getX() + " Player y : "+ p.getY());
*/
		x = x-6;
		if(x<=300 &&  Math.abs(y-435)<=39 && y>435){
			visible = false;
			return false;
		}
		if(x<0){
			visible = false;
			return false;
		}

		return true;
	}
}
