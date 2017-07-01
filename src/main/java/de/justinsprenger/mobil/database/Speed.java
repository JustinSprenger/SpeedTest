package de.justinsprenger.mobil.database;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Speed {
	
	public static double getSpeed(){
		long start,fin;
		double result=0;
		long length=0;
		int counter=0;
		try {

			//URL url = new URL("http://dl.cdn.chip.de/downloads/760121/Firefox_Setup_54.0.1.exe?cid=54394990&platform=chip&1498913772-1498921272-ae834e-B-e7ac6268d8acac908884fa201d6d5db2.exe");
			URL url = new URL("http://r2.computerbild.de/exec/r2r.pl?m=w-cobi;u=http%3A%2F%2Fd.computerbild.de%2Fdownloads%2F4590482%2Fadwcleaner_6.047.exe%3F__cbodl__%3D1498927627_82aad620fb6ba6d6c0963717e1edc4ea%26_chksum_%3D1ace8128cfa67e825635012b2cf705a9;ct=1;thc=1;b=4590482;c=7855560;tit=AdwCleaner+6.047;url=http%3A%2F%2Fwww.computerbild.de%2Fdownload%2FAdwCleaner-7855560.html;sep=%7C;tce=%7C;tid=506;tn=Anti-Spyware;tp=95%7C367;tc=95%7C367%7C506;tpn=Software+Kategorie%7CSicherheit;cs=1");
			InfoThread r = new InfoThread(1000);
			Thread t = new Thread(r);
			t.start();
			
			InputStream in = url.openConnection().getInputStream();

			byte[] buffer = new byte[1024];

			start = System.currentTimeMillis();
			for (int n;(n = in.read(buffer)) != -1;){length = length + n; r.setFilesize(length);}
			fin = System.currentTimeMillis();
			r.stop();
			result = fin - start;
			System.out.println("Dateigroesse : " + length + " in Byte");
			System.out.println("benoetigte Zeit : " + result + " in Ms");
			System.out.println(length/result + " Byte pro Millisekunde");

		} catch (IOException e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
		}

		return ((length/1024)/(result/1000));
	}
	
}
