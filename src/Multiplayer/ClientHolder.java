package Multiplayer;

import java.io.IOException;
import java.util.Random;

import Main.Multiplayer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;

public class ClientHolder implements Runnable {

	private static Client client;
	private ClientListener cl;
	private static Multiplayer mp;

	public ClientHolder(Multiplayer multiplayer) {
		mp = multiplayer;
		System.out.println("Creating the client!");
		client = new Client();
		cl = new ClientListener(mp);
		client.addListener(cl);
		System.out.println("Registering the packets!");
		regPack();
		System.out.println("Starting the client!");
		client.start();
		connect("");
		System.out.println("Done!");
	}

	private void regPack() {
		Kryo kryo = client.getKryo();
		kryo.register(PlayerPacket.class);
		kryo.register(MisslePacket.class);
		kryo.register(ListPacket.class);
	}

	public static void sendListTCP(int po) {
		ListPacket lp = new ListPacket();
		lp.playersOnline = po;
		client.sendTCP(po);
	}

	public static void sendPlayer() {
		PlayerPacket pp = new PlayerPacket();
		pp.x = (int) mp.getPlayer().x;
		pp.y = (int) mp.getPlayer().y;
		pp.id = mp.getPlayer().getID();
		pp.name = "-" + new Random().nextInt(100);
		client.sendTCP(pp);
	}

	public void connect(String s) {
		System.out.println(s + "Connecting to main server!");
		try {
			client.connect(120000, "serverandclient.ddns.net", 1998);
		} catch (IOException e) {
			System.out.println("Failed!");
			connect2local();
		}
	}

	private void connect2local() {
		System.out.println("Connecting to local server!");
		try {
			client.connect(120000, "localhost", 1998);
		} catch (IOException e) {
			System.out.println("Failed!");
			connect("Re: ");
		}
	}

	public void setIP(int ip, int port) {

	}

	public void addListener(ClientListener cl) {

	}

	public void run() {
		while (client.isConnected()) {
			sendPlayer();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
