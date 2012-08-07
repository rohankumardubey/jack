
/**
 * Autogenerated by Jack
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
/* generated from migration version 20110324000133 */
package com.rapleaf.jack.test_project.database_1.models;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import com.rapleaf.jack.test_project.database_1.IDatabase1;

import com.rapleaf.jack.ModelWithId;
import com.rapleaf.jack.BelongsToAssociation;
import com.rapleaf.jack.HasManyAssociation;
import com.rapleaf.jack.HasOneAssociation;

import com.rapleaf.jack.test_project.IDatabases;

public class Image extends ModelWithId<Image, IDatabases> {
  // Fields
  private Integer __user_id;

  // Associations
  private BelongsToAssociation<User> __assoc_user;

  public enum _Fields {
    user_id,
  }

  public Image(long id, final Integer user_id, IDatabases databases) {
    super(id);
    this.__user_id = user_id;
    this.__assoc_user = new BelongsToAssociation<User>(databases.getDatabase1().users(), __user_id == null ? null : __user_id.longValue());
  }

  public Image(long id, final Integer user_id) {
    super(id);
    this.__user_id = user_id;
  }

  public Image(long id, Map<Enum, Object> fieldsMap) {
    super(id);
    Integer user_id = (Integer) fieldsMap.get(Image._Fields.user_id);
    this.__user_id = user_id;
  }

  public Image (Image other) {
    this(other, (IDatabases)null);
  }

  public Image (Image other, IDatabases databases) {
    super(other.getId());
    this.__user_id = other.getUserId();

    if (databases != null) {
      this.__assoc_user = new BelongsToAssociation<User>(databases.getDatabase1().users(), __user_id == null ? null : __user_id.longValue());
    }
  }

  public Integer getUserId(){
    return __user_id;
  }

  public void setUserId(Integer newval){
    this.__user_id = newval;
    cachedHashCode = 0;
  }

  public void setField(_Fields field, Object value) {
    switch (field) {
      case user_id:
        setUserId((Integer) value);
        break;
      default:
        throw new IllegalStateException("Invalid field: " + field);
    }
  }
  
  public void setField(String fieldName, Object value) {
    if (fieldName.equals("user_id")) {
      setUserId((Integer)  value);
      return;
    }
    throw new IllegalStateException("Invalid field: " + fieldName);
  }

  public static Class getFieldType(_Fields field) {
    switch (field) {
      case user_id:
        return Integer.class;
      default:
        throw new IllegalStateException("Invalid field: " + field);
    }    
  }

  public static Class getFieldType(String fieldName) {    
    if (fieldName.equals("user_id")) {
      return Integer.class;
    }
    throw new IllegalStateException("Invalid field name: " + fieldName);
  }

  public User getUser() throws IOException {
    return __assoc_user.get();
  }

  @Override
  public Object getField(String fieldName) {
    if (fieldName.equals("id")) {
      return getId();
    }
    if (fieldName.equals("user_id")) {
      return getUserId();
    }
    throw new IllegalStateException("Invalid field name: " + fieldName);
  }

  public Object getField(_Fields field) {
    switch (field) {
      case user_id:
        return getUserId();
    }
    throw new IllegalStateException("Invalid field: " + field);
  }
  
   public boolean hasField(String fieldName) {
    if (fieldName.equals("id")) {
      return true;
    }
    if (fieldName.equals("user_id")) {
      return true;
    }
    return false;
  }

  public static Object getDefaultValue(_Fields field) {
    switch (field) {
      case user_id:
        return null;
    }
    throw new IllegalStateException("Invalid field: " + field);
  }

  @Override
  public Set<Enum> getFieldSet() {
    Set set = EnumSet.allOf(_Fields.class);
    return set;
  }

  @Override
  public Image getCopy() {
    return new Image(this);
  }

  public Image getCopy(IDatabases databases) {
    return new Image(this, databases);
  }

  public String toString() {
    return "<Image"
      + " user_id: " + __user_id
      + ">";
  }
}
