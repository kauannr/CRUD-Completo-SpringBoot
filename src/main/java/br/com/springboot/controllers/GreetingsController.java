package br.com.springboot.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.Usuario;

import java.util.List;
import java.util.Optional;

import org.hibernate.id.IdentifierGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.springboot.repositories.UsuarioRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class GreetingsController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = "/mostrar/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String requestMethodName(@PathVariable String nome) {
        return "Deu tudo certo, senhor " + nome;
    }

    @GetMapping(value = "/listartodos")
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        List<Usuario> listaComTodos = usuarioRepository.findAll();

        return ResponseEntity.ok().body(listaComTodos);
    }

    @PostMapping(value = "/salvar")
    public ResponseEntity<?> salvarUsuario(@RequestBody Usuario usuario) {

        if(usuario.getNome() != null && usuario.getIdade() != null){

            Usuario user = usuarioRepository.save(usuario);
            return ResponseEntity.created(null).body(user);

        }else{
            return ResponseEntity.badRequest().body("Preencha os campos corretamente");
        }

    }

    @DeleteMapping(value = "/deletar")
    public ResponseEntity<String> deletarUsuario(@RequestParam Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id não existente"));

        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().body("Usuario " + id + " deletado com sucesso!");
    }

    @PutMapping(value = "/atualizar")
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario entity) {
        Usuario usuario = usuarioRepository.findById(entity.getId()).orElseThrow(
                () -> new IllegalArgumentException("Usuário com Id " + entity.getId() + " não existente "));
        usuario.setNome(entity.getNome());
        usuario.setIdade(entity.getIdade());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping(value = "/consultarPorUmNome")
    public ResponseEntity<?> consultarPorContemNome(@RequestParam String param) {
        List<Usuario> minhaLista = usuarioRepository.buscarPorNome(param.trim().toUpperCase());
        if (minhaLista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(minhaLista);
    }

    @GetMapping(value = "/consultarPorNome")
    public ResponseEntity<List<Usuario>> consultarPorNomeIgual(@RequestParam(name = "param") String param) {
        List<Usuario> minhaLista = usuarioRepository.buscarPorNomeIgual(param.toUpperCase().trim());
        if (minhaLista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(minhaLista);
    }

    @GetMapping(value = "/buscarPorId")
    public ResponseEntity<Usuario> buscarPorId(@RequestParam long id) {
        Usuario usuario = usuarioRepository.findById(id).get();

        return ResponseEntity.ok().body(usuario);
    }

}
