package br.ufma.vagas.service.geral;

import org.springframework.stereotype.Service;

import br.ufma.vagas.domain.geral.Empresa;
import br.ufma.vagas.repository.geral.EmpresaRepository;
import br.ufma.vagas.service.ServiceBase;

@Service
public class EmpresaService extends ServiceBase<Empresa, EmpresaRepository> {

}
