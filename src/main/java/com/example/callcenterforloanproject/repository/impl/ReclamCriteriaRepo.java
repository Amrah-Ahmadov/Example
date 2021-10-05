package com.example.callcenterforloanproject.repository.impl;

import com.example.callcenterforloanproject.exception.ReclameNotFoundException;
import com.example.callcenterforloanproject.model.Credit;
import com.example.callcenterforloanproject.model.Reclame;
import com.example.callcenterforloanproject.repository.IReclamCriteriaRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
@Primary
public class ReclamCriteriaRepo implements IReclamCriteriaRepo {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Reclame saveReclame(Reclame reclame){
        entityManager.persist(reclame);
        return reclame;
    }
    @Override
    @Transactional
    public Reclame deleteReclame(Reclame reclame){
        if (entityManager.contains(reclame)) {
            entityManager.remove(reclame);
        } else {
            entityManager.remove(entityManager.merge(reclame));
        }
        return reclame;
    }
    @Override
    @Transactional
    public Reclame updateReclame(Reclame reclame){
        entityManager.merge(reclame);
        return reclame;
    }
    @Override
    public Reclame getReclameById(Long id){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Reclame> cq = criteriaBuilder.createQuery(Reclame.class);
            Root<Reclame> root = cq.from(Reclame.class);
            Predicate reclameIdPredicate = criteriaBuilder.equal(root.get("id"), id);
            cq.where(reclameIdPredicate);
            TypedQuery<Reclame> query = entityManager.createQuery(cq);
            return query.getSingleResult();
        }catch (NoResultException e){
            throw new ReclameNotFoundException();
        }
    }
    @Override
    public Reclame getReclameByName(String name){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Reclame> cq = criteriaBuilder.createQuery(Reclame.class);
            Root<Reclame> root = cq.from(Reclame.class);
            Predicate reclameNamePredicate = criteriaBuilder.equal(root.get("name"), name);
            cq.where(reclameNamePredicate);
            TypedQuery<Reclame> query = entityManager.createQuery(cq);
            return query.getSingleResult();
        }catch (NoResultException e){
            throw new RuntimeException();
        }
    }
}
