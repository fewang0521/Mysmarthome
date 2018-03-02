package mysmarthome;

public class main {

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Serialfunction port =new Serialfunction();
		Databasefunction db = new Databasefunction();
		

		port.serialconnect("COM5");
		port.commandsend('t');
		port.commandreceive();
		port.serialclose();
		String value = new String(port.save,0,0,port.buffer_index-1);
		int int_value = Integer.parseInt(value);
		db.sql_run("temp", int_value, "succes");
		System.exit(1);
		

	}

}
