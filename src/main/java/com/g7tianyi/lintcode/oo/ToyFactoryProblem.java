package com.g7tianyi.lintcode.oo;

import org.junit.Test;

/** Created by g7tianyi on Aug 28, 2019 */
public class ToyFactoryProblem {

  interface Toy {
    void talk();
  }

  class Dog implements Toy {
    @Override
    public void talk() {
      System.out.println("Wow");
    }
  }

  class Cat implements Toy {
    @Override
    public void talk() {
      System.out.println("Meow");
    }
  }

  public class ToyFactory {
    public Toy getToy(String type) {
      if ("Cat".equals(type)) {
        return new Cat();
      }
      return new Dog();
    }
  }

  @Test
  public void test() {
    ToyFactory tf = new ToyFactory();
    Toy toy = tf.getToy("Cat");
    toy.talk();

    toy = tf.getToy("Dog");
    toy.talk();
  }
}
