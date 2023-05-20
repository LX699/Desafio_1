package dci.ufro.cl.desafio1.Controller;

import dci.ufro.cl.desafio1.Entity.Automovil;
import dci.ufro.cl.desafio1.Service.AutomovilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "automoviles")

public class AutomovilController {
    @Autowired
    private AutomovilService automovilService;

    @GetMapping("/generar/{cantidad}")
    public ResponseEntity<List<Automovil>> generarAutomoviles(@PathVariable int cantidad) {
        List<Automovil> automoviles = automovilService.generateCars(cantidad);
        return new ResponseEntity<>(automoviles, HttpStatus.OK);
    }

    @GetMapping("/buscarPorColor")
    public ResponseEntity<List<Automovil>> buscarPorColor(@RequestParam String color) {
        List<Automovil> automoviles = automovilService.findByColor(color);
        return new ResponseEntity<>(automoviles, HttpStatus.OK);
    }

    @GetMapping("/buscarPorTipo")
    public ResponseEntity<List<Automovil>> buscarPorTipo(@RequestParam String tipo) {
        List<Automovil> automoviles = automovilService.findByTypes(tipo);
        return new ResponseEntity<>(automoviles, HttpStatus.OK);
    }

    @GetMapping("/colores")
    public List<String> obtenerColores() {
        return automovilService.getColores();
    }

    @GetMapping("/filtrarPorPrecio")
    public ResponseEntity<List<Automovil>> filtrarPorPrecio(@RequestParam double maxPrice) {
        List<Automovil> automovilesFiltrados = automovilService.filterByPrice(maxPrice);
        return new ResponseEntity<>(automovilesFiltrados, HttpStatus.OK);
    }


    @GetMapping("/tipoAutomovil")
    public List<String> obtenerTipoAutomovil() {
        return automovilService.getTipos();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Automovil>> obtenerAutomoviles() {
        List<Automovil> automoviles = automovilService.getAutomoviles();
        return new ResponseEntity<>(automoviles, HttpStatus.OK);
    }


}
