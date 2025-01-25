package esami.epicode.DAO;

import esami.epicode.Classi.Pubblicazione;
import esami.epicode.Exceptions.PubblicazioneNonDisponibileException;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

public class PubblicazioneDAO {
    private EntityManager em;

    public PubblicazioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Pubblicazione e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Pubblicazione getByID(long id) {
        return em.find(Pubblicazione.class, id);
    }

    public void delete(Pubblicazione e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }

    public Pubblicazione findByIsbnQuery(String isbn) {
        try {
            Query q = em.createNamedQuery("findByIsbnQuery", Pubblicazione.class);
            q.setParameter("isbn", isbn);
            Pubblicazione pubblicazione = (Pubblicazione) q.getSingleResult();
            System.out.println("Pubblicazione trovata: " + pubblicazione);
            return pubblicazione;
        } catch (NoResultException e) {
            System.out.println("Nessuna pubblicazione trovata con ISBN: " + isbn);
            return null;
        } catch (NonUniqueResultException e) {
            System.out.println("Più di una pubblicazione trovata con ISBN: " + isbn);
            return null;
        }
    }

    public List<Pubblicazione> findByAnno(int anno) {

        Query q = em.createQuery("SELECT p FROM Pubblicazione p WHERE p.anno = :anno"); //Dynamic query//
        q.setParameter("anno", anno);
        List<Pubblicazione> risultati = q.getResultList();
        if (risultati.isEmpty()) {
            throw new PubblicazioneNonDisponibileException(
                    "Ci dispiace! Non abbiamo pubblicazioni in catalogo per l'anno " + anno
            );
        }
        return risultati;
    }
}
