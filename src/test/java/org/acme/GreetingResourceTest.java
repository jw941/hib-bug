package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Inject
    EntityManager em;

    @Test
    public void testHibernateBug() {
        Player bill = (Player) em.createQuery("select p from Player p where p.name='Bill'").getSingleResult();
        List<Player> people = em.createQuery("select p from Player p inner join fetch p.team order by p.id desc").getResultList();
    }

}