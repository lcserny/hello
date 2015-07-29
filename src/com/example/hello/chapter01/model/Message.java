package com.example.hello.chapter01.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by user on 27.07.2015.
 */
@Entity
@Table(name = "MESSAGE")
@NamedQueries({
        @NamedQuery(
                name = "findMessages",
                query = "select m from Message m"
        ),
        @NamedQuery(
                name = "deleteMessages",
                query = "delete from Message"
        )
})
public class Message implements Serializable
{
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "MESSAGE")
    private String messageString;

    public Message() {}

    public Message(String id, String messageString)
    {
        this.id = id;
        this.messageString = messageString;
    }

    public String getId()
    {
        return id;
    }

    public String getMessageString()
    {
        return messageString;
    }
}
