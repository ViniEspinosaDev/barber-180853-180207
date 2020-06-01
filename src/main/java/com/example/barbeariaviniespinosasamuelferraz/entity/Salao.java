package com.example.barbeariaviniespinosasamuelferraz.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.UniqueConstraint;

@Entity
public class Salao implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idSalao;

    private String nome;
    private String endereco;
    private String cidade;

    private LocalTime horaAbre;
    private LocalTime horaFecha;

    @OneToMany
    @JoinColumn(name = "idSalao")
    private List<Barbeiro> barbeiros;

    @ManyToMany
    @JoinTable(name = "ClienteSalao", uniqueConstraints = @UniqueConstraint(columnNames = { "idCliente",
            "idSalao" }), joinColumns = @JoinColumn(name = "idSalao"), inverseJoinColumns = @JoinColumn(name = "idCliente"))
    private List<Cliente> clientes;

    @OneToMany
    @JoinColumn(name = "idSalao")
    private List<Agendamento> agendamentos;

    public int getIdSalao() {
        return idSalao;
    }

    public void setIdSalao(int idSalao) {
        this.idSalao = idSalao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public LocalTime getHoraAbre() {
        return horaAbre;
    }

    public void setHoraAbre(LocalTime horaAbre) {
        this.horaAbre = horaAbre;
    }

    public List<Barbeiro> getBarbeiros() {
        return barbeiros;
    }

    public void setBarbeiros(List<Barbeiro> barbeiros) {
        this.barbeiros = barbeiros;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getHoraFecha() {
        return horaFecha;
    }

    public void setHoraFecha(LocalTime horaFecha) {
        this.horaFecha = horaFecha;
    }

}