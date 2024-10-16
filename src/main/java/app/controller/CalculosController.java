package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Entrada;
import app.entity.Saida;
import app.service.CalculosService;

@RestController
@RequestMapping("/api/calculos")
public class CalculosController {
	
	@Autowired
	private CalculosService calculosService;
	
	@PostMapping("/calcular")
	public ResponseEntity<Saida> calcular(@RequestBody Entrada entrada){
		
		try {
			Saida saida = this.calculosService.calcular(entrada);
			return new ResponseEntity<>(saida,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}	
		
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Saida>> findAll(){
		
		try {
			List<Saida> lista = this.calculosService.findAll();
			return new ResponseEntity<>(lista,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}	
		
	}

	@GetMapping("/findById")
	public ResponseEntity<Saida> findById(long id){

		try {
			Saida objeto = this.calculosService.findById(id);
			return new ResponseEntity<>(objeto,HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/buscarMaiorNumero")
	public ResponseEntity<Integer> buscarMaiorNumero(@RequestBody List<Integer> lista) {
		try {
			int maiorNumero = calculosService.buscarMaiorNumero(lista);
			return new ResponseEntity<>(maiorNumero, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
