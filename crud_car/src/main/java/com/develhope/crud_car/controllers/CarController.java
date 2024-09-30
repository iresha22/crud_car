package com.develhope.crud_car.controllers;

import com.develhope.crud_car.entities.Car;
import com.develhope.crud_car.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    // Post per creare un nuovo Car
    @PostMapping("/newcar")
    public ResponseEntity<Car> newCar(@RequestBody Car car){
        carRepository.save(car);
        return ResponseEntity.ok().build();
    }
    // GET per restituire la lista del car
    @GetMapping("/carlist")
    public ResponseEntity<List<Car>> elencoCar(){
        List<Car> carList = carRepository.findAll();
        return ResponseEntity.ok(carList);
    }
    // GET per restituire un singola car tramite il ID
    //se id non è presente in db (usa existsById()), restituisce Car vuota
    @GetMapping("/{id}")
    public ResponseEntity<Car>  visualizzareUnCar(@PathVariable Long id){
        Optional<Car> carOptional = carRepository.findById(id);
        if(carOptional.isPresent()){
            Car car = carOptional.get();
            return ResponseEntity.ok(car);
        }
        new Car();
        return ResponseEntity.notFound().build();
    }

    // PUT per aggiornare Type della Car specifica
    @PutMapping("/{id}")
    public ResponseEntity<Car> aggiornaCarType(@PathVariable Long id, @RequestParam(required = true) String type){
        Optional<Car> carOptional = carRepository.findById(id);
        if(carOptional.isPresent()){
            Car car = carOptional.get();
            car.setType(type);
            carRepository.save(car);
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.status(404).build();
    }
    //Delete per eliminare il Car specificata
    //se non presente, la risposta deve avere Conflict HTTP status
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            carRepository.delete(car);
            return ResponseEntity.ok().build();
        }
         return ResponseEntity.status(404).build();
    }

    //Delete per cancellare tutti i car nella database
    @DeleteMapping("/deleteall")
    public ResponseEntity<Void> deleteAllCars(){
        carRepository.deleteAll();
         return ResponseEntity.ok().build();
    }
}
