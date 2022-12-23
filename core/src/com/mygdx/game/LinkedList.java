package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class LinkedList {
    private LinkedList.Node head;
    private LinkedList.Node tail;
    private int size;

    public LinkedList() {
    }

    public void add(Actor actor) {
        if (this.find(actor) == null) {
            LinkedList.Node node = new LinkedList.Node(actor);
            if (this.size == 0) {
                this.head = this.tail = node;
            } else {
                this.tail.next = node;
                node.previous = this.tail;
                this.tail = node;
            }

            ++this.size;
        }

    }

    private LinkedList.Node find(Actor actor) {
        LinkedList.Node temp;
        for(temp = this.head; temp != null && temp.value != actor; temp = temp.next) {
        }

        return temp;
    }

    public Actor get(int position) {
        LinkedList.Node temp;
        for(temp = this.head; position > 0; --position) {
            temp = temp.next;
        }

        return temp.value;
    }

    public void removeAll() {
        while(this.head != null) {
            this.remove(this.head.value);
        }
    }

    public void remove(Actor actor) {
        LinkedList.Node node = this.find(actor);
        if (node.previous == null) {
            this.head = this.head.next;
            if (this.head != null) {
                this.head.previous = null;
            }
        } else if (node.next == null) {
            this.tail = node.previous;
            this.tail.next = null;
        } else {
            node.previous.next = node.next;
            node.next.previous = node.previous;
        }

        --size;
    }

    public int getSize() {
        return this.size;
    }

    private class Node {
        Actor value;
        LinkedList.Node next;
        LinkedList.Node previous;

        Node(Actor value) {
            this.value = value;
            this.next = this.previous = null;
        }
    }
}