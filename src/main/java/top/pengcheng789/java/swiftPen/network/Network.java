/**
 *
 */
package top.pengcheng789.java.swiftPen.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * @author chance
 *
 */
public class Network {
	String username;
	String password;
	String ip;
	String mac;
	int index;
	String session;
	DatagramSocket post;

	public Network(String username, String password, String ip, String mac){
		this.username = username;
		this.password = password;
		this.ip = ip;
		this.mac = mac;
		this.index = 0x01000000;
		try {
			this.post = new DatagramSocket(3848);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("端口被占用，程序退出。");
			System.exit(0);
		}
	}
        
        public void setUsername(String username){
            this.username = username;
        }
        
        public void setPassword(String password){
            this.password = password;
        }

	byte[] generateOnline (){
		ArrayList<Byte> packet = new ArrayList<Byte>();

		packet.add((byte)1);

		int packetLen = this.username.length()+this.password.length()+this.ip.length()+52;
		packet.add((byte)packetLen);

		for (int i = 0; i < 16 ; i ++)
			packet.add((byte)0);

		packet.add((byte) 7);
		packet.add((byte) 8);
		for (String i : Pattern.compile("-").split(this.mac))
			packet.add((byte)Integer.parseInt(i, 16));

		packet.add((byte)1);
		packet.add((byte)(this.username.length()+2));
		for (byte i : this.username.getBytes())
			packet.add(i);

		packet.add((byte)2);
		packet.add((byte)(this.password.length()+2));
		for (byte i : this.password.getBytes())
			packet.add(i);

		packet.add((byte)9);
		packet.add((byte)(this.ip.length()+2));
		for (byte i : this.ip.getBytes())
			packet.add(i);

		int[] otherField = {0x0A, 10, 105, 110, 116, 101, 114, 110, 101, 116, 0x0E, 3, 0, 0x1F, 7, 51, 46, 54, 46, 53};
		for (int i : otherField)
			packet.add((byte)i);

		byte[] packetByte = new byte[packet.size()];
		for (int i = 0; i < packetByte.length; i++)
			packetByte[i] = packet.toArray(new Byte[0])[i];

		byte[] md5 = Util.getMD5(packetByte);

		for(int i = 2; i < 18; i++)
			packetByte[i] = md5[i-2];

		Util.encrypt(packetByte);

		return packetByte;
	}

	byte[] generateBrathe(){
		ArrayList<Byte> packet = new ArrayList<Byte>();

		packet.add((byte)3);

		int packetLen = this.session.length() + this.ip.length() + 72;
		packet.add((byte)packetLen);

		for (int i = 0; i < 16 ; i ++)
			packet.add((byte)0);

		packet.add((byte)8);
		packet.add((byte)(this.session.length()+2));
		for (byte i : this.session.getBytes())
			packet.add(i);

		packet.add((byte)9);
		packet.add((byte)(this.ip.length()+2));
		for (byte i : this.ip.getBytes())
			packet.add(i);

		packet.add((byte) 7);
		packet.add((byte) 8);
		for (String i : Pattern.compile("-").split(this.mac))
			packet.add((byte)Integer.parseInt(i, 16));

		packet.add((byte) 0x14);
		packet.add((byte) 6);
		for (byte i : Util.intToByte(this.index))
			packet.add(i);
		this.index += 3;

		int[] otherField = {0X2A, 6, 0, 0, 0, 0, 0x2B, 6, 0, 0, 0, 0, 0x2C, 6, 0, 0, 0, 0, 0x2D, 6, 0, 0, 0, 0, 0x2E, 6, 0, 0, 0, 0, 0x2F, 6, 0, 0, 0, 0};
		for (int i : otherField)
			packet.add((byte)i);

		byte[] packetByte = new byte[packet.size()];
		for (int i = 0; i < packetByte.length; i++)
			packetByte[i] = packet.toArray(new Byte[0])[i];

		byte[] md5 = Util.getMD5(packetByte);

		for(int i = 2; i < 18; i++)
			packetByte[i] = md5[i-2];

		Util.encrypt(packetByte);

		return packetByte;
	}

	byte[] generateOffline(){
		ArrayList<Byte> packet = new ArrayList<Byte>();

		packet.add((byte) 5);

		int packetLen = this.session.length() + this.ip.length() + 72;
		packet.add((byte)packetLen);

		for (int i = 0; i < 16 ; i ++)
			packet.add((byte)0);

		packet.add((byte)8);
		packet.add((byte)(this.session.length()+2));
		for (byte i : this.session.getBytes())
			packet.add(i);

		packet.add((byte)9);
		packet.add((byte)(this.ip.length()+2));
		for (byte i : this.ip.getBytes())
			packet.add(i);

		packet.add((byte) 7);
		packet.add((byte) 8);
		for (String i : Pattern.compile("-").split(this.mac))
			packet.add((byte)Integer.parseInt(i, 16));

		packet.add((byte) 0x14);
		packet.add((byte) 6);
		for (byte i : Util.intToByte(this.index))
			packet.add(i);

		int[] otherField = {0X2A, 6, 0, 0, 0, 0, 0x2B, 6, 0, 0, 0, 0, 0x2C, 6, 0, 0, 0, 0, 0x2D, 6, 0, 0, 0, 0, 0x2E, 6, 0, 0, 0, 0, 0x2F, 6, 0, 0, 0, 0};
		for (int i : otherField)
			packet.add((byte)i);

		byte[] packetByte = new byte[packet.size()];
		for (int i = 0; i < packetByte.length; i++)
			packetByte[i] = packet.toArray(new Byte[0])[i];

		byte[] md5 = Util.getMD5(packetByte);

		for(int i = 2; i < 18; i++)
			packetByte[i] = md5[i-2];

		Util.encrypt(packetByte);

		return packetByte;
	}

	public String online() throws IOException {
		byte[] packet = this.generateOnline();
		InetAddress ipAddress = InetAddress.getByName("172.16.1.180");
		DatagramPacket dgPacket = new DatagramPacket(packet, packet.length, ipAddress, 3848);
		this.post.send(dgPacket);

		packet = new byte[300];
		dgPacket = new DatagramPacket(packet, packet.length);
		this.post.setSoTimeout(10000);
		this.post.receive(dgPacket);

		packet = dgPacket.getData();
		Util.dencrypt(packet);

		int messageIndex = 24;
		if(packet[20] != 0){
			messageIndex = 30;
		}
		int session_len = packet[22] & 0xff;
		int message_len = packet[session_len+messageIndex] & 0xff;
		byte[] message = new byte[message_len];
		byte[] session = new byte[session_len];
		for(int i = 23; i < session_len + 23; i++){
		    session[i-23] = packet[i];
		}
		for(int i = session_len+messageIndex+1; i < message_len + session_len + messageIndex+1; i++){
		    message[i-session_len-messageIndex-1] = packet[i];
		}
		this.session = new String(session, "gb2312");
                return new String(message, "gb2312");
	}

	public void brathe() throws IOException{
		byte[] packet = this.generateBrathe();
		InetAddress ipAddress = InetAddress.getByName("172.16.1.180");
		DatagramPacket dgPacket = new DatagramPacket(packet, packet.length, ipAddress, 3848);
		this.post.send(dgPacket);
	}

	public void offline() throws IOException{
		byte[] packet = this.generateOffline();
		InetAddress ipAddress = InetAddress.getByName("172.16.1.180");
		DatagramPacket dgPacket = new DatagramPacket(packet, packet.length, ipAddress, 3848);
		this.post.send(dgPacket);
		this.post.setSoTimeout(10000);
		this.post.receive(dgPacket);
		this.post.close();
	}
}
