package br.com.casadocodigo.loja.validation;

import br.com.casadocodigo.loja.models.UsuarioForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UsuarioValidation implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {return UsuarioForm.class.isAssignableFrom(clazz); }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "senha", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "senhaRepetida", "field.required");


        UsuarioForm usuarioForm = (UsuarioForm) target;

        if(usuarioForm.getSenha().length() < 5){
            errors.rejectValue("senha", "field.lessThan5");
        }

        if(!usuarioForm.getSenha().equals(usuarioForm.getSenhaRepetida())){
            errors.rejectValue("senhaRepetida", "field.notEquals");
        }



    }

}
