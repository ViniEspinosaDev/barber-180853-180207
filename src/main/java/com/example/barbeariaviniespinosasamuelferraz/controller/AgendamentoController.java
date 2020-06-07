package com.example.barbeariaviniespinosasamuelferraz.controller;

import com.example.barbeariaviniespinosasamuelferraz.entity.Agendamento;
import com.example.barbeariaviniespinosasamuelferraz.entity.Barbeiro;
import com.example.barbeariaviniespinosasamuelferraz.entity.DataBarbeiro;
import com.example.barbeariaviniespinosasamuelferraz.service.AgendamentoService;
import com.example.barbeariaviniespinosasamuelferraz.service.BarbeiroService;
import com.example.barbeariaviniespinosasamuelferraz.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private BarbeiroService barbeiroService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/agendamentos")
    public ModelAndView getAgendamentos() {
        ModelAndView mv = new ModelAndView("agendamentosTemplate");

        mv.addObject("agendamentos", agendamentoService.getAgendamentos());
        mv.addObject("barbeiros", barbeiroService.getBarbeiros());
        mv.addObject("dataBarbeiro", new DataBarbeiro());

        return mv;
    }

    @GetMapping("/agendamentosBarbeiro/{idBarbeiro}")
    public ModelAndView getAgendamentosBarbeiro(@PathVariable(name = "idBarbeiro") Integer idBarbeiro) {
        ModelAndView mv = new ModelAndView("agendamentosTemplate");

        Barbeiro barbeiro = barbeiroService.getBarbeiroById(idBarbeiro);
        mv.addObject("agendamentos", barbeiro.getAgendamentos());

        return mv;
    }

    @GetMapping("/criarAgendamento")
    public ModelAndView criarAgendamento() {

        ModelAndView mv = new ModelAndView("criarAgendamento");

        mv.addObject("agendamento", new Agendamento());
        mv.addObject("barbeiros", barbeiroService.getBarbeiros());
        mv.addObject("clientes", clienteService.getClientes());

        return mv;
    }

    @PostMapping("/salvarAgendamento")
    public String salvarAgendamento(@ModelAttribute Agendamento agendamento) {

        if (agendamentoService.salvarAgendamento(agendamento)) {
            return "redirect:/detalhesAgendamento/" + agendamento.getIdAgendamento();
        } else {
            return "redirect:/naoDeuCertoAgendar";
        }

    }

    @GetMapping("/editarAgendamento")
    public ModelAndView editarAgendamento(@ModelAttribute Agendamento agendamento,
            @RequestParam Integer idAgendamento) {

        ModelAndView mv = new ModelAndView("agendamentoEdit");

        Agendamento agendamentoAux = agendamentoService.getAgendamentoById(agendamento.getIdAgendamento());

        mv.addObject("agendamento", agendamentoAux);
        mv.addObject("barbeiros", barbeiroService.getBarbeiros());
        mv.addObject("clientes", clienteService.getClientes());

        return mv;
    }

    @GetMapping("/removerAgendamento")
    public String removerAgendamento(@ModelAttribute Agendamento agendamento, @RequestParam Integer idAgendamento) {

        agendamentoService.removerAgendamento(agendamento);

        return "redirect:/agendamentos";
    }

    @GetMapping("/naoDeuCertoAgendar")
    public ModelAndView naoDeuCerto() {
        ModelAndView mv = new ModelAndView("naoDeuCerto");

        return mv;
    }

    @GetMapping("/detalhesAgendamento/{idAgendamento}")
    public ModelAndView getAgendamentoDetalhes(@PathVariable(name = "idAgendamento") Integer idAgendamento) {

        Agendamento agendamento = agendamentoService.getAgendamentoById(idAgendamento);
        ModelAndView mv = new ModelAndView("detalhesAgendamento");

        mv.addObject("agendamento", agendamento);

        return mv;
    }

}