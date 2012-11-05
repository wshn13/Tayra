package com.ee.beaver;

import java.util.Iterator;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Oplog implements MongoCollection {

  private final DBCollection oplog;

  public Oplog(final DB local) {
    if (!"local".equals(local.getName())) {
      throw new NotALocalDB("Not a local DB");
    }
    oplog = local.getCollection("oplog.rs");
  }

  @Override
  public final Iterator<DBObject> find() {
    return new OplogIterator(oplog);
  }

  private static class OplogIterator implements Iterator<DBObject> {

    private final DBCursor cursor;

    public OplogIterator(final DBCollection oplog) {
      cursor = oplog.find();
    }

    @Override
    public boolean hasNext() {
      return cursor.hasNext();
    }

    @Override
    public DBObject next() {
      return cursor.next();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException(
        "remove document on oplog is not supported"
      );
    }
  }
}
