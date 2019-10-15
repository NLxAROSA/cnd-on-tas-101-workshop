package io.pivotal.workshops.cnd.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Fortune {

    public Fortune()    {}

    public Fortune(String text) {
        this.text = text;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text)  {
        this.text = text;
    }
}
