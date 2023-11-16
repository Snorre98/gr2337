module albumreview.core {
  requires transitive com.fasterxml.jackson.databind;

  exports domainlogic;
  exports statepersistence;

  opens domainlogic to com.fasterxml.jackson.databind;
  exports statepersistence.serializer;
}
