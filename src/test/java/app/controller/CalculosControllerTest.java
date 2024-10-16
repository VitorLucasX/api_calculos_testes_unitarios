package app.controller;

import app.entity.Entrada;
import app.entity.Saida;
import app.repository.CalculosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CalculosControllerTest {

    @Autowired
    CalculosController calculosController;

    @MockBean
    CalculosRepository calculosRepository;

    @BeforeEach
    void setup() {
        List<Saida> lista = new ArrayList<>();
        lista.add(new Saida(1, 5, 10));
        lista.add(new Saida(2, 5, 10));
        lista.add(new Saida(3, 5, 10));

        Saida saida = new Saida(1, 50, 100);

        when(calculosRepository.findAll()).thenReturn(lista); // findAll -> repository
        when(calculosRepository.findById(1L)).thenReturn(Optional.of(saida)); // findById -> repository
    }

    @Test
    void cenario01() {
        // TESTE DE INTEGRAÇÃO COM MOCKITO (USA REPOSITORY -> BANCO DE DADOS)
        // BUSCANDO QUANTAS SAÍDAS POSSUEM
        ResponseEntity<List<Saida>> retorno = this.calculosController.findAll();
        assertEquals(3, retorno.getBody().size());
    }

    @Test
    void cenario02() {
        // TESTE DE INTEGRAÇÃO COM MOCKITO (USA REPOSITORY -> BANCO DE DADOS)
        // BUSCANDO TODOS
        ResponseEntity<List<Saida>> retorno = this.calculosController.findAll();
        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void cenario03() {
        // TESTE DE INTEGRAÇÃO SEM MOCKITO/SEM MOCK (NÃO USA REPOSITORY)
        // CALCULANDO A ENTRADA
        List<Integer> lista = new ArrayList<>();
        lista.add(2);
        lista.add(4);
        lista.add(6);

        Entrada entrada = new Entrada();
        entrada.setLista(lista);

        ResponseEntity<Saida> retorno = this.calculosController.calcular(entrada);
        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void cenario04() {
        // TESTE DE INTEGRAÇÃO COM MOCKITO (USA REPOSITORY -> BANCO DE DADOS)
        // BUSCANDO O ID 1
        ResponseEntity<Saida> retorno = this.calculosController.findById(1L);
        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void cenario05() {
        // TESTE DE INTEGRAÇÃO COM MOCKITO (USA REPOSITORY -> BANCO DE DADOS)
        // VERIFICANDO SE A SOMA DOS DOIS É 50
        ResponseEntity<Saida> retorno = this.calculosController.findById(1L);
        assertEquals(50, retorno.getBody().getSoma());
    }

    @Test
    void cenario06() {
        // TESTE DE INTEGRAÇÃO COM MOCKITO (USA REPOSITORY -> BANCO DE DADOS)
        // BUSCANDO UMA SAÍDA DO ID MENOR QUE 1 (NÃO TEM)
        ResponseEntity<Saida> retorno = this.calculosController.findById(-1L);
        Saida saida = retorno.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
    }
}
