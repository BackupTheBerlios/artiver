/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controller;

import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.exceptions.NonexistentEntityException;
import eu.gymnaila.chunks.artiver.exceptions.PreexistingEntityException;
import eu.gymnaila.chunks.artiver.entity.MasterData;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author User
 */
public class MasterDataController implements Serializable {

    public MasterDataController() {

    }

    public EntityManager getEntityManager() {
        return AppConfig.createEntityManager();
    }

    public void create(MasterData masterData) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            if(masterData.getIdMasterData() != 1)
            {
                throw new PreexistingEntityException("MasterData " + masterData + " allows just one record.");
            }
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(masterData);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMasterData(masterData.getIdMasterData()) != null) {
                throw new PreexistingEntityException("MasterData " + masterData + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MasterData masterData) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            if(masterData.getIdMasterData() != 1)
            {
                throw new PreexistingEntityException("MasterData " + masterData + " allows just one record.");
            }
            em = getEntityManager();
            em.getTransaction().begin();
            masterData = em.merge(masterData);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = masterData.getIdMasterData();
                if (findMasterData(id) == null || id != 1) {
                    throw new NonexistentEntityException("The masterData with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

   
    public MasterData getMasterData() {
        EntityManager em = getEntityManager();
        try {
            return em.find(MasterData.class, 1);
        } finally {
            em.close();
        }
    }
    

    public MasterData findMasterData(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MasterData.class, id);
        } finally {
            em.close();
        }
    }

    public int getMasterDataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MasterData> rt = cq.from(MasterData.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
