package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    @Autowired
    private NinjaRepository ninjaRepository;

    @Autowired
    private NinjaMapper ninjaMapper;


    // Listar todos os ninjas
    public List<NinjaDTO> listarNinjas() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .toList();
    }

    // Listar todos os meus ninjas por ID
    public NinjaDTO listarNinjasPorId(Long id) {

       return ninjaRepository.findById(id)
               .map(ninjaMapper::map)
               .orElse(null);
    }

    // Criar um novo ninja
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
       NinjaModel ninja = ninjaMapper.map(ninjaDTO);
       ninja = ninjaRepository.save(ninja);
       return ninjaMapper.map(ninja);
    }

    // Deletar Ninja
    public void deletarNinja(Long id) {
        ninjaRepository.deleteById(id);
    }

    // Atualizar Ninja
    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO) {
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);
        if(ninjaExistente.isPresent()) {
            NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO);
            ninjaAtualizado.setId(id);
            NinjaModel ninjaSalvo = ninjaRepository.save(ninjaAtualizado);
            return ninjaMapper.map(ninjaSalvo);
        } else {
            return null;
        }
    }

}
