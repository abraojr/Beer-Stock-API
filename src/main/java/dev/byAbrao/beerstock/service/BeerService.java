package dev.byAbrao.beerstock.service;

import dev.byAbrao.beerstock.dto.BeerDTO;
import dev.byAbrao.beerstock.entity.Beer;
import dev.byAbrao.beerstock.exception.BeerAlreadyRegisteredException;
import dev.byAbrao.beerstock.mapper.BeerMapper;
import dev.byAbrao.beerstock.repository.BeerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

    public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
        Optional<Beer> optSavedBeer = beerRepository.findByName(beerDTO.getName());
        if (optSavedBeer.isPresent()) {
            throw new BeerAlreadyRegisteredException(beerDTO.getName());
        }
        Beer beer = beerMapper.toModel(beerDTO);
        Beer savedBeer = beerRepository.save(beer);
        return beerMapper.toDTO(savedBeer);
    }
}
