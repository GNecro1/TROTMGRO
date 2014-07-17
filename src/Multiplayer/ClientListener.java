package Multiplayer;

import Entity.PlayerOnline;
import Main.Multiplayer;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientListener extends Listener{
	
	Multiplayer multi;
	
	public ClientListener(Multiplayer mp) {
		multi = mp;
	}

	@Override
	public void connected(Connection c) {
		
	}

	@Override
	public void disconnected(Connection c) {
		
	}

	@Override
	public void received(Connection c, Object o) {
		if(o instanceof ListPacket){
			
		}
		if(o instanceof PlayerPacket){
			PlayerPacket pp = (PlayerPacket) o;
			boolean has = false;
			for (int i = 0; i< multi.po.size();i++) {
				if(multi.po.get(i).getID() == pp.id && !has){
					multi.po.get(i).x = pp.x;
					multi.po.get(i).y = pp.y;
					has = true;
				}
			}
			if(!has){
				PlayerOnline neww = new PlayerOnline(pp.x,pp.y);
				neww.setID(pp.id);
				multi.po.add(neww);
			}
		}
		c.setKeepAliveTCP(99999999);
	}
	
}
