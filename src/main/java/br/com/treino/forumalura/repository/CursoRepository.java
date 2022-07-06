package br.com.treino.forumalura.repository;

import br.com.treino.forumalura.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository <Curso, Long> {

    Curso findByNome(String nome);
}
