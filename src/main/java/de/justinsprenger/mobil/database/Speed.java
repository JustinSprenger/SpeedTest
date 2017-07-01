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

			URL url = new URL("http://dl.cdn.chip.de/downloads/760121/Firefox_Setup_54.0.1.exe?cid=54394990&platform=chip&1498913772-1498921272-ae834e-B-e7ac6268d8acac908884fa201d6d5db2.exe");

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
