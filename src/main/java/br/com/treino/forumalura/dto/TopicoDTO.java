package br.com.treino.forumalura.dto;

import br.com.treino.forumalura.modelo.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDTO {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    public TopicoDTO(Topico topico){
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }

    //método de conversão de topico para topicoDTO encapsulado
    //recebe a lista de tópicos e converte para tópico DTO
    public static List<TopicoDTO> converter(List<Topico> topicos) {
        //stream do java8 e faz um map de tópico para tópico DTO
        //mapeamento com :: ele chama o próprio construtor como parâmetro
        //collectors.tolist transforma em lista
        return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
