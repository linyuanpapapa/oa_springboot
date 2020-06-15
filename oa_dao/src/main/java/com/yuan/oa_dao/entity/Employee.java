package com.yuan.oa_dao.entity;

public class Employee {
    private String sn;
    private String password;
    private String name;
    private String departmentSn;
    private String post;
    private Department department;


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentSn() {
        return departmentSn;
    }

    public void setDepartmentSn(String departmentSn) {
        this.departmentSn = departmentSn;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "sn='" + sn + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", departmentSn='" + departmentSn + '\'' +
                ", post='" + post + '\'' +
                '}';
    }
}
