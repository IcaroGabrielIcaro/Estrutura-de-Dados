package Dicionario;

import Dicionario.Interfaces.Dict;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

public class SkipList implements Dict{
    public class QuadNode {
        Item entry;
        QuadNode prev;
        QuadNode next;
        QuadNode down;
        QuadNode up;

        public QuadNode(Item item) {
            this.entry = item;
        }

        public QuadNode(int key, Object value) {
            Item novo = new Item(key, value);
            this(novo);
        }

        @Override
        public String toString() {
            return Integer.toString(entry.key());
        }
    }

    private QuadNode topHead;
    private QuadNode topTail;
    private int levels;
    private int size;
    private final Random rand = new Random();

    public SkipList() {
        QuadNode baseHead = new QuadNode(Integer.MIN_VALUE, null);
        QuadNode baseTail = new QuadNode(Integer.MAX_VALUE, null);

        baseHead.next = baseTail;
        baseTail.prev = baseHead;

        this.topHead = baseHead;
        this.topTail = baseTail;
        this.levels = 1;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public Item findElement(int valor) {
        return this.findElementNode(valor).entry;
    }

    public QuadNode findElementNode(int valor) {
        QuadNode atual = this.topHead;

        while (true) {
            while (atual.next != null && atual.next.entry.key() <= valor && atual.next.entry.key() != Integer.MAX_VALUE) {
                atual = atual.next;
            }

            if (atual.down != null) {
                atual = atual.down;
            } else {
                return atual;
            }
        }
    }

    public void insertItem(int key, Object value) {
        Item novo = new Item(key, value);

        QuadNode pos = findElementNode(novo.key());

        if (pos.entry.key() == key) {
            throw new IllegalArgumentException("Valor " + value + " já existe. Ignorando inserção.");
        }

        if (pos.next != null && pos.next.entry.key() == key) {
            throw new IllegalArgumentException("Valor " + value + " já existe (à direita). Ignorando inserção.");
        }

        QuadNode newNode = new QuadNode(novo);
        newNode.prev = pos;
        newNode.next = pos.next;
        pos.next.prev = newNode;
        pos.next = newNode;
        
        QuadNode lowerNode = newNode;
        int currentLevel = 1;


        while (this.rand.nextBoolean()) {
            currentLevel++;

            if (currentLevel > this.levels) {
                this.levels++;

                QuadNode newHead = new QuadNode(Integer.MIN_VALUE, null);
                QuadNode newTail = new QuadNode(Integer.MAX_VALUE, null);

                newHead.next = newTail;
                newTail.prev = newHead;

                newHead.down = this.topHead;
                this.topHead.up = newHead;
                newTail.down = this.topTail;
                this.topTail.up = newTail;

                this.topHead = newHead;
                this.topTail = newTail;
            }

            while (pos.up == null && pos != null) {
                pos = pos.prev;
            }

            if (pos == null) {
                pos = this.topHead;
            } else {
                pos = pos.up;
            }

            QuadNode elevatedNode = new QuadNode(novo);
            elevatedNode.prev = pos;
            elevatedNode.next = pos.next;
            pos.next.prev = elevatedNode;
            pos.next = elevatedNode;

            elevatedNode.down = lowerNode;
            lowerNode.up = elevatedNode;

            lowerNode = elevatedNode;
        }

        this.size++;
    }

    public Item removeElement(int key) {
        QuadNode node = this.findElementNode(key);

        if (node == null || node.entry.key() != key) {
            if (node != null && node.next != null && node.next.entry.key() == key) {
                node = node.next;
            } else {
                throw new IllegalArgumentException("Chave" + key + " não encontrada");
            }
        }

        Item remove = node.entry;

        while (node != null) {
            QuadNode left = node.prev;
            QuadNode right = node.next;

            if (left != null) left.next = right;
            if (right != null) right.prev = left;

            QuadNode up = node.up;
            node.prev = node.next = node.up = node.down = null;
            node = up;
        }

        while (this.levels > 1 && this.topHead.next == this.topTail) {
            this.topHead = this.topHead.down;
            this.topTail = this.topTail.down;
            if (this.topHead != null) this.topHead.up = null;
            if (this.topTail != null) this.topTail.up = null;
            this.levels--;
        }

        this.size--;
        return remove;
    }

    public boolean contains(int key) {
        QuadNode node = this.findElementNode(key);
        if (node != null && node.entry.key() == key) return true;
        if (node != null && node.next != null && node.next.entry.key() == key) return true;
        return false;
    }

    public void printLevels() {
        System.out.println("Levels = " + levels);
        QuadNode head = topHead;
        int lvl = levels;
        while (head != null) {
            System.out.print("Level " + lvl + ": ");
            QuadNode cur = head;
            while (cur != null) {
                System.out.print(cur.entry.key() == Integer.MIN_VALUE ? "H" : (cur.entry.key() == Integer.MAX_VALUE ? "T" : cur.entry.key()));
                if (cur.next != null) System.out.print(" -> ");
                cur = cur.next;
                if (cur != null && cur == head) break;
            }
            System.out.println();
            head = head.down;
            lvl--;
        }
    }

    public Iterator<Integer> keys() {
        List<Integer> listaChaves = new ArrayList<>();
        QuadNode atual = this.topHead;

        while (atual.down != null) {
            atual = atual.down;
        }

        atual = atual.next;
        while (atual != null && atual.entry.key() != Integer.MAX_VALUE) {
            listaChaves.add(atual.entry.key());
            atual = atual.next;
        }

        return listaChaves.iterator();
    }

    public Iterator<Item> elements() {
        List<Item> listaItens = new ArrayList<>();
        QuadNode atual = this.topHead;

        while (atual.down != null) {
            atual = atual.down;
        }

        atual = atual.next;
        while (atual != null && atual.entry.key() != Integer.MAX_VALUE) {
            listaItens.add(atual.entry);
            atual = atual.next;
        }

        return listaItens.iterator();
    }
}