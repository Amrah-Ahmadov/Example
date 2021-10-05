package com.example.callcenterforloanproject.repository.impl;

import com.example.callcenterforloanproject.exception.LoanNotFoundException;
import com.example.callcenterforloanproject.model.Credit;
import com.example.callcenterforloanproject.model.Loan;
import com.example.callcenterforloanproject.model.User;
import com.example.callcenterforloanproject.repository.ILoanCriteriaRepo;
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
import java.math.BigDecimal;
import java.util.List;

@Repository
@Primary
public class LoanCriteriaRepo implements ILoanCriteriaRepo {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Loan saveLoan(Loan loan){
        entityManager.persist(loan);
        return loan;
    }
    @Override
    @Transactional
    public Loan deleteLoan(Loan loan){
        if (entityManager.contains(loan)) {
            entityManager.remove(loan);
        } else {
            entityManager.remove(entityManager.merge(loan));
        }
        return loan;
    }
    @Override
    @Transactional
    public Loan updateLoan(Loan loan){
        entityManager.merge(loan); /// deyisiklik ola biler
        return loan;
    }
    @Override
    public Loan getLoanById(Long id){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Loan> cq = criteriaBuilder.createQuery(Loan.class);
            Root<Loan> root = cq.from(Loan.class);
            Predicate loanIdPredicate = criteriaBuilder.equal(root.get("id"), id);
            cq.where(loanIdPredicate);
            TypedQuery<Loan> query = entityManager.createQuery(cq);
            return query.getSingleResult();
        }catch (NoResultException e){
            throw new LoanNotFoundException();
        }
    }
    @Override
    public List<Loan> getLoanByCustomerName(String name){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Loan> cq = criteriaBuilder.createQuery(Loan.class);
            Root<Loan> root = cq.from(Loan.class);
            Predicate customerNamePredicate = criteriaBuilder.equal(root.get("name"), name);
            cq.where(customerNamePredicate);
            TypedQuery<Loan> query = entityManager.createQuery(cq);
            return query.getResultList();
        }catch (NoResultException e){
            throw new LoanNotFoundException();
        }

    }
    @Override
    public List<Loan> getLoanByCustomerSurname(String surname){
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Loan> cq = criteriaBuilder.createQuery(Loan.class);
            Root<Loan> root = cq.from(Loan.class);
            Predicate customerSurnamePredicate = criteriaBuilder.equal(root.get("surname"), surname);
            cq.where(customerSurnamePredicate);
            TypedQuery<Loan> query = entityManager.createQuery(cq);
            return query.getResultList();
        }catch (NoResultException e){
            throw new LoanNotFoundException();
        }
    }
    @Override
    public List<Loan> getLoanByCustomerNameAndSurname(String name, String surname){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Loan> cq = criteriaBuilder.createQuery(Loan.class);
            Root<Loan> root = cq.from(Loan.class);
            Predicate customerNamePredicate = criteriaBuilder.equal(root.get("name"), name);
            Predicate customerSurnamePredicate = criteriaBuilder.equal(root.get("surname"), surname);
            cq.where(customerNamePredicate, customerSurnamePredicate);
            TypedQuery<Loan> query = entityManager.createQuery(cq);
            return query.getResultList();
        }catch (NoResultException e){
            throw new LoanNotFoundException();
        }
    }
    @Override
    public List<Loan> getLoanByCustomerAddress(String address){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Loan> cq = criteriaBuilder.createQuery(Loan.class);
            Root<Loan> root = cq.from(Loan.class);
            Predicate customerAddressPredicate = criteriaBuilder.equal(root.get("address"), address);
            cq.where(customerAddressPredicate);
            TypedQuery<Loan> query = entityManager.createQuery(cq);
            return query.getResultList();
        }catch (NoResultException e){
            throw new LoanNotFoundException();
        }
    }
    @Override
    public List<Loan> getLoanByCustomerAmount(BigDecimal amount){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Loan> cq = criteriaBuilder.createQuery(Loan.class);
            Root<Loan> root = cq.from(Loan.class);
            Predicate customerAmountPredicate = criteriaBuilder.equal(root.get("amount"), amount);
            cq.where(customerAmountPredicate);
            TypedQuery<Loan> query = entityManager.createQuery(cq);
            return query.getResultList();
        }catch (NoResultException e){
            throw new LoanNotFoundException();
        }
    }
    @Override
    public List<Loan> getLoanByCustomerNote(String note){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Loan> cq = criteriaBuilder.createQuery(Loan.class);
            Root<Loan> root = cq.from(Loan.class);
            Predicate customerNotePredicate = criteriaBuilder.equal(root.get("note"), note);
            cq.where(customerNotePredicate);
            TypedQuery<Loan> query = entityManager.createQuery(cq);
            return query.getResultList();
        }catch (NoResultException e){
            throw new LoanNotFoundException();
        }
    }
    @Override
    public List<Loan> getLoanByCreditId(Long id){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Loan> cq = criteriaBuilder.createQuery(Loan.class);
            Root<Loan> root = cq.from(Loan.class);
            Predicate creditPredicate = criteriaBuilder.equal(root.get("creditId"), id);
            cq.where(creditPredicate);
            TypedQuery<Loan> query = entityManager.createQuery(cq);
            return query.getResultList();
        }catch (NoResultException e){
            throw new LoanNotFoundException();
        }
    }
    @Override
    public List<Loan> getLoanByReclameId(Long id){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Loan> cq = criteriaBuilder.createQuery(Loan.class);
            Root<Loan> root = cq.from(Loan.class);
            Predicate reclamePredicate = criteriaBuilder.equal(root.get("reclameId"), id);
            cq.where(reclamePredicate);
            TypedQuery<Loan> query = entityManager.createQuery(cq);
            return query.getResultList();
        }catch (NoResultException e){
            throw new LoanNotFoundException();
        }
    }
    @Override
    public List<Loan> getLoanByUserId(Long id){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Loan> cq = criteriaBuilder.createQuery(Loan.class);
            Root<Loan> root = cq.from(Loan.class);
            Predicate userPredicate = criteriaBuilder.equal(root.get("userId"), id);
            cq.where(userPredicate);
            TypedQuery<Loan> query = entityManager.createQuery(cq);
            return query.getResultList();
        }catch (NoResultException e){
            throw new LoanNotFoundException();
        }
    }
    public List<Loan> getAllLoans(){
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Loan> cq = criteriaBuilder.createQuery(Loan.class);
            Root<Loan> root = cq.from(Loan.class);
            TypedQuery<Loan> query = entityManager.createQuery(cq);
            return query.getResultList();
        }catch (NoResultException e){
            throw new LoanNotFoundException();
        }
    }

}
