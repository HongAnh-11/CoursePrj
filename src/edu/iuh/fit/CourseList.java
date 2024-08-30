/*
 * @ (#) CourseList.java   1.0     8/30/2024
 *
 *Copyright (c) 2024 IUH. All rights reserved
 */

package edu.iuh.fit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @discription:
 * @author: Nguyen Hong Anh
 * @date: 8/30/2024
 * @version:     1.0
 */

public class CourseList {
    private List<Course> courses;

    // Constructor
    public CourseList(int capacity) {
        this.courses = new ArrayList<>(capacity);
    }

    public boolean addCourse(Course course) {
        if (exists(course)) {
            System.out.println("Mã khóa học đã tồn tại.");
            return false;
        }
        courses.add(course);
        return true;
    }

    public boolean exists(Course course) {
        return courses.stream().anyMatch(c -> c.getId().equals(course.getId()));
    }

    public boolean removeCourse(String id) {
        Course courseToRemove = searchCourseById(id);
        if (courseToRemove != null) {
            courses.remove(courseToRemove);
            return true;
        } else {
            System.out.println("Mã khóa học không tồn tại.");
            return false;
        }
    }

    public Course searchCourseById(String id) {
        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Course> searchCourse(String title) {
        return courses.stream()
                .filter(c -> c.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Course> searchCourseByDepartment(String department) {
        return courses.stream()
                .filter(c -> c.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    public List<Course> findMaxCreditCourse() {
        int maxCredit = courses.stream()
                .mapToInt(Course::getCredit)
                .max()
                .orElse(0);
        return courses.stream()
                .filter(c -> c.getCredit() == maxCredit)
                .collect(Collectors.toList());
    }

    public String findDepartmentWithMostCourses() {
        return courses.stream()
                .collect(Collectors.groupingBy(Course::getDepartment, Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(entry -> entry.getValue()))
                .map(entry -> entry.getKey())
                .orElse(null);
    }

    public Course[] getCourses() {
        return courses.toArray(new Course[0]);
    }

    public Course[] sortCourses() {
        return courses.stream()
                .sorted(Comparator.comparing(Course::getTitle))
                .toArray(Course[]::new);
    }
}