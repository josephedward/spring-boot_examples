/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc;

/**
 *
 * @author Joseph Edward <josephedwardwork@gmail.com>
 */
import org.springframework.data.jpa.repository.JpaRepository;
import springmvc.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProdName(final String prodName);

}