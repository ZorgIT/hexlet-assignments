package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.model.Product;
import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> index() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(p -> productMapper.map(p))
                .toList();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO show(@PathVariable Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with "
                        + "id " + id + " not found"));
        var productDTO = productMapper.map(product);
        return productDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody @Valid ProductCreateDTO productData) {
        if (!categoryRepository.existsById(productData.getCategoryId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category "
                    + "with id: " + productData.getCategoryId() + " not found");
        }
        var product = productMapper.map(productData);
        productRepository.save(product);

        return productMapper.map(product);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO update(@PathVariable Long id, @RequestBody @Valid ProductUpdateDTO productData) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        productMapper.update(productData, product);
        productRepository.save(product);

        return productMapper.map(product);
    }


    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product "
                        + "with id " + id + " not found"));
        productRepository.delete(product);
    }






    /*
GET /products – просмотр списка всех товаров
GET /products/{id} – просмотр конкретного товара
POST /products – добавление нового товара. При указании id несуществующей категории должен вернуться ответ с кодом 400 Bad request
PUT /products/{id} – редактирование товара. При редактировании мы должны иметь возможность поменять название, цену и категорию товара. При указании id несуществующей категории также должен вернуться ответ с кодом 400 Bad request
DELETE /products/{id} – удаление товара
     */
    // END
}
