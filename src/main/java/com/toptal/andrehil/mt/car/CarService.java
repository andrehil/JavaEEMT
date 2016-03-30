package com.toptal.andrehil.mt.car;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Car services.
 *
 * @author André Hildinger
 *
 */
@Path("/cars")
public class CarService {

    @PersistenceContext(unitName = "pu")
    protected EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getUserCars() {
        final CriteriaBuilder builder = em.getCriteriaBuilder();
        final CriteriaQuery<Car> query = builder.createQuery(Car.class);
        final Root<Car> carRoot = query.from(Car.class);
        query.select(carRoot);

        return em.createQuery(query).getResultList();
    }

}
