package com.ruthlessgames.ArkaPong;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Block extends Image{
	int tipo;
	int hp;
	int armor; //Regenera com timer
	boolean estatico;
	
	
	Block(int t, boolean estatico){
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
	
	
	
}


