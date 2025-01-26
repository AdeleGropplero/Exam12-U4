package esami.epicode.DAO;

import esami.epicode.Classi.Prestito;
import esami.epicode.Classi.Pubblicazione;
import esami.epicode.Exceptions.NessunPrestitoScadutoException;
import esami.epicode.Exceptions.PrestitPerTesseraException;
import esami.epicode.Exceptions.PubblicazioneNonDisponibileException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class PrestitoDAO {
    private EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Prestito getByID(long id) {
        return em.find(Prestito.class, id);
    }

    public void delete(Prestito e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public void update(Prestito prestito) {
        em.getTransaction().begin();
        em.merge(prestito);
        em.getTransaction().commit();
    }

    public List<Prestito> findByNTessera(long nTessera) {
        Query q = em.createQuery("SELECT p FROM Prestito p WHERE p.utente.nTessera = :nTessera"); //Dynamic query//
        q.setParameter("nTessera", nTessera);
        List<Prestito> risultati = q.getResultList();
        if (risultati.isEmpty()) {
            throw new PrestitPerTesseraException(
                    "Non abbiamo prestiti attivi per la Tessera " + nTessera
            );
        }
        return risultati;
    }

    public List<Prestito> findAllPrestitiScaduti() {
        Query q = em.createQuery("SELECT p FROM Prestito p WHERE p.restituzionePrevista < :oggi AND p.restituzioneEffettiva IS NULL"); //Dynamic query//
        q.setParameter("oggi", LocalDate.now());
        List<Prestito> risultati = q.getResultList();
        if (risultati.isEmpty()) {
            throw new NessunPrestitoScadutoException(
                    "Ottimo! tutti i libri sono satati restituiti!"
            );
        }
        return risultati;
    }
}
