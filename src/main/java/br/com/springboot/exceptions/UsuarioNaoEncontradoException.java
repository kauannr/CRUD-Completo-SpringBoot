package br.com.springboot.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(String msg){
        super(msg);
    }
    
}
