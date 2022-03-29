package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.models.UsuarioForm;
import br.com.casadocodigo.loja.validation.UsuarioValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioDAO dao;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UsuarioValidation());
    }

    @RequestMapping("/form")
    public ModelAndView form(UsuarioForm usuarioForm) {
        ModelAndView modelAndView = new ModelAndView("usuarios/form");
        return modelAndView;
    }

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView gravar(@Valid UsuarioForm usuarioForm, BindingResult result, RedirectAttributes redirectAttributes){

        Usuario usuario = dao.loadUserByUsername(usuarioForm.getEmail());
        if(usuarioForm.getEmail().equals(usuario.getEmail())){
           result.rejectValue("email", "field.emailExist");
        }

        if(result.hasErrors()){
            return form(usuarioForm);
        }


        dao.gravar(usuarioForm.convertToUsuario());
        redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");

        return new ModelAndView("redirect:/usuarios");
    }

    @RequestMapping( method= RequestMethod.GET)
    public ModelAndView listar() {
        List<Usuario> usuarios = dao.listar();
        ModelAndView modelAndView = new ModelAndView("usuarios/lista");
        modelAndView.addObject("usuarios", usuarios);
        return modelAndView;
    }

}
