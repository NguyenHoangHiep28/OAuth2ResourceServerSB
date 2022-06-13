package com.example.resourceserver;

import java.util.ArrayList;
import java.util.List;

public class Resource {
    private static final List<Avatar> avatarList;
    private static final List<User> userList;
    static {
        avatarList = new ArrayList<>();
        avatarList.add(Avatar.builder().id(1).userId(1).url("https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp").build());
        avatarList.add(Avatar.builder().id(2).userId(2).url("https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp").build());
        avatarList.add(Avatar.builder().id(3).userId(3).url("https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp").build());

        userList = new ArrayList<>();
        userList.add(User.builder().id(1).username("harry").email("harrymachai@gmail.com").firstName("Harry").lastName("MacHai").address("Hanoi").phone("0123456789").build());
        userList.add(User.builder().id(2).username("john").email("john@gmail.com").firstName("John").lastName("Cena").address("Hanoi").phone("04712613721").build());
        userList.add(User.builder().id(3).username("begging").email("begging@gmail.com").firstName("Begging").lastName("Lauren").address("Hanoi").phone("0571638271").build());
    }

    public static List<Avatar> getAvatarList() {
        return avatarList;
    }

    public static List<User> getUserList() {
        return userList;
    }
}
