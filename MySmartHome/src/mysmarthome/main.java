package mysmarthome;

public class main {

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Serialfunction port =new Serialfunction();
		Databasefunction db = new Databasefunction();

		port.serialconnect("COM5");
		port.commandsend('r');
		port.commandreceive();
		port.serialclose();
		db.dbwrite(new String(port.save,0,0,port.buffer_index-1));
		db.sql_run("t_para", 1, "succes");
		System.exit(1);
		

	}

}
