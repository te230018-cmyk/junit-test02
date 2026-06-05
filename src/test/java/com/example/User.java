package com.example;

public class User {
    private String code;
    private String name;
    private int age;
    private int age2; // 画像にあった変数

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }

    // 画像の通りのバグのあるロジック
    public void setAge(int age) {
        if (age < 0 || age > 150) {
            age = -1;
        }
        this.age2 = this.age;
        this.age = age;
    }
}