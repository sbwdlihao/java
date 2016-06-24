package com.lihao.hibernate.annotations;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sbwdlihao on 6/24/16.
 */
@Entity
@Table(name = "ANNOTATION_EVENTS")
@Audited
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

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
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

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "EVENT_DATE")
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
