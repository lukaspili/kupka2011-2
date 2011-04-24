package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * User: derf4002
 * Date: 4/24/11
 * Time: 10:18 AM
 */
@Entity
public class VideoMessage extends Model {

    public String videoPath;

    @ManyToOne
    public User recipient;

    @ManyToOne
    public User sender;

    @Temporal(TemporalType.TIMESTAMP)
    public Date creationDate;


    public VideoMessage(String videoPath, User recipient, User sender) {
        this.videoPath = videoPath;
        this.recipient = recipient;
        this.sender = sender;
    }

    @PrePersist
    private void prePersist() {
        if(creationDate == null) creationDate = new Date();
    }
    
}
