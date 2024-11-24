package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    @Autowired
    private MissoesRepository missoesRepository;

    @Autowired
    private MissoesMapper missoesMapper;

    // Listar todas as missões
    public List<MissoesDTO> listarMissoes() {
        List<MissoesModel> missoes = missoesRepository.findAll();
        return missoes.stream()
                .map(missoesMapper::map)
                .toList();
    }

    // Listar missões por id
    public MissoesDTO listarMissoesPorId(Long id) {
        return missoesRepository.findById(id).map(missoesMapper::map).orElse(null);
    }

    // Criar nova missão
    public MissoesDTO criarMissao(MissoesDTO missoesDTO) {
        MissoesModel missoesModel = missoesMapper.map(missoesDTO);
        missoesModel = missoesRepository.save(missoesModel);
        return missoesMapper.map(missoesModel);
    }

    // Deletar missão
    public void deletarMissao(Long id) {
        missoesRepository.deleteById(id);
    }

    // Atualizar missão
    public MissoesDTO atualizarMissao(Long id, MissoesDTO missoesDTO) {
        Optional <MissoesModel> missaoExistente = missoesRepository.findById(id);
        if(missaoExistente.isPresent()) {
            MissoesModel missaoAtualizada = missoesMapper.map(missoesDTO);
            missaoAtualizada.setId(id);
            MissoesModel missaoSalva = missoesRepository.save(missaoAtualizada);
            return missoesMapper.map(missaoSalva);
        }

        return null;
    }

}
