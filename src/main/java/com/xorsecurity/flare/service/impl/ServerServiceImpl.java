package com.xorsecurity.flare.service.impl;

import com.xorsecurity.flare.service.ServerService;
import com.xorsecurity.flare.domain.Server;
import com.xorsecurity.flare.repository.ServerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Server.
 */
@Service
public class ServerServiceImpl implements ServerService{

    private final Logger log = LoggerFactory.getLogger(ServerServiceImpl.class);
    
    @Inject
    private ServerRepository serverRepository;

    /**
     * Save a server.
     *
     * @param server the entity to save
     * @return the persisted entity
     */
    public Server save(Server server) {
        log.debug("Request to save Server : {}", server);
        Server result = serverRepository.save(server);
        return result;
    }

    /**
     *  Get all the servers.
     *  
     *  @return the list of entities
     */
    public List<Server> findAll() {
        log.debug("Request to get all Servers");
        List<Server> result = serverRepository.findAll();

        return result;
    }

    /**
     *  Get one server by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public Server findOne(String id) {
        log.debug("Request to get Server : {}", id);
        Server server = serverRepository.findOne(id);
        return server;
    }

    /**
     *  Delete the  server by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Server : {}", id);
        serverRepository.delete(id);
    }
}
