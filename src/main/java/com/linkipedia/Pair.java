package com.linkipedia;

public class Pair<A, B> {
    private A a;
    private B b;

    public Pair(A a, B b) {
        this.setA(a);
        this.setB(b);
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public boolean equals(Pair<A, B> that) {
    	return (this.a == that.getA() && this.b == that.getB());
    }
}
