import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

class Conexao extends Thread {
	
	DataInputStream ent;
	DataOutputStream sai;
	Socket cliente;

	public Conexao(Socket s) {
		try {
			cliente = s;
			ent = new DataInputStream(cliente.getInputStream());
			sai = new DataOutputStream(cliente.getOutputStream());
			this.start();
		}
		catch (IOException e) {
			System.out.println("Erro IO Conexao: " + e.getMessage());
		}
	}

	public void run() {
		try {
			String recebido = ent.readUTF();
			sai.writeUTF(recebido.toUpperCase());
			System.out.println("recebido de " + recebido);
		}
		catch (EOFException e) {
			System.out.println("Conexao: EOFException " + e.getMessage());
		}
		catch (IOException e) {
			System.out.println("Conexao: IOException " + e.getMessage());
		}
		finally {
			try {
				cliente.close();
			}
			catch (IOException e) {
				System.out.println("Conexao: erro close do socket");
			}
		}
	}

}