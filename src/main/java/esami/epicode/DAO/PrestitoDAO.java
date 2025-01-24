package esami.epicode.DAO;

import esami.epicode.Classi.Prestito;

import javax.persistence.EntityManager;

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
}
