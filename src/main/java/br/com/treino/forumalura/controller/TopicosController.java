package br.com.treino.forumalura.controller;

import br.com.treino.forumalura.dto.AtualizacaoTopicoFormDTO;
import br.com.treino.forumalura.dto.DetalhesTopicoDTO;
import br.com.treino.forumalura.dto.TopicoDTO;
import br.com.treino.forumalura.dto.TopicoFormDTO;
import br.com.treino.forumalura.modelo.Curso;
import br.com.treino.forumalura.modelo.Topico;
import br.com.treino.forumalura.repository.CursoRepository;
import br.com.treino.forumalura.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController //utilizado para indicar que o controller vai retornar um resultado (por ser rest), não redirecionar para outra página
@RequestMapping("/topicos") //este seria um "prefixo", qualquer complemento a mais pode ser feito nos métodos
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDTO> lista(String nomeCurso){
        if (nomeCurso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDTO.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDTO.converter(topicos);
        }
        //Abaixo, procedimento para criar dados manuais e retornar os resultados em uma lista;
        //Topico topico = new Topico("Dúvida", "Dúvida com Spring", new Curso("Spring", "Programação"));
        //return TopicoDTO.converter(Arrays.asList(topico)); //converte o resultado em um array
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDTO> cadastrar (@RequestBody @Valid TopicoFormDTO form, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicoDTO> detalhar(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()){
            return ResponseEntity.ok(new DetalhesTopicoDTO(topico.get())) ;
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoFormDTO form){
        Optional<Topico> optional = topicoRepository.findById(id);
        if(optional.isPresent()){
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover (@PathVariable Long id){
        Optional<Topico> optional = topicoRepository.findById(id);
        if(optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
