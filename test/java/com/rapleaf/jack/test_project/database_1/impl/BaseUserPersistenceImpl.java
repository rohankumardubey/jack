
/**
 * Autogenerated by Jack
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.rapleaf.jack.test_project.database_1.impl;

import java.sql.SQLRecoverableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;

import com.rapleaf.jack.AbstractDatabaseModel;
import com.rapleaf.jack.BaseDatabaseConnection;
import com.rapleaf.jack.queries.where_operators.IWhereOperator;
import com.rapleaf.jack.queries.WhereConstraint;
import com.rapleaf.jack.queries.ModelQuery;
import com.rapleaf.jack.ModelWithId;
import com.rapleaf.jack.test_project.database_1.iface.IUserPersistence;
import com.rapleaf.jack.test_project.database_1.models.User;
import com.rapleaf.jack.test_project.database_1.query.UserQueryBuilder;


import com.rapleaf.jack.test_project.IDatabases;

public class BaseUserPersistenceImpl extends AbstractDatabaseModel<User> implements IUserPersistence {
  private final IDatabases databases;

  public BaseUserPersistenceImpl(BaseDatabaseConnection conn, IDatabases databases) {
    super(conn, "users", Arrays.<String>asList("handle", "created_at_millis", "num_posts", "some_date", "some_datetime", "bio", "some_binary", "some_float", "some_decimal", "some_boolean"));
    this.databases = databases;
  }

  @Override
  public User create(Map<Enum, Object> fieldsMap) throws IOException {
    String handle = (String) fieldsMap.get(User._Fields.handle);
    Long created_at_millis = (Long) fieldsMap.get(User._Fields.created_at_millis);
    int num_posts = (Integer) fieldsMap.get(User._Fields.num_posts);
    Long some_date = (Long) fieldsMap.get(User._Fields.some_date);
    Long some_datetime = (Long) fieldsMap.get(User._Fields.some_datetime);
    String bio = (String) fieldsMap.get(User._Fields.bio);
    byte[] some_binary = (byte[]) fieldsMap.get(User._Fields.some_binary);
    Double some_float = (Double) fieldsMap.get(User._Fields.some_float);
    Double some_decimal = (Double) fieldsMap.get(User._Fields.some_decimal);
    Boolean some_boolean = (Boolean) fieldsMap.get(User._Fields.some_boolean);
    return create(handle, created_at_millis, num_posts, some_date, some_datetime, bio, some_binary, some_float, some_decimal, some_boolean);
  }

  public User create(final String handle, final Long created_at_millis, final int num_posts, final Long some_date, final Long some_datetime, final String bio, final byte[] some_binary, final Double some_float, final Double some_decimal, final Boolean some_boolean) throws IOException {
    long __id = realCreate(new AttrSetter() {
      public void set(PreparedStatement stmt) throws SQLException {
          stmt.setString(1, handle);
        if (created_at_millis == null) {
          stmt.setNull(2, java.sql.Types.INTEGER);
        } else {
          stmt.setLong(2, created_at_millis);
        }
          stmt.setInt(3, num_posts);
        if (some_date == null) {
          stmt.setNull(4, java.sql.Types.DATE);
        } else {
          stmt.setDate(4, new Date(some_date));
        }
        if (some_datetime == null) {
          stmt.setNull(5, java.sql.Types.DATE);
        } else {
          stmt.setTimestamp(5, new Timestamp(some_datetime));
        }
        if (bio == null) {
          stmt.setNull(6, java.sql.Types.CHAR);
        } else {
          stmt.setString(6, bio);
        }
        if (some_binary == null) {
          stmt.setNull(7, java.sql.Types.BINARY);
        } else {
          stmt.setBytes(7, some_binary);
        }
        if (some_float == null) {
          stmt.setNull(8, java.sql.Types.DOUBLE);
        } else {
          stmt.setDouble(8, some_float);
        }
        if (some_decimal == null) {
          stmt.setNull(9, java.sql.Types.DECIMAL);
        } else {
          stmt.setDouble(9, some_decimal);
        }
        if (some_boolean == null) {
          stmt.setNull(10, java.sql.Types.BOOLEAN);
        } else {
          stmt.setBoolean(10, some_boolean);
        }
      }
    }, getInsertStatement(Arrays.<String>asList("handle", "created_at_millis", "num_posts", "some_date", "some_datetime", "bio", "some_binary", "some_float", "some_decimal", "some_boolean")));
    User newInst = new User(__id, handle, created_at_millis, num_posts, some_date, some_datetime, bio, some_binary, some_float, some_decimal, some_boolean, databases);
    newInst.setCreated(true);
    cachedById.put(__id, newInst);
    clearForeignKeyCache();
    return newInst;
  }


  public User create(final String handle, final int num_posts) throws IOException {
    long __id = realCreate(new AttrSetter() {
      public void set(PreparedStatement stmt) throws SQLException {
          stmt.setString(1, handle);
          stmt.setInt(2, num_posts);
      }
    }, getInsertStatement(Arrays.<String>asList("handle", "num_posts")));
    User newInst = new User(__id, handle, null, num_posts, null, null, null, null, null, null, null, databases);
    newInst.setCreated(true);
    cachedById.put(__id, newInst);
    clearForeignKeyCache();
    return newInst;
  }


  public User createDefaultInstance() throws IOException {
    return create("", 0);
  }

  public List<User> find(Map<Enum, Object> fieldsMap) throws IOException {
    return find(null, fieldsMap);
  }

  public List<User> find(Set<Long> ids, Map<Enum, Object> fieldsMap) throws IOException {
    List<User> foundList = new ArrayList<User>();
    
    if (fieldsMap == null || fieldsMap.isEmpty()) {
      return foundList;
    }

    StringBuilder statementString = new StringBuilder();
    statementString.append("SELECT * FROM users WHERE (");
    List<Object> nonNullValues = new ArrayList<Object>();
    List<User._Fields> nonNullValueFields = new ArrayList<User._Fields>();

    Iterator<Map.Entry<Enum, Object>> iter = fieldsMap.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<Enum, Object> entry = iter.next();
      Enum field = entry.getKey();
      Object value = entry.getValue();
      
      String queryValue = value != null ? " = ? " : " IS NULL";
      if (value != null) {
        nonNullValueFields.add((User._Fields) field);
        nonNullValues.add(value);
      }

      statementString.append(field).append(queryValue);
      if (iter.hasNext()) {
        statementString.append(" AND ");
      }
    }
    if (ids != null) statementString.append(" AND ").append(getIdSetCondition(ids));
    statementString.append(")");

    int retryCount = 0;
    PreparedStatement preparedStatement;

    while (true) {
      preparedStatement = getPreparedStatement(statementString.toString());

      for (int i = 0; i < nonNullValues.size(); i++) {
        User._Fields field = nonNullValueFields.get(i);
        try {
          switch (field) {
            case handle:
              preparedStatement.setString(i+1, (String) nonNullValues.get(i));
              break;
            case created_at_millis:
              preparedStatement.setLong(i+1, (Long) nonNullValues.get(i));
              break;
            case num_posts:
              preparedStatement.setInt(i+1, (Integer) nonNullValues.get(i));
              break;
            case some_date:
              preparedStatement.setDate(i+1, new Date((Long) nonNullValues.get(i)));
              break;
            case some_datetime:
              preparedStatement.setTimestamp(i+1, new Timestamp((Long) nonNullValues.get(i)));
              break;
            case bio:
              preparedStatement.setString(i+1, (String) nonNullValues.get(i));
              break;
            case some_binary:
              preparedStatement.setBytes(i+1, (byte[]) nonNullValues.get(i));
              break;
            case some_float:
              preparedStatement.setDouble(i+1, (Double) nonNullValues.get(i));
              break;
            case some_decimal:
              preparedStatement.setDouble(i+1, (Double) nonNullValues.get(i));
              break;
            case some_boolean:
              preparedStatement.setBoolean(i+1, (Boolean) nonNullValues.get(i));
              break;
          }
        } catch (SQLException e) {
          throw new IOException(e);
        }
      }

      try {
        executeQuery(foundList, preparedStatement);
        return foundList;
      } catch (SQLRecoverableException e) {
        if (++retryCount > AbstractDatabaseModel.MAX_CONNECTION_RETRIES) {
          throw new IOException(e);
        }
      } catch (SQLException e) {
        throw new IOException(e);
      }
    }
  }

  @Override
  protected void setStatementParameters(PreparedStatement preparedStatement, ModelQuery query) throws IOException {
    int index = 0;
    for (WhereConstraint constraint : query.getWhereConstraints()) {
      User._Fields field = (User._Fields)constraint.getField();
      for (Object parameter : constraint.getParameters()) {
        if (parameter == null) {
          continue;
        }
        try {
          switch (field) {
            case handle:
              preparedStatement.setString(++index, (String) parameter);
              break;
            case created_at_millis:
              preparedStatement.setLong(++index, (Long) parameter);
              break;
            case num_posts:
              preparedStatement.setInt(++index, (Integer) parameter);
              break;
            case some_date:
              preparedStatement.setDate(++index, new Date((Long) parameter));
              break;
            case some_datetime:
              preparedStatement.setTimestamp(++index, new Timestamp((Long) parameter));
              break;
            case bio:
              preparedStatement.setString(++index, (String) parameter);
              break;
            case some_binary:
              preparedStatement.setBytes(++index, (byte[]) parameter);
              break;
            case some_float:
              preparedStatement.setDouble(++index, (Double) parameter);
              break;
            case some_decimal:
              preparedStatement.setDouble(++index, (Double) parameter);
              break;
            case some_boolean:
              preparedStatement.setBoolean(++index, (Boolean) parameter);
              break;
          }
        } catch (SQLException e) {
          throw new IOException(e);
        }
      }
    }
  }

  @Override
  protected void setAttrs(User model, PreparedStatement stmt) throws SQLException {
    {
      stmt.setString(1, model.getHandle());
    }
    if (model.getCreatedAtMillis() == null) {
      stmt.setNull(2, java.sql.Types.INTEGER);
    } else {
      stmt.setLong(2, model.getCreatedAtMillis());
    }
    {
      stmt.setInt(3, model.getNumPosts());
    }
    if (model.getSomeDate() == null) {
      stmt.setNull(4, java.sql.Types.DATE);
    } else {
      stmt.setDate(4, new Date(model.getSomeDate()));
    }
    if (model.getSomeDatetime() == null) {
      stmt.setNull(5, java.sql.Types.DATE);
    } else {
      stmt.setTimestamp(5, new Timestamp(model.getSomeDatetime()));
    }
    if (model.getBio() == null) {
      stmt.setNull(6, java.sql.Types.CHAR);
    } else {
      stmt.setString(6, model.getBio());
    }
    if (model.getSomeBinary() == null) {
      stmt.setNull(7, java.sql.Types.BINARY);
    } else {
      stmt.setBytes(7, model.getSomeBinary());
    }
    if (model.getSomeFloat() == null) {
      stmt.setNull(8, java.sql.Types.DOUBLE);
    } else {
      stmt.setDouble(8, model.getSomeFloat());
    }
    if (model.getSomeDecimal() == null) {
      stmt.setNull(9, java.sql.Types.DECIMAL);
    } else {
      stmt.setDouble(9, model.getSomeDecimal());
    }
    if (model.isSomeBoolean() == null) {
      stmt.setNull(10, java.sql.Types.BOOLEAN);
    } else {
      stmt.setBoolean(10, model.isSomeBoolean());
    }
    stmt.setLong(11, model.getId());
  }

  @Override
  protected User instanceFromResultSet(ResultSet rs, Set<Enum> selectedFields) throws SQLException {
    boolean allFields = selectedFields == null || selectedFields.isEmpty();
    return new User(rs.getLong("id"),
      allFields || selectedFields.contains(User._Fields.handle) ? rs.getString("handle") : "",
      allFields || selectedFields.contains(User._Fields.created_at_millis) ? getLongOrNull(rs, "created_at_millis") : null,
      allFields || selectedFields.contains(User._Fields.num_posts) ? getIntOrNull(rs, "num_posts") : 0,
      allFields || selectedFields.contains(User._Fields.some_date) ? getDateAsLong(rs, "some_date") : null,
      allFields || selectedFields.contains(User._Fields.some_datetime) ? getDateAsLong(rs, "some_datetime") : null,
      allFields || selectedFields.contains(User._Fields.bio) ? rs.getString("bio") : null,
      allFields || selectedFields.contains(User._Fields.some_binary) ? rs.getBytes("some_binary") : null,
      allFields || selectedFields.contains(User._Fields.some_float) ? getDoubleOrNull(rs, "some_float") : null,
      allFields || selectedFields.contains(User._Fields.some_decimal) ? getDoubleOrNull(rs, "some_decimal") : null,
      allFields || selectedFields.contains(User._Fields.some_boolean) ? getBooleanOrNull(rs, "some_boolean") : null,
      databases
    );
  }

  public List<User> findByHandle(final String value) throws IOException {
    return find(new HashMap<Enum, Object>(){{put(User._Fields.handle, value);}});
  }

  public List<User> findByCreatedAtMillis(final Long value) throws IOException {
    return find(new HashMap<Enum, Object>(){{put(User._Fields.created_at_millis, value);}});
  }

  public List<User> findByNumPosts(final int value) throws IOException {
    return find(new HashMap<Enum, Object>(){{put(User._Fields.num_posts, value);}});
  }

  public List<User> findBySomeDate(final Long value) throws IOException {
    return find(new HashMap<Enum, Object>(){{put(User._Fields.some_date, value);}});
  }

  public List<User> findBySomeDatetime(final Long value) throws IOException {
    return find(new HashMap<Enum, Object>(){{put(User._Fields.some_datetime, value);}});
  }

  public List<User> findByBio(final String value) throws IOException {
    return find(new HashMap<Enum, Object>(){{put(User._Fields.bio, value);}});
  }

  public List<User> findBySomeBinary(final byte[] value) throws IOException {
    return find(new HashMap<Enum, Object>(){{put(User._Fields.some_binary, value);}});
  }

  public List<User> findBySomeFloat(final Double value) throws IOException {
    return find(new HashMap<Enum, Object>(){{put(User._Fields.some_float, value);}});
  }

  public List<User> findBySomeDecimal(final Double value) throws IOException {
    return find(new HashMap<Enum, Object>(){{put(User._Fields.some_decimal, value);}});
  }

  public List<User> findBySomeBoolean(final Boolean value) throws IOException {
    return find(new HashMap<Enum, Object>(){{put(User._Fields.some_boolean, value);}});
  }

  public UserQueryBuilder query() {
    return new UserQueryBuilder(this);
  }
}