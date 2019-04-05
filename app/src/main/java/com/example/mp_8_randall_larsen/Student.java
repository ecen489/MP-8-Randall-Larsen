package com.example.mp_8_randall_larsen;

public class Student {

    String  email;
    int     id;
    String name;
    String password;

    public Student() {}
    public Student(String s_email, int s_id, String s_name, String s_password){
        email = s_email;
        id=s_id;
        name = s_name;
        password = s_password;
    }

    public String  getstudent_email()       { return email; }
    public int getstudent_id()  { return id; }
    public String getstudent_name() { return name; }
    public String getstudent_password() { return password;}
}
