package com.crud.clientes.form;

import com.crud.clientes.model.Cliente;
import com.crud.clientes.repository.ClientesRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizarClienteForm {
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

    public Cliente atualizarCliente(Long id, ClientesRepository clientesRepository) {
        Cliente cliente = clientesRepository.getOne(id);

        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setIdade(this.idade);
        cliente.setEndereco(this.endereco);
        cliente.setCep(this.cep);

        return cliente;
    }
}
