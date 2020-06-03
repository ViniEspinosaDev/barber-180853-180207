package com.example.barbeariaviniespinosasamuelferraz.controller;

import com.example.barbeariaviniespinosasamuelferraz.entity.Cliente;
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
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

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

    @GetMapping("/detalhesCliente/{idCliente}")
    public ModelAndView getClienteDetalhes(@PathVariable(name = "idCliente") Integer idCliente) {

        Cliente cliente = clienteService.getClienteById(idCliente);
        ModelAndView mv = new ModelAndView("detalhesCliente");

        mv.addObject("cliente", cliente);

        return mv;
    }

    @GetMapping("/editarCliente")
    public ModelAndView editarCliente(@ModelAttribute Cliente cliente, @RequestParam Integer idCliente) {

        ModelAndView mv = new ModelAndView("clienteEdit");

        Cliente clienteAux = clienteService.getClienteById(cliente.getIdCliente());

        mv.addObject("cliente", clienteAux);

        return mv;
    }

    @GetMapping("/removerCliente")
    public String removerCliente(@ModelAttribute Cliente cliente, @RequestParam Integer idCliente) {

        clienteService.removerCliente(cliente);

        return "redirect:/clientes";
    }

}