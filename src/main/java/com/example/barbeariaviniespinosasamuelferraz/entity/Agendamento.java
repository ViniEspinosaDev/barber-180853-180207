package com.example.barbeariaviniespinosasamuelferraz.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.UniqueConstraint;

@Entity
public class Agendamento implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAgendamento;

    private Date data;
    private Time hora;
    private double precoTotal;
    private Time tempoEstimado;

    @ManyToOne
    @JoinColumn(name = "idBarbeiro")
    private Barbeiro barbeiro;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "AgendamentoEspecialidade", uniqueConstraints = @UniqueConstraint(columnNames = { "idAgendamento",
            "idEspecialidade" }), joinColumns = @JoinColumn(name = "idAgendamento"), inverseJoinColumns = @JoinColumn(name = "idEspecialidade"))
    private List<Especialidade> especialidades;

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Time getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Time tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    @Override
    public String toString() {
        return "Agendamento [barbeiro=" + barbeiro + ", cliente=" + cliente + ", data=" + data + ", especialidades="
                + especialidades + ", hora=" + hora + ", idAgendamento=" + idAgendamento + ", precoTotal=" + precoTotal
                + ", tempoEstimado=" + tempoEstimado + "]";
    }

}