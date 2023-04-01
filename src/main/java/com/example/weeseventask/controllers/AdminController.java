package com.example.weeseventask.controllers;
import com.example.weeseventask.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.weeseventask.DTO.ProductDTO;
import com.example.weeseventask.model.Product;
import com.example.weeseventask.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    private ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("productList", productService.getAllProducts());
        return "dashboard";
    }

    @GetMapping("/product/new")
    public  String showNewForm (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return  "user_form";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("products") ProductDTO productDTO, RedirectAttributes redirectAttributes) {
        System.out.println("This is the product DTO: " + productDTO);
        productService.saveProduct(productDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Product saved successfully");
        redirectAttributes.addFlashAttribute("products", productService.getAllProducts());
        return "redirect:/dashboard";
    }

    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategory(product.getCategory());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageURL(product.getImageURL());
        model.addAttribute("productDTO", productDTO);
        return "edit-product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/dashboard";
    }
}