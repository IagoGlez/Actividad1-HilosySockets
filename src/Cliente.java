import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub

		while (true) {
			//Creamos el socket
			Socket socket = new Socket("127.0.0.1", 9994);
			
			//Recibir las posibles opciones del servidor
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String questions = input.readLine();
			System.out.println(questions);
			
			// Solicitar numero
			String number = System.console().readLine("--Introduce un número entre 1 y 5 según se ajuste a su actual problema.\n");
			
			//Enviar el dato al server
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			out.println(number);
			
			//Recibir la respuesta del server
			BufferedReader definitiveImput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String definitiveAnswer = definitiveImput.readLine();
			
			//Imprimir por consola
			System.out.println(definitiveAnswer);
			
			//Cerramos el socket
			socket.close();
		}
	}

}
