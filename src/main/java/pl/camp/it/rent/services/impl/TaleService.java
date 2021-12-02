package pl.camp.it.rent.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.rent.database.ITaleDAO;
import pl.camp.it.rent.database.imp.memory.TaleDatabase;
import pl.camp.it.rent.model.Tale;
import pl.camp.it.rent.services.ITaleService;

import java.util.List;

@Service
public class TaleService implements ITaleService {

    @Autowired
    ITaleDAO taleDatabase;

    @Override
    public List<Tale> getAllTales(){
        return this.taleDatabase.getTales();
    }
}
