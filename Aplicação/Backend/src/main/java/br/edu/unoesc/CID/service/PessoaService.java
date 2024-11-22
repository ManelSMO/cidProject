package br.edu.unoesc.CID.service;

import br.edu.unoesc.CID.entity.Pessoa;
import br.edu.unoesc.CID.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço responsável por gerenciar as operações relacionadas às pessoas.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    /**
     * Cadastra uma nova pessoa no sistema.
     *
     * @param pessoa a entidade da pessoa a ser cadastrada.
     * @return a pessoa cadastrada.
     */
    public Pessoa cadastrarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    /**
     * Lista todas as pessoas cadastradas no sistema.
     *
     * @return uma lista de pessoas.
     */
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    /**
     * Busca uma pessoa pelo seu ID.
     *
     * @param id o ID da pessoa a ser buscada.
     * @return a pessoa correspondente ao ID informado.
     * @throws RuntimeException se a pessoa não for encontrada.
     */
    public Pessoa buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id)

