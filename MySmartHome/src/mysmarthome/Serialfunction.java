package mysmarthome;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class Serialfunction {
	SerialPort serialPort;
	InputStream in;
	OutputStream out;
	byte save[]=new byte[1024];
	int buffer_index=0;
	
	public Serialfunction(){
		this.serialPort=null;
		this.in=null;
		this.out=null;
	}
	
	void serialconnect(String portName) throws Exception{
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		if ( portIdentifier.isCurrentlyOwned() )
		{
			System.out.println("Error: Port is currently in use");
		}
		else
		{
			//클래스 이름을 식별자로 사용하여 포트 오픈
			CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);

			if ( commPort instanceof SerialPort )
			{

				//포트 설정(통신속도 설정. 기본 9600으로 사용)
				this.serialPort = (SerialPort) commPort;
				this.serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

				//Input,OutputStream 버퍼 생성 후 오픈


			}
		}

	}
	void serialclose() throws IOException{
		this.out.flush();
		this.out.close();
		this.serialPort.close();	
	}
	
	void commandsend(int command) throws IOException{
		this.out = (OutputStream) this.serialPort.getOutputStream();
		out.write(command);
	
	}
	
	void commandreceive() throws IOException{
		this.in = (InputStream) this.serialPort.getInputStream();
		
		byte[] buffer = new byte[1024];
        int len = -1;
        byte[] save_buffer = new byte[1024];
        int bufferindex =0;
        String comp="|";
        while ( (len = in.read(buffer)) > -1 ){
            	            	
        	if(len>0){
        		for (int i=0;i<(len);i++){
        			save_buffer[bufferindex]=buffer[i];
        			bufferindex++;
        		}
        		if (new String(save_buffer,(bufferindex-1),1).equals("|")){
        			System.out.print(new String(save_buffer,0,bufferindex-1));
        			this.save=save_buffer;
        			this.buffer_index=bufferindex;
        			break;
        			//bufferindex=0;
        			//Arrays.fill(save_buffer, (byte)0);
        			
        		}	
        		
           	}
        	
        	
                //System.out.print(len);
                /*if (in.read()==-1){
                	System.out.print("exit");
                	break;
                }*/
            };
	}
}
