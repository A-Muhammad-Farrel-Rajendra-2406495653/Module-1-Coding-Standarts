package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controller car sama product bisa dipisah aja
@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

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

