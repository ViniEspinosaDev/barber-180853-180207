package com.example.barbeariaviniespinosasamuelferraz.controller;

import java.util.List;

import com.example.barbeariaviniespinosasamuelferraz.entity.Cliente;
import com.example.barbeariaviniespinosasamuelferraz.entity.Salao;
import com.example.barbeariaviniespinosasamuelferraz.service.ClienteService;
import com.example.barbeariaviniespinosasamuelferraz.service.SalaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SalaoController {

    @Autowired
    private SalaoService salaoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/saloes")
    public ModelAndView getSaloes() {
        ModelAndView mv = new ModelAndView("saloesTemplate");

        mv.addObject("salao", new Salao());
        mv.addObject("saloes", salaoService.getSaloes());

        return mv;
    }

    @PostMapping("/salvarSalao")
    public String salvarSalao(@ModelAttribute Salao salao) {

        salaoService.salvarSalao(salao);

        return "redirect:/saloes";
    }

    @PostMapping("/associarClienteSalao")
    public String associarCliente(@ModelAttribute Cliente cliente, @RequestParam Integer idSalao) {

        Salao salao = salaoService.getSalaoById(idSalao);
        cliente = clienteService.getClienteById(cliente.getIdCliente());

        salao.getClientes().add(cliente);
        salaoService.salvarSalao(salao);

        return "redirect:/detalhesSalao/" + idSalao.toString();
    }

    @GetMapping("/detalhesSalao/{idSalao}")
    public ModelAndView getSalaoDetalhes(@PathVariable(name = "idSalao") Integer idSalao) {

        Salao salao = salaoService.getSalaoById(idSalao);
        ModelAndView mv = new ModelAndView("detalhesSalao");

        mv.addObject("salao", salao);

        List<Cliente> clientesNaoAssociados = clienteService.getClientes();
        clientesNaoAssociados.removeAll(salao.getClientes());

        mv.addObject("clientes", clientesNaoAssociados);

        return mv;
    }

    @GetMapping("/editarSalao")
    public ModelAndView editarSalao(@ModelAttribute Salao salao, @RequestParam Integer idSalao) {

        ModelAndView mv = new ModelAndView("salaoEdit");

        Salao salaoAux = salaoService.getSalaoById(salao.getIdSalao());

        mv.addObject("salao", salaoAux);

        List<Cliente> clientesNaoAssociados = clienteService.getClientes();
        clientesNaoAssociados.removeAll(salaoAux.getClientes());

        mv.addObject("clientes", clientesNaoAssociados);

        return mv;
    }

    @GetMapping("/removerClienteSalao/{idSalao}/{idCliente}")
    public String removerClienteSalao(@PathVariable(name = "idSalao") Integer idSalao,
            @PathVariable(name = "idCliente") Integer idCliente) {

        Salao salao = salaoService.getSalaoById(idSalao);

        salaoService.removerClienteSalao(salao, clienteService.getClienteById(idCliente));

        return "redirect:/editarSalao?idSalao=" + idSalao.toString();
    }

    @GetMapping("/removerSalao")
    public String removerSalao(@ModelAttribute Salao salao, @RequestParam Integer idSalao) {

        salaoService.removerSalao(salao);

        return "redirect:/saloes";
    }
}