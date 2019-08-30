package com.g7tianyi.lintcode.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/employee-importance/description
 */
public class EmployeeImportance {

  class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
  }

  public class Solution {

    public int getImportance(List<Employee> employees, int id) {

      Map<Integer, Integer> importances = new HashMap<>();
      Map<Integer, List<Integer>> subordinates = new HashMap<>();
      for (Employee employee : employees) {
        importances.put(employee.id, employee.importance);
        subordinates.put(employee.id, employee.subordinates);
      }

      return getImportance(id, importances, subordinates);
    }

    private int getImportance(
        int id, Map<Integer, Integer> importances, Map<Integer, List<Integer>> subordinates) {
      int result = importances.get(id);
      if (subordinates.containsKey(id)) {
        List<Integer> subEmployees = subordinates.get(id);
        for (Integer subEmployee : subEmployees) {
          result += getImportance(subEmployee, importances, subordinates);
        }
      }
      return result;
    }
  }
}
