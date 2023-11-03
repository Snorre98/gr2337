module albumreview.springboot {
  requires albumreview.core;
  requires com.fasterxml.jackson.databind;

  requires spring.web;
  requires spring.beans;
  requires spring.boot;
  requires spring.context;
  requires spring.boot.autoconfigure;

  opens RESTserver to spring.beans, spring.context, spring.web;

}