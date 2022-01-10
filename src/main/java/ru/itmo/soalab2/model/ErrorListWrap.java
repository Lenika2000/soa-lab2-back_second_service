package ru.itmo.soalab2.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "errorListWrap")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorListWrap {
    public ErrorListWrap(){}

    public ErrorListWrap(ArrayList<Error> errors){
        this.errors = errors;
    }

    @XmlElement
    private ArrayList<Error> errors;

    public ArrayList<Error> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<Error> errors) {
        this.errors = errors;
    }
}
