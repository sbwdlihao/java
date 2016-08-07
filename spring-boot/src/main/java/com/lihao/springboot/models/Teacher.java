package com.lihao.springboot.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by sbwdlihao on 5/27/16.
 */
@Entity
public class Teacher {
  private String id;
  private String name;

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(name = "id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Teacher teacher = (Teacher) o;

    if (id != null ? !id.equals(teacher.id) : teacher.id != null) return false;
    if (name != null ? !name.equals(teacher.name) : teacher.name != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
}
