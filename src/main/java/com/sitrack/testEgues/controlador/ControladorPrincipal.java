package com.sitrack.testEgues.controlador;

import com.sitrack.testEgues.servicios.ServicioOpciones;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ControladorPrincipal {
    
    
    
     @Autowired
    private ServicioOpciones servicio;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView method() {
            
        servicio.opciones();
        
        
        /*
            System.out.println("Ingrese una direccion URL valida (ej: http://www.google.com)");
            
            Scanner leer = new Scanner(System.in).useDelimiter("\n");
            String direccion = leer.next();
           
            return new ModelAndView("redirect:" + direccion);
         }catch(Exception e){
            System.out.println("La direccion URL ingresada no es válidad vuelva a intentarlo");
            return new ModelAndView("redirect:" + "/");
         }
         */
        return null;
    }

}
