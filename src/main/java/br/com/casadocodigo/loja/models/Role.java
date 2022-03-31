package br.com.casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
public class Role implements GrantedAuthority{

	private static final long serialVersionUID = 1L;

	@Id
	private String nome;
	
	public Role(){
	}

	public Role(String nome) {
	  this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}

	@Override
	public String toString() {
		return this.nome;
	}
}
