package com.sitrack.testEgues.servicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class ServicioOpciones {

    public void opciones() {

        System.out.println("Se realizarán una serie de pasos en el siguiente orden:");
        System.out.println("1- Ingresar desde consola una URL válida");
        System.out.println("2- Ingresar desde consola una frase compuesta de N palabras");
        System.out.println("3- Hacer un request a dicha URL");
        System.out.println("4- Mostrar en consola la cantidad de veces que se repite dicha frase ingresada");
        System.out.println("5- Mostrar en consola la cantidad de veces que se repite cada palabra de la frase ingresada");
        System.out.println("////////////////INICIO/////////////");
        URL url = validacionDeURL();
        String frase = fraseNPalabras();
        String pagina = request(url);
        buscarFrase(frase, pagina);
        buscarPalabras(frase, pagina);

    }

    public URL validacionDeURL() {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        try {

            System.out.println("Ingrese la URL a continuación");
            String ingreso = leer.next();
            URL url = new URL(ingreso);
            URLConnection conn = url.openConnection();
            conn.connect();
            System.out.println("La URL ingresada es válida y fue guardada para la opcion 3,4 y 5");
            return url;
        } catch (MalformedURLException e) {
            System.out.println("La URL ingresada es inválida.(Ej valido: http://www.google.com)");
            validacionDeURL();
        } catch (IOException e) {
            System.out.println("La conexion no pudo establecerse.");
            validacionDeURL();
        }
        return null;

    }

    public String fraseNPalabras() {

        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Ingrese la frase que desea buscar a continuación: ");
        String frase = leer.next();

        if (frase.isEmpty()) {
            System.out.println("No ingreso ninguna frase, vuelva a intentar");
            fraseNPalabras();
        }
        System.out.println("La frase ingresada es: " + frase);
        return frase;

    }

    public String request(URL url) {

        try {
            StringBuffer codeBuffered = new StringBuffer();
            String code;

            InputStream in = url.openStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = read.readLine()) != null) {
                codeBuffered.append(line).append("\n");
            }

            code = codeBuffered.toString(); // Este es el código de la página :)
            //convertirlo a utf-8
            String pagUTF8 = new String(code.getBytes("ISO-8859-1"), "UTF-8");
            //cerramos los streams
            in.close();
            read.close();
            return pagUTF8;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> palabras(String frase) {

        String[] palabra = frase.split(" ");
        List<String> palabras = Arrays.asList(palabra);     
        return palabras;
    }

    public void buscarFrase(String frase, String pagina) {

        String lowerPagina = pagina.toLowerCase();
        String lowerFrase = frase.toLowerCase();

        int contador = 0;
        int c = 0;
        int t;

        for (int i = 0; i < lowerPagina.length() - lowerFrase.length(); i++) {

            t = i;
            for (int j = 0; j < lowerFrase.length(); j++) {
                if (lowerPagina.charAt(t) == lowerFrase.charAt(j)) {
                    c++;
                }
                t++;
                if (c == lowerFrase.length()) {
                    contador++;
                }

            }
            c = 0;
        }
        System.out.println("Se repite " + contador + " veces");
    }

    public void buscarPalabras(String frase, String pagina) {
        String lowerPagina = pagina.toLowerCase();
        String lowerFrase = frase.toLowerCase();
        
        String[] palabrasFrase = lowerFrase.split(" ");
        String texto  = Normalizer.normalize(lowerPagina , Normalizer.Form.NFD);
        texto  = texto.replaceAll("[^\\p{ASCII}]", "");
        String[] textoPalabras = texto.split("\\s|[^a-z]");
       
        for (String busqueda : palabrasFrase) {
            
            int con = 0;
            
           
            
            busqueda  = Normalizer.normalize(busqueda , Normalizer.Form.NFD);
            busqueda  = busqueda.replaceAll("[^\\p{ASCII}]", "");
            String regex = "^".concat(busqueda).concat("$");
             

            for (String palabrasDeTexto : textoPalabras) {
                
              
               
                if (palabrasDeTexto.matches(regex)) {
                    con++;
                }
            }
            System.out.println("\""+busqueda + "\" se repite " + con);
        }
    }

}
