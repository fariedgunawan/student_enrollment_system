package com.example.studentenrollment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents the relationship between a student (Mahasiswa) and a course (MataKuliah).
 * 
 * <p>This entity includes scores for assessments (UTS, UAS, quizzes), the total score,
 * and the calculated grade for the student in a particular course.</p>
 */
@Entity
public class MataKuliahMahasiswa {

    /**
     * The unique identifier for the MataKuliahMahasiswa entity.
     * Automatically generated using {@code GenerationType.IDENTITY}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Many-to-One relationship to the {@link Mahasiswa} entity.
     * 
     * <p>This property links the record to a specific student. It is ignored during JSON
     * serialization to avoid cyclic references.</p>
     */
    @ManyToOne
    @JoinColumn(name = "mahasiswa_id")
    @JsonIgnore
    private Mahasiswa mahasiswa;

    /**
     * Many-to-One relationship to the {@link MataKuliah} entity.
     * 
     * <p>This property links the record to a specific course. It is ignored during JSON
     * serialization to avoid cyclic references.</p>
     */
    @ManyToOne
    @JoinColumn(name = "mata_kuliah_id")
    @JsonIgnore
    private MataKuliah mataKuliah;

    /**
     * The score obtained by the student in the Mid-Term Exam (UTS).
     */
    private int uts;

    /**
     * The score obtained by the student in the Final Exam (UAS).
     */
    private int uas;

    /**
     * The score obtained by the student in quizzes (Kuis).
     */
    private int kuis;

    /**
     * The total score of the student in the course, calculated as the average
     * of UTS, UAS, and Kuis scores.
     */
    private int total;

    /**
     * The grade of the student in the course, calculated based on the total score.
     * Possible grades are "A", "AB", "B", "BC", "C", and "D".
     */
    private String grade;

    /**
     * Default constructor for the MataKuliahMahasiswa entity.
     */
    public MataKuliahMahasiswa() {}

    /**
     * Constructor to initialize MataKuliahMahasiswa with specific student and course.
     * 
     * @param mahasiswa The student (Mahasiswa) associated with this record.
     * @param mataKuliah The course (MataKuliah) associated with this record.
     */
    public MataKuliahMahasiswa(Mahasiswa mahasiswa, MataKuliah mataKuliah) {
        this.mahasiswa = mahasiswa;
        this.mataKuliah = mataKuliah;
    }

    // Getters and Setters

    /**
     * Returns the unique ID of the MataKuliahMahasiswa record.
     * 
     * @return The unique ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the student (Mahasiswa) associated with this record.
     * 
     * @return The student.
     */
    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    /**
     * Sets the student (Mahasiswa) for this record.
     * 
     * @param mahasiswa The student to set.
     */
    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    /**
     * Returns the course (MataKuliah) associated with this record.
     * 
     * @return The course.
     */
    public MataKuliah getMataKuliah() {
        return mataKuliah;
    }

    /**
     * Sets the course (MataKuliah) for this record.
     * 
     * @param mataKuliah The course to set.
     */
    public void setMataKuliah(MataKuliah mataKuliah) {
        this.mataKuliah = mataKuliah;
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
     * Sets the score of the Mid-Term Exam (UTS) and recalculates the total score and grade.
     * 
     * @param uts The UTS score to set.
     */
    public void setUts(int uts) {
        this.uts = uts;
        calculateTotalAndGrade();
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
     * Sets the score of the Final Exam (UAS) and recalculates the total score and grade.
     * 
     * @param uas The UAS score to set.
     */
    public void setUas(int uas) {
        this.uas = uas;
        calculateTotalAndGrade();
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
     * Sets the score of quizzes (Kuis) and recalculates the total score and grade.
     * 
     * @param kuis The quiz score to set.
     */
    public void setKuis(int kuis) {
        this.kuis = kuis;
        calculateTotalAndGrade();
    }

    /**
     * Returns the total score of the student in the course.
     * 
     * @return The total score.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Returns the grade of the student in the course.
     * 
     * @return The grade.
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Calculates the total score and grade based on UTS, UAS, and Kuis scores.
     * 
     * <p>The total score is the average of the three assessments, and the grade is assigned
     * based on predefined thresholds:
     * <ul>
     * <li>A: Total > 85</li>
     * <li>AB: Total ≥ 85</li>
     * <li>B: Total ≥ 80</li>
     * <li>BC: Total ≥ 70</li>
     * <li>C: Total ≥ 50</li>
     * <li>D: Total < 50</li>
     * </ul>
     * </p>
     */
    private void calculateTotalAndGrade() {
        this.total = (this.uts + this.uas + this.kuis) / 3;

        if (total > 85) {
            this.grade = "A";
        } else if (total >= 80) {
            this.grade = "AB";
        } else if (total >= 75) {
            this.grade = "B";
        } else if (total >= 70) {
            this.grade = "BC";
        } else if (total >= 50) {
            this.grade = "C";
        } else {
            this.grade = "D";
        }
    }
}
