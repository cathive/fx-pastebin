package com.cathive.fx.pastebin.common.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Benjamin p. Jung
 */
@Entity
@Table(name = "paste")
public class Paste implements Serializable {

    /** @see java.io.Serializale */
    private static final long serialVersionUID = 1L;

}
