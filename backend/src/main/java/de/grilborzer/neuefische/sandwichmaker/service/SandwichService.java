package de.grilborzer.neuefische.sandwichmaker.service;

import de.grilborzer.neuefische.sandwichmaker.model.Sandwich;
import de.grilborzer.neuefische.sandwichmaker.model.SandwichDTO;
import de.grilborzer.neuefische.sandwichmaker.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SandwichService {

    private final SandwichRepository sandwichRepository;
    private final IdService idService;

    @Autowired
    public SandwichService(SandwichRepository sandwichRepository, IdService idService) {
        this.sandwichRepository = sandwichRepository;
        this.idService = idService;
    }

    public List<Sandwich> getAllSandwiches() {
        return sandwichRepository.findAll();
    }

    public Sandwich saveNewSandwich(SandwichDTO sandwichDTO) {
        Sandwich newSandwich = Sandwich.builder()
                // Ein Service der eine ID generiert
                .id(idService.generateID())

                // Was wir übernehmen
                .name(sandwichDTO.getName())
                .patty(sandwichDTO.getPatty())
                .numberOfPatties(sandwichDTO.getNumberOfPatties())
                .isGrilled(sandwichDTO.isGrilled())
                .extraWishes(sandwichDTO.getExtraWishes())
                // Das bauen wir
                .build();

        return sandwichRepository.save(newSandwich);
    }

    public Sandwich getSandwichById(String id) {
        return sandwichRepository.findById(id).orElseThrow(() -> new NoSuchElementException("There is no Sandwich with the requested ID " + id));
    }

    public Sandwich updateSandwich(Sandwich eineVariableVomTypSandwich) {
        if (!sandwichRepository.existsById(eineVariableVomTypSandwich.getId())){
            throw new NoSuchElementException("There is no Sandwich with the requested ID " + eineVariableVomTypSandwich.getId());
        }

        return sandwichRepository.save(eineVariableVomTypSandwich);
    }

    public String deleteSandwichById(String id) {
        if (!sandwichRepository.existsById(id)){
            throw new NoSuchElementException("There is no Sandwich with the requested ID " + id);
        }

        sandwichRepository.deleteById(id);
        return id;
    }
}
