package com.xorsecurity.flare.repository;

import com.xorsecurity.flare.domain.Server;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Server entity.
 */
@SuppressWarnings("unused")
public interface ServerRepository extends MongoRepository<Server,String> {

}
