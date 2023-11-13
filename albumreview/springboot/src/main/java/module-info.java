module albumreview.springboot {
  requires albumreview.core;
  requires com.fasterxml.jackson.databind;

  requires spring.core;
  requires spring.web;
  requires spring.beans;
  requires spring.boot;
  requires spring.context;
  requires spring.boot.autoconfigure;

  opens restserver to albumreview.core, spring.beans, spring.context, spring.web, spring.core;

}