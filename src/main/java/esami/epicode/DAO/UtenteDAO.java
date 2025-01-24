package esami.epicode.DAO;


import esami.epicode.Classi.Utente;

import javax.persistence.EntityManager;

public class UtenteDAO {
    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Utente getByID(long id) {
        return em.find(Utente.class, id);
    }

    public void delete(Utente e) {
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }
}
