package com.crud.clientes.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.crud.clientes.model.Cliente;
import com.crud.clientes.repository.ClientesRepository;
import org.hibernate.validator.constraints.Length;

public class ClienteForm {

    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String nome;

    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String cpf;

    @NotNull
    @NotEmpty
    @Length(min = 2)
    private String idade;

    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String endereco;

    @NotNull
    @NotEmpty
    @Length(min = 8)
    private String cep;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cliente cadastrarCliente(ClientesRepository clientesRepository) {
        return new Cliente(nome, cpf, idade, endereco, cep);
    }
}
