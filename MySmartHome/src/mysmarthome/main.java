package mysmarthome;

public class main {

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if ((args.length)==0){
			System.out.println("please enter args");
		}
		else{
			String parameter_name =args[0];
			
			switch (parameter_name) {
			case "light": 
				Serialfunction port =new Serialfunction();
				Databasefunction db = new Databasefunction();
				
		
				port.serialconnect("COM5");
				port.commandsend('r');
				port.commandreceive();
				port.serialclose();
				String value = new String(port.save,0,0,port.buffer_index-1);
				int int_value = Integer.parseInt(value);
				db.sql_run("light", 0, value);
				System.exit(1);
			}
			
			/*
			Serialfunction port =new Serialfunction();
			Databasefunction db = new Databasefunction();
			
	
			port.serialconnect("COM5");
			port.commandsend('r');
			port.commandreceive();
			port.serialclose();
			String value = new String(port.save,0,0,port.buffer_index-1);
			int int_value = Integer.parseInt(value);
			db.sql_run("light", 0, value);
			System.exit(1);
			*/
		}

	}

}
