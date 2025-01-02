package com.example.studentenrollment.model;

/**
 * Enumeration representing the roles in the student enrollment system.
 * 
 * <p>This enum is used to differentiate between different types of users 
 * in the system. Each role determines the permissions and functionalities 
 * accessible to the user.</p>
 */
public enum Role {
    
   /**
    * Role for students (Mahasiswa).
    * 
    * <p>Users with this role are students who can perform actions such as:
    * <ul>
    *   <li>Creating and managing their study plans.</li>
    *   <li>Viewing and managing their course registrations.</li>
    * </ul>
    * </p>
    */
   MAHASISWA, 
   
   /**
    * Role for LAA employees.
    * 
    * <p>Users with this role are administrative staff who can perform actions such as:
    * <ul>
    *   <li>Managing student data.</li>
    *   <li>Adding or updating course information.</li>
    *   <li>Overseeing semester registrations.</li>
    * </ul>
    * </p>
    */
   LAA
}
