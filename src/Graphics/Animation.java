package Graphics;

import java.awt.image.BufferedImage;

import Main.Main;

public class Animation {

	private BufferedImage[] animImages = {};
	private boolean animate = false;
	private int index = 0;
	private long miliSeconds, targetTime, now;

	public Animation(int mili,BufferedImage[] images) {
		this.miliSeconds = mili;
		animImages = images;
		now = System.currentTimeMillis();
		targetTime = now + miliSeconds;
		Main.anims.add(this);
	}
	
	public void stopAnimation(){
		animate = false;
	}
	
	public void startAnimation(){
		animate = true;
	}
	
	public BufferedImage getCurrentImage(){
		if(index < 0 || index > animImages.length){
			new Exception();
		}
		return animImages[index];
	}
	
	public void disposeAnimation(){
		Main.anims.remove(this);
	}
	
	public void setIndex(int i){
		index = i;
	}
	
	public void tick(){
		if (System.currentTimeMillis() >= targetTime && animate) {
			index++;
			now = System.currentTimeMillis();
			targetTime = now + miliSeconds;
			if(index>animImages.length-1){
				index = 0;
			}
		}
	}

}
