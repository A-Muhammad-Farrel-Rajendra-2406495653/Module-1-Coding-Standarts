package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl; // bisa pakai onterface nya aja
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "create-product";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "product-list";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String productId) {
        System.out.println("id mau dihapus: " + productId); // Cek di console/terminal
        service.delete(productId);
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable("id") String productId, Model model) {
        System.out.println("id mau diedit: " + productId); // Cek di console/terminal
        Product product = service.find(productId);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product) {
        service.edit(product);
        return "redirect:list";
    }
}

// controller car sama product bisa dipisah aja
@Controller
@RequestMapping("/car")
class CarController extends ProductController {
    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/create-car")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "create-car";
    }

    @PostMapping("/create-car")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        carService.create(car);
        return "redirect:car-list";
    }

    @GetMapping("/car-list")
    public String carListPage(Model model) {
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "car-list";
    }

    @GetMapping("/edit-car/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carService.findById(carId);
        model.addAttribute("car", car);
        return "edit-car";
    }

    @PostMapping("/edit-car")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        System.out.println(car.getCarId());
        carService.update(car.getCarId(), car);
        return "redirect:car-list";
    }

    @PostMapping("/delete-car")
    public String deleteCar(@RequestParam("carId") String carId) {
        carService.deleteCarById(carId);
        return "redirect:car-list";
    }
}
