package pl.camp.it.rent.database;

import jdk.jshell.spi.ExecutionControl;
import pl.camp.it.rent.model.Tale;

import java.util.List;
import java.util.Optional;

public interface ITaleDAO {
    List<Tale> getTales();
    Optional<Tale> getBookByTitle(String title);
    Optional<Tale> getTaleById(int taleId) throws ExecutionControl.NotImplementedException;

    void updateTale(Tale tale) throws ExecutionControl.NotImplementedException;
}
