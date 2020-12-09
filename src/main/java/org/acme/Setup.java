package org.acme;

import io.quarkus.runtime.Startup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
@Startup
@Transactional
public class Setup {

    @Inject
    EntityManager entityManager;

    @PostConstruct
    void setup() {
        Team team = new Team();
        team.setName("Football Players");
        entityManager.persist(team);

        Player bob = new Player();
        bob.setName("Bob");
        bob.setTeam(team);

        entityManager.persist(bob);
        Player barry = new Player();
        barry.setName("Barry");
        barry.setTeam(team);

        entityManager.persist(barry);
        Player bill = new Player();
        bill.setName("Bill");
        bill.setTeam(team);
        entityManager.persist(bill);
        team.setBestPlayer(bob);
    }
}
