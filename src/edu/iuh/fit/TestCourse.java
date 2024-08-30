/*
 * @ (#) TestCourse.java   1.0     8/30/2024
 *
 *Copyright (c) 2024 IUH. All rights reserved
 */

package edu.iuh.fit;

import java.util.List;
import java.util.Scanner;

/*
 * @discription:
 * @author: Nguyen Hong Anh
 * @date: 8/30/2024
 * @version:     1.0
 */

public class TestCourse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseList courseList = new CourseList(10);

        initData(courseList);

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add a course");
            System.out.println("2. Display all courses");
            System.out.println("3. Remove a course");
            System.out.println("4. Search course by ID");
            System.out.println("5. Search course by title");
            System.out.println("6. Search course by department");
            System.out.println("7. Sort courses by title");
            System.out.println("8. Find courses with maximum credits");
            System.out.println("9. Find department with most courses");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addCourse(sc, courseList);
                case 2 -> displayCourses(courseList);
                case 3 -> removeCourse(sc, courseList);
                case 4 -> searchCourseById(sc, courseList);
                case 5 -> searchCourseByTitle(sc, courseList);
                case 6 -> searchCourseByDepartment(sc, courseList);
                case 7 -> displaySortedCourses(courseList);
                case 8 -> findMaxCreditCourses(courseList);
                case 9 -> findDepartmentWithMostCourses(courseList);
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }

    private static void addCourse(Scanner sc, CourseList courseList) {
        System.out.print("Enter course id: ");
        String id = sc.nextLine();
        System.out.print("Enter course title: ");
        String title = sc.nextLine();
        System.out.print("Enter course credit: ");
        int credit = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter course department: ");
        String department = sc.nextLine();
        Course course = new Course(id, title, credit, department);
        if (courseList.addCourse(course)) {
            System.out.println("Course added successfully!");
        } else {
            System.out.println("Course not added!");
        }
    }

    private static void displayCourses(CourseList courseList) {
        System.out.println("Course List");
        System.out.println("-------------------------------------------------------------");
        System.out.println(String.format("%-10s %-30s %5s %-10s", "ID", "Title", "Credit", "Department"));
        System.out.println("-------------------------------------------------------------");
        for (Course c : courseList.getCourses()) {
            if (c != null) {
                System.out.println(c);
            }
        }
        System.out.println("-------------------------------------------------------------");
    }

    private static void removeCourse(Scanner sc, CourseList courseList) {
        System.out.print("Enter course id to remove: ");
        String idToRemove = sc.nextLine();
        if (courseList.removeCourse(idToRemove)) {
            System.out.println("Course removed successfully!");
        } else {
            System.out.println("Course not found!");
        }
    }

    private static void searchCourseById(Scanner sc, CourseList courseList) {
        System.out.print("Enter course id to search: ");
        String id = sc.nextLine();
        Course course = courseList.searchCourseById(id);
        if (course != null) {
            System.out.println("Course found: " + course);
        } else {
            System.out.println("Course not found!");
        }
    }

    private static void searchCourseByTitle(Scanner sc, CourseList courseList) {
        System.out.print("Enter course title to search: ");
        String title = sc.nextLine();
        List<Course> foundCourses = courseList.searchCourse(title);
        if (foundCourses.isEmpty()) {
            System.out.println("No courses found with the title: " + title);
        } else {
            System.out.println("Courses found:");
            for (Course course : foundCourses) {
                System.out.println(course);
            }
        }
    }

    private static void searchCourseByDepartment(Scanner sc, CourseList courseList) {
        System.out.print("Enter department to search: ");
        String department = sc.nextLine();
        List<Course> foundCourses = courseList.searchCourseByDepartment(department);
        if (foundCourses.isEmpty()) {
            System.out.println("No courses found in the department: " + department);
        } else {
            System.out.println("Courses found:");
            for (Course course : foundCourses) {
                System.out.println(course);
            }
        }
    }

    private static void displaySortedCourses(CourseList courseList) {
        Course[] sortedCourses = courseList.sortCourses();
        System.out.println("Sorted Course List");
        System.out.println("-------------------------------------------------------------");
        System.out.println(String.format("%-10s %-30s %5s %-10s", "ID", "Title", "Credit", "Department"));
        System.out.println("-------------------------------------------------------------");
        for (Course c : sortedCourses) {
            System.out.println(c);
        }
        System.out.println("-------------------------------------------------------------");
    }

    private static void findMaxCreditCourses(CourseList courseList) {
        List<Course> maxCreditCourses = courseList.findMaxCreditCourse();
        if (maxCreditCourses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            System.out.println("Courses with maximum credits:");
            for (Course course : maxCreditCourses) {
                System.out.println(course);
            }
        }
    }

    private static void findDepartmentWithMostCourses(CourseList courseList) {
        String department = courseList.findDepartmentWithMostCourses();
        if (department != null) {
            System.out.println("Department with most courses: " + department);
        } else {
            System.out.println("No courses available.");
        }
    }

    private static void initData(CourseList courseList) {
        Course[] initialCourses = {
                new Course("FIT101", "Java Programming", 3, "FIT"),
                new Course("FIT102", "Web Programming", 3, "FIT"),
                new Course("FIT103", "Database Programming", 3, "FIT"),
                new Course("FIT104", "Mobile Programming", 3, "FIT"),
                new Course("FIT105", "Software Engineering", 3, "FIT"),
                new Course("FIT106", "Data Science", 3, "FIT"),
                new Course("FIT107", "Machine Learning", 3, "FIT"),
                new Course("FIT108", "Artificial Intelligence", 3, "FIT")
        };

        for (Course course : initialCourses) {
            courseList.addCourse(course);
        }
    }
}