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
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private SalaoService salaoService;

    @GetMapping("/clientes")
    public ModelAndView getClientes() {
        ModelAndView mv = new ModelAndView("clientesTemplate");

        mv.addObject("cliente", new Cliente());
        mv.addObject("clientes", clienteService.getClientes());

        return mv;
    }

    @PostMapping("/salvarCliente")
    public String salvarCliente(@ModelAttribute Cliente cliente) {

        clienteService.salvarCliente(cliente);

        return "redirect:/clientes";
    }

    @PostMapping("/associarSalaoCliente")
    public String associarSalao(@ModelAttribute Salao salao, @RequestParam Integer idCliente) {

        Cliente cliente = clienteService.getClienteById(idCliente);
        salao = salaoService.getSalaoById(salao.getIdSalao());

        cliente.getSaloes().add(salao);
        clienteService.salvarCliente(cliente);

        return "redirect:/detalhesCliente/" + idCliente.toString();
    }

    @GetMapping("/detalhesCliente/{idCliente}")
    public ModelAndView getClienteDetalhes(@PathVariable(name = "idCliente") Integer idCliente) {

        Cliente cliente = clienteService.getClienteById(idCliente);
        ModelAndView mv = new ModelAndView("detalhesCliente");

        mv.addObject("cliente", cliente);

        List<Salao> saloesNaoAssociados = salaoService.getSaloes();
        saloesNaoAssociados.removeAll(cliente.getSaloes());

        mv.addObject("saloes", saloesNaoAssociados);

        return mv;
    }

    @GetMapping("/editarCliente")
    public ModelAndView editarCliente(@ModelAttribute Cliente cliente, @RequestParam Integer idCliente) {

        ModelAndView mv = new ModelAndView("clienteEdit");

        Cliente clienteAux = clienteService.getClienteById(cliente.getIdCliente());

        mv.addObject("cliente", clienteAux);

        List<Salao> saloesNaoAssociados = salaoService.getSaloes();
        saloesNaoAssociados.removeAll(clienteAux.getSaloes());

        mv.addObject("saloes", saloesNaoAssociados);

        return mv;
    }

    @GetMapping("/removerSalaoCliente/{idCliente}/{idSalao}")
    public String removerSalaoCliente(@PathVariable(name = "idCliente") Integer idCliente,
            @PathVariable(name = "idSalao") Integer idSalao) {

        Cliente cliente = clienteService.getClienteById(idCliente);

        clienteService.removerSalaoCliente(cliente, salaoService.getSalaoById(idSalao));

        return "redirect:/editarCliente?idCliente='" + idCliente.toString();
    }

}