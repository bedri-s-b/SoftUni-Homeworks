package exam.repository;

import exam.model.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop,Long> {
    boolean existsByMacAddress(String macAddress);

    @Query("SELECT l FROM Laptop l JOIN FETCH l.shop ls JOIN FETCH ls.town t ORDER BY l.cpuSpeed DESC ,l.ram DESC , l.macAddress")
    List<Laptop> exportBestLaptops();
}
