package com.ruthlessgames.ArkaPong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Player extends Image{
	float speed = 0;
	int speed_max = 4;
	void update(){
		this.speed -= Gdx.input.getAccelerometerX()/30;
		
		
		if(speed > speed_max) speed = speed_max;
		else if(speed < -speed_max) speed = -speed_max;
		this.setX(this.getX()+speed);
		if(this.getX()<=0){
		this.setX(0);
		speed = 0;
		}
		else if(this.getX()> GameMain.s_width - this.getWidth())
		{
			speed = 0;
			this.setX(GameMain.s_width - this.getWidth());
		}
	}
}

