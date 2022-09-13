package com.sitrack.testEgues;

import com.sitrack.testEgues.servicios.ServicioOpciones;
import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestEguesApplication {

	public static void main(String[] args) {
              int i =0;
       ServicioOpciones ser = new ServicioOpciones();
       do{
           ser.opciones();
           System.out.println("¿Desea continuar buscando?");
           System.out.println("Ingrese 0 para Si");
           System.out.println("Ingrese 1 para No");
           Scanner leer = new Scanner(System.in).useDelimiter("\n");
           try{
           i= leer.nextInt();
           }catch(Exception e){
               System.out.println("Ingresó una opción inválida.");
               ser.opciones();
           }
       }while(i==0);
       
    }
		
               
	

}
