package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    @Autowired
    private NinjaRepository ninjaRepository;

    // Listar todos os ninjas
    public List<NinjaModel> listarNinjas() {
        return ninjaRepository.findAll();
    }

    // Listar todos os meus ninjas por ID
    public NinjaModel listarNinjasPorId(Long id) {
        return ninjaRepository.findById(id).orElse(null);
    }

    // Criar um novo ninja
    public NinjaModel criarNinja(NinjaModel ninja) {
        return ninjaRepository.save(ninja);
    }

    // Deletar Ninja
    public void deletarNinja(Long id) {
        ninjaRepository.deleteById(id);
    }

    // Atualizar Ninja
    public NinjaModel atualizarNinja(Long id, NinjaModel ninja) {
        if (ninjaRepository.existsById(id)) {
            ninja.setId(id);
            return ninjaRepository.save(ninja);
        }
        return null;
    }

}
