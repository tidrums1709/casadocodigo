package br.com.casadocodigo.loja.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
public class UsuarioForm extends Usuario{

	private String senhaRepetida;

	public Usuario convertToUsuario (){
		Usuario usuario = new Usuario();
		usuario.setNome(this.getNome());
		usuario.setEmail(this.getEmail());
		usuario.setSenha(this.getSenha());

		return usuario;
	}
	
}
