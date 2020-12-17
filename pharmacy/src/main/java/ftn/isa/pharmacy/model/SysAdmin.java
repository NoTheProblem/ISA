package ftn.isa.pharmacy.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("sysadmin")
public class SysAdmin extends User {
    

    public SysAdmin() {
    }

    public SysAdmin(SysAdmin u) {
        super();
    }
}

