package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.models.UsuarioForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm() {
		return "loginForm";
	}

}
