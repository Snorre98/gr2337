module REST.API {
  requires jakarta.ws.rs;

  requires jersey.common;
  requires jersey.server;
  requires jersey.media.json.jackson;

  requires org.glassfish.hk2.api;

  requires albumreview.core;

  opens albumreview.restapi to jersey.server;

}