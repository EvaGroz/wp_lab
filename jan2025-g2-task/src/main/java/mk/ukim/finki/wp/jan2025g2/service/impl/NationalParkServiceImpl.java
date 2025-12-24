package mk.ukim.finki.wp.jan2025g2.service.impl;


import mk.ukim.finki.wp.jan2025g2.model.NationalPark;
import mk.ukim.finki.wp.jan2025g2.model.ParkLocation;
import mk.ukim.finki.wp.jan2025g2.model.ParkType;
import mk.ukim.finki.wp.jan2025g2.model.exceptions.InvalidNationalParkIdException;
import mk.ukim.finki.wp.jan2025g2.repository.NationalParkRepository;
import mk.ukim.finki.wp.jan2025g2.service.NationalParkService;
import mk.ukim.finki.wp.jan2025g2.service.ParkLocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.jan2025g2.service.FieldFilterSpecification.*;

@Service
public class NationalParkServiceImpl implements NationalParkService {
    private final NationalParkRepository nationalParkRepository;
    private final ParkLocationService parkLocationService;

    public NationalParkServiceImpl(NationalParkRepository nationalParkRepository, ParkLocationService parkLocationService) {
        this.nationalParkRepository = nationalParkRepository;
        this.parkLocationService = parkLocationService;
    }

    /**
     * @return List of all national parks in the database
     */
    @Override
    public List<NationalPark> listAll(){
        return nationalParkRepository.findAll();
    }

    /**
     * @param id The id of the national park that we want to obtain
     * @return The national park with the appropriate id
     * @throws InvalidNationalParkIdException when there is no national park with the given id
     */
    @Override
    public NationalPark findById(Long id){
        return nationalParkRepository.findById(id).orElseThrow(() -> new InvalidNationalParkIdException());
    }

    /**
     * This method is used to create a new national park and save it in the database.
     *
     * @param name       The name of the national park
     * @param areaSize   The area size of the national park
     * @param rating     The rating of the national park
     * @param parkType   The park type associated with the national park
     * @param locationId The id of the location where the national park is found
     * @return The national park that is created. The id should be generated when the national park is created.
     */
    @Override
    public NationalPark create(String name, Double areaSize, Double rating, ParkType parkType, Long locationId){
        if (name == null || name.isEmpty() || areaSize < 0 || rating < 0 || parkType == null || locationId <0 ){
            throw new IllegalArgumentException();
        }

        ParkLocation parkLocation = parkLocationService.findById(locationId);
        NationalPark np = new NationalPark(name, areaSize, rating, parkType, parkLocation);

        return this.nationalParkRepository.save(np);
    }

    /**
     * This method is used to update a national park and save it in the database.
     *
     * @param id         The id of the national park that is being updated
     * @param name       The name of the national park
     * @param areaSize   The area size of the national park
     * @param rating     The rating of the national park
     * @param parkType   The park type associated with the national park
     * @param locationId The id of the location of the national park
     * @return The national park that is updated.
     * @throws InvalidNationalParkIdException when there is no national park with the given id
     */
    @Override
    public NationalPark update(Long id, String name, Double areaSize, Double rating, ParkType parkType, Long locationId){
        if (name == null || name.isEmpty() || areaSize < 0 || rating < 0 || parkType == null || locationId <0 ){
            throw new IllegalArgumentException();
        }

        ParkLocation parkLocation = parkLocationService.findById(locationId);
        NationalPark np = nationalParkRepository.findById(id).orElseThrow(() -> new InvalidNationalParkIdException());
        np.setName(name);
        np.setAreaSize(areaSize);
        np.setRating(rating);
        np.setParkType(parkType);
        np.setLocation(parkLocation);

        return this.nationalParkRepository.save(np);
    }

    /**
     * This method deletes a national park from the database.
     *
     * @param id The id of the national park that we want to delete
     * @return The national park that is deleted.
     * @throws InvalidNationalParkIdException when there is no national park with the given id
     */
    @Override
    public NationalPark delete(Long id){
        NationalPark np = nationalParkRepository.findById(id).orElseThrow(() -> new InvalidNationalParkIdException());
        this.nationalParkRepository.deleteById(id);

        return np;
    }

    /**
     * This method closes a national park.
     *
     * @param id The ID of the national park we want to close.
     * @return The updated national park.
     * @throws InvalidNationalParkIdException If the national park with the given ID does not exist.
     */
    @Override
    public NationalPark close(Long id){
        NationalPark np = nationalParkRepository.findById(id).orElseThrow(() -> new InvalidNationalParkIdException());
        np.setClosed(true);

        return this.nationalParkRepository.save(np);
    }

    /**
     * Returns a page of national parks that match the given criteria.
     *
     * @param name       Filters national parks whose names contain the specified text.
     * @param areaSize   Filters national parks bigger than the specified area size.
     * @param rating     Filters national parks with a rating greater than the specified value.
     * @param parkType   Filters national parks based on the park type.
     * @param locationId Filters national parks by the specified locationId.
     * @param pageNum    The page number.
     * @param pageSize   The number of items per page.
     * @return The page of national parks that match the given criteria.
     */
    @Override
    public Page<NationalPark> findPage(String name, Double areaSize, Double rating, ParkType parkType, Long locationId, int pageNum, int pageSize){
        Specification<NationalPark> specification = Specification.allOf(
                filterContainsText(NationalPark.class, "name", name),
                greaterThan(NationalPark.class, "areaSize", areaSize),
                greaterThan(NationalPark.class, "rating", rating),
                filterEqualsV(NationalPark.class, "parkType", parkType),
                filterEqualsV(NationalPark.class, "parkLocation.id", locationId)
        );

        return this.nationalParkRepository.findAll(
                specification,
                PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "name"))
        );
    }
}
