package com.dugonggames.elclasico;

public class Leaf<T> implements Node<T> {
    T item;

    public Leaf(T item){this.item = item;}

    public T predict(DigitImage im) {return item;}
}
