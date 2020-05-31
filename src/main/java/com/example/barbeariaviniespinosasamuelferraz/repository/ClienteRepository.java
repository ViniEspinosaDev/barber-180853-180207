package com.example.barbeariaviniespinosasamuelferraz.repository;

import com.example.barbeariaviniespinosasamuelferraz.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}