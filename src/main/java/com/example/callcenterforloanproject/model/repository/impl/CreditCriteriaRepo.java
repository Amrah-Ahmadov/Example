package com.example.callcenterforloanproject.model.repository.impl;

import com.example.callcenterforloanproject.exception.CreditNotFoundException;
import com.example.callcenterforloanproject.model.entity.Credit;
import com.example.callcenterforloanproject.model.repository.ICreditCriteriaRepo;
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
import java.util.List;

@Repository
@Primary
@Transactional
public class CreditCriteriaRepo implements ICreditCriteriaRepo {
    @PersistenceContext
    EntityManager em;

    @Override
    public Credit saveCredit(Credit credit){
        em.persist(credit);
        return credit;
    }
    @Override
    public Credit deleteCredit(Credit credit){
        if (em.contains(credit)) {
            em.remove(credit);
        } else {
            em.remove(em.merge(credit));
        }
        return credit;
    }
    @Override
    @Transactional
    public Credit updateCredit(Credit credit){
        em.merge(credit);
        return credit;
    }
    @Override
    public Credit getCreditById(Long id){
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Credit> cq = cb.createQuery(Credit.class);
            Root<Credit> root = cq.from(Credit.class);
            cq.select(root).where(cb.equal(root.get("id"),id));
            return em.createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            throw new CreditNotFoundException();
        }
    }
    @Override
    public Credit getCreditByName(String name){
        try{
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Credit> cq = criteriaBuilder.createQuery(Credit.class);
            Root<Credit> root = cq.from(Credit.class);
            Predicate creditNamePredicate = criteriaBuilder.equal(root.get("name"), name);
            cq.where(creditNamePredicate);
            TypedQuery<Credit> query = em.createQuery(cq);
            return query.getSingleResult();
        }catch (NoResultException e){
            throw new CreditNotFoundException();
        }
    }
    @Override
    public List<Credit> getAllCredits(){
        try{
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Credit> cq = criteriaBuilder.createQuery(Credit.class);
            Root<Credit> root = cq.from(Credit.class);

            TypedQuery<Credit> query = em.createQuery(cq);
            return query.getResultList();
        }catch (NoResultException e){
            throw new CreditNotFoundException();
        }
    }
}
