package com.xorsecurity.flare.service;

import com.xorsecurity.flare.domain.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xor.bcmcgroup.core.ServiceExecutor;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumMap;
import java.util.stream.Collectors;

/**
 * https://jhipster.github.io/creating-a-service/
 * Add @Secured to class and use the provided AuthoritiesConstants class to restrict access to specific user authorities
 * Add @Timed annotations on the methods you want to monitor
 */
@Service
public class DiscoveryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoveryService.class);


    public static String sendDiscoveryRequest(Server server) {
        if (server == null) {
//            throw new NotFou
            return "Error: Server object null.";
        }
        boolean useAuth = false;
        if (server.getUsername() != null && !server.getUsername().isEmpty()) {
            useAuth = true;
        }
        return ServiceExecutor.configureServer(server.getName(), server.getUri(), useAuth, server.getUsername(), server.getPassword()).toJSONString();
    }


    /**
     * Configures a server by sending a discovery request to the given uri
     *
     * @return true if method does not error out. Presumably that means configuration was successful.
     */
//    public static FlareResponse configureServer(String label, String uri, Boolean auth, String user, String pass) {
//        LOGGER.trace("Configuring Server...");
//        if (label == null) {
//            return FlareResponseFactory.badRequest("Server Label cannot be null");
//        } else if (uri == null) {
//            return FlareResponseFactory.badRequest("Uri cannot be null");
//        }
//        URI serverUri;
//        try {
//            serverUri = new URI(uri);
//        } catch (URISyntaxException e) {
//            String errorMessage = String.format("Unrecognized URI syntax: %s", e.getMessage());
//            LOGGER.error(errorMessage);
//            LOGGER.error(DEV, "Bad URI", e);
//            return FlareResponseFactory.badRequest(errorMessage);
//        }
//        boolean ssl;
//        int port;
//        try {
//            ssl = serverUri.getScheme().equals("https");
//            if (!ssl && !serverUri.getScheme().equals("http")) {
//                String errorMessage = String.format("Invalid URI %s ... URI must start with http or https.", serverUri);
//                LOGGER.error(errorMessage);
//                LOGGER.error(DEV, "Invalid URI", new URISyntaxException(uri, "URI must start with http or https"));
//                return FlareResponseFactory.badRequest(errorMessage);
//            }
//            port = (serverUri.getPort() > 0 && serverUri.getPort() < 65536) ? serverUri.getPort() : 0;
//            if (port > 65536) {
//                String errorMessage = String.format("Invalid port specified: %d is outside the allowable range (0-65536)", serverUri.getPort());
//                LOGGER.error(errorMessage);
//                LOGGER.error(DEV, "", new IllegalArgumentException("Port " + serverUri.getPort() + " is outside the allowable range (0-65536)"));
//                return FlareResponseFactory.badRequest(errorMessage);
//            } else if (port == 0) {
//                if (ssl) {
//                    LOGGER.info("No port specified. Using default HTTPS port: 443");
//                    port = 443;
//                } else {
//                    LOGGER.info("No port specified. Using default HTTP port: 80");
//                    port = 80;
//                }
//            }
//        } catch (NullPointerException e) {
//            String errorMessage = String.format("Couldn't parse the URI: %s... Ensure the URI is valid and try again.", serverUri);
//            LOGGER.error(errorMessage);
//            LOGGER.error(DEV, "Null Pointer Exception", e);
//            return FlareResponseFactory.internalServerError(errorMessage);
//        }
//        LOGGER.info("Configuring server with the following information:");
//        LOGGER.info("Label: {}", label);
//        LOGGER.info("URI: {}", uri);
//        LOGGER.info("Port: {}", port);
//        LOGGER.info("SSL: {}", ssl);
//
//        if (auth) {
//            LOGGER.info("\tUser: {}", user);
//        }
//        LOGGER.info("");
//
//        ServerData server = new ServerData(
//            label,
//            uri,
//            port,
//            ssl,
//            auth,
//            auth ? user : null,
//            auth ? PasswordUtil.getEncryptedPassword(pass, properties.getEncKy()) : null,
//            new EnumMap<>(ServiceTypeEnum.class));
//
//
//        return discovery11Request(server);
//    }

//    public static FlareResponse configureServer(JsonObject contract) {
//        String label = contract.has("server") ? contract.get("server").getAsString() : null;
//        String uri = contract.has("uri") ? contract.get("uri").getAsString() : null;
//        Boolean auth = contract.has("auth") ? contract.get("auth").getAsBoolean() : null;
//        String user = contract.has("user") ? contract.get("user").getAsString() : null;
//        String pass = contract.has("pass") ? contract.get("pass").getAsString() : null;
//        return configureServer(label, uri, auth, user, pass);
//    }

//    private static FlareResponse discovery11Request(ServerData server) {
//        LOGGER.trace("Sending Discovery Request...");
//        TaxiiMessageBroker messageBroker;
//        DiscoveryResponse discoveryResponse;
//        try {
//            messageBroker = new TaxiiMessageBroker(server);
//        } catch (MalformedURLException | HttpsClientBuildException e) {
//            String errorMessage = String.format("Error when attempting to establish a message broker. Error: %s - %s", e.getClass().getName(), e.getMessage());
//            LOGGER.error(errorMessage);
//            LOGGER.error(DEV, "Stack Trace", e);
//            return FlareResponseFactory.internalServerError(errorMessage);
//        }
//        try {
//            LOGGER.info(String.format("Requesting services information from %s using a TAXII 1.1 Discovery Request...", server.getAddress()));
//            // Build and post the message
//            FlareBinder responseBinder = messageBroker.postDiscovery(DiscoveryRequestBuilder.create11());
//            MessageType responseObject = responseBinder.getJAXBNode();
//            LOGGER.trace("Received response to Discovery Request of: {}", responseObject);
//            LOGGER.trace("Checking if FlareResponse is of DiscoveryResponse...");
//            if (responseObject != null && responseObject instanceof DiscoveryResponse) {
//                discoveryResponse = (DiscoveryResponse) responseObject;
//            } else {
//                String errorMessage;
//                LOGGER.error("FlareResponse was not a DiscoveryResponse.");
//                if (responseObject instanceof StatusMessage) {
//                    errorMessage = String.format("Received Status message in response to Discovery Request: %s - %s",
//                        ((StatusMessage) responseObject).getStatusType(),
//                        ((StatusMessage) responseObject).getMessage());
//                } else {
//                    if (responseObject != null) {
//                        errorMessage = String.format("Received unexpected message in response to Discovery Request: %s", responseObject.getClass().getCanonicalName());
//                    } else {
//                        errorMessage = "Received null in response to Discovery Request";
//                    }
//                    LOGGER.error(errorMessage);
//                    LOGGER.error(DEV, "See Hub/Gateway logs to find out why the response was not a Discovery Response.");
//                }
//                return FlareResponseFactory.badGateway(errorMessage);
//            }
//        } catch (URISyntaxException e) {
//            String errorMessage = String.format("Invalid URI syntax: %s", e.getMessage());
//            LOGGER.error(errorMessage);
//            LOGGER.error(DEV, "Stack Trace", e);
//            return FlareResponseFactory.badRequest(errorMessage);
//        } catch (InterruptedException e) {
//            LOGGER.error("Interrupted during post Discovery : {}", e.getMessage());
//            return FlareResponseFactory.internalServerError("Interrupted during post Discovery : " + e.getMessage());
//        } catch (IOException e) {
//            //Read timed out goes here. For example, if FLAREhub is not up.
//            LOGGER.error("Error occurred during post discovery request: {}", e.getMessage());
//            return FlareResponseFactory.badGateway("Error occurred during post discovery request: " + e.getMessage());
//        } catch (JAXBException e) {
//            LOGGER.error("JAXBException: {}", e.getMessage());
//            return FlareResponseFactory.internalServerError("JAXBException: " + e.getMessage());
//        }
//
//        FlareResponse collectionResponse = collectionRequest11(server, messageBroker, discoveryResponse);
//        if (collectionResponse.success()) {
//            return FlareResponseFactory.created("Both DiscoveryRequest and CollectionRequest were successful. The results have been stored.");
//        } else {
//            String warnMessage = "The DiscoveryRequest was successful and the server has been added. However, the CollectionRequest was not fulfilled.\n" +
//                "You can use 'refresh' later to retry the CollectionRequest for this server without re-running configure.";
//            LOGGER.warn(warnMessage);
//            return FlareResponseFactory.multiStatus(warnMessage).withSubresponses(
//                FlareResponseFactory.ok("DiscoveryRequest Succeeded"),
//                collectionResponse);
//        }
//    }

//    private static FlareResponse collectionRequest11(ServerData server, TaxiiMessageBroker messageBroker, DiscoveryResponse discoveryResponse) {
//        LOGGER.trace("Sending Collection Request...");
//        try {
//            // If we could get a collection information service address, send a collection info request
//            LOGGER.info(String.format("Received Discovery FlareResponse from the %s server, now requesting collection information...", server.getName()));
//            if ((discoveryResponse.getServiceInstances().stream().map(ServiceInstanceType::getServiceType)
//                .collect(Collectors.toList())).contains(ServiceTypeEnum.COLLECTION_MANAGEMENT)) {
//                FlareBinder responseBinder = messageBroker.postCollectionInformationRequest(CollectionInformationRequestBuilder.create11());
//                MessageType responseObject = responseBinder.getJAXBNode();
//
//                LOGGER.trace("Received the following response to our Collection Information Request: {}", responseObject);
//
//                if (!(responseObject instanceof CollectionInformationResponse)) {
//                    // Check if response is status message, and log
//                    String errorMessage;
//                    LOGGER.error("FlareResponse was not a Collection Information FlareResponse.");
//                    if (responseObject instanceof StatusMessage) {
//                        errorMessage = String.format("Received Status message in response to Collection Information Request: %s - %s",
//                            ((StatusMessage) responseObject).getStatusType(),
//                            ((StatusMessage) responseObject).getMessage());
//                    } else {
//                        errorMessage = String.format("Received unexpected message in response to Collection Information Request : %s", responseObject.getClass().getCanonicalName());
//                    }
//                    LOGGER.error(errorMessage);
//                    LOGGER.error(DEV, "See Hub/Gateway logs to find out why the response was not a Discovery Response");
//                    return FlareResponseFactory.badGateway(errorMessage);
//                } else {
//                    LOGGER.info("Successfully configured server with label " + server.getName() + ".");
//                    String collectionListing = listCollectionsForServer(server);
//                    return FlareResponseFactory.created(collectionListing);
//                }
//            } else {
//                String warnMessage = "Collection Management ServiceType not found in Discovery FlareResponse. Could not populate collection information";
//                LOGGER.warn(warnMessage);
//                return FlareResponseFactory.badRequest(warnMessage);
//            }
//        } catch (URISyntaxException e) {
//            String errorMessage = String.format("Invalid URI syntax: %s", e.getMessage());
//            LOGGER.error(errorMessage);
//            LOGGER.error(DEV, "Stack Trace", e);
//            return FlareResponseFactory.badRequest(errorMessage);
//        } catch (InterruptedException e) {
//            LOGGER.error("Process interrupted: {}", e.getMessage());
//            return FlareResponseFactory.internalServerError("Process interrupted: " + e.getMessage());
//        } catch (IOException e) {
//            //Read timed out goes here. For example, if FLAREhub went down.
//            LOGGER.error("Error occurred during post collection info request: {}", e.getMessage());
//            return FlareResponseFactory.badGateway("Error occurred during post collection info request: " + e.getMessage());
//        } catch (JAXBException e) {
//            LOGGER.error("JAXBException: {}", e.getMessage());
//            return FlareResponseFactory.internalServerError("JAXBException: " + e.getMessage());
//        }
//    }


}
