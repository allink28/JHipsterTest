package com.xorsecurity.flare.repository;

import com.xorsecurity.flare.domain.Server;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the Server entity.
 */
@SuppressWarnings("unused")
public interface ServerRepository extends MongoRepository<Server,String> {

//TODO mongo query    @Query("select server from Server server where server.user.login = ?#{principal.username}")
    List<Server> findByUserIsCurrentUser();

}
