package br.com.casadocodigo.loja.validation;

import br.com.casadocodigo.loja.models.Usuario;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UsuarioValidation implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {return Usuario.class.isAssignableFrom(clazz); }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "senha", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "senhaRepetida", "field.required");


        Usuario usuario = (Usuario) target;

        if(usuario.getSenha().length() < 5){
            errors.rejectValue("senha", "field.lessThan5");
        }

        if(!usuario.getSenha().equals(usuario.getSenhaRepetida())){
            errors.rejectValue("senhaRepetida", "field.notEquals");
        }



    }

}
