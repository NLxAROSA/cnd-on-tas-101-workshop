package io.pivotal.workshops.cnd.configserver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FortuneRepository extends JpaRepository<Fortune, Long> {

    // This query is not very efficient, but ok for such a small DB
    @Query(value="select * from fortune order by rand() limit 1", nativeQuery = true)
    Fortune findRandomFortune();

}
