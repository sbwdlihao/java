package com.lihao.hibernate.hbm;

import java.util.Date;

/**
 * Created by sbwdlihao on 6/17/16.
 */
public class Event {

  private Long id;

  private String title;

  private Date date;

  public Event() {
    // this form used by Hibernate
  }

  public Event(String title, Date date) {
    // for application use, to create new events
    this.title = title;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
