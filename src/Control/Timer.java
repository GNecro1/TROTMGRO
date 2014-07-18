package Control;

import Main.Main;

public class Timer {

	public long miliSeconds, targetTime;
	public long now;

	private boolean done = false;
	private boolean start = false;

	public Timer(double sec) {
		miliSeconds = (long) (sec * 1000);
		Main.timers.add(this);
	}

	public void tick() {
		if (System.currentTimeMillis() >= targetTime && start) {
			done = true;
		}
	}
	
	public void start(){
		now = System.currentTimeMillis();
		targetTime = now + miliSeconds;
		start  = true;
	}
	
	public void reset() {
		now = System.currentTimeMillis();
		targetTime = now + miliSeconds;
		done = false;
	}

	public boolean Ring() {
		return done;
	}
	
	public void drop(){
		Main.timers.remove(this);
	}

}
