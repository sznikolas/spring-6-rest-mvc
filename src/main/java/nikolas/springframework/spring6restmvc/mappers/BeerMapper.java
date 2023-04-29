package nikolas.springframework.spring6restmvc.mappers;

import nikolas.springframework.spring6restmvc.entities.Beer;
import nikolas.springframework.spring6restmvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
