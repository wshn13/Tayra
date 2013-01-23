package com.ee.tayra.domain.operation;

import com.mongodb.DB;
import com.mongodb.DBObject;

class DropCollection implements SchemaOperation {

  public final void doExecute(final DB db, final DBObject spec) {
    final String dropCollectionName = (String) spec.get("drop");
    if (!db.collectionExists(dropCollectionName)) {
      throw new DropCollectionFailed("Could Not Drop Collection "
          + dropCollectionName);
    }
    db.getCollection(dropCollectionName).drop();
}

}