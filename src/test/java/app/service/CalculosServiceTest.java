package app.service;

import app.entity.Saida;
import app.repository.CalculosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CalculosServiceTest {

    @Autowired
    CalculosService calculosService;

    @MockBean
    CalculosRepository calculosRepository;

    @BeforeEach
    void setup() {
        List<Saida> lista = new ArrayList<>();
        lista.add(new Saida(1, 5, 10));
        lista.add(new Saida(2, 5, 10));

        when(calculosRepository.findAll()).thenReturn(lista); // findAll -> repository
    }

    @Test
    // TESTE UNITÁRIO PARA PASSAR SE A SOMA DER CERTO
    void cenario01() {
        List<Integer> lista = new ArrayList<>();
        lista.add(2);
        lista.add(4);
        lista.add(6);

        int retorno = this.calculosService.somar(lista);
        assertEquals(12, retorno);
    }

    @Test
    // TESTE UNITÁRIO PARA NÃO SOMAR SE CASO ALGUM NÚMERO SEJA NULL (Assert Throws))
    void cenario02() {
        List<Integer> lista = new ArrayList<>();
        lista.add(null);
        lista.add(4);
        lista.add(6);

        assertThrows(Exception.class, () -> {
            int retorno = this.calculosService.somar(lista);
        });
    }

    @Test
    void cenario03() {
        // TESTE DE INTEGRAÇÃO COM MOCKITO PARA VER QUANTAS SAÍDAS POSSUEM
        List<Saida> lista = this.calculosService.findAll();
        assertEquals(2, lista.size());
    }

}
