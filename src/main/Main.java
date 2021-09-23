package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.Instruction;
import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {
	private Socket socketcito;
	private BufferedWriter escritorcito;
	private BufferedReader lectorcito;
	

	public static void main(String[] args) {
		PApplet.main("main.Main");

	}
	public void settings(){
		size(500,500);
	}
		public void setup(){
			background(100,0,0);
			initServer();
		}
		public void draw(){
			background(0);
				fill(255,0,0);
				ellipse(10,10,20,20);
			}
		
		public void initServer() {
			new Thread(
					
					()->{
						try {
							//Paso 1: Esperar una conexion	
							ServerSocket server = new ServerSocket(6969);
							System.out.println("Esperando conexión....");
							socketcito = server.accept();
							//Paso 3: Cliente y Server conectados
							System.out.println("Cliente conectado!!!");
							
							InputStream is = socketcito.getInputStream();
							InputStreamReader isr = new InputStreamReader(is);
							lectorcito = new BufferedReader(isr);
							
							OutputStream os = socketcito.getOutputStream();
							OutputStreamWriter osw = new OutputStreamWriter(os);
							escritorcito = new BufferedWriter(osw);
							//Recibir mensajitos
							
							while(true) {
							System.out.println("Esperando mensaje....");
							String line = lectorcito.readLine();
							//System.out.println("Recibido: " + line);
							Gson gson = new Gson();
							Instruction je = gson.fromJson(line, Instruction.class);
							System.out.println(je.getKey());
							System.out.println(je.isActive());
							
							}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
					
					).start();
		}
		public void sendMessage(String msg) {
			new Thread(
					()->{
						try {
							escritorcito.write(msg+"\n");
							escritorcito.flush();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					).start();
		}
	}



