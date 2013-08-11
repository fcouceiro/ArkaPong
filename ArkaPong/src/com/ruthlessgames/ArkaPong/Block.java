package com.ruthlessgames.ArkaPong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Block extends Image{
	int tipo;
	int hp;
	int armor; //Regenera com timer
	boolean estatico;
	Sound sound;
	
	Block(int t, boolean estatico){
		sound = Gdx.audio.newSound(Gdx.files.internal("sfx/block_collision.wav"));
		this.tipo = t;
		switch(t){
			case 0:{
				this.hp = 10;
				this.armor = 0;
				this.estatico = estatico;
				break;
			}
			case 1:{
				this.hp = 25;
				this.armor = 5;
				this.estatico = estatico;
				break;
			}
			case 2:{
				this.hp = 50;
				this.armor = 20;
				this.estatico = estatico;
				break;
			}
			case 99:
			{
				this.hp = 0;
				this.estatico = estatico;
			}
		}
	}
	
	int subhealth(int ball_power){
		if(this.armor == 0){
			this.hp -= ball_power;
		}
		else
		{
			if(armor > ball_power)
				this.armor -= ball_power;
			else
			{
				ball_power -= this.armor;
				this.armor = 0;
				this.hp -= ball_power;
			}
		}
		if(this.armor < 0)
		{
			this.armor = 0;
		}
		if(this.hp < 0)
			this.hp = 0;
		return(this.hp);
	}
	
	boolean collision(Ball ball, Player player){
		int tol = 3; //+-
		
		if((((int)ball.getY() <= (int)(this.getY()+ this.getHeight()+tol) && (int)ball.getY() >= (int)(this.getY()+ this.getHeight()-tol)) && (((int)ball.getX() >= (int)this.getX() && (int)(ball.getX()+ball.getWidth()) <= (int)(this.getX()+this.getWidth())) || ((int)ball.getX() <= (int)this.getX() && (int)(ball.getX()+ball.getWidth()) >= (int)this.getX()) || ((int)ball.getX() <= (int)(this.getX()+this.getWidth()) && (int)(ball.getX()+ball.getWidth()) >= (int)(this.getX()+this.getWidth()))))){
			Gdx.app.log("tag", "Top");
			if(this == player){
				
			}
			else
				ball.velocity.y *= -1;
			return(true);
		}
		
		else if(((int)(ball.getY()+ball.getHeight()) >= (int)(this.getY()-tol) && (int)(ball.getY()+ball.getHeight())<= (int)(this.getY()+tol)) && (((int)ball.getX() >= (int)this.getX() && (int)(ball.getX()+ball.getWidth()) <= (int)(this.getX()+this.getWidth())) || ((int)ball.getX() <= (int)this.getX() && (int)(ball.getX()+ball.getWidth()) >= (int)this.getX()) || ((int)ball.getX() <= (int)(this.getX()+this.getWidth()) && (int)(ball.getX()+ball.getWidth()) >= (int)(this.getX()+this.getWidth()))))
		{
			Gdx.app.log("tag", "bottom");
			ball.velocity.y *= -1;
			return (true);
		}
		else if((((int)(ball.getY()+ball.getHeight()) <= (int)(this.getY() + this.getHeight()) && (int)ball.getY() >= (int)this.getY()) || ((int)(ball.getY()+ball.getHeight()) >= (int)this.getY() && (int)ball.getY() <= (int)this.getY()) || ((int)(ball.getY()) <= (int)(this.getY()+this.getHeight()) && (int)(ball.getY()+ball.getHeight()) >= (int)(this.getY()+this.getHeight()))) && ((int)ball.getX() >= (int)(this.getX() + this.getWidth()- tol) && (int)ball.getX() <= (int)(this.getX()+ this.getWidth() + tol))){
			Gdx.app.log("tag", "left side");
			ball.velocity.x *= -1;
			return(true);
		}
		else if((((int)(ball.getY()+ball.getHeight()) <= (int)(this.getY() + this.getHeight()) && (int)ball.getY() >= (int)this.getY()) || ((int)(ball.getY()+ball.getHeight()) >= (int)this.getY() && (int)ball.getY() <= (int)this.getY()) || ((int)(ball.getY()) <= (int)(this.getY()+this.getHeight()) && (int)(ball.getY()+ball.getHeight()) >= (int)(this.getY()+this.getHeight())))  && ((int)(ball.getX()+ball.getWidth()) >= (int)(this.getX()-tol) && (int)(ball.getX()+ball.getWidth()) <= (int)(this.getX()+tol))){
			Gdx.app.log("tag", "right side");
			ball.velocity.x *= -1;
			return(true);
		}
		return(false);
	}
	
	
	
}


