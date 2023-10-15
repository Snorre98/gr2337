module albumreview.core {
  requires transitive com.fasterxml.jackson.databind;

  exports domainlogic;
  exports statepersistence;
}
