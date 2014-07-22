package Res;

import Graphics.RL;
import World.World;
import World.Tile.Grass;
import World.Tile.Stone;
import World.Tile.Tile;

public class WorldLoader {
	
	public static Tile[][] getWorld(World w, int ws) {
		Tile[][] tile = new Tile[ws][ws];
		
		int[] index = new int[ws * ws];
		RL.map1_0.getRGB(0, 0, RL.map1_0.getWidth(), RL.map1_0.getHeight(), index, 0, RL.map1_0.getWidth());
		
		for (int i = 0; i < ws; i++) {
			for (int j = 0; j < ws; j++) {
				if (index[i + j * ws] == RL.map1_0.getRGB(0, 0)) {
					tile[i][j] = new Stone(i * 64, j * 64, w);
				}
				else if (index[i + j * ws] == RL.map1_0.getRGB(11, 9)) {
					tile[i][j] = new Grass(i * 64, j * 64);
				}
				else if (index[i + j * ws] == RL.map1_0.getRGB(20, 17)) {
					tile[i][j] = new Stone(i * 64, j * 64, w);
				}
			}
			System.out.println("");
		}
		
		return tile;
	}
	
}
