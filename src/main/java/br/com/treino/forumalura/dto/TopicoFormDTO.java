package br.com.treino.forumalura.dto;

import br.com.treino.forumalura.modelo.Curso;
import br.com.treino.forumalura.modelo.Topico;
import br.com.treino.forumalura.repository.CursoRepository;
import br.com.treino.forumalura.repository.TopicoRepository;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicoFormDTO {

    @NotNull @NotEmpty
    private String titulo;
    @NotNull @NotEmpty
    private String mensagem;
    @NotNull @NotEmpty
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
