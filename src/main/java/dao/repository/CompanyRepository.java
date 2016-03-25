package dao.repository;

import dao.entity.Company;
import dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by maksim.kalenik on 25.03.2016.
 */
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
