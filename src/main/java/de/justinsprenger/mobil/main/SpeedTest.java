package de.justinsprenger.mobil.main;

import de.justinsprenger.mobil.database.Database;
import de.justinsprenger.mobil.database.Dateandtime;
import de.justinsprenger.mobil.database.Network;
import de.justinsprenger.mobil.database.Speed;
import de.justinsprenger.mobil.inout.Read;

public class SpeedTest {

	public static void main(String[] args) {
		char wieder = 'j';
		int auswahl = 0;
		String[] arg = new String[5];
		Read sc = new Read();
		Database db = new Database("angewandteinfo.spdns.org",3306,"Mobile-Anwendungen","Justin","Tresor132");
		
		System.out.println("   _____                     _   _______        _ ");
		System.out.println("  / ____|                   | | |__   __|      | |  ");
		System.out.println(" | (___  _ __   ___  ___  __| |    | | ___  ___| |_ ");
		System.out.println("  \\___ \\| '_ \\ / _ \\/ _ \\/ _` |    | |/ _ \\/ __| __|");
		System.out.println("  ____) | |_) |  __/  __/ (_| |    | |  __/\\__ \\ |_ ");
		System.out.println(" |_____/| .__/ \\___|\\___|\\__,_|    |_|\\___||___/\\__|");
		System.out.println("        | |                                         ");
		System.out.println("        |_|                                         ");
		System.out.println();
		System.out.println();
		
		do{
			
			System.out.println("1. Ausgabe der Accespoint IP");
			System.out.println("2. Ausgabe der Router IP");
			System.out.println("3. um speedtest durchzuf?hren");
			System.out.println("4. Datens?tze ausgeben");
			System.out.println("0. um Abzubrechen");
			
			auswahl = sc.readInt();
			
			switch (auswahl) {
			case 1:
				System.out.println(Network.getAccessPoint());
				break;
			case 2:
				System.out.println(Network.getRouterIP());
				break;
			case 3:
				double speed;
				boolean t = false;

				try {
					speed = Speed.getSpeed();
					arg[0] = Network.getAccessPoint();
					arg[1] = Network.getRouterIP();
					arg[2] = speed+"";
					arg[3] = Dateandtime.getTime();
					arg[4] = Dateandtime.getDate();
					t = db.getRow(arg);
					if(t == true){
						System.out.println("Update");
						db.updateDatabase("t_Messungen", arg);
						db.insertDatabase("t_Messungen", arg);
					}else{
						System.out.println("neuer Eintrag");
						db.insertDatabase("t_Messungen", arg);
					}

				} catch (Exception e) {
					e.getStackTrace();
				}
				
				
				break;
			case 4:
				db.getDatabase();
				break;
			case 0:
				
				break;
			default:
				break;
			}
			
			System.out.println("");
			System.out.println("M?chten Sie wiederhohlen ?(j/n):");
			System.out.println("");
			
			wieder = sc.readChar();
			
			System.out.println("");
			
		}while(wieder == 'j');

	}

}
