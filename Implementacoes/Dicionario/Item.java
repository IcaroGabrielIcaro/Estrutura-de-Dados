package Dicionario;

import Dicionario.Interfaces.Entry;;

public class Item implements Entry{
    private int key;
    private Object value;

    public Item(int k, Object v) {
        this.key = k;
        this.value = v;
    }

    public int key() {
        return this.key;
    }

    public Object value() {
        return this.value;
    }
}
