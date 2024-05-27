package br.com.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where upper(trim(u.nome)) like %?1%") 
    public List<Usuario> buscarPorNome(String nome);

    @Query("select u from Usuario u where upper(trim(u.nome)) = :nome") 
    public List<Usuario> buscarPorNomeIgual(String nome);
}
