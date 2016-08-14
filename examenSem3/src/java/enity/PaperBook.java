/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author RolfMoikjær
 */
@Entity
public class PaperBook extends Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private int shippingWait;
    private int inStock;

    public int getShippingWait() {
        return shippingWait;
    }

    public void setShippingWait(int shippingWait) {
        this.shippingWait = shippingWait;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

}
