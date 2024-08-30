/*
 * @ (#) Course.java   1.0     8/30/2024
 *
 *Copyright (c) 2024 IUH. All rights reserved
 */

package edu.iuh.fit;


/*
 * @discription:
 * @author: Nguyen Hong Anh
 * @date: 8/30/2024
 * @version:     1.0
 */

public class Course {
    private String id;
    private String title;
    private int credit;
    private String department;



    public Course() {
        this("","",0,"");
    }

    public Course(String id, String title, int credit, String department) {

        this.id = id;
        this.title = title;
        this.credit = credit;
        this.department = department;


    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        if(credit < 0)
            throw new IllegalArgumentException("credit must be greater than 0");
        this.credit = credit;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id == null||id.length()<3)
            throw new IllegalArgumentException("id must be have at least 3 characters");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title==null||title.isEmpty())
            throw new IllegalArgumentException("title must not be empty");
    }

    @Override
    public String toString() {
        return String.format("%-10s%-30s%2d  %-10s", id, title, credit,department);
    }

}
