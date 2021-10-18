package com.example.callcenterforloanproject.repository.impl;

import com.example.callcenterforloanproject.exception.CreditNotFoundException;
import com.example.callcenterforloanproject.exception.LoanNotFoundException;
import com.example.callcenterforloanproject.model.Credit;
import com.example.callcenterforloanproject.model.Loan;
import com.example.callcenterforloanproject.model.User;
import com.example.callcenterforloanproject.repository.ICreditCriteriaRepo;
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
public class CreditCriteriaRepo implements ICreditCriteriaRepo {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Credit saveCredit(Credit credit){
        entityManager.persist(credit);
        return credit;
    }
    @Override
    @Transactional
    public Credit deleteCredit(Credit credit){
        if (entityManager.contains(credit)) {
            entityManager.remove(credit);
        } else {
            entityManager.remove(entityManager.merge(credit));
        }
        return credit;
    }
    @Override
    @Transactional
    public Credit updateCredit(Credit credit){
        entityManager.merge(credit);
        return credit;
    }
    @Override
    public Credit getCreditById(Long id){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Credit> cq = criteriaBuilder.createQuery(Credit.class);
            Root<Credit> root = cq.from(Credit.class);
            Predicate creditIdPredicate = criteriaBuilder.equal(root.get("id"), id);
            cq.where(creditIdPredicate);
            TypedQuery<Credit> query = entityManager.createQuery(cq);
            return query.getSingleResult();
        }catch (NoResultException e){
            throw new CreditNotFoundException();
        }
    }
    @Override
    public Credit getCreditByName(String name){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Credit> cq = criteriaBuilder.createQuery(Credit.class);
            Root<Credit> root = cq.from(Credit.class);
            Predicate creditNamePredicate = criteriaBuilder.equal(root.get("name"), name);
            cq.where(creditNamePredicate);
            TypedQuery<Credit> query = entityManager.createQuery(cq);
            return query.getSingleResult();
        }catch (NoResultException e){
            throw new CreditNotFoundException();
        }
    }
    @Override
    public List<Credit> getAllCredits(){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Credit> cq = criteriaBuilder.createQuery(Credit.class);
            Root<Credit> root = cq.from(Credit.class);
            TypedQuery<Credit> query = entityManager.createQuery(cq);
            return query.getResultList();
        }catch (NoResultException e){
            throw new CreditNotFoundException();
        }
    }
}
