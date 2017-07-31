/**
 * 
 */
package top.pengcheng789.java.swiftPen.network;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author chance
 *
 */
class Util {
	static void encrypt(byte[] a){
		for(int i = 0; i < a.length; i++){
			a[i] =  (byte) (
					(a[i] & 0x80) >> 6
	              | (a[i] & 0x40) >> 4
	              | (a[i] & 0x20) >> 2
	              | (a[i] & 0x10) << 2
	              | (a[i] & 0x08) << 2
	              | (a[i] & 0x04) << 2
	              | (a[i] & 0x02) >> 1
	              | (a[i] & 0x01) << 7
	         );

		}
	}
	
	static void dencrypt(byte[] a){
		for(int i = 0; i < a.length; i++){
			a[i] =  (byte) (
					(a[i] & 0x80) >> 7
	              | (a[i] & 0x40) >> 2
	              | (a[i] & 0x20) >> 2
	              | (a[i] & 0x10) >> 2
	              | (a[i] & 0x08) << 2
	              | (a[i] & 0x04) << 4
	              | (a[i] & 0x02) << 6
	              | (a[i] & 0x01) << 1
	         );
		}
	}
	
	static byte[] intToByte(int a){
		byte[] aByte = new byte[4];
		
		aByte[3] = (byte) (a & 0xff);
		aByte[2] = (byte) (a >> 8 & 0xff);
		aByte[1] = (byte) (a >> 16 & 0xff);
		aByte[0] = (byte) (a >> 24 & 0xff);
		
		return aByte;
	}
	
	static byte[] getMD5(byte[] a){
		byte[] md5 = new byte[16];
		
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(a);
			md5 = mdInst.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return md5;
	}
}
