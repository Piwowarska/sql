package pl.camp.it.rent.database;

import pl.camp.it.rent.model.view.Address;

import java.util.Optional;

public interface IAddressDAO {
    void addAddress(Address address);
    Optional<Address>getAddressById(int id);
}
