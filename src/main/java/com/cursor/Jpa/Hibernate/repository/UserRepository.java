package com.cursor.Jpa.Hibernate.repository;

import com.cursor.Jpa.Hibernate.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

@Repository
public class UserRepository {
    Scanner scanner = new Scanner(System.in);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void createUser() {
        System.out.println("Enter your user first name: ");
        String inputFirstName = scanner.next();

        System.out.println("Enter your user last name: ");
        String inputLastName = scanner.next();

        System.out.println("Enter your user age : ");
        int inputAge = scanner.nextInt();

        entityManager.persist(new User(inputFirstName, inputLastName, inputAge));

        System.out.println("User add");
    }

    @Transactional
    public void updateUser() {

        System.out.println("User id: ");
        long inputUserId = scanner.nextLong();
        User user = findById(inputUserId);
        if (user == null) {
            System.out.println("User not found");
            System.exit(0);
        } else {

            System.out.println("Enter your user first name: ");
            String inputFirstName = scanner.next();

            System.out.println("Enter your user last name: ");
            String inputLastName = scanner.next();

            System.out.println("Enter your user age : ");
            int inputAge = scanner.nextInt();
            entityManager.merge(new User(inputUserId, inputFirstName, inputLastName, inputAge));
        }
    }

    @Transactional
    public void deleteUser() {
        System.out.println("User id :");
        Long inputUserId = scanner.nextLong();
        User user = findById(inputUserId);
        if (user == null) {
            System.out.println("User not found");
        } else {
            entityManager.remove(user);
            System.out.println("User deleted");
        }
    }

    @Transactional
    public void getInfoUser() {
        System.out.println("User id: ");
        Long inputUserId = scanner.nextLong();

        User user = findById(inputUserId);
        if (user == null) {
            System.out.println("User not found");
        } else {
            System.out.println(user);
        }
    }

    @Transactional
    public List<User> getAllUsers() {
        Query selectAll = entityManager.createQuery("SELECT u FROM User u");
        return selectAll.getResultList();
    }
}
