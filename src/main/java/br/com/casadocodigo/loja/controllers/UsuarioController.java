package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioDAO dao;

    @Autowired
    private RoleDAO roleDAO;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UsuarioValidation());
    }

    @RequestMapping("/form")
    public ModelAndView form(Usuario usuario) {
        ModelAndView modelAndView = new ModelAndView("usuarios/form");
        return modelAndView;
    }

    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView gravar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes){

        Usuario user = dao.loadUserByUsername(usuario.getEmail());
        if(usuario.getEmail().equals(user.getEmail())){
           result.rejectValue("email", "field.emailExist");
        }

        if(result.hasErrors()){
            return form(usuario);
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setSenha(senhaCriptografada);
        dao.gravar(usuario);
        redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");

        return new ModelAndView("redirect:/usuarios");
    }

    @RequestMapping(value = "/atualizaRoles", method = RequestMethod.POST)
    public ModelAndView atualizaRoles(Usuario usuario, RedirectAttributes redirectAttributes){

        Usuario updateUser = dao.find(usuario.getId());
        updateUser.setRoles(usuario.getRoles());

        updateUser = dao.atualizarRoles(updateUser);

        redirectAttributes.addFlashAttribute("sucesso", "Roles atualizadas com sucesso!");

        ModelAndView modelAndView = new ModelAndView("redirect:/usuarios");
        return modelAndView;
    }


    @RequestMapping("/listarRole")
    public ModelAndView listarRole(String email) {
        Usuario usuario = dao.loadUserByUsername(email);
        List<Role> roles = roleDAO.listRole();
        ModelAndView modelAndView = new ModelAndView("usuarios/role");
        modelAndView.addObject("usuario", usuario);
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }

    @RequestMapping( method= RequestMethod.GET)
    public ModelAndView listar() {
        List<Usuario> usuarios = dao.listar();
        ModelAndView modelAndView = new ModelAndView("usuarios/lista");
        modelAndView.addObject("usuarios", usuarios);
        return modelAndView;
    }

}
