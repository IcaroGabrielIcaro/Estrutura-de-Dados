package FilaPrioridade;

import FilaPrioridade.Interfaces.Entry;

public class Item implements Entry{
    private Object key;
    private Object value;

    public Item(Object k, Object v) {
        this.key = k;
        this.value = v;
    }

    public Object key() {
        return this.key;
    }

    public Object value() {
        return this.value;
    }
}
