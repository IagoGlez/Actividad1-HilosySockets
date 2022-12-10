import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		// Trabajaremos sobre el puerto 9994
		ServerSocket listener = new ServerSocket(9994);
		
		System.out.println("El servidor está iniciado");
		
		//Creamos las 5 posibles opciones
		String opcs = "1.¿Tiene usted problemas con el teléfono móvil?, marque el número 1; 2.¿Tiene usted problemas con el teléfono fijo?, marque el número 2; 3.¿Tiene usted problemas con el internet?, marque el número 3; 4.¿Tiene usted problemas con la televisión de pago?, marque el número 4; 5.¿No coinciden los mensajes previos con su actual problema?, marque el número 5";
		
		
		try {
			//hacemos un bucle while(true) para que el servidor esté activo hasta que cerremos la aplicación
			while(true) {
				//Aceptamos la petición del socket
				Socket socket = listener.accept(); 
				
				//Creamos el string de salida con las posibles opciones
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println(opcs);
				
				
				//leemos el número que nos envia el cliente asociado a las correspondientes opciones
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//Declaramos a 0 inicialmente el numero
				int clientNumber = 0;
				//Leemos el numero que nos está llegando
				String inputString = input.readLine();
				//Verificar un numero
				if(isNumeric(inputString)) {
					clientNumber = Integer.parseInt(inputString);
				}
				
				//Creamos el string de salida
				if (clientNumber == 1) {
					out.println("Llame al 1004 para resolver su problema.");
				}else if (clientNumber == 2) {
					out.println("LLame al 984636124 para resolver su problema.");
				}else if (clientNumber == 3) {
					out.println("Pruebe a reiniciar el router permitiendole 10 segundos de refresco, sino contáctenos en el teléfono que le aparece en la parte inferior del propio router.");
				}else if (clientNumber == 4) {
					out.println("Pruebe a acceder al servicio desde otro dispositivo(móvil, tablet, pc...), si el problema continua, acceda a la cuenta y gestione su subscripción.");
				}else if (clientNumber == 5) {
					out.println("Pongase en contacto con la oficina donde usted haya hecho el registro, disculpe las molestias.");
				}else if (!isNumeric(inputString)) {
					out.println("Indique un número entre 1 y 5 basándose en las opciones previamente descritas.");
				}else {
					out.println("Indique una opción valida entre el 1 y el 5.");
				}
				
				//Cerramos el socket
				socket.close();
			}
		}finally {
			//Cerramos el listener
			listener.close();
		}
	}
	
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}

}
