package com.ruthlessgames.ArkaPong;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class Level implements Screen{
	ArrayList <Block> blocks = new ArrayList<Block>(10);
	int nivel_actual;
	//int nr_blocks;
	boolean time_level;
	Sprite background;
	GameMain gamemain;
	WaitScreen waitscreen;
	Stage stage;
	Table table;
	Random rdm;
	Player player;
	Sprite player_img;
	Ball ball;
	Sprite Bola_img;
	Sprite Bloco_img;
	int b_height = (int) (GameMain.s_height/25);
	Music music;
	
	Level(int lvl, boolean timed, Sprite bg, GameMain GM)
	{
		this.music = Gdx.audio.newMusic(Gdx.files.internal("sfx/Lvl1.mp3"));
		music.setLooping(true);
		this.gamemain = GM;
		this.waitscreen = new WaitScreen();
		player = new Player();
		player_img = new Sprite(new Texture(Gdx.files.internal("gfx/player.png")));
		player.setDrawable(new TextureRegionDrawable(player_img));
		player.setSize(GameMain.s_width/5, b_height);
		player.setPosition(GameMain.s_width/2 - player.getWidth()/2, GameMain.s_height/25);
		
		ball = new Ball(0);
		Bola_img = new Sprite(new Texture(Gdx.files.internal("gfx/bola.png")));
		ball.setDrawable(new TextureRegionDrawable(Bola_img));
		ball.setSize(GameMain.s_width/25, GameMain.s_width/25);
	
		
		
		
		this.nivel_actual = lvl;
		time_level = timed;
		background = bg;
		rdm = new Random();
		
		stage = new Stage(GameMain.s_width,GameMain.s_height,true); //experimentar true/false
		table = new Table();
		table.setFillParent(true);
		table.setBackground(new TextureRegionDrawable(background));
		table.addActor(player);
		table.addActor(ball);
		
		populate_blocks();
		
		stage.addActor(table);
		
		
	}
	void populate_blocks(){
		int espacamento = 3;
		blocks.add(player);
		int linha = 0;
		boolean troca_linha = false;
		float space_left = 0;
		float pos_anterior=0;
		switch(nivel_actual){
			case 1:{
				for(int i = 0; i < 25; i++){
					Block bloco = new Block(0,true);
					blocks.add(bloco);
					
					int aux = rdm.nextInt(3);
					if(i!=0){
						space_left = GameMain.s_width - (pos_anterior+espacamento);
						if(space_left < GameMain.s_width/6 && space_left > GameMain.s_width/8 )
							aux = 1;
						else if(space_left < GameMain.s_width/8 && space_left > GameMain.s_width/10 )
							aux = 2;
						if(space_left < GameMain.s_width/10){
							troca_linha = true;
							linha++;
						}
						if( !troca_linha)
							bloco.setPosition(espacamento+(pos_anterior) , GameMain.s_height - GameMain.s_height/8 - (linha*b_height)- linha*GameMain.s_height/100);
						else{
							bloco.setPosition(GameMain.s_width/25 , GameMain.s_height - GameMain.s_height/8 - (linha*b_height) - linha*GameMain.s_height/100);
							troca_linha = false;
						}
					}
					else
						//bloco.setPosition(GameMain.s_width/2, GameMain.s_height/2);
						bloco.setPosition(GameMain.s_width/25 , GameMain.s_height - GameMain.s_height/8);
					
					switch(aux){
						case 0:{
							Bloco_img = new Sprite(new Texture(Gdx.files.internal("gfx/bloco0.png")));
							bloco.setSize(GameMain.s_width/6, b_height);
							if(bloco.getX()+bloco.getWidth() > GameMain.s_width - GameMain.s_width/25)
								bloco.setWidth(GameMain.s_width - GameMain.s_width/25 - bloco.getX());
							else
								bloco.setSize(GameMain.s_width/6, b_height);
							break;
						}
						case 1:{
							Bloco_img = new Sprite(new Texture(Gdx.files.internal("gfx/bloco1.png")));
							bloco.setSize(GameMain.s_width/8, b_height);
							if(bloco.getX()+bloco.getWidth() > GameMain.s_width - GameMain.s_width/25)
								bloco.setWidth(GameMain.s_width - GameMain.s_width/25 - bloco.getX());
							else
								bloco.setSize(GameMain.s_width/8, b_height);
							break;
						}
						default:{
							Bloco_img = new Sprite(new Texture(Gdx.files.internal("gfx/bloco2.png")));
							bloco.setSize(GameMain.s_width/10, b_height);
							if(bloco.getX()+bloco.getWidth() > GameMain.s_width - GameMain.s_width/25)
								bloco.setWidth(GameMain.s_width - GameMain.s_width/25 - bloco.getX());
							else
								bloco.setSize(GameMain.s_width/10, b_height);
							break;
						}
						
					}
					
					pos_anterior = bloco.getX()+bloco.getWidth();
					bloco.setDrawable(new TextureRegionDrawable(Bloco_img));
					table.addActor(bloco);
				}
				
				break;
			}
		}
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		music.play();
		stage.act(delta);
		stage.draw();
		update();
		
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		ball.setPosition(GameMain.s_width/2, GameMain.s_height/2);
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
		
	}
	
	private void update(){
		player.update();
		ArrayList <Block> To_remove = new ArrayList <Block>();
		ball.setPosition(ball.getX()+ ball.speed*ball.velocity.x, ball.getY()+ball.speed*ball.velocity.y);
		if(ball.getY() + ball.getHeight() >= GameMain.s_height){
			ball.velocity.y *= -1;
		}
		else if(ball.getX() <= 0 || ball.getX()+ball.getWidth()>= GameMain.s_width)
		{
			ball.velocity.x *= -1;
		}
		else if(ball.getY()<0){
			gamemain.setScreen(waitscreen);
			gamemain.androidbridge.ShowGameOver();
		}
		for(Block cur_block: this.blocks)
		{
			if(cur_block.collision(ball, player)){
				cur_block.sound.play();
				if(cur_block.subhealth(ball.power)==0 && cur_block != player){
					table.removeActor(cur_block);
					To_remove.add(cur_block);
				}
			}
		}
		for(Block i: To_remove){
			blocks.remove(i);
		}
		gamemain.shaperenderer.begin(ShapeType.Curve);
		
		float x1 = player.getX();
		float y1 = player.getY()+player.getHeight()/2;
		float cx1 = player.getX()+ player.getWidth()/2;
		float cy1 = (float) (player.getY() + 1.5*b_height);
		float x2 = player.getX()+ player.getWidth();
		float y2 = player.getY()+player.getHeight()/2;
		float cx2 = player.getX() + player.getWidth();
		float cy2 = player.getY()+player.getHeight()/2;
		gamemain.shaperenderer.curve(x1, y1, cx1, cy1, cx2, cy2, x2, y2);
		gamemain.shaperenderer.setColor(Color.BLACK);
		gamemain.shaperenderer.end();
	}
	
}
