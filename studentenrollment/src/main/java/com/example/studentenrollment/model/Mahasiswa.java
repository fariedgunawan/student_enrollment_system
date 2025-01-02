package com.example.studentenrollment.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

/**
 * Represents the Mahasiswa (Student) entity in the system.
 * 
 * <p>This class inherits properties from {@link User} and adds specific attributes
 * for students, such as NIM (Student Identification Number) and study program (prodi).
 * Additionally, it models the relationship between students and the courses they are enrolled in.</p>
 */
@Entity
public class Mahasiswa extends User {

    /**
     * The unique Student Identification Number (NIM).
     */
    private String nim;

    /**
     * The study program (prodi) the student is enrolled in.
     */
    private String prodi;

    /**
     * One-to-Many relationship between Mahasiswa and MataKuliahMahasiswa.
     * 
     * <p>This property stores the list of courses the student has enrolled in.
     * It is ignored during JSON serialization to prevent cyclic references.</p>
     */
    @JsonIgnore
    @OneToMany(mappedBy = "mahasiswa", cascade = CascadeType.ALL)
    private List<MataKuliahMahasiswa> mataKuliahMahasiswa;

    /**
     * List of MataKuliah (Courses) for the student.
     * 
     * <p>This property is used only at the application level and is not stored in the database.</p>
     */
    @Transient
    private List<MataKuliah> mataKuliah;

    /**
     * Default constructor for the Mahasiswa entity.
     */
    public Mahasiswa() {}

    /**
     * Parameterized constructor for creating a Mahasiswa entity with specific attributes.
     * 
     * @param username The student's username.
     * @param password The student's password.
     * @param role The role of the student in the system.
     * @param nim The unique Student Identification Number (NIM).
     * @param prodi The study program (prodi) of the student.
     */
    public Mahasiswa(String username, String password, Role role, String nim, String prodi) {
        super(username, password, role);
        this.nim = nim;
        this.prodi = prodi;
    }

    /**
     * Returns the Student Identification Number (NIM).
     * 
     * @return The NIM of the student.
     */
    public String getNim() {
        return nim;
    }

    /**
     * Sets the Student Identification Number (NIM).
     * 
     * @param nim The NIM to be assigned to the student.
     */
    public void setNim(String nim) {
        this.nim = nim;
    }

    /**
     * Returns the study program (prodi) of the student.
     * 
     * @return The study program of the student.
     */
    public String getProdi() {
        return prodi;
    }

    /**
     * Sets the study program (prodi) of the student.
     * 
     * @param prodi The study program to be assigned to the student.
     */
    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    /**
     * Returns the list of MataKuliahMahasiswa entities associated with the student.
     * 
     * @return The list of MataKuliahMahasiswa.
     */
    public List<MataKuliahMahasiswa> getMataKuliahMahasiswa() {
        return mataKuliahMahasiswa;
    }

    /**
     * Sets the list of MataKuliahMahasiswa entities for the student.
     * 
     * @param mataKuliahMahasiswa The list of MataKuliahMahasiswa to be assigned.
     */
    public void setMataKuliahMahasiswa(List<MataKuliahMahasiswa> mataKuliahMahasiswa) {
        this.mataKuliahMahasiswa = mataKuliahMahasiswa;
    }

    /**
     * Returns the list of MataKuliah (Courses) for the student.
     * 
     * @return The list of MataKuliah.
     */
    public List<MataKuliah> getMataKuliah() {
        return mataKuliah;
    }

    /**
     * Sets the list of MataKuliah (Courses) for the student.
     * 
     * @param mataKuliah The list of MataKuliah to be assigned.
     */
    public void setMataKuliah(List<MataKuliah> mataKuliah) {
        this.mataKuliah = mataKuliah;
    }
}
