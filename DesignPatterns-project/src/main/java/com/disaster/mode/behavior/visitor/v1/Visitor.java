package com.disaster.mode.behavior.visitor.v1;

public interface Visitor {
    void visit(Customer customer);

    void visit(Order order);

    void visit(Item item);
}
