package pl.camp.it.rent.database.imp.memory;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.camp.it.rent.database.ITaleDAO;
import pl.camp.it.rent.model.Tale;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TaleDatabase implements ITaleDAO {
    private final List<Tale> tales=new ArrayList<>();

    public TaleDatabase() {
        this.tales.add(
                new Tale(1,"Raya i ostatni smok",
                         "Antonina Żbikowska, Katarzyna Dąbrowska, Monika Szomko, Pola Pietrzak, Pola Piłat, Teresa Zdanowska",
                         "księżniczka, odwaga, podróże, poszukiwania, potwory, smoki, Wojna",10,50));
        this.tales.add(
                new Tale(2,"Vaiana: Skarb Oceanu",
                         "Igor Kwiatkowski, Maciej Maleńczuk, Piotr Grabowski, Weronika Bochat-Piotrowska",
                         "ocean, odwaga, przygoda, wyspa",10,20));
        this.tales.add(
                new Tale(3,"Coco",
                         "Agata Kulesza, Bartosz Opania, Ewa Szykulska, Maciej Stuhr, Michał Rosiński, Tomasz Błasiak",
                         "czary, duchy, magia, muzyka, zmarli",10,56));
        this.tales.add(
                new Tale(4,"Mulan",
                         "Jerzy Bończak, Jerzy Stuhr, Jolanta Fraszyńska, Maciej Molęda, Marek Bocianiak, Robert Rozmus, Tomasz Marzecki",
                         "przebranie, wojownicy",10,26));
        this.tales.add(
                new Tale(5,"Kraina lodu",
                         "Alan Tudyk, Anna Cieślak, Bożena Furczyk, Czesław Mozil, Elżbieta Gaertner, Grzegorz Kwiecień, Idina Menzel, Jacek Król, Janusz R. Nowicki, Jonathan Groff, Josh Gad, Kristen Bell, Krzysztof Dracz, Lidia Sadowa, Milogost Reczek, Paweł Ciołkosz, Santino Fontana, Stefan Knothe",
                         "magia, przygoda, przyjaźń, zima",10,90));

    }
    @Override
    public List<Tale> getTales() {
        return tales;
    }
    @Override
    public Optional<Tale> getBookByTitle(String title){
        for(Tale tale1:this.tales){
            if(tale1.getTitle().equals(title)){
                return Optional.of(tale1);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Tale> getTaleById(int taleId) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("TODO");
    }

    @Override
    public void updateTale(Tale tale) throws ExecutionControl.NotImplementedException {

    }


}


