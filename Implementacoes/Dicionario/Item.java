package Dicionario;

import Dicionario.Interfaces.Entry;;

public class Item implements Entry{
    private int key;
    private Object value;

    public Item(int k, Object v) {
        this.key = k;
        this.value = v;
    }

    public void setKey(int k) {
        this.key = k;
    }

    public int key() {
        return this.key;
    }

    public void setValue(Object o) {
        this.value = o;
    }

    public Object value() {
        return this.value;
    }
}
