package mysmarthome;

public class main {

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*
		if ((args.length)==0){
			System.out.println("please enter args");
		}
		else{
			String parameter_name =args[0];
			Serialfunction port =new Serialfunction();
			Databasefunction db = new Databasefunction();
			String value;
			int int_value;
			
			switch (parameter_name) {
			case "light": 
					
				port.serialconnect("COM5");
				port.commandsend('r');
				port.commandreceive();
				port.serialclose();
				value = new String(port.save,0,0,port.buffer_index-1);
				//int int_value = Integer.parseInt(value);
				db.sql_run("light", 0, value);
				System.exit(1);
				
				
			case "temp": 
						
				port.serialconnect("COM5");
				port.commandsend('t');
				port.commandreceive();
				port.serialclose();
				value = new String(port.save,0,0,port.buffer_index-1);
				int_value = Integer.parseInt(value);
				db.sql_run("temp", int_value, "sucess");
				System.exit(1);
				
			case "humidity": 
				
				port.serialconnect("COM5");
				port.commandsend('h');
				port.commandreceive();
				port.serialclose();
				value = new String(port.save,0,0,port.buffer_index-1);
				int_value = Integer.parseInt(value);
				db.sql_run("humidity", int_value, "sucess");
				System.exit(1);	
			}			
		}*/
		
		Databasefunction db = new Databasefunction();
		db.sql_read("light");

	}
	

}
