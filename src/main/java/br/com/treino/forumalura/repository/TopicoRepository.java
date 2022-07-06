package br.com.treino.forumalura.repository;

import br.com.treino.forumalura.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    //Curso é entidade, então concateno na sequência Nome
    List<Topico> findByCursoNome(String nomeCurso);
}
