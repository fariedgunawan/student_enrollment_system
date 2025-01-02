package com.example.studentenrollment.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;


/**
 * Represents a course (Mata Kuliah) entity in the student enrollment system.
 * 
 * <p>This class contains attributes such as course name, code, credits (sks), grades, 
 * and relationships with students and their performance in the course.</p>
 */
@Entity
public class MataKuliah {

    /**
     * The unique identifier for the Mata Kuliah entity.
     * Automatically generated using {@code GenerationType.IDENTITY}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the course.
     */
    private String nama;

    /**
     * The unique code of the course.
     */
    private String kode;

    /**
     * The number of credits (sks) assigned to the course.
     */
    private int sks;

    /**
     * The score of the Mid-Term Exam (UTS) for the course.
     */
    private int uts;

    /**
     * The score of the Final Exam (UAS) for the course.
     */
    private int uas;

    /**
     * The score of quizzes (Kuis) for the course.
     */
    private int kuis;

    /**
     * The total score for the course.
     */
    private int total;

    /**
     * The grade obtained in the course, represented as a letter (e.g., A, B, C).
     */
    private String grade;

    /**
     * One-to-Many relationship with the {@link MataKuliahMahasiswa} entity.
     * 
     * <p>This property stores the list of students' performance in the course.
     * It is ignored during JSON serialization to avoid cyclic references.</p>
     */
    @JsonIgnore
    @OneToMany(mappedBy = "mataKuliah", cascade = CascadeType.ALL)
    private List<MataKuliahMahasiswa> mataKuliahMahasiswa;

    /**
     * A transient property to store the list of students (Mahasiswa) enrolled in the course.
     * 
     * <p>This property is used only at the application level and is not persisted in the database.</p>
     */
    @Transient
    private List<Mahasiswa> mahasiswa;

    // Getters and Setters

    /**
     * Returns the unique ID of the course.
     * 
     * @return The unique ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique ID for the course.
     * 
     * @param id The unique ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of the course.
     * 
     * @return The course name.
     */
    public String getNama() {
        return nama;
    }

    /**
     * Sets the name of the course.
     * 
     * @param nama The course name to set.
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * Returns the unique code of the course.
     * 
     * @return The course code.
     */
    public String getKode() {
        return kode;
    }

    /**
     * Sets the unique code of the course.
     * 
     * @param kode The course code to set.
     */
    public void setKode(String kode) {
        this.kode = kode;
    }

    /**
     * Returns the number of credits (sks) for the course.
     * 
     * @return The number of credits.
     */
    public int getSks() {
        return sks;
    }

    /**
     * Sets the number of credits (sks) for the course.
     * 
     * @param sks The number of credits to set.
     */
    public void setSks(int sks) {
        this.sks = sks;
    }

    /**
     * Returns the score of the Mid-Term Exam (UTS).
     * 
     * @return The UTS score.
     */
    public int getUts() {
        return uts;
    }

    /**
     * Sets the score of the Mid-Term Exam (UTS).
     * 
     * @param uts The UTS score to set.
     */
    public void setUts(int uts) {
        this.uts = uts;
    }

    /**
     * Returns the score of the Final Exam (UAS).
     * 
     * @return The UAS score.
     */
    public int getUas() {
        return uas;
    }

    /**
     * Sets the score of the Final Exam (UAS).
     * 
     * @param uas The UAS score to set.
     */
    public void setUas(int uas) {
        this.uas = uas;
    }

    /**
     * Returns the score of quizzes (Kuis).
     * 
     * @return The quiz score.
     */
    public int getKuis() {
        return kuis;
    }

    /**
     * Sets the score of quizzes (Kuis).
     * 
     * @param kuis The quiz score to set.
     */
    public void setKuis(int kuis) {
        this.kuis = kuis;
    }

    /**
     * Returns the total score for the course.
     * 
     * @return The total score.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the total score for the course.
     * 
     * @param total The total score to set.
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Returns the grade obtained in the course.
     * 
     * @return The grade.
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets the grade for the course.
     * 
     * @param grade The grade to set.
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Returns the list of {@link MataKuliahMahasiswa} entities associated with the course.
     * 
     * @return The list of MataKuliahMahasiswa entities.
     */
    public List<MataKuliahMahasiswa> getMataKuliahMahasiswa() {
        return mataKuliahMahasiswa;
    }

    /**
     * Sets the list of {@link MataKuliahMahasiswa} entities for the course.
     * 
     * @param mataKuliahMahasiswa The list of MataKuliahMahasiswa to set.
     */
    public void setMataKuliahMahasiswa(List<MataKuliahMahasiswa> mataKuliahMahasiswa) {
        this.mataKuliahMahasiswa = mataKuliahMahasiswa;
    }

    /**
     * Returns the list of students (Mahasiswa) enrolled in the course.
     * 
     * @return The list of students.
     */
    public List<Mahasiswa> getMahasiswa() {
        return mahasiswa;
    }

    /**
     * Sets the list of students (Mahasiswa) enrolled in the course.
     * 
     * @param mahasiswa The list of students to set.
     */
    public void setMahasiswa(List<Mahasiswa> mahasiswa) {
        this.mahasiswa = mahasiswa;
    }
}
