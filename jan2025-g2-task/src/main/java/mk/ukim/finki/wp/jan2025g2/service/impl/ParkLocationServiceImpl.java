package mk.ukim.finki.wp.jan2025g2.service.impl;

import mk.ukim.finki.wp.jan2025g2.model.ParkLocation;
import mk.ukim.finki.wp.jan2025g2.model.exceptions.InvalidParkLocationIdException;
import mk.ukim.finki.wp.jan2025g2.repository.ParkLocationRepository;
import mk.ukim.finki.wp.jan2025g2.service.ParkLocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkLocationServiceImpl implements ParkLocationService {
    private final ParkLocationRepository parkLocationRepository;

    public ParkLocationServiceImpl(ParkLocationRepository parkLocationRepository) {
        this.parkLocationRepository = parkLocationRepository;
    }

    /**
     * @param id The id of the location that we want to obtain
     * @return The location with the appropriate id
     * @throws InvalidParkLocationIdException when there is no location with the given id
     */
    @Override
    public ParkLocation findById(Long id){
        return this.parkLocationRepository.findById(id).orElseThrow(() -> new InvalidParkLocationIdException());
    }

    /**
     * @return List of all locations in the database
     */
    @Override
    public List<ParkLocation> listAll(){
        return this.parkLocationRepository.findAll();
    }

    /**
     * This method is used to create a new location and save it in the database.
     *
     * @param country   The country of the location
     * @param continent The continent of the location
     * @return The location that is created. The id should be generated when the location is created.
     */
    @Override
    public ParkLocation create(String country, String continent){
        ParkLocation parkLocation = new ParkLocation(country, continent);

        return this.parkLocationRepository.save(parkLocation);
    }
}
