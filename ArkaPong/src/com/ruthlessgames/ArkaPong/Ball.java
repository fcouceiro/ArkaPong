package com.ruthlessgames.ArkaPong;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Ball extends Image{
	int tipo;
	int power;
	Vector2 velocity;
	float speed;
	
	Ball(int t){
		this.tipo = t;
		velocity = new Vector2(1,-1);
		switch(t){
			case 0:{
				this.power = 10;
				this.speed = 1f;
				break;
			}
			case 1:{
				this.power = 20;
				this.speed = 3f;
				break;
			}
		}
	}
	
//	void update(){
//		this.setPosition(this.getX()+ speed*velocity.x, this.getY()+speed*velocity.y);
//		
//	}
}
