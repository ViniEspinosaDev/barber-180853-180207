package com.example.barbeariaviniespinosasamuelferraz.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Cliente implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCliente;

    private String nome;
    private String cidade;
    private String cpf;
    private String endereco;
    private Date dataNascimento;

    @OneToMany
    @JoinColumn(name = "idCliente")
    private List<Agendamento> agendamentos;

    /*
     * @ManyToMany
     * 
     * @JoinTable(name = "ClienteSalao", uniqueConstraints
     * = @UniqueConstraint(columnNames = { "idCliente", "idSalao" }), joinColumns
     * = @JoinColumn(name = "idCliente"), inverseJoinColumns = @JoinColumn(name =
     * "idSalao")) private List<Salao> saloes;
     */

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Cliente [agendamentos=" + agendamentos + ", cidade=" + cidade + ", cpf=" + cpf + ", dataNascimento="
                + dataNascimento + ", endereco=" + endereco + ", idCliente=" + idCliente + ", nome=" + nome + "]";
    }

}
