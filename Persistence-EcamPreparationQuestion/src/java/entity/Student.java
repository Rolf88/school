/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author RolfMoikjær
 */
@Entity
public class Student extends Person implements Serializable{
    private static final long serialVersionUID = 1L;
    private int matNr;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date matDate;

    public int getMatNr() {
        return matNr;
    }

    public void setMatNr(int matNr) {
        this.matNr = matNr;
    }

    public Date getMatDate() {
        return matDate;
    }

    public void setMatDate(Date matDate) {
        this.matDate = matDate;
    }

}
