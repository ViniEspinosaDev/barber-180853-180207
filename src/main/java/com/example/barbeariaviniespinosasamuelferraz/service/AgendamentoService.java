package com.example.barbeariaviniespinosasamuelferraz.service;

import java.sql.Date;
import java.util.ArrayList;
import java.time.LocalTime;
import java.util.List;

import com.example.barbeariaviniespinosasamuelferraz.entity.Agendamento;
import com.example.barbeariaviniespinosasamuelferraz.entity.Barbeiro;
import com.example.barbeariaviniespinosasamuelferraz.entity.Especialidade;
import com.example.barbeariaviniespinosasamuelferraz.repository.AgendamentoRepository;
import com.example.barbeariaviniespinosasamuelferraz.repository.BarbeiroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private BarbeiroService barbeiroService;

    public List<Agendamento> getAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public boolean salvarAgendamento(Agendamento agendamento) {

        Barbeiro b = barbeiroService.getBarbeiroById(agendamento.getBarbeiro().getIdBarbeiro());
        List<Especialidade> especi = new ArrayList<Especialidade>();
        for (Especialidade esp : b.getEspecialidades()) {
            especi.add(esp);
        }
        agendamento.setEspecialidades(especi);
        // Pega todos as especialidades do agendamento
        List<Especialidade> especialidadesAgendamento = agendamento.getEspecialidades();

        if (especialidadesAgendamento.size() == 0) {
            return false;
        }
        // Inicia uma variavel contadora de minutos em 0
        int minutos = 0;
        // Inicia uma variavel contadora de preco em 0
        double preco = 0.00;
        // Pega a hora inicial do agendamento
        LocalTime horaInicial = agendamento.getHoraInicial();

        // Para cada especialidade do agendamento, somar o tempo medio em minutos e
        // passar para variavel contadora de tempo
        for (Especialidade e : especialidadesAgendamento) {
            minutos += e.getTempoMedio().getMinute();
            preco += e.getPreco();
        }
        // Definir o preco total do agendamento
        agendamento.setPrecoTotal(preco);

        // Definir hora final do agendamento sendo a soma da hora inicial mais os
        // minutos do tempo medio de cada especialidade
        agendamento.setHoraFinal(agendamento.getHoraInicial().plusMinutes(minutos));
        // Pegar a hora final do agendamento
        LocalTime horaFinal = agendamento.getHoraFinal();

        // Para cada agendamento já marcado para o mesmo dia e com o mesmo barbeiro do
        // agendamento atual
        for (Agendamento n : getAgendamentosMarcados(agendamento.getBarbeiro().getIdBarbeiro(),
                agendamento.getData())) {

            // Verificar se a hora inicial A e hora final A estão dentro de algum intervalo
            // de agendamento já existente
            if (!(horaInicial.isAfter(n.getHoraFinal()) && horaFinal.isAfter(n.getHoraFinal()))
                    && !(horaInicial.isBefore(n.getHoraInicial()) && horaFinal.isBefore(n.getHoraInicial()))) {
                // Se já existir retorna falso para nao agendar novamente
                return false;
            }
        }
        // Se chegou até aqui é por que nao existe nenhum agendamento que esteja no
        // intervalo entre a hora inicial e final do agendamento atual
        agendamentoRepository.save(agendamento);
        return true;
    }

    public Agendamento getAgendamentoById(int id) {
        return agendamentoRepository.findById(id).get();
    }

    public void removerAgendamento(Agendamento agendamento) {
        agendamentoRepository.delete(agendamento);
    }

    public List<Agendamento> getAgendamentosMarcados(int idBarbeiro, Date data) {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        List<Agendamento> agendamentoMarcado = new ArrayList<Agendamento>();

        for (Agendamento a : agendamentos) {
            if (a.getBarbeiro().getIdBarbeiro() == idBarbeiro) {
                if (a.getData().equals(data)) {
                    agendamentoMarcado.add(a);
                }
            }
        }

        return agendamentoMarcado;
    }
}