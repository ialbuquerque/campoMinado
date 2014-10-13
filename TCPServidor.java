/*
 * TCPServidor.java
 *
 * Servidor ECHO (Coulouris, 2001) pg. 137
 * Passar numero da porta como argumento (opcional)
 * Ex. java TCPServidor <porta> ou
 *     java TCPServidor
 */

import java.net.*;
import java.io.*;

public class TCPServidor {

    public static void main(String args[]) {
        try {
            int porta = 6789; // porta do servico
            if (args.length > 0) porta = Integer.parseInt(args[0]);
            ServerSocket escuta = new ServerSocket(porta);
            System.out.println("*** Servidor ***");
            System.out.println("*** Porta de escuta (listen): " + porta);
            while (true) {
                // accept bloqueia ate que chegue um pedido de conexao de um cliente
                Socket cliente = escuta.accept();
                System.out.println("*** conexao aceita de (remoto): " + cliente.getRemoteSocketAddress());
                // quando chega, cria nova thread para atender apenas o cliente
                Conexao c = new Conexao(cliente);
            }
        } catch (IOException e) {
            System.out.println("Erro na escuta: " + e.getMessage());
        }
    }
}