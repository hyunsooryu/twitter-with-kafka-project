package me.twitter.consumer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterRepository extends JpaRepository<Twitter, Long> {

}
