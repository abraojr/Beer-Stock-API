package dev.byAbrao.beerstock.service;

import dev.byAbrao.beerstock.dto.BeerDTO;
import dev.byAbrao.beerstock.entity.Beer;
import dev.byAbrao.beerstock.exception.BeerAlreadyRegisteredException;
import dev.byAbrao.beerstock.exception.BeerNotFoundException;
import dev.byAbrao.beerstock.mapper.BeerMapper;
import dev.byAbrao.beerstock.repository.BeerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

    public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(beerDTO);
        Beer beer = beerMapper.toModel(beerDTO);
        Beer savedBeer = beerRepository.save(beer);
        return beerMapper.toDTO(savedBeer);
    }

    public BeerDTO findByName(String name) throws BeerNotFoundException {
        Beer foundBeer = beerRepository.findByName(name)
                .orElseThrow(() -> new BeerNotFoundException(name));
        return beerMapper.toDTO(foundBeer);
    }

    public List<BeerDTO> listAll() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws BeerNotFoundException {
        verifyIfExists(id);
        beerRepository.deleteById(id);
    }

    private void verifyIfIsAlreadyRegistered(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        Optional<Beer> optSavedBeer = beerRepository.findByName(beerDTO.getName());
        if (optSavedBeer.isPresent()) {
            throw new BeerAlreadyRegisteredException(beerDTO.getName());
        }
    }

    private void verifyIfExists(Long id) throws BeerNotFoundException {
        beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException(id));
    }

}
