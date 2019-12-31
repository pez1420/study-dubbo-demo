package com.study.performance.removeall;

import java.util.Objects;

/**
 * @author pez1420@gmail.com
 * @version $Id: User.java v 0.1 2019/9/16 7:12 PM pez1420 Exp $$
 */
public class User {

    private String username;

    private int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return age == user.age &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, age);
    }
}
