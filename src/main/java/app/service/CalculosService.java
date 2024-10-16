package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Entrada;
import app.entity.Saida;
import app.repository.CalculosRepository;

@Service
public class CalculosService {
	
	@Autowired
	private CalculosRepository calculosRepository;

	public List<Saida> findAll(){
		return this.calculosRepository.findAll();
	}
	
	public Saida calcular(Entrada entrada) {
		
		Saida saida = new Saida();
		int soma = this.somar(  entrada.getLista() );
		System.out.println("A soma deu "+ soma);
		
		saida.setSoma(soma);
		
		double media = this.media(entrada.getLista());
		saida.setMedia(media);
		
		return saida;
		
	}
	
	public int somar(List<Integer> lista) {
		
		int soma = 0;
		
		for(int i=0; i<lista.size(); i++) {
			soma += lista.get(i);
		}
		
		return soma;
		
	}

	private double media(List<Integer> lista) {
		return (double) this.somar(lista) / lista.size();
	}

	public int buscarMaiorNumero(List<Integer> lista) {
		int maiorNumero = 0;

		for (int i = 0; i < lista.size(); i++) {
			if(lista.get(i) > maiorNumero)
				maiorNumero = lista.get(i);
		}

		return maiorNumero;
	}

	public Saida findById(long id) {
		return this.calculosRepository.findById(id).get();
	}

}
