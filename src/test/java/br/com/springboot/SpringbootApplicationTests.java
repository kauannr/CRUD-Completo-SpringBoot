package br.com.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.springboot.model.Usuario;
import br.com.springboot.repositories.UsuarioRepository;

@SpringBootTest
class SpringbootApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	void inserirUser() {
		Usuario usuario = new Usuario("fatima bernadez", 80);
		usuarioRepository.save(usuario);
		System.out.println("Usuário inserido");
	}

}
