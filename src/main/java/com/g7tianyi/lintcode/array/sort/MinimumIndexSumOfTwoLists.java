package com.g7tianyi.lintcode.array.sort;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/minimum-index-sum-of-two-lists/description
 */
public class MinimumIndexSumOfTwoLists {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Restaurant {

      String name;
      int index;

      public Restaurant(String name, int index) {
        this.name = name;
        this.index = index;
      }

      @Override
      public String toString() {
        return this.name;
      }
    }

    public String[] findRestaurant(String[] list1, String[] list2) {

      Restaurant[] restaurants1 = new Restaurant[list1.length];
      for (int i = 0; i < list1.length; ++i) {
        restaurants1[i] = new Restaurant(list1[i], i);
      }

      Restaurant[] restaurants2 = new Restaurant[list2.length];
      for (int i = 0; i < list2.length; ++i) {
        restaurants2[i] = new Restaurant(list2[i], i);
      }

      Comparator<Restaurant> comparator =
          new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
              return o1.name.compareTo(o2.name);
            }
          };

      Arrays.sort(restaurants1, comparator);
      Arrays.sort(restaurants2, comparator);

      int minIndexSum = Integer.MAX_VALUE;
      for (Restaurant restaurant1 : restaurants1) {
        Restaurant restaurant2 = binarySearch(restaurants2, restaurant1.name);
        if (restaurant2 != null) {
          minIndexSum = Math.min(minIndexSum, restaurant1.index + restaurant2.index);
        }
      }

      List<String> result = new ArrayList<>();
      for (Restaurant restaurant1 : restaurants1) {
        Restaurant restaurant2 = binarySearch(restaurants2, restaurant1.name);
        if (restaurant2 != null && restaurant1.index + restaurant2.index == minIndexSum) {
          result.add(restaurant1.name);
        }
      }

      return result.toArray(new String[0]);
    }

    private Restaurant binarySearch(Restaurant[] restaurants, String name) {
      int i = 0, j = restaurants.length - 1, middle, compare;
      Restaurant restaurant;
      while (i <= j) {
        middle = i + ((j - i) >> 1);
        restaurant = restaurants[middle];
        compare = name.compareTo(restaurant.name);
        if (compare == 0) {
          return restaurant;
        }
        if (compare < 0) {
          j = middle - 1;
        } else {
          i = middle + 1;
        }
      }
      return null;
    }
  }

  @AllArgsConstructor
  private class Case {
    String[] list1;
    String[] list2;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(Strings.format(aCase.list1));
          log.info(Strings.format(aCase.list2));
          log.info(Strings.format(s.findRestaurant(aCase.list1, aCase.list2)));
          log.info();
        };

    c.accept(
        new Case(
            new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"},
            new String[] {
              "Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"
            }));

    c.accept(
        new Case(
            new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"},
            new String[] {"KFC", "Shogun", "Burger King"}));

    c.accept(
        new Case(
            new String[] {"Shogun", "KFC", "Tapioca Express", "Burger King"},
            new String[] {"KFC", "Shogun", "Burger King"}));
  }
}
