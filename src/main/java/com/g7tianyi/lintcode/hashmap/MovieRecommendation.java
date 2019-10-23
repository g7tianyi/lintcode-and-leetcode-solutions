package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 23, 2019
 *
 * @link https://www.lintcode.com/problem/movie-recommendation/description
 */
public class MovieRecommendation {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Item {
      Integer movie;
      Integer count;

      public Item(Integer movie, Integer count) {
        this.movie = movie;
        this.count = count;
      }
    }

    private final Comparator<Item> comparator =
        new Comparator<Item>() {
          @Override
          public int compare(Item o1, Item o2) {
            return Integer.compare(o2.count, o1.count);
          }
        };

    public List<List<Integer>> minMalwareSpread(List<List<Integer>> graph) {

      Map<Integer, Set<Integer>> movieUsers = new HashMap<>();
      for (int i = 0; i < graph.size(); ++i) {
        List<Integer> movies = graph.get(i);
        for (Integer movie : movies) {
          if (!movieUsers.containsKey(movie)) {
            movieUsers.put(movie, new HashSet<>());
          }
          movieUsers.get(movie).add(i);
        }
      }

      Map<Integer, Set<Integer>> userMovies = new HashMap<>();
      for (int i = 0; i < graph.size(); ++i) {
        List<Integer> movies = graph.get(i);
        userMovies.put(i, new HashSet<>(movies));
      }

      List<List<Integer>> result = new ArrayList<>();
      for (int currUser = 0; currUser < graph.size(); ++currUser) {
        Map<Integer, Integer> movieVotes = new HashMap<>();
        Set<Integer> myMovies = userMovies.get(currUser);
        for (Integer movie : myMovies) {
          Set<Integer> users = movieUsers.get(movie);
          for (Integer peerUser : users) {
            if (peerUser == currUser) {
              continue;
            }
            for (Integer peerUserMovie : userMovies.get(peerUser)) {
              if (!myMovies.contains(peerUserMovie)) {
                movieVotes.put(peerUserMovie, movieVotes.getOrDefault(peerUserMovie, 0) + 1);
              }
            }
          }
        }

        List<Item> items = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : movieVotes.entrySet()) {
          items.add(new Item(entry.getKey(), entry.getValue()));
        }
        items.sort(comparator);

        List<Integer> recommendations = new ArrayList<>();
        for (Item item : items) {
          recommendations.add(item.movie);
          if (recommendations.size() == 5) {
            break;
          }
        }
        result.add(recommendations);
      }

      return result;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<List<List<Integer>>> c =
      graph -> {
        List<List<Integer>> result = s.minMalwareSpread(graph);
        for (List<Integer> row : result) {
          log.info(row);
        }
        log.info();
      };

  @Test
  public void test() {

    c.accept(
        Lists.newArrayList(
            Lists.newArrayList(1, 2), Lists.newArrayList(1, 3, 5), Lists.newArrayList(2, 5)));

    c.accept(
        Lists.newArrayList(Lists.newArrayList(1), Lists.newArrayList(2), Lists.newArrayList(3)));

    c.accept(
        Lists.newArrayList(
            Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
            Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
            Lists.newArrayList(7),
            Lists.newArrayList(9, 10),
            Lists.newArrayList(1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
            Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
            Lists.newArrayList(1, 3, 4, 6, 8, 9, 10, 11, 12)));
  }
}
