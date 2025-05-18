package Deque;

public interface Deque {
    public void inserirInicio (Object o);
    public Object removerInicio () throws EDequeVazio;
    public void inserirFim (Object o);
    public Object removerFim() throws EDequeVazio;
    public Object primeiro() throws EDequeVazio;
    public Object ultimo() throws EDequeVazio;
    public int tamanho();
    public boolean estaVazia();
    public void print();
    public int menor();
}
