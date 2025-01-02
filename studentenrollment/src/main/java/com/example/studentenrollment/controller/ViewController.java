package com.example.studentenrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.studentenrollment.model.Mahasiswa;
import com.example.studentenrollment.repository.MahasiswaRepository;

/**
 * Spring MVC Controller for managing navigation pages in a JSP-based application.
 * 
 * <p>This controller handles various GET requests to display the dashboard,
 * CRUD pages, search pages, and specific features like course registration and
 * transcript display for students.</p>
 */
@Controller
public class ViewController {

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    /**
     * Displays the student dashboard page.
     *
     * @param auth Authentication object to get the username of the logged-in user
     * @param model Model to add attributes that will be passed to the JSP page
     * @return The name of the JSP page `dashboard_mahasiswa`
     */
    @GetMapping("/dashboard_mahasiswa")
    public String dashboardMahasiswa(Authentication auth, Model model) {
        String username = auth.getName();
        model.addAttribute("username", username);
        return "dashboard_mahasiswa";
    }

    /**
     * Displays the LAA dashboard page.
     *
     * @param auth Authentication object to get the username of the logged-in user
     * @param model Model to add attributes that will be passed to the JSP page
     * @return The name of the JSP page `dashboard_laa`
     */
    @GetMapping("/dashboard_laa")
    public String dashboardLaa(Authentication auth, Model model) {
        String username = auth.getName();
        model.addAttribute("username", username);
        return "dashboard_laa";
    }

    /**
     * Displays the CRUD page for managing students.
     *
     * @return The name of the JSP page `crud_mahasiswa`
     */
    @GetMapping("/dashboard_laa/mahasiswa")
    public String showCrudMahasiswaPage() {
        return "crud_mahasiswa";
    }

    /**
     * Displays the CRUD page for managing LAA.
     *
     * @return The name of the JSP page `crud_laa`
     */
    @GetMapping("/dashboard_laa/laa")
    public String showCrudLaaPage() {
        return "crud_laa";
    }

    /**
     * Displays the CRUD page for managing courses.
     *
     * @return The name of the JSP page `crud_matakuliah`
     */
    @GetMapping("/dashboard_laa/matakuliah")
    public String showCrudMataKuliahPage() {
        return "crud_matakuliah";
    }

    /**
     * Displays the search page by student NIM.
     *
     * @return The name of the JSP page `search_nim`
     */
    @GetMapping("/dashboard_laa/search_nim")
    public String showNimPage() {
        return "search_nim";
    }

    /**
     * Displays the search page by student ID.
     *
     * @return The name of the JSP page `search_id`
     */
    @GetMapping("/dashboard_laa/search_id")
    public String showIdPage() {
        return "search_id";
    }

    /**
     * Displays the search page by course.
     *
     * @return The name of the JSP page `search_matkul`
     */
    @GetMapping("/dashboard_laa/search_matkul")
    public String showMatkulPage() {
        return "search_matkul";
    }

    /**
     * Displays the course registration page for students.
     *
     * @param auth Authentication object to get the username of the logged-in user
     * @param model Model to add the `mahasiswaId` attribute to the JSP page
     * @return The name of the JSP page `regis_matakuliah`
     */
    @GetMapping("/dashboard_mahasiswa/registrasi_matakuliah")
    public String registrasiMataKuliah(Authentication auth, Model model) {
        String username = auth.getName();
        Long mahasiswaId = findMahasiswaIdByUsername(username);
        model.addAttribute("mahasiswaId", mahasiswaId);
        return "regis_matakuliah";
    }

    /**
     * Displays the transcript page for students.
     *
     * @param auth Authentication object to get the username of the logged-in user
     * @param model Model to add the `mahasiswaId` attribute to the JSP page
     * @return The name of the JSP page `view_transkrip`
     */
    @GetMapping("/dashboard_mahasiswa/view_transkrip")
    public String showMahasiswaTranskripPage(Authentication auth, Model model) {
        String username = auth.getName();
        Long mahasiswaId = findMahasiswaIdByUsername(username);
        model.addAttribute("mahasiswaId", mahasiswaId);
        return "view_transkrip";
    }

    /**
     * Displays the page for updating grades.
     *
     * @return The name of the JSP page `update_nilai`
     */
    @GetMapping("dashboard_laa/update-nilai-page")
    public String updateNilaiPage() {
        return "update_nilai";
    }

    /**
     * Finds the student ID based on the username.
     *
     * @param username The username of the student
     * @return The ID of the student if found
     * @throws RuntimeException if the student is not found
     */
    private Long findMahasiswaIdByUsername(String username) {
        return mahasiswaRepository.findByUsername(username)
                .map(Mahasiswa::getId)
                .orElseThrow(() -> new RuntimeException("Student not found with username: " + username));
    }
}