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

import javax.persistence.UniqueConstraint;

@Entity
public class Especialidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEspecialidade;

    private String descricao;
    private double preco;
    private LocalTime tempoMedio;

    @ManyToMany
    @JoinTable(name = "BarbeiroEspecialidade", uniqueConstraints = @UniqueConstraint(columnNames = { "idBarbeiro",
            "idEspecialidade" }), joinColumns = @JoinColumn(name = "idEspecialidade"), inverseJoinColumns = @JoinColumn(name = "idBarbeiro"))
    private List<Barbeiro> barbeiros;

    @ManyToMany
    @JoinTable(name = "AgendamentoEspecialidade", uniqueConstraints = @UniqueConstraint(columnNames = { "idAgendamento",
            "idEspecialidade" }), joinColumns = @JoinColumn(name = "idEspecialidade"), inverseJoinColumns = @JoinColumn(name = "idAgendamento"))
    private List<Agendamento> agendamentos;

    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalTime getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(LocalTime tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public List<Barbeiro> getBarbeiros() {
        return barbeiros;
    }

    public void setBarbeiros(List<Barbeiro> barbeiros) {
        this.barbeiros = barbeiros;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

}
