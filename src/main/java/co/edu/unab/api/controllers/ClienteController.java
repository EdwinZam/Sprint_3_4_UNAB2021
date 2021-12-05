package co.edu.unab.api.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unab.api.models.ClienteModel;
import co.edu.unab.api.services.ClienteService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping()
    public ArrayList<ClienteModel> obtenerClientes(){
        return  clienteService.obtenerClientes();
    }

    @PostMapping()
    public ClienteModel guardarCliente(@RequestBody ClienteModel cliente){
        
        return clienteService.guardarCliente(cliente);
    }
    
    @DeleteMapping(path = "/{id}")
    public String eliminarClientePorId(@PathVariable("id") String id){
        boolean resultadoEliminar=this.clienteService.eliminarCliente(id);
        if (resultadoEliminar){
            return "Se eliminó el usuario con id: "+id;
        }else{
            return "No se pudo eliminar el usuario con el id: "+id;
        }
    }
    
    @GetMapping(path = "/{id}") //Consulta por ID
    public Optional<ClienteModel> obtenerClientePorId(@PathVariable("id") String id){
        return this.clienteService.obtenerClientePorId(id);
    }

    @GetMapping(path = "nombre/{nombre}") //Consulta por nombre 
    public ArrayList<ClienteModel> clientesporNombres(@PathVariable("nombre") String nombre){
        return this.clienteService.obtenerClientePorNombre(nombre);
    }

    @GetMapping(path = "ubicacion/{ciudad}/{departamento}") //Consulta por ciudad y departamento
    public ArrayList<ClienteModel> clientesporUbicacion(@PathVariable("ciudad") String ciudad,@PathVariable("departamento") String departamento ){
        return this.clienteService.clientesPorCiudad(ciudad, departamento);
    }
    
    @GetMapping(path = "puntos/{puntos}") //consulta por Puntos Mayor o Igual
    public ArrayList<ClienteModel> clientesPuntosMayor(@PathVariable("puntos") Long puntos){
        return this.clienteService.clientesMayorOIgualPuntos(puntos);
    }

    @GetMapping(path = "menorpuntos/{puntos}") //consulta por Puntos Menor o Igual 
    public ArrayList<ClienteModel> clientesPuntosMenor(@PathVariable("puntos") Long puntos){
        return this.clienteService.clientesMenorOIgual(puntos);
    }

    @GetMapping(path = "nomapellido/{nombre}/{apellido}") //Consulta por nombre y apellido
    public ArrayList<ClienteModel> clientesPorNombreApellido(@PathVariable("nombre") String nombre, @PathVariable("apellido") String apellido ){
        return this.clienteService.obtenerClientesPorNombreApellido(nombre, apellido);
    }

    @GetMapping(path = "solociudad/{ciudad}") //Consulta por ciudad
    public ArrayList<ClienteModel> clientesPorSoloCiudad(@PathVariable("ciudad") String ciudad){
        return this.clienteService.obtenerClientesPorSoloCiudad(ciudad);
    }

    @GetMapping(path = "puntosentre/{puntos}/{puntosfinal}") //Consulta por puntos Entre X y Y
    public ArrayList<ClienteModel> clientesPorPuntosEntre(@PathVariable("puntos") Long puntos, @PathVariable("puntosfinal") Long puntosfinal ){
        return this.clienteService.obetenerClientesPorPuntosEntre(puntos, puntosfinal);
    }

    @GetMapping(path = "nombrenot/{nombre}") //Consulta por nombre diferente al escrito
    public ArrayList<ClienteModel> clientesporNombreDiferente(@PathVariable("nombre") String nombre){
        return this.clienteService.obtenerClientePorNombreDiferenteDe(nombre);
    }
}