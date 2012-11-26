package com.ee.beaver.domain.operation;

import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class SchemaOperationsFactory {

  private final Mongo mongo;

  public SchemaOperationsFactory(final Mongo mongo) {
    this.mongo = mongo;
  }

  public final SchemaOperation make(final DBObject spec) {
    if (spec.containsField("create")) {
      return new CreateCollection();
    }

    if (spec.containsField("drop")) {
      return new DropCollection();
    }

    if (spec.containsField("dropDatabase")) {
      return new DropDatabase(mongo);
    }

    throw new IllegalArgumentException();
  }

}