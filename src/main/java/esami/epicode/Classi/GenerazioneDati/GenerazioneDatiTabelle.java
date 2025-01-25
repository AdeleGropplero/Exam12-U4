package esami.epicode.Classi.GenerazioneDati;

import com.github.javafaker.Faker;
import esami.epicode.Classi.Enum.Genere;
import esami.epicode.Classi.Enum.Periodicità;
import esami.epicode.Classi.Libro;
import esami.epicode.Classi.Rivista;
import esami.epicode.Classi.Utente;
import esami.epicode.DAO.PubblicazioneDAO;
import esami.epicode.DAO.UtenteDAO;

import java.time.ZoneId;
import java.util.Random;

public class GenerazioneDatiTabelle {

    //Qui ho deciso di inserire la logica per generare automaticamente pubblicazioni e utenti
    //così da avere un main pulito e un posto dove cercare e comprendere eventuali errori.

    public static void generateBooks(PubblicazioneDAO pubblicazioneDAO, Faker faker) {
        for (int i = 0; i < 10; i++) { //Con faker generiamo n libri da aggiungere in archivio

            Libro libro = new Libro(
                    faker.book().author(),
                    faker.options().option(Genere.values()),
                    faker.code().isbn10(),
                    faker.book().title(),
                    faker.number().numberBetween(1900, 2025),
                    faker.number().numberBetween(120, 900)
            );
            pubblicazioneDAO.save(libro);
        }
    }

    public static void generateRiviste(PubblicazioneDAO pubblicazioneDAO, Faker faker) {
        for (int i = 0; i < 6; i++) {

            Rivista rivista = new Rivista(
                    faker.code().isbn10(),
                    faker.book().title(),
                    faker.number().numberBetween(1920, 2025),
                    faker.number().numberBetween(30, 100),
                    faker.options().option(Periodicità.values())
            );
            pubblicazioneDAO.save(rivista);
        }
    }

    public static void generateUtenti(UtenteDAO utenteDAO, Faker faker) {
        for (int i = 0; i < 3; i++) {

            Utente utente = new Utente(
                   faker.name().firstName(),
                    faker.name().lastName(),
                    faker.date().birthday().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                    //Trovata documentazione su Stack Overflow a riguardo, per trasformare un obj Date in LocalDate:
                    // https://stackoverflow.com/questions/21242110/convert-java-util-date-to-java-time-localdate
            );
            utenteDAO.save(utente);
        }
    }


}
