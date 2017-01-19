package com.javarush.test.level20.lesson10.bonus04;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;

/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать Solution.
Элементы должны следовать так:
1->3->7->15
    ->8...
 ->4->9
    ->10
2->5->11
    ->12
 ->6->13
    ->14
Удалили 2 и 9
1->3->7->15
    ->8
 ->4->10
Добавили 16,17,18,19,20 (всегда добавляются на самый последний уровень к тем элементам, которые есть)
1->3->7->15
       ->16
    ->8->17
       ->18
 ->4->10->19
        ->20
Удалили 18 и 20
1->3->7->15
       ->16
    ->8->17
 ->4->10->19
Добавили 21 и 22 (всегда добавляются на самый последний уровень к тем элементам, которые есть.
Последний уровень состоит из 15, 16, 17, 19. 19 последний добавленный элемент, 10 - его родитель.
На данный момент 10 не содержит оба дочерних элемента, поэтому 21 добавился к 10. 22 добавляется в следующий уровень.)
1->3->7->15->22
       ->16
    ->8->17
 ->4->10->19
        ->21

Во внутренней реализации элементы должны добавляться по 2 на каждый уровень
Метод getParent должен возвращать элемент, который на него ссылается.
Например, 3 ссылается на 7 и на 8, т.е.  getParent("8")=="3", а getParent("13")=="6"
Строки могут быть любыми.
При удалении элемента должна удаляться вся ветка. Например, list.remove("5") должен удалить "5", "11", "12"
Итерироваться элементы должны в порядке добавления
Доступ по индексу запрещен, воспользуйтесь при необходимости UnsupportedOperationException
Должно быть наследование AbstractList<String>, List<String>, Cloneable, Serializable
Метод main в тестировании не участвует
*/
public class Solution
        extends AbstractList<String>
        implements List<String>, Cloneable, Serializable    {

    public static void main(String[] args) {
        List<String> list = new Solution();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));
    }

    public String getParent(String value) {
        for(Node<String> node = first;  node != null; node = node.left) {
            if(node.parent == null & node.left == null)
                return node.item;
            else if()
        }
        return null;
    }

    private int size = 0;
    private Node<String> first;
    private Node<String> last;
    //private Node<String> root = new Node<>(null, null, null, null);
    //private Node<String> parent = root;

    public Solution() {}

    private static class Node<String> implements Serializable {
        String item;
        Node<String> next;
        Node<String> prev;
        Node<String> parent;

        Node(Node<String> prev, String element, Node<String> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException("Access from index denied");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(String s) {
        final Node<String> l = last;
        final Node<String> newNode = new Node<>(l, s, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    String unlink(Node<String> x) {
        final String element = x.item;
        final Node<String> next = x.next;
        final Node<String> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        return element;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<String> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                }
            }
        } else {
            for (Node<String> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public Iterator<String> iterator() {
        return new Itr(this);
    }

    private class Itr implements Iterator<String> {

        private int index = 0;
        private Node<String> lastReturned;
        private Node<String> next;
        private Solution context;

        private Itr(Solution s) {
            this.context = s;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public String next() {
            if (!hasNext())
                //throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            index++;
            return lastReturned.item;
        }

        @Override
        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<String> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                index--;
            lastReturned = null;

        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
