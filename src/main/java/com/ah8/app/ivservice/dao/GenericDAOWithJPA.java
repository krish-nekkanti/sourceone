package com.ah8.app.ivservice.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;


public abstract class GenericDAOWithJPA<T, ID extends Serializable> {

    private Class<T> persistentClass;

    //This you might want to get injected by the container
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public GenericDAOWithJPA() {
            this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
            return entityManager.createQuery("Select t from " + persistentClass.getSimpleName() + " t").getResultList();
    }
}