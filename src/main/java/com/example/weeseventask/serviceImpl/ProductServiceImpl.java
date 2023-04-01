package com.example.weeseventask.serviceImpl;
import com.example.weeseventask.DTO.ProductDTO;
import com.example.weeseventask.model.Product;
import com.example.weeseventask.repositories.ProductRepository;
import com.example.weeseventask.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Product saveProduct(ProductDTO productDTO) {
        Product product = new Product(productDTO);
//        product.setCategory(productDTO.getCategory());
        return productRepository.save(product);
    }

//    @Override
//    public List<Product> findAllByUserId(Long id) {
//        return productRepository.findByUserId(id);
//    }

//    @Override
//    public List<Product> getAllProducts() {
//        List<Product> productList =  productRepository.findAll();
//        return productList;
//    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return modelMapper.map(product, Product.class);
        }
        return productRepository.findById(id).orElse(null);
    }

        @Override
        public void deleteProduct (Long id){
            productRepository.deleteById(id);
        }

        @Override
        public Product editProduct (ProductDTO productDTO, Long id){
            Product product = productRepository.findById(id).get();
            product.setName(productDTO.getName());
            product.setCategory(productDTO.getCategory());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setImageURL(productDTO.getImageURL());
            return productRepository.save(product);
        }
    }
