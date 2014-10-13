/*
 * TCPCliente.java
 *
 * Estabelece conexao com o servidor e envia mensagem.
 * O servidor devolve a mensagem enviada e o cliente a imprime.
 * Argumentos: java TCPCliente <IP servidor> <porta> "msg a ser enviada"
 */

import java.net.*;
import java.io.*;

public class TCPCliente {

	public static void main(String args[]) {

		Socket s = null;

		try {
			s = new Socket("localhost", 6789); // conecta o socket aa porta remota
			DataInputStream ent = new DataInputStream(s.getInputStream());
			DataOutputStream sai = new DataOutputStream(s.getOutputStream());
			sai.writeUTF("testando_123!");
			// le buffer de entrada
			String recebido = ent.readUTF();
			System.out.println("*** Recebido do servidor: " + recebido);
		}
		catch (UnknownHostException e) {
			System.out.println("!!! Servidor desconhecido: " + e.getMessage());
		}
		catch (EOFException e) {
			System.out.println("!!! Nao ha mais dados de entrada: " + e.getMessage());
		}
		catch (IOException e) {
			System.out.println("!!! E/S: " + e.getMessage());
		}
		finally {
			if (s!=null) {
				try {
					s.close();
				} 
				catch (IOException e){
					System.out.println("!!! Encerramento do socket falhou: " + e.getMessage());
				}
			}
		}
	}   
}
