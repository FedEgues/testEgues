package com.sitrack.testEgues.controlador;

import com.sitrack.testEgues.servicios.ServicioOpciones;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ControladorPrincipal {
    
    
    
     @Autowired
    private ServicioOpciones servicio;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        try{    
        servicio.opciones();
        }catch(Exception e){
            e.printStackTrace();
        return "redirect:/bucle";
        }
        return "redirect:/bucle";
    }
    @GetMapping("/bucle")
    public String bucle() {
        
        try{
        servicio.opciones();
        }catch(Exception e){
        e.printStackTrace();
        return "redirect:/";
        }
            
        return "redirect:/";
    }
    

}
