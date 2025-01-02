package com.example.studentenrollment.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentenrollment.model.Mahasiswa;
import com.example.studentenrollment.model.MataKuliah;
import com.example.studentenrollment.model.User;
import com.example.studentenrollment.repository.UserRepository;
import com.example.studentenrollment.service.MahasiswaService;

/**
 * MahasiswaController is a REST controller that handles CRUD operations for Mahasiswa (student) entities
 * and other related actions like fetching MataKuliah (course) information for a student.
 */
@RestController
@RequestMapping("/api/mahasiswa")
public class MahasiswaController {

    @Autowired
    private MahasiswaService mahasiswaService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new Mahasiswa record.
     * 
     * @param mahasiswa the Mahasiswa object to be created.
     * @return a ResponseEntity containing the created Mahasiswa object.
     */
    @PostMapping
    public ResponseEntity<Mahasiswa> createMahasiswa(@RequestBody Mahasiswa mahasiswa) {
        return ResponseEntity.ok(mahasiswaService.createMahasiswa(mahasiswa));
    }

    /**
     * Retrieves all Mahasiswa records.
     * 
     * @return a ResponseEntity containing a list of all Mahasiswa records.
     */
    @GetMapping
    public ResponseEntity<List<Mahasiswa>> getAllMahasiswa() {
        return ResponseEntity.ok(mahasiswaService.getAllMahasiswa());
    }

    /**
     * Retrieves a Mahasiswa record by its ID.
     * 
     * @param id the ID of the Mahasiswa to retrieve.
     * @return a ResponseEntity containing the Mahasiswa record with the specified ID, or an error message if the ID is invalid.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getMahasiswaById(@PathVariable Long id) {
        if (id == 0) {
            return ResponseEntity.badRequest().body("ID Mahasiswa tidak valid.");
        }
        Mahasiswa mahasiswa = mahasiswaService.getMahasiswaWithDetails(id);
        return ResponseEntity.ok(mahasiswa);
    }

    /**
     * Updates an existing Mahasiswa record.
     * 
     * @param id the ID of the Mahasiswa to update.
     * @param mahasiswa the updated Mahasiswa object.
     * @return a ResponseEntity containing the updated Mahasiswa object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Mahasiswa> updateMahasiswa(@PathVariable Long id, @RequestBody Mahasiswa mahasiswa) {
        return ResponseEntity.ok(mahasiswaService.updateMahasiswa(id, mahasiswa));
    }

    /**
     * Deletes a Mahasiswa record by its ID.
     * 
     * @param id the ID of the Mahasiswa to delete.
     * @return a ResponseEntity with no content after the Mahasiswa record is deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMahasiswa(@PathVariable Long id) {
        mahasiswaService.deleteMahasiswa(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves the list of MataKuliah (courses) for a Mahasiswa by their NIM (student ID).
     * 
     * @param nim the NIM (student ID) of the Mahasiswa.
     * @return a ResponseEntity containing a list of MataKuliah for the Mahasiswa with the specified NIM.
     */
    @GetMapping("/nim/{nim}/matakuliah")
    public ResponseEntity<List<MataKuliah>> getMataKuliahByNim(@PathVariable String nim) {
        List<MataKuliah> mataKuliahList = mahasiswaService.getMataKuliahByNim(nim);
        return ResponseEntity.ok(mataKuliahList);
    }

    /**
     * Retrieves the current logged-in Mahasiswa based on the authenticated username.
     * 
     * @param auth the Authentication object containing the current authenticated user's details.
     * @return a ResponseEntity containing the Mahasiswa ID if the logged-in user is a Mahasiswa.
     * @throws RuntimeException if the logged-in user is not a Mahasiswa.
     */
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentMahasiswa(Authentication auth) {
        String username = auth.getName();
        System.out.println("DEBUG: Logged-in username = " + username);

        // Fetch user based on username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        // Cast to Mahasiswa
        if (user instanceof Mahasiswa) {
            Mahasiswa mahasiswa = (Mahasiswa) user;
            System.out.println("DEBUG: Mahasiswa ID = " + mahasiswa.getId());
            return ResponseEntity.ok(Map.of("mahasiswaId", mahasiswa.getId()));
        } else {
            throw new RuntimeException("Logged-in user is not a Mahasiswa");
        }
    }
}
