package esami.epicode.DAO;

import esami.epicode.Classi.Pubblicazione;


import javax.persistence.EntityManager;

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
}
