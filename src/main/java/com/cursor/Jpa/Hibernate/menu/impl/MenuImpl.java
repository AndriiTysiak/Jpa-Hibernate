package com.cursor.Jpa.Hibernate.menu.impl;

import com.cursor.Jpa.Hibernate.menu.Menu;
import com.cursor.Jpa.Hibernate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Scanner;

@Repository
public class MenuImpl implements Menu {
    @Autowired
    UserRepository userRepository;
    private  String[] items = {"""
          1. Create user
          --------------
          2. Update user
          --------------
          3. Delete user
          --------------
          4. Show user info
          --------------
          5. Show all users
          --------------
          0. Exit
          --------------
          """};

    @Override
    public void show() {
        showItems(items);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    userRepository.createUser();
                    show();
                case 2:
                    userRepository.updateUser();
                    show();
                case 3:
                    userRepository.deleteUser();
                    show();
                case 4:
                    userRepository.getInfoUser();
                    show();
                case 5:
                    userRepository.getAllUsers().forEach(System.out::println);
                    show();
                case 0:
                    exit();
                    break;
            }
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }
}
