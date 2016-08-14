/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author RolfMoikjær
 */
public class Group {

    private String _author;
    private String _group;
    private String _class;

    public Group() {
    }

    public Group(String _author, String _group, String _class) {
        this._author = _author;
        this._group = _group;
        this._class = _class;
    }

    public String getAuthor() {
        return _author;
    }

    public void setAuthor(String _author) {
        this._author = _author;
    }

    public String getGroup() {
        return _group;
    }

    public void setGroup(String _group) {
        this._group = _group;
    }

    public String getClass1() {
        return _class;
    }

    public void setClass1(String _class) {
        this._class = _class;
    }

}
